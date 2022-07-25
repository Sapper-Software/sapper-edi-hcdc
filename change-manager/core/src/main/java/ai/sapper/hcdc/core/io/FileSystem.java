package ai.sapper.hcdc.core.io;

import ai.sapper.hcdc.common.ConfigReader;
import ai.sapper.hcdc.common.model.SchemaEntity;
import ai.sapper.hcdc.core.model.DFSBlockState;
import ai.sapper.hcdc.core.model.DFSFileState;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.apache.commons.configuration2.HierarchicalConfiguration;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.apache.commons.configuration2.tree.ImmutableNode;

import java.io.IOException;
import java.util.List;

@Getter
@Accessors(fluent = true)
public abstract class FileSystem {
    private PathInfo root;

    public abstract FileSystem init(@NonNull HierarchicalConfiguration<ImmutableNode> config, String pathPrefix) throws IOException;

    public abstract PathInfo get(@NonNull String path, String domain) throws IOException;

    protected abstract PathInfo get(@NonNull String path) throws IOException;

    public abstract String mkdir(@NonNull PathInfo path, @NonNull String name) throws IOException;

    public abstract String mkdirs(@NonNull PathInfo path) throws IOException;

    public abstract boolean delete(@NonNull PathInfo path, boolean recursive) throws IOException;

    public boolean delete(@NonNull PathInfo path) throws IOException {
        return delete(path, false);
    }

    public abstract List<String> list(@NonNull PathInfo path, boolean recursive) throws IOException;

    public abstract List<String> find(@NonNull PathInfo path, String dirQuery, @NonNull String fileQuery) throws IOException;

    public abstract List<String> findFiles(@NonNull PathInfo path, String dirQuery, @NonNull String fileQuery) throws IOException;

    public FileSystem setRootPath(@NonNull PathInfo rootPath) {
        this.root = rootPath;
        return this;
    }

    public boolean exists(@NonNull String path, String domain) throws IOException {
        PathInfo pi = get(path, domain);
        if (pi != null) return pi.exists();
        return false;
    }

    public boolean isDirectory(@NonNull String path, String domain) throws IOException {
        PathInfo pi = get(path, domain);
        if (pi != null) return pi.isDirectory();
        else {
            throw new IOException(String.format("File not found. [path=%s]", path));
        }
    }

    public boolean isFile(@NonNull String path, String domain) throws IOException {
        PathInfo pi = get(path, domain);
        if (pi != null) return pi.isFile();
        else {
            throw new IOException(String.format("File not found. [path=%s]", path));
        }
    }

    public FSFile create(@NonNull DFSFileState fileState,
                         @NonNull FileSystem fs,
                         @NonNull SchemaEntity entity) throws IOException {
        return new FSFile(fileState, entity.getDomain(), fs);
    }

    public FSBlock create(@NonNull PathInfo dir,
                          @NonNull DFSBlockState blockState,
                          @NonNull FileSystem fs,
                          @NonNull SchemaEntity entity) throws IOException {
        return new FSBlock(blockState, dir, fs, entity.getDomain());
    }

    public FSFile get(@NonNull DFSFileState fileState,
                      @NonNull FileSystem fs,
                      @NonNull SchemaEntity entity) throws IOException {
        return new FSFile(fileState, entity.getDomain(), fs, false);
    }

    public FSBlock get(@NonNull PathInfo dir,
                       @NonNull DFSBlockState blockState,
                       @NonNull FileSystem fs,
                       @NonNull SchemaEntity entity) throws IOException {
        return new FSBlock(blockState, dir, fs, entity.getDomain(), false);
    }

    public abstract Writer writer(@NonNull PathInfo path, boolean createDir, boolean overwrite) throws IOException;

    public Writer writer(@NonNull PathInfo path, boolean overwrite) throws IOException {
        return writer(path, false, overwrite);
    }

    public Writer writer(@NonNull PathInfo path) throws IOException {
        return writer(path, false, false);
    }

    public abstract Reader reader(@NonNull PathInfo path) throws IOException;

    @Getter
    @Setter
    @Accessors(fluent = true)
    public static class FileSystemConfig extends ConfigReader {
        private static final String CONFIG_ROOT = "root";

        private String rootPath;

        public FileSystemConfig(@NonNull HierarchicalConfiguration<ImmutableNode> config, @NonNull String path) {
            super(config, path);
        }

        public void read() throws ConfigurationException {
            if (get() == null) {
                throw new ConfigurationException("Kafka Configuration not drt or is NULL");
            }
            rootPath = get().getString(CONFIG_ROOT);
        }
    }
}
