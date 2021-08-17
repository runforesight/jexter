package io.thundra.jexter.junit5.core.sw;

import org.junit.jupiter.api.extension.ExtendWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * {@code @Stopwatch} is a JUnit Jupiter extension
 * to measure the elapsed time of a test execution.
 *
 * <p>{@code Stopwatch} can be used on the method and
 * on the class level. If a class is annotated, total execution time will be
 * measured for all tests inside that class.</p>
 *
 * @author serkan
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.TYPE })
@Inherited
@ExtendWith(StopwatchExtension.class)
public @interface Stopwatch {
}
