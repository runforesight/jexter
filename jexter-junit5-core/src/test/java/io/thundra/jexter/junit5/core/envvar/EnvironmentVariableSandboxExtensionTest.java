package io.thundra.jexter.junit5.core.envvar;

import io.thundra.jexter.core.envvar.EnvironmentVariableHelper;
import io.thundra.jexter.junit5.core.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author serkan
 */
public class EnvironmentVariableSandboxExtensionTest extends BaseTest {

    private static final String TEST_ENV_VAR_NAME = "key";
    private static final String TEST_ENV_VAR_VALUE = "val";
    private static final String TEST_ENV_VAR_VALUE_UPDATED = "val-updated";

    @Test
    public void setEnvVarShouldBeClearedAfterTestMethod_whenTestMethodAnnotated() {
        Assertions.assertNull(EnvironmentVariableHelper.get(TEST_ENV_VAR_NAME));
        runTestMethod(EnvironmentVariableSandboxExtensionTestInternal1.class, "setEnvVar");
        Assertions.assertNull(EnvironmentVariableHelper.get(TEST_ENV_VAR_NAME));
    }

    @Test
    public void updatedEnvVarShouldBeRevertedBackAfterTestMethod_whenTestMethodAnnotated() {
        EnvironmentVariableHelper.set(TEST_ENV_VAR_NAME, TEST_ENV_VAR_VALUE);
        try {
            Assertions.assertEquals(TEST_ENV_VAR_VALUE, EnvironmentVariableHelper.get(TEST_ENV_VAR_NAME));
            runTestMethod(EnvironmentVariableSandboxExtensionTestInternal1.class, "updateEnvVar");
            Assertions.assertEquals(TEST_ENV_VAR_VALUE, EnvironmentVariableHelper.get(TEST_ENV_VAR_NAME));
        } finally {
            EnvironmentVariableHelper.remove(TEST_ENV_VAR_NAME);
        }
    }

    @Test
    public void clearedEnvVarShouldBePutBackAfterTestMethod_whenTestMethodAnnotated() {
        EnvironmentVariableHelper.set(TEST_ENV_VAR_NAME, TEST_ENV_VAR_VALUE);
        try {
            Assertions.assertEquals(TEST_ENV_VAR_VALUE, EnvironmentVariableHelper.get(TEST_ENV_VAR_NAME));
            runTestMethod(EnvironmentVariableSandboxExtensionTestInternal1.class, "clearEnvVar");
            Assertions.assertEquals(TEST_ENV_VAR_VALUE, EnvironmentVariableHelper.get(TEST_ENV_VAR_NAME));
        } finally {
            EnvironmentVariableHelper.remove(TEST_ENV_VAR_NAME);
        }
    }

    @Test
    public void setEnvVarShouldBeClearedAfterTestMethod_whenTestClassAnnotated() {
        Assertions.assertNull(EnvironmentVariableHelper.get(TEST_ENV_VAR_NAME));
        runTestMethod(EnvironmentVariableSandboxExtensionTestInternal2.class, "setEnvVar");
        Assertions.assertNull(EnvironmentVariableHelper.get(TEST_ENV_VAR_NAME));
    }

    @Test
    public void updatedEnvVarShouldBeRevertedBackAfterTestMethod_whenTestClassAnnotated() {
        EnvironmentVariableHelper.set(TEST_ENV_VAR_NAME, TEST_ENV_VAR_VALUE);
        try {
            Assertions.assertEquals(TEST_ENV_VAR_VALUE, EnvironmentVariableHelper.get(TEST_ENV_VAR_NAME));
            runTestMethod(EnvironmentVariableSandboxExtensionTestInternal2.class, "updateEnvVar");
            Assertions.assertEquals(TEST_ENV_VAR_VALUE, EnvironmentVariableHelper.get(TEST_ENV_VAR_NAME));
        } finally {
            EnvironmentVariableHelper.remove(TEST_ENV_VAR_NAME);
        }
    }

    @Test
    public void clearedEnvVarShouldBePutBackAfterTestMethod_whenTestClassAnnotated() {
        EnvironmentVariableHelper.set(TEST_ENV_VAR_NAME, TEST_ENV_VAR_VALUE);
        try {
            Assertions.assertEquals(TEST_ENV_VAR_VALUE, EnvironmentVariableHelper.get(TEST_ENV_VAR_NAME));
            runTestMethod(EnvironmentVariableSandboxExtensionTestInternal2.class, "clearEnvVar");
            Assertions.assertEquals(TEST_ENV_VAR_VALUE, EnvironmentVariableHelper.get(TEST_ENV_VAR_NAME));
        } finally {
            EnvironmentVariableHelper.remove(TEST_ENV_VAR_NAME);
        }
    }

    public static class EnvironmentVariableSandboxExtensionTestInternal1 {

        @Test
        @EnvironmentVariableSandbox
        public void setEnvVar() {
            EnvironmentVariableHelper.set(TEST_ENV_VAR_NAME, TEST_ENV_VAR_VALUE);
            Assertions.assertEquals(TEST_ENV_VAR_VALUE, EnvironmentVariableHelper.get(TEST_ENV_VAR_NAME));
        }

        @Test
        @EnvironmentVariableSandbox
        public void updateEnvVar() {
            EnvironmentVariableHelper.set(TEST_ENV_VAR_NAME, TEST_ENV_VAR_VALUE_UPDATED);
            Assertions.assertEquals(TEST_ENV_VAR_VALUE_UPDATED, EnvironmentVariableHelper.get(TEST_ENV_VAR_NAME));
        }

        @Test
        @EnvironmentVariableSandbox
        public void clearEnvVar() {
            EnvironmentVariableHelper.remove(TEST_ENV_VAR_NAME);
            Assertions.assertNull(EnvironmentVariableHelper.get(TEST_ENV_VAR_NAME));
        }

    }

    @EnvironmentVariableSandbox
    public static class EnvironmentVariableSandboxExtensionTestInternal2 {

        @Test
        public void setEnvVar() {
            EnvironmentVariableHelper.set(TEST_ENV_VAR_NAME, TEST_ENV_VAR_VALUE);
            Assertions.assertEquals(TEST_ENV_VAR_VALUE, EnvironmentVariableHelper.get(TEST_ENV_VAR_NAME));
        }

        @Test
        public void updateEnvVar() {
            EnvironmentVariableHelper.set(TEST_ENV_VAR_NAME, TEST_ENV_VAR_VALUE_UPDATED);
            Assertions.assertEquals(TEST_ENV_VAR_VALUE_UPDATED, EnvironmentVariableHelper.get(TEST_ENV_VAR_NAME));
        }

        @Test
        public void clearEnvVar() {
            EnvironmentVariableHelper.remove(TEST_ENV_VAR_NAME);
            Assertions.assertNull(EnvironmentVariableHelper.get(TEST_ENV_VAR_NAME));
        }

    }

}
