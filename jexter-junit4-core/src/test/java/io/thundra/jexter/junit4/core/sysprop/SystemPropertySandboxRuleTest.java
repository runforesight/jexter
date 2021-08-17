package io.thundra.jexter.junit4.core.sysprop;

import io.thundra.jexter.junit4.core.BaseTest;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

/**
 * @author serkan
 */
public class SystemPropertySandboxRuleTest extends BaseTest {

    private static final String TEST_SYS_PROP_NAME = "key";
    private static final String TEST_SYS_PROP_VALUE = "val";
    private static final String TEST_SYS_PROP_VALUE_UPDATED = "val-updated";

    @Test
    public void setSysPropShouldBeClearedAfterTestMethod_whenTestMethodAnnotated() {
        Assert.assertNull(System.getProperty(TEST_SYS_PROP_NAME));
        runTestMethod(EnvironmentVariableSandboxRuleTestInternal1.class, "setSysProp");
        Assert.assertNull(System.getProperty(TEST_SYS_PROP_NAME));
    }

    @Test
    public void updatedSysPropShouldBeRevertedBackAfterTestMethod_whenTestMethodAnnotated() {
        System.setProperty(TEST_SYS_PROP_NAME, TEST_SYS_PROP_VALUE);
        try {
            Assert.assertEquals(TEST_SYS_PROP_VALUE, System.getProperty(TEST_SYS_PROP_NAME));
            runTestMethod(EnvironmentVariableSandboxRuleTestInternal1.class, "updateSysProp");
            Assert.assertEquals(TEST_SYS_PROP_VALUE, System.getProperty(TEST_SYS_PROP_NAME));
        } finally {
            System.clearProperty(TEST_SYS_PROP_NAME);
        }
    }

    @Test
    public void clearedSysPropShouldBePutBackAfterTestMethod_whenTestMethodAnnotated() {
        System.setProperty(TEST_SYS_PROP_NAME, TEST_SYS_PROP_VALUE);
        try {
            Assert.assertEquals(TEST_SYS_PROP_VALUE, System.getProperty(TEST_SYS_PROP_NAME));
            runTestMethod(EnvironmentVariableSandboxRuleTestInternal1.class, "clearSysProp");
            Assert.assertEquals(TEST_SYS_PROP_VALUE, System.getProperty(TEST_SYS_PROP_NAME));
        } finally {
            System.clearProperty(TEST_SYS_PROP_NAME);
        }
    }

    @Test
    public void setSysPropShouldBeClearedAfterTestMethod_whenTestClassAnnotated() {
        Assert.assertNull(System.getProperty(TEST_SYS_PROP_NAME));
        runTestMethod(EnvironmentVariableSandboxRuleTestInternal2.class, "setSysProp");
        Assert.assertNull(System.getProperty(TEST_SYS_PROP_NAME));
    }

    @Test
    public void updatedSysPropShouldBeRevertedBackAfterTestMethod_whenTestClassAnnotated() {
        System.setProperty(TEST_SYS_PROP_NAME, TEST_SYS_PROP_VALUE);
        try {
            Assert.assertEquals(TEST_SYS_PROP_VALUE, System.getProperty(TEST_SYS_PROP_NAME));
            runTestMethod(EnvironmentVariableSandboxRuleTestInternal2.class, "updateSysProp");
            Assert.assertEquals(TEST_SYS_PROP_VALUE, System.getProperty(TEST_SYS_PROP_NAME));
        } finally {
            System.clearProperty(TEST_SYS_PROP_NAME);
        }
    }

    @Test
    public void clearedSysPropShouldBePutBackAfterTestMethod_whenTestClassAnnotated() {
        System.setProperty(TEST_SYS_PROP_NAME, TEST_SYS_PROP_VALUE);
        try {
            Assert.assertEquals(TEST_SYS_PROP_VALUE, System.getProperty(TEST_SYS_PROP_NAME));
            runTestMethod(EnvironmentVariableSandboxRuleTestInternal2.class, "clearSysProp");
            Assert.assertEquals(TEST_SYS_PROP_VALUE, System.getProperty(TEST_SYS_PROP_NAME));
        } finally {
            System.clearProperty(TEST_SYS_PROP_NAME);
        }
    }

    public static class EnvironmentVariableSandboxRuleTestInternal1 {

        @Rule
        public SystemPropertySandboxRule rule = new SystemPropertySandboxRule();

        @Test
        public void setSysProp() {
            System.setProperty(TEST_SYS_PROP_NAME, TEST_SYS_PROP_VALUE);
            Assert.assertEquals(TEST_SYS_PROP_VALUE, System.getProperty(TEST_SYS_PROP_NAME));
        }

        @Test
        public void updateSysProp() {
            System.setProperty(TEST_SYS_PROP_NAME, TEST_SYS_PROP_VALUE_UPDATED);
            Assert.assertEquals(TEST_SYS_PROP_VALUE_UPDATED, System.getProperty(TEST_SYS_PROP_NAME));
        }

        @Test
        public void clearSysProp() {
            System.clearProperty(TEST_SYS_PROP_NAME);
            Assert.assertNull(System.getProperty(TEST_SYS_PROP_NAME));
        }

    }

    public static class EnvironmentVariableSandboxRuleTestInternal2 {

        @ClassRule
        public static SystemPropertySandboxRule rule = new SystemPropertySandboxRule();

        @Test
        public void setSysProp() {
            System.setProperty(TEST_SYS_PROP_NAME, TEST_SYS_PROP_VALUE);
            Assert.assertEquals(TEST_SYS_PROP_VALUE, System.getProperty(TEST_SYS_PROP_NAME));
        }

        @Test
        public void updateSysProp() {
            System.setProperty(TEST_SYS_PROP_NAME, TEST_SYS_PROP_VALUE_UPDATED);
            Assert.assertEquals(TEST_SYS_PROP_VALUE_UPDATED, System.getProperty(TEST_SYS_PROP_NAME));
        }

        @Test
        public void clearSysProp() {
            System.clearProperty(TEST_SYS_PROP_NAME);
            Assert.assertNull(System.getProperty(TEST_SYS_PROP_NAME));
        }

    }

}
