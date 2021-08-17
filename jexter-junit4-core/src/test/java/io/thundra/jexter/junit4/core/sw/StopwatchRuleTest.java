package io.thundra.jexter.junit4.core.sw;

import io.thundra.jexter.junit4.core.BaseTest;
import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * @author serkan
 */
public class StopwatchRuleTest extends BaseTest {

    @Test
    public void elapsedTimeShouldBePrinted_whenTestMethodAnnotated() {
        PrintStream out = System.out;
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            System.setOut(new PrintStream(baos));
            runTestMethod(StopwatchRuleTest.StopwatchRuleTestInternal1.class, "test");
            String outContent = baos.toString();
            Assert.assertNotNull(outContent);
            Assert.assertTrue(outContent.contains("Execution of test"));
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
            runTestMethod(StopwatchRuleTest.StopwatchRuleTestInternal2.class, "test");
            String outContent = baos.toString();
            Assert.assertNotNull(outContent);
            Assert.assertTrue(outContent.contains("Execution of test suite"));
        } finally {
            System.setOut(out);
        }
    }

    public static class StopwatchRuleTestInternal1 {

        @Rule
        public StopwatchRule rule = new StopwatchRule();

        @Test
        public void test() {
        }

    }

    public static class StopwatchRuleTestInternal2 {

        @ClassRule
        public static StopwatchRule rule = new StopwatchRule();

        @Test
        public void test() {
        }

    }

}
