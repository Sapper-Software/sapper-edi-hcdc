package ai.sapper.hcdc.agents.common;

import ai.sapper.cdc.common.model.AvroChangeRecord;
import ai.sapper.cdc.common.model.AvroChangeType;
import ai.sapper.cdc.common.model.EntityDef;
import ai.sapper.cdc.common.model.SchemaEntity;
import ai.sapper.cdc.core.model.EFileType;
import ai.sapper.cdc.core.schema.SchemaManager;
import ai.sapper.hcdc.agents.model.DFSFileState;
import com.google.common.base.Preconditions;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.Accessors;
import org.apache.avro.Schema;
import org.apache.avro.generic.GenericRecord;
import org.apache.hadoop.hdfs.HDFSBlockReader;
import org.apache.parquet.Strings;

import java.io.File;
import java.io.IOException;

@Getter
@Accessors(fluent = true)
public abstract class FormatConverter {
    private SchemaManager schemaManager;
    private EFileType fileType;

    public FormatConverter(@NonNull EFileType fileType) {
        this.fileType = fileType;
    }

    public FormatConverter withSchemaManager(@NonNull SchemaManager schemaManager) {
        this.schemaManager = schemaManager;
        return this;
    }

    public EntityDef hasSchema(DFSFileState fileState, SchemaEntity schemaEntity) throws Exception {
        EntityDef schema = schemaManager().get(schemaEntity);
        if (schema == null) {
            if (!Strings.isNullOrEmpty(fileState.getFileInfo().getSchemaLocation())) {
                schema = schemaManager().get(fileState.getFileInfo().getSchemaLocation());
            }
        }
        return schema;
    }

    public GenericRecord wrap(@NonNull Schema schema,
                              @NonNull SchemaEntity schemaEntity,
                              @NonNull String namespace,
                              @NonNull String hdfsPath,
                              @NonNull GenericRecord record,
                              @NonNull AvroChangeType.EChangeType op,
                              long txId) throws IOException {
        Preconditions.checkNotNull(record);
        AvroChangeRecord avro = new AvroChangeRecord()
                .txId(txId)
                .timestamp(System.currentTimeMillis())
                .op(op)
                .targetEntity(schemaEntity)
                .sourceEntity(new SchemaEntity(namespace, hdfsPath))
                .data(record);

        return avro.toAvro(schema);
    }

    public abstract boolean canParse(@NonNull String path, EFileType fileType) throws IOException;

    public abstract File convert(@NonNull File source,
                                 @NonNull File output,
                                 @NonNull DFSFileState fileState,
                                 @NonNull SchemaEntity schemaEntity,
                                 long txId,
                                 @NonNull AvroChangeType.EChangeType op) throws IOException;

    public abstract boolean supportsPartial();

    public abstract boolean detect(@NonNull String path, byte[] data, int length) throws IOException;

    public abstract EntityDef extractSchema(@NonNull HDFSBlockReader reader,
                                            @NonNull DFSFileState fileState,
                                            @NonNull SchemaEntity schemaEntity) throws IOException;
}
