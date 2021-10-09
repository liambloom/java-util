package dev.liambloom.util.function;

import java.util.function.IntBinaryOperator;

@Unchecked(IntBinaryOperator.class)
@FunctionalInterface
public interface IntBinaryOperatorThrowsException {
    int applyAsInt(int left, int right) throws Exception;
}
