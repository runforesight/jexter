package io.thundra.jexter.junit5.core.sysprop;

import io.thundra.jexter.junit5.core.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * @author serkan
 */
public class SystemPropertySandboxExtensionTest extends BaseTest {

    private static final String TEST_SYS_PROP_NAME = "key";
    private static final String TEST_SYS_PROP_VALUE = "val";
    private static final String TEST_SYS_PROP_VALUE_UPDATED = "val-updated";

    @Test
    public void setSysPropShouldBeClearedAfterTestMethod_whenTestMethodAnnotated() {
        Assertions.assertNull(System.getProperty(TEST_SYS_PROP_NAME));
        runTestMethod(SystemPropertySandboxExtensionTestInternal1.class, "setSysProp");
        Assertions.assertNull(System.getProperty(TEST_SYS_PROP_NAME));
    }

    @Test
    public void updatedSysPropShouldBeRevertedBackAfterTestMethod_whenTestMethodAnnotated() {
        System.setProperty(TEST_SYS_PROP_NAME, TEST_SYS_PROP_VALUE);
        try {
            Assertions.assertEquals(TEST_SYS_PROP_VALUE, System.getProperty(TEST_SYS_PROP_NAME));
            runTestMethod(SystemPropertySandboxExtensionTestInternal1.class, "updateSysProp");
            Assertions.assertEquals(TEST_SYS_PROP_VALUE, System.getProperty(TEST_SYS_PROP_NAME));
        } finally {
            System.clearProperty(TEST_SYS_PROP_NAME);
        }
    }

    @Test
    public void clearedSysPropShouldBePutBackAfterTestMethod_whenTestMethodAnnotated() {
        System.setProperty(TEST_SYS_PROP_NAME, TEST_SYS_PROP_VALUE);
        try {
            Assertions.assertEquals(TEST_SYS_PROP_VALUE, System.getProperty(TEST_SYS_PROP_NAME));
            runTestMethod(SystemPropertySandboxExtensionTestInternal1.class, "clearSysProp");
            Assertions.assertEquals(TEST_SYS_PROP_VALUE, System.getProperty(TEST_SYS_PROP_NAME));
        } finally {
            System.clearProperty(TEST_SYS_PROP_NAME);
        }
    }

    @Test
    public void setSysPropShouldBeClearedAfterTestMethod_whenTestClassAnnotated() {
        Assertions.assertNull(System.getProperty(TEST_SYS_PROP_NAME));
        runTestMethod(SystemPropertySandboxExtensionTestInternal2.class, "setSysProp");
        Assertions.assertNull(System.getProperty(TEST_SYS_PROP_NAME));
    }

    @Test
    public void updatedSysPropShouldBeRevertedBackAfterTestMethod_whenTestClassAnnotated() {
        System.setProperty(TEST_SYS_PROP_NAME, TEST_SYS_PROP_VALUE);
        try {
            Assertions.assertEquals(TEST_SYS_PROP_VALUE, System.getProperty(TEST_SYS_PROP_NAME));
            runTestMethod(SystemPropertySandboxExtensionTestInternal2.class, "updateSysProp");
            Assertions.assertEquals(TEST_SYS_PROP_VALUE, System.getProperty(TEST_SYS_PROP_NAME));
        } finally {
            System.clearProperty(TEST_SYS_PROP_NAME);
        }
    }

    @Test
    public void clearedSysPropShouldBePutBackAfterTestMethod_whenTestClassAnnotated() {
        System.setProperty(TEST_SYS_PROP_NAME, TEST_SYS_PROP_VALUE);
        try {
            Assertions.assertEquals(TEST_SYS_PROP_VALUE, System.getProperty(TEST_SYS_PROP_NAME));
            runTestMethod(SystemPropertySandboxExtensionTestInternal2.class, "clearSysProp");
            Assertions.assertEquals(TEST_SYS_PROP_VALUE, System.getProperty(TEST_SYS_PROP_NAME));
        } finally {
            System.clearProperty(TEST_SYS_PROP_NAME);
        }
    }

    public static class SystemPropertySandboxExtensionTestInternal1 {

        @Test
        @SystemPropertySandbox
        public void setSysProp() {
            System.setProperty(TEST_SYS_PROP_NAME, TEST_SYS_PROP_VALUE);
            Assertions.assertEquals(TEST_SYS_PROP_VALUE, System.getProperty(TEST_SYS_PROP_NAME));
        }

        @Test
        @SystemPropertySandbox
        public void updateSysProp() {
            System.setProperty(TEST_SYS_PROP_NAME, TEST_SYS_PROP_VALUE_UPDATED);
            Assertions.assertEquals(TEST_SYS_PROP_VALUE_UPDATED, System.getProperty(TEST_SYS_PROP_NAME));
        }

        @Test
        @SystemPropertySandbox
        public void clearSysProp() {
            System.clearProperty(TEST_SYS_PROP_NAME);
            Assertions.assertNull(System.getProperty(TEST_SYS_PROP_NAME));
        }

    }

    @SystemPropertySandbox
    public static class SystemPropertySandboxExtensionTestInternal2 {

        @Test
        public void setSysProp() {
            System.setProperty(TEST_SYS_PROP_NAME, TEST_SYS_PROP_VALUE);
            Assertions.assertEquals(TEST_SYS_PROP_VALUE, System.getProperty(TEST_SYS_PROP_NAME));
        }

        @Test
        public void updateSysProp() {
            System.setProperty(TEST_SYS_PROP_NAME, TEST_SYS_PROP_VALUE_UPDATED);
            Assertions.assertEquals(TEST_SYS_PROP_VALUE_UPDATED, System.getProperty(TEST_SYS_PROP_NAME));
        }

        @Test
        public void clearSysProp() {
            System.clearProperty(TEST_SYS_PROP_NAME);
            Assertions.assertNull(System.getProperty(TEST_SYS_PROP_NAME));
        }

    }

}
