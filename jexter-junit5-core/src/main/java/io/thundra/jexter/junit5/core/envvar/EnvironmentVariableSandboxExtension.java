package io.thundra.jexter.junit5.core.envvar;

import io.thundra.jexter.core.envvar.EnvironmentVariableHelper;
import io.thundra.jexter.junit5.core.JexterBaseExtension;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.Map;

/**
 * {@link org.junit.jupiter.api.extension.Extension} implementation
 * which stores environment variables before the test
 * and restores them back to original value after the test.
 *
 * @author serkan
 */
public class EnvironmentVariableSandboxExtension
        extends JexterBaseExtension
        implements BeforeAllCallback, BeforeEachCallback, AfterAllCallback, AfterEachCallback {

    private void storeEnvVars(ExtensionContext context) {
        ExtensionContext.Store store = getStore(context);
        String storeKey = getStoreKey(context);
        EnvVarsBackup envVarsBackup = new EnvVarsBackup(EnvironmentVariableHelper.getAllCopy());
        store.put(storeKey, envVarsBackup);
    }

    private void restoreEnvVars(ExtensionContext context) {
        ExtensionContext.Store store = getStore(context);
        String storeKey = getStoreKey(context);
        EnvVarsBackup envVarsBackup = (EnvVarsBackup) store.get(storeKey);
        if (envVarsBackup != null) {
            EnvironmentVariableHelper.setAll(envVarsBackup.envVars);
        }
    }

    private static class EnvVarsBackup {

        private final Map<String, String> envVars;

        public EnvVarsBackup(Map<String, String> envVars) {
            this.envVars = envVars;
        }

    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        storeEnvVars(context);
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        storeEnvVars(context);
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        restoreEnvVars(context);
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        restoreEnvVars(context);
    }

}
