package io.thundra.jexter.junit5.core;

import io.thundra.jexter.core.utils.ExceptionUtils;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestExecutionListener;
import org.junit.platform.launcher.TestIdentifier;
import org.junit.platform.launcher.TestPlan;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectMethod;

/**
 * @author serkan
 */
public abstract class BaseTest {

    private static final Launcher launcher = LauncherFactory.create();

    protected static void runTestMethod(Class<?> testClass, String testMethodName) {
        LauncherDiscoveryRequest launcherDiscoveryRequest =
                LauncherDiscoveryRequestBuilder.
                        request().
                        selectors(selectMethod(testClass, testMethodName)).
                        build();
        TestPlan testPlan = launcher.discover(launcherDiscoveryRequest);
        AtomicReference<Throwable> errorRef = new AtomicReference<>();
        launcher.execute(testPlan, new TestExecutionListener[]{ new TestExecutionListener() {
            @Override
            public void executionFinished(TestIdentifier testIdentifier, TestExecutionResult testExecutionResult) {
                if (testExecutionResult.getThrowable().isPresent()) {
                    errorRef.set(testExecutionResult.getThrowable().get());
                }
            }
        }});
        if (errorRef.get() != null) {
            ExceptionUtils.sneakyThrow(errorRef.get());
        }
    }

}
