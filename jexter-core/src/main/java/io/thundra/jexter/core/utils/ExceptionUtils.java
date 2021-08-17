package io.thundra.jexter.core.utils;

/**
 * Utility class for exception related stuffs.
 *
 * @author serkan
 */
public final class ExceptionUtils {

    private ExceptionUtils() {
    }

    /**
     * Throws given {@link Throwable error}
     * @param t the {@link Throwable} to be thrown
     * @param <T> the {@link Throwable} type
     * @return the {@link Throwable}
     */
    public static <T> T sneakyThrow(Throwable t) {
        ExceptionUtils.<RuntimeException>sneakyThrowInternal(t);
        return (T) t;
    }

    private static <T extends Throwable> void sneakyThrowInternal(Throwable t) throws T {
        throw (T) t;
    }

}
