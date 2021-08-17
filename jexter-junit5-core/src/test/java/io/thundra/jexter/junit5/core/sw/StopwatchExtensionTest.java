package io.thundra.jexter.junit5.core.sw;

import io.thundra.jexter.junit5.core.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * @author serkan
 */
public class StopwatchExtensionTest extends BaseTest {

    @Test
    public void elapsedTimeShouldBePrinted_whenTestMethodAnnotated() {
        PrintStream out = System.out;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            System.setOut(new PrintStream(baos));
            runTestMethod(StopwatchExtensionTest.StopwatchExtensionTestInternal1.class, "test");
            String outContent = baos.toString();
            Assertions.assertNotNull(outContent);
            Assertions.assertTrue(outContent.contains("Execution of test"));
        } finally {
            System.setOut(out);
        }
    }

    @Test
    public void elapsedTimeShouldBePrinted_whenTestClassAnnotated() {
        PrintStream out = System.out;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            System.setOut(new PrintStream(baos));
            runTestMethod(StopwatchExtensionTest.StopwatchExtensionTestInternal2.class, "test");
            String outContent = baos.toString();
            Assertions.assertNotNull(outContent);
            Assertions.assertTrue(outContent.contains("Execution of test suite"));
        } finally {
            System.setOut(out);
        }
    }

    public static class StopwatchExtensionTestInternal1 {

        @Stopwatch
        @Test
        public void test() {
        }

    }

    @Stopwatch
    public static class StopwatchExtensionTestInternal2 {

        @Test
        public void test() {
        }

    }

}
