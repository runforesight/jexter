package io.thundra.jexter.junit5.core.sysprop;

import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code @SystemPropertySandbox} is a JUnit Jupiter extension
 * to manage system properties in a sandbox for a test execution.
 *
 * <p>So modifications over system properties during the text execution are rolled-back
 * after the test to prevent effecting subsequent tests.</p>
 *
 * <p>{@code SystemPropertySandboxExtension} can be used on the method and
 * on the class level. If a class is annotated, esystem properties will be
 * sandboxed for all tests inside that class.</p>
 *
 * @author serkan
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
@Inherited
@ExtendWith(SystemPropertySandboxExtension.class)
public @interface SystemPropertySandbox {
}
