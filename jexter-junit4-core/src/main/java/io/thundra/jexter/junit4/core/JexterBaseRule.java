package io.thundra.jexter.junit4.core;

import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

/**
 * Base class for <b>Jexter</b> {@link TestRule}s
 *
 * @author serkan
 */
public abstract class JexterBaseRule<C> implements TestRule {

    @Override
    public Statement apply(Statement base, Description description) {
        return new Statement() {
            @Override
            public void evaluate() throws Throwable {
                C context = onBeforeEvaluate(description);
                try {
                    base.evaluate();
                    onEvaluateSuccess(description, context);
                } catch (Throwable error) {
                    onEvaluateError(description, context, error);
                    throw error;
                } finally {
                    onAfterEvaluate(description, context);
                }
            }
        };
    }

    protected C onBeforeEvaluate(Description description) {
        return null;
    }

    protected void onEvaluateSuccess(Description description, C context) {
    }

    protected void onEvaluateError(Description description, C context, Throwable error) {
    }

    protected void onAfterEvaluate(Description description, C context) {
    }

}
