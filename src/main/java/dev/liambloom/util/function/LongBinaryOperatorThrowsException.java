package dev.liambloom.util.function;

import java.util.function.LongBinaryOperator;

@Unchecked(LongBinaryOperator.class)
@FunctionalInterface
public interface LongBinaryOperatorThrowsException {
    long applyAsLong(long left, long right) throws Exception;
}
