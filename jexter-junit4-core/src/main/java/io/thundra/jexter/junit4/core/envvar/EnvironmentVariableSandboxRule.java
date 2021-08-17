package io.thundra.jexter.junit4.core.envvar;

import io.thundra.jexter.core.envvar.EnvironmentVariableHelper;
import io.thundra.jexter.junit4.core.JexterBaseRule;
import org.junit.runner.Description;

import java.util.Map;

import static io.thundra.jexter.junit4.core.envvar.EnvironmentVariableSandboxRule.EnvironmentVariablesContext;

/**
 * {@link org.junit.rules.TestRule} implementation
 * which stores environment variables before the test
 * and restores them back to original value after the test.
 *
 * @author serkan
 */
public class EnvironmentVariableSandboxRule
        extends JexterBaseRule<EnvironmentVariablesContext> {

    protected static class EnvironmentVariablesContext {

        private final Map<String, String> envVars;

        private EnvironmentVariablesContext(Map<String, String> envVars) {
            this.envVars = envVars;
        }

    }

    @Override
    protected EnvironmentVariablesContext onBeforeEvaluate(Description description) {
        return new EnvironmentVariablesContext(EnvironmentVariableHelper.getAllCopy());
    }

    @Override
    protected void onAfterEvaluate(Description description, EnvironmentVariablesContext context) {
        if (context != null) {
            EnvironmentVariableHelper.setAll(context.envVars);
        }
    }

}
