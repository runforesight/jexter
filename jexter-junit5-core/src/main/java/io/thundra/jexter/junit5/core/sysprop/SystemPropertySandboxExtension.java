package io.thundra.jexter.junit5.core.sysprop;

import io.thundra.jexter.core.sysprop.ThreadLocalProperties;
import io.thundra.jexter.junit5.core.JexterBaseExtension;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

import java.util.Properties;

/**
 * {@link org.junit.jupiter.api.extension.Extension} implementation
 * which stores system properties before the test
 * and restores them back to original value after the test.
 *
 * @author serkan
 */
public class SystemPropertySandboxExtension
        extends JexterBaseExtension
        implements BeforeAllCallback, BeforeEachCallback, AfterAllCallback, AfterEachCallback {

    private void storeSysProps(ExtensionContext context) {
        ExtensionContext.Store store = getStore(context);
        String storeKey = getStoreKey(context);
        Properties sysProps = System.getProperties();
        System.setProperties(new ThreadLocalProperties(sysProps));
        SysPropsBackup sysPropsBackup = new SysPropsBackup(sysProps);
        store.put(storeKey, sysPropsBackup);
    }

    private void restoreSysProps(ExtensionContext context) {
        ExtensionContext.Store store = getStore(context);
        String storeKey = getStoreKey(context);
        SysPropsBackup sysPropsBackup = (SysPropsBackup) store.get(storeKey);
        if (sysPropsBackup != null) {
            System.setProperties(sysPropsBackup.sysProps);
        }
    }

    private static class SysPropsBackup {

        private final Properties sysProps;

        public SysPropsBackup(Properties sysProps) {
            this.sysProps = sysProps;
        }

    }

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        storeSysProps(context);
    }

    @Override
    public void beforeEach(ExtensionContext context) throws Exception {
        storeSysProps(context);
    }

    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        restoreSysProps(context);
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        restoreSysProps(context);
    }

}
