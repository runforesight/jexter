package io.thundra.jexter.junit4.core.sysprop;

import io.thundra.jexter.core.sysprop.ThreadLocalProperties;
import io.thundra.jexter.junit4.core.JexterBaseRule;
import org.junit.runner.Description;

import java.util.Properties;

import static io.thundra.jexter.junit4.core.sysprop.SystemPropertySandboxRule.SystemPropertiesContext;

/**
 * {@link org.junit.rules.TestRule} implementation
 * which stores system properties before the test
 * and restores them back to original value after the test.
 *
 * @author serkan
 */
public class SystemPropertySandboxRule
        extends JexterBaseRule<SystemPropertiesContext> {

    protected static class SystemPropertiesContext {

        private final Properties sysProps;

        private SystemPropertiesContext(Properties sysProps) {
            this.sysProps = sysProps;
        }

    }

    @Override
    protected SystemPropertiesContext onBeforeEvaluate(Description description) {
        Properties sysProps = System.getProperties();
        System.setProperties(new ThreadLocalProperties(sysProps));
        return new SystemPropertiesContext(sysProps);
    }

    @Override
    protected void onAfterEvaluate(Description description, SystemPropertiesContext context) {
        if (context != null) {
            System.setProperties(context.sysProps);
        }
    }

}
