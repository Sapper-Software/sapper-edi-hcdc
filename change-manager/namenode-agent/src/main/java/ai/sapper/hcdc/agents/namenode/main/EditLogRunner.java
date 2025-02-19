package ai.sapper.hcdc.agents.namenode.main;

import ai.sapper.hcdc.agents.common.NameNodeEnv;
import ai.sapper.hcdc.agents.namenode.EditLogProcessor;
import ai.sapper.hcdc.common.ConfigReader;
import ai.sapper.hcdc.common.model.services.EConfigFileType;
import ai.sapper.hcdc.common.utils.DefaultLogger;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.google.common.base.Preconditions;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.apache.commons.configuration2.HierarchicalConfiguration;
import org.apache.commons.configuration2.tree.ImmutableNode;
import org.apache.parquet.Strings;

@Getter
@Setter
public class EditLogRunner {
    @Parameter(names = {"--config", "-c"}, required = true, description = "Path to the configuration file.")
    private String configfile;
    @Parameter(names = {"--type", "-t"}, description = "Configuration file type. (File, Resource, Remote)")
    private String configSource;
    private EConfigFileType fileSource = EConfigFileType.File;
    @Setter(AccessLevel.NONE)
    private HierarchicalConfiguration<ImmutableNode> config;
    @Setter(AccessLevel.NONE)
    private EditLogProcessor processor;
    @Setter(AccessLevel.NONE)
    private Thread runner;

    public void init() throws Exception {
        try {
            Preconditions.checkState(!Strings.isNullOrEmpty(configfile));
            if (!Strings.isNullOrEmpty(configSource)) {
                fileSource = EConfigFileType.parse(configSource);
            }
            Preconditions.checkNotNull(fileSource);
            config = ConfigReader.read(configfile, fileSource);
            NameNodeEnv.setup(config);

            processor = new EditLogProcessor(NameNodeEnv.stateManager());
            processor.init(NameNodeEnv.get().configNode(), NameNodeEnv.connectionManager());
        } catch (Throwable t) {
            NameNodeEnv.get().error(t);
            throw t;
        }
    }

    public void run() throws Exception {
        try {
            runner = new Thread(processor);
            runner.start();
        } catch (Throwable t) {
            NameNodeEnv.get().error(t);
            throw t;
        }
    }

    public long runOnce(@NonNull String configfile) throws Exception {
        try {
            this.configfile = configfile;
            init();
            NameNodeEnv.globalLock().lock();
            try {
                return processor.doRun();
            } finally {
                NameNodeEnv.globalLock().unlock();
            }
        } finally {
            NameNodeEnv.ENameNEnvState state = NameNodeEnv.dispose();
            DefaultLogger.LOG.warn(String.format("Edit Log Processor Shutdown...[state=%s]", state.name()));
        }
    }

    public static void main(String[] args) {
        try {
            EditLogRunner runner = new EditLogRunner();
            JCommander.newBuilder().addObject(runner).build().parse(args);
            runner.init();
            runner.run();
            runner.runner.join();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
