package ai.sapper.hcdc.agents.common;

import ai.sapper.cdc.common.model.AvroChangeType;
import ai.sapper.cdc.common.model.EntityDef;
import ai.sapper.cdc.common.model.SchemaEntity;
import ai.sapper.cdc.common.schema.SchemaVersion;
import ai.sapper.cdc.common.utils.PathUtils;
import ai.sapper.cdc.core.connections.HdfsConnection;
import ai.sapper.cdc.core.io.CDCFileSystem;
import ai.sapper.cdc.core.io.PathInfo;
import ai.sapper.cdc.core.model.EFileType;
import ai.sapper.cdc.core.model.HDFSBlockData;
import ai.sapper.cdc.core.schema.SchemaManager;
import ai.sapper.hcdc.agents.common.converter.AvroConverter;
import ai.sapper.hcdc.agents.common.converter.ParquetConverter;
import ai.sapper.hcdc.agents.model.DFSBlockState;
import ai.sapper.hcdc.agents.model.DFSFileReplicaState;
import ai.sapper.hcdc.agents.model.DFSFileState;
import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.avro.Schema;
import org.apache.commons.io.FilenameUtils;
import org.apache.hadoop.hdfs.HDFSBlockReader;

import java.io.File;
import java.io.IOException;

@Getter
@Accessors(fluent = true)
public class CDCDataConverter {
    private final FormatConverter[] CONVERTERS = {new ParquetConverter(), new AvroConverter()};

    private CDCFileSystem fs;
    private HdfsConnection hdfsConnection;

    public CDCDataConverter withFileSystem(@NonNull CDCFileSystem fs) {
        this.fs = fs;
        return this;
    }

    public CDCDataConverter withHdfsConnection(@NonNull HdfsConnection hdfsConnection) {
        this.hdfsConnection = hdfsConnection;
        return this;
    }

    public CDCDataConverter withSchemaManager(@NonNull SchemaManager schemaManager) {
        for (FormatConverter converter : CONVERTERS) {
            converter.withSchemaManager(schemaManager);
        }
        return this;
    }

    public EFileType detect(@NonNull String path, byte[] data, int length) throws IOException {
        try {
            for (FormatConverter converter : CONVERTERS) {
                if (converter.detect(path, data, length)) return converter.fileType();
            }
            return null;
        } catch (Exception ex) {
            throw new IOException(ex);
        }
    }

    public PathInfo convert(@NonNull DFSFileState fileState,
                            @NonNull DFSFileReplicaState replicaState,
                            @NonNull AvroChangeType.EChangeType op,
                            long startTxId,
                            long currentTxId) throws IOException {
        Preconditions.checkNotNull(fs);
        Preconditions.checkArgument(replicaState.getEntity() != null);
        Preconditions.checkArgument(replicaState.getStoragePath() != null);
        try {
            for (FormatConverter converter : CONVERTERS) {
                if (converter.canParse(fileState.getFileInfo().getHdfsPath(),
                        replicaState.getFileInfo().getFileType())) {
                    File output = convert(converter, fileState, replicaState, startTxId, currentTxId, op);
                    return upload(output, fileState, replicaState, currentTxId);
                }
            }
            return null;
        } catch (Exception ex) {
            throw new IOException(ex);
        }
    }

