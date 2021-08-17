package io.thundra.jexter.junit5.core.envvar;

import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code @EnvironmentVariableSandbox} is a JUnit Jupiter extension
 * to manage environment variables in a sandbox for a test execution.
 *
 * <p>So modifications over environment variables during the text execution are rolled-back
 * after the test to prevent effecting subsequent tests.</p>
 *
 * <p>{@code EnvironmentVariableSandbox} can be used on the method and
 * on the class level. If a class is annotated, environment variables will be
 * sandboxed for all tests inside that class.</p>
 *
 * <p>WARNING: Java considers environment variables to be immutable, so this extension
 * uses reflection to change them. This requires that the {@link SecurityManager}
 * allows modifications and can potentially break on different operating systems and
 * Java versions. Be aware that this is a fragile solution and consider finding a
 * better one for your specific situation. If you're running on Java 9 or later, you
 * may have to add {@code --add-opens=java.base/java.util=ALL-UNNAMED} to your test
 * execution to prevent warnings or even errors.</p>
 *
 * @author serkan
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
@Inherited
@ExtendWith(EnvironmentVariableSandboxExtension.class)
public @interface EnvironmentVariableSandbox {
}
