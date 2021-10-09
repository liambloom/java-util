package dev.liambloom.util.function;

import java.util.function.DoubleBinaryOperator;

@Unchecked(DoubleBinaryOperator.class)
@FunctionalInterface
public interface DoubleBinaryOperatorThrowsException {
    double applyAsDouble(double left, double right) throws Exception;
}
