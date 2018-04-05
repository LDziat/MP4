package jenkins.mvn;

import hudson.EnvVars;
import hudson.FilePath;
import hudson.Util;
import hudson.model.AbstractBuild;
import hudson.model.TaskListener;
import hudson.util.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.kohsuke.stapler.DataBoundConstructor;

import java.io.File;

public class FilePathSuper {

    @DataBoundConstructor
    public FilePathSuper() { }

    public static FilePath superSupplySettings(AbstractBuild<?, ?> build, TaskListener listener, String path) {
        if (StringUtils.isEmpty(path)) {
            return null;
        }

        try {
            EnvVars env = build.getEnvironment(listener);
            String targetPath = Util.replaceMacro(path, build.getBuildVariableResolver());
            targetPath = env.expand(targetPath);

            if (IOUtils.isAbsolute(targetPath)) {
                return new FilePath(new File(targetPath));
            } else {
                FilePath mrSettings = build.getModuleRoot().child(targetPath);
                FilePath wsSettings = build.getWorkspace().child(targetPath);
                try {
                    if (!wsSettings.exists() && mrSettings.exists()) {
                        wsSettings = mrSettings;
                    }
                } catch (Exception e) {
                    throw new IllegalStateException("failed to find settings.xml at: " + wsSettings.getRemote());
                }
                return wsSettings;
            }
        } catch (Exception e) {
            throw new IllegalStateException("failed to prepare global settings.xml");
        }

    }



}
