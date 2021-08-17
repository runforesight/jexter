package io.thundra.jexter.junit5.core;

import org.junit.jupiter.api.extension.Extension;
import org.junit.jupiter.api.extension.ExtensionContext;

/**
 * Base class for <b>Jexter</b> {@link Extension}s
 *
 * @author serkan
 */
public abstract class JexterBaseExtension implements Extension {

    protected ExtensionContext.Store getStore(ExtensionContext context) {
        return context.getStore(ExtensionContext.Namespace.create(getClass()));
    }

    protected String getStoreKey(ExtensionContext context) {
        return context.getUniqueId() + "$" + getClass().getName();
    }

}
