package io.thundra.jexter.junit4.core.envvar;

import io.thundra.jexter.core.envvar.EnvironmentVariableHelper;
import io.thundra.jexter.junit4.core.JexterBaseRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.util.Map;

/**
 * {@link org.junit.rules.TestRule} implementation
 * which stores environment variables before the test
 * and restores them back to original value after the test.
 *
 * @author serkan
 */
public class EnvironmentVariableSandboxRule extends JexterBaseRule {

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                Map<String, String> envVars = EnvironmentVariableHelper.getAllCopy();
                try {
                    base.evaluate();
                } finally {
                    EnvironmentVariableHelper.setAll(envVars);
                }
            }
        };
    }

}
