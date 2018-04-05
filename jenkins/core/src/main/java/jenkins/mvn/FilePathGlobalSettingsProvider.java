package jenkins.mvn;

import hudson.EnvVars;
import hudson.Extension;
import hudson.FilePath;
import hudson.Util;
import hudson.model.AbstractBuild;
import hudson.model.TaskListener;
import hudson.util.IOUtils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.kohsuke.stapler.DataBoundConstructor;

import static jenkins.mvn.FilePathSuper.superSupplySettings;
/**
 * @author <a href="mailto:nicolas.deloof@gmail.com">Nicolas De Loof</a>
 * @author Dominik Bartholdi (imod)
 * @since 1.491
 */
public class FilePathGlobalSettingsProvider extends GlobalSettingsProvider {

    private final String path;

    @DataBoundConstructor
    public FilePathGlobalSettingsProvider(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    @Override
    public FilePath supplySettings(AbstractBuild<?, ?> build, TaskListener listener) {

		return superSupplySettings(build, listener, path);
    }

    @Extension(ordinal = 10)
    public static class DescriptorImpl extends GlobalSettingsProviderDescriptor {

        @Override
        public String getDisplayName() {
            return Messages.FilePathGlobalSettingsProvider_DisplayName();
        }

    }
}
