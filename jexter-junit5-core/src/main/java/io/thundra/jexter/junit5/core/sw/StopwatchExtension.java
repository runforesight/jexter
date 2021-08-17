package io.thundra.jexter.junit5.core.sw;

import io.thundra.jexter.junit5.core.JexterBaseExtension;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.lang.reflect.Method;
import java.util.Optional;

/**
 * {@link org.junit.jupiter.api.extension.Extension} implementation
 * which measures the elapsed time of a test execution.
 *
 * @author serkan
 */
public class StopwatchExtension
        extends JexterBaseExtension
        implements BeforeAllCallback, BeforeEachCallback, AfterAllCallback, AfterEachCallback {

    private void storeStopwatch(ExtensionContext context) {
        ExtensionContext.Store store = getStore(context);
        String storeKey = getStoreKey(context);
        StopwatchExtension.StopwatchBackup stopwatchBackup =
                new StopwatchExtension.StopwatchBackup(System.currentTimeMillis());
        store.put(storeKey, stopwatchBackup);
    }

    private void restoreStopwatch(ExtensionContext context) {
        ExtensionContext.Store store = getStore(context);
        String storeKey = getStoreKey(context);
        StopwatchExtension.StopwatchBackup stopwatchBackup = (StopwatchExtension.StopwatchBackup) store.get(storeKey);
        if (stopwatchBackup != null) {
            long elapsedTime = System.currentTimeMillis() - stopwatchBackup.startTime;
            Optional<Method> testMethod = context.getTestMethod();
            String message;
            if (!testMethod.isPresent()) {
                message = String.format(
                        "Execution of test suite '%s' took [%d] ms.",
                        context.getRequiredTestClass().getName(),
                        elapsedTime);
            } else {
                message = String.format(
                        "Execution of test '%s#%s' took [%d] ms.",
                        context.getRequiredTestClass().getName(),
                        testMethod.get().getName(),
                        elapsedTime);
            }
            System.out.println(message);
            context.publishReportEntry(getStoreKey(context), message);
        }
    }

    private static class StopwatchBackup {

        private final long startTime;

        public StopwatchBackup(long startTime) {
            this.startTime = startTime;
        }

    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        storeStopwatch(context);
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        storeStopwatch(context);
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        restoreStopwatch(context);
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        restoreStopwatch(context);
    }

}
