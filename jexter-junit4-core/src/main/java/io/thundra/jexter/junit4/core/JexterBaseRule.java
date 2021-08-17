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
                C context = onBeforeEvaluate();
                try {
                    base.evaluate();
                    onEvaluateSuccess(context);
                } catch (Throwable error) {
                    onEvaluateError(context, error);
                    throw error;
                } finally {
                    onAfterEvaluate(context);
                }
            }
        };
    }

    protected C onBeforeEvaluate() {
        return null;
    }

    protected void onEvaluateSuccess(C context) {
    }

    protected void onEvaluateError(C context, Throwable error) {
    }

    protected void onAfterEvaluate(C context) {
    }

}
