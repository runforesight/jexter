package io.thundra.jexter.junit4.core;

import io.thundra.jexter.core.utils.ExceptionUtils;
import org.junit.runner.JUnitCore;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author serkan
 */
public abstract class BaseTest {

    protected static void runTestMethod(Class<?> testClass, String testMethodName) {
        try {
            JUnit4MethodRunner runner = JUnit4MethodRunner.create(testClass, testMethodName);
            JUnitCore jUnitCore = new JUnitCore();
            jUnitCore.run(runner);
        } catch (InitializationError e) {
            ExceptionUtils.sneakyThrow(e);
        }
    }

    private static class JUnit4MethodRunner extends BlockJUnit4ClassRunner {

        private static final ThreadLocal<String> threadLocalMethodName = new ThreadLocal<>();

        private final String methodName;

        private JUnit4MethodRunner(Class<?> klass, String methodName) throws InitializationError {
            super(klass);
            this.methodName = methodName;
        }

        private static JUnit4MethodRunner create(Class<?> klass, String methodName) throws InitializationError {
            threadLocalMethodName.set(methodName);
            try {
                return new JUnit4MethodRunner(klass, methodName);
            } finally {
                threadLocalMethodName.remove();
            }
        }

        @Override
        protected List<FrameworkMethod> computeTestMethods() {
            String mName = methodName != null ? methodName : threadLocalMethodName.get();
            return super.computeTestMethods().
                    stream().
                    filter(fm -> fm.getName().equals(mName)).
                    collect(Collectors.toList());
        }

    }

}
