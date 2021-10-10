package dev.liambloom.util.function;

import java.util.function.DoubleBinaryOperator;
import dev.liambloom.util.annotation.Unchecked;

@Unchecked(DoubleBinaryOperator.class)
@FunctionalInterface
public interface DoubleBinaryOperatorThrowsException {
    double applyAsDouble(double left, double right) throws Exception;
}