    public ExtractSchemaResponse extractSchema(@NonNull DFSFileState fileState,
                                               @NonNull SchemaEntity schemaEntity) throws IOException {
        Preconditions.checkNotNull(hdfsConnection);
        try {
            try (HDFSBlockReader reader = new HDFSBlockReader(hdfsConnection.dfsClient(), fileState.getFileInfo().getHdfsPath())) {
                reader.init();
                DFSBlockState blockState = fileState.findFirstBlock();
                if (blockState == null) {
                    throw new Exception(
                            String.format("Error fetching first block from FileState. [path=%s]",
                                    fileState.getFileInfo().getHdfsPath()));
                }
                HDFSBlockData data = reader.read(blockState.getBlockId(),
                        blockState.getGenerationStamp(),
                        0L,
                        -1);
                if (data != null) {
                    for (FormatConverter converter : CONVERTERS) {
                        if (converter.detect(fileState.getFileInfo().getHdfsPath(),
                                data.data().array(),
                                (int) data.dataSize())) {
                            EFileType fileType = converter.fileType();
                            EntityDef schema = converter.extractSchema(reader,
                                    fileState, schemaEntity);
                            if (schema != null) {
                                ExtractSchemaResponse response = new ExtractSchemaResponse();
                                return response.fileType(fileType)
                                        .schema(schema.schema())
                                        .version(schema.version());
                            }
                        }
                    }
                }
            }
            return null;
        } catch (Exception ex) {
            throw new IOException(ex);
        }
    }

    public FormatConverter getConverter(@NonNull EFileType fileType) {
        for (FormatConverter converter : CONVERTERS) {
            if (converter.fileType() == fileType) return converter;
        }
        return null;
    }

    private PathInfo upload(File source,
                            DFSFileState fileState,
                            DFSFileReplicaState replicaState,
                            long txId) throws Exception {
        Preconditions.checkNotNull(fs);

        String uploadPath = String.format("%s/%s/%d/%d",
                replicaState.getEntity().getDomain(),
                replicaState.getEntity().getEntity(),
                replicaState.getFileInfo().getInodeId(),
                txId);
        PathInfo path = fs.get(uploadPath, replicaState.getEntity().getDomain());
        fs.mkdirs(path);

        return fs.upload(source, path);
    }

    private File convert(FormatConverter converter,
                         DFSFileState fileState,
                         DFSFileReplicaState replicaState,
                         long startTxId,
                         long currentTxId,
                         @NonNull AvroChangeType.EChangeType op) throws Exception {
        File source = null;
        if (converter.supportsPartial()) {
            source = createDeltaFile(fileState, replicaState, startTxId, currentTxId);
        } else {
            source = createSourceFile(fileState, replicaState, currentTxId);
        }
        String fname = FilenameUtils.getName(replicaState.getFileInfo().getHdfsPath());
        fname = FilenameUtils.removeExtension(fname);
        String path = PathUtils.formatPath(String.format("%s/%s-%d.avro", fs.tempPath(), fname, currentTxId));
        return converter.convert(source, new File(path), fileState, replicaState.getEntity(), currentTxId, op);
    }

    private File createSourceFile(DFSFileState fileState,
                                  DFSFileReplicaState replicaState,
                                  long currentTxId) throws Exception {
        PathInfo source = fs.get(replicaState.getStoragePath());
        if (!source.exists()) {
            throw new IOException(String.format("Change Set not found. [path=%s]", source.toString()));
        }
        String fname = FilenameUtils.getName(replicaState.getFileInfo().getHdfsPath());
        String path = String.format("%s/%s", fs.tempPath(), fname);
        File file = new File(path);

        if (!ChangeSetHelper.createChangeSet(fs,
                fileState,
                replicaState,
                file,
                -1,
                currentTxId)) {
            return null;
        }
        return file;
    }

    private File createDeltaFile(DFSFileState fileState,
                                 DFSFileReplicaState replicaState,
                                 long startTxId,
                                 long currentTxId) throws Exception {
        PathInfo source = fs.get(replicaState.getStoragePath());
        if (!source.exists()) {
            throw new IOException(String.format("Change Set not found. [path=%s]", source.toString()));
        }
        String fname = FilenameUtils.getName(replicaState.getFileInfo().getHdfsPath());
        String path = String.format("%s/%s", fs.tempPath(), fname);
        File file = new File(path);

        if (!ChangeSetHelper.createChangeSet(fs,
                fileState,
                replicaState,
                file,
                startTxId,
                currentTxId)) {
            return null;
        }
        return file;
    }

    @Getter
    @Setter
    @Accessors(fluent = true)
    public static class ExtractSchemaResponse {
        private EFileType fileType;
        private Schema schema;
        private SchemaVersion version;
    }
}
