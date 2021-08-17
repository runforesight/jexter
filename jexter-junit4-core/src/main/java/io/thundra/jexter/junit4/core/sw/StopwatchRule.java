package io.thundra.jexter.junit4.core.sw;

import io.thundra.jexter.junit4.core.JexterBaseRule;
import org.junit.runner.Description;

import static io.thundra.jexter.junit4.core.sw.StopwatchRule.StopwatchContext;

/**
 * {@link org.junit.rules.TestRule} implementation
 * which measures the elapsed time of a test execution.
 *
 * @author serkan
 */
public class StopwatchRule
        extends JexterBaseRule<StopwatchContext> {

    protected static class StopwatchContext {

        private final long startTime;

        private StopwatchContext(long startTime) {
            this.startTime = startTime;
        }

    }

    @Override
    protected StopwatchContext onBeforeEvaluate(Description description) {
        return new StopwatchContext(System.currentTimeMillis());
    }

    @Override
    protected void onAfterEvaluate(Description description, StopwatchContext context) {
        if (context != null) {
            long elapsedTime = System.currentTimeMillis() - context.startTime;
            if (description.isSuite()) {
                System.out.printf(
                        "Execution of test suite '%s' took [%d] ms.\n",
                        description.getClassName(),
                        elapsedTime);
            } else {
                System.out.printf(
                        "Execution of test '%s#%s' took [%d] ms.\n",
                        description.getClassName(),
                        description.getMethodName(),
                        elapsedTime);
            }
        }
    }

}
