package io.thundra.jexter.junit4.core.envvar;

import io.thundra.jexter.core.envvar.EnvironmentVariableHelper;
import io.thundra.jexter.junit4.core.BaseTest;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author serkan
 */
public class EnvironmentVariableSandboxRuleTest extends BaseTest {

    private static final String TEST_ENV_VAR_NAME = "key";
    private static final String TEST_ENV_VAR_VALUE = "val";
    private static final String TEST_ENV_VAR_VALUE_UPDATED = "val-updated";

    @Test
    public void setEnvVarShouldBeClearedAfterTestMethod_whenTestMethodAnnotated() {
        Assert.assertNull(EnvironmentVariableHelper.get(TEST_ENV_VAR_NAME));
        runTestMethod(EnvironmentVariableSandboxRuleTestInternal1.class, "setEnvVar");
        Assert.assertNull(EnvironmentVariableHelper.get(TEST_ENV_VAR_NAME));
    }

    @Test
    public void updatedEnvVarShouldBeRevertedBackAfterTestMethod_whenTestMethodAnnotated() {
        EnvironmentVariableHelper.set(TEST_ENV_VAR_NAME, TEST_ENV_VAR_VALUE);
        try {
            Assert.assertEquals(TEST_ENV_VAR_VALUE, EnvironmentVariableHelper.get(TEST_ENV_VAR_NAME));
            runTestMethod(EnvironmentVariableSandboxRuleTestInternal1.class, "updateEnvVar");
            Assert.assertEquals(TEST_ENV_VAR_VALUE, EnvironmentVariableHelper.get(TEST_ENV_VAR_NAME));
        } finally {
            EnvironmentVariableHelper.remove(TEST_ENV_VAR_NAME);
        }
    }

    @Test
    public void clearedEnvVarShouldBePutBackAfterTestMethod_whenTestMethodAnnotated() {
        EnvironmentVariableHelper.set(TEST_ENV_VAR_NAME, TEST_ENV_VAR_VALUE);
        try {
            Assert.assertEquals(TEST_ENV_VAR_VALUE, EnvironmentVariableHelper.get(TEST_ENV_VAR_NAME));
            runTestMethod(EnvironmentVariableSandboxRuleTestInternal1.class, "clearEnvVar");
            Assert.assertEquals(TEST_ENV_VAR_VALUE, EnvironmentVariableHelper.get(TEST_ENV_VAR_NAME));
        } finally {
            EnvironmentVariableHelper.remove(TEST_ENV_VAR_NAME);
        }
    }

    @Test
    public void setEnvVarShouldBeClearedAfterTestMethod_whenTestClassAnnotated() {
        Assert.assertNull(EnvironmentVariableHelper.get(TEST_ENV_VAR_NAME));
        runTestMethod(EnvironmentVariableSandboxRuleTestInternal2.class, "setEnvVar");
        Assert.assertNull(EnvironmentVariableHelper.get(TEST_ENV_VAR_NAME));
    }

    @Test
    public void updatedEnvVarShouldBeRevertedBackAfterTestMethod_whenTestClassAnnotated() {
        EnvironmentVariableHelper.set(TEST_ENV_VAR_NAME, TEST_ENV_VAR_VALUE);
        try {
            Assert.assertEquals(TEST_ENV_VAR_VALUE, EnvironmentVariableHelper.get(TEST_ENV_VAR_NAME));
            runTestMethod(EnvironmentVariableSandboxRuleTestInternal2.class, "updateEnvVar");
            Assert.assertEquals(TEST_ENV_VAR_VALUE, EnvironmentVariableHelper.get(TEST_ENV_VAR_NAME));
        } finally {
            EnvironmentVariableHelper.remove(TEST_ENV_VAR_NAME);
        }
    }

    @Test
    public void clearedEnvVarShouldBePutBackAfterTestMethod_whenTestClassAnnotated() {
        EnvironmentVariableHelper.set(TEST_ENV_VAR_NAME, TEST_ENV_VAR_VALUE);
        try {
            Assert.assertEquals(TEST_ENV_VAR_VALUE, EnvironmentVariableHelper.get(TEST_ENV_VAR_NAME));
            runTestMethod(EnvironmentVariableSandboxRuleTestInternal2.class, "clearEnvVar");
            Assert.assertEquals(TEST_ENV_VAR_VALUE, EnvironmentVariableHelper.get(TEST_ENV_VAR_NAME));
        } finally {
            EnvironmentVariableHelper.remove(TEST_ENV_VAR_NAME);
        }
    }

    public static class EnvironmentVariableSandboxRuleTestInternal1 {

        @Rule
        public EnvironmentVariableSandboxRule rule = new EnvironmentVariableSandboxRule();

        @Test
        public void setEnvVar() {
            EnvironmentVariableHelper.set(TEST_ENV_VAR_NAME, TEST_ENV_VAR_VALUE);
            Assert.assertEquals(TEST_ENV_VAR_VALUE, EnvironmentVariableHelper.get(TEST_ENV_VAR_NAME));
        }

        @Test
        public void updateEnvVar() {
            EnvironmentVariableHelper.set(TEST_ENV_VAR_NAME, TEST_ENV_VAR_VALUE_UPDATED);
            Assert.assertEquals(TEST_ENV_VAR_VALUE_UPDATED, EnvironmentVariableHelper.get(TEST_ENV_VAR_NAME));
        }

        @Test
        public void clearEnvVar() {
            EnvironmentVariableHelper.remove(TEST_ENV_VAR_NAME);
            Assert.assertNull(EnvironmentVariableHelper.get(TEST_ENV_VAR_NAME));
        }

    }

    public static class EnvironmentVariableSandboxRuleTestInternal2 {

        @ClassRule
        public static EnvironmentVariableSandboxRule rule = new EnvironmentVariableSandboxRule();

        @Test
        public void setEnvVar() {
            EnvironmentVariableHelper.set(TEST_ENV_VAR_NAME, TEST_ENV_VAR_VALUE);
            Assert.assertEquals(TEST_ENV_VAR_VALUE, EnvironmentVariableHelper.get(TEST_ENV_VAR_NAME));
        }

        @Test
        public void updateEnvVar() {
            EnvironmentVariableHelper.set(TEST_ENV_VAR_NAME, TEST_ENV_VAR_VALUE_UPDATED);
            Assert.assertEquals(TEST_ENV_VAR_VALUE_UPDATED, EnvironmentVariableHelper.get(TEST_ENV_VAR_NAME));
        }

        @Test
        public void clearEnvVar() {
            EnvironmentVariableHelper.remove(TEST_ENV_VAR_NAME);
            Assert.assertNull(EnvironmentVariableHelper.get(TEST_ENV_VAR_NAME));
        }

    }

}
