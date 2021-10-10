package dev.liambloom.util.function;

import java.util.function.LongBinaryOperator;
import dev.liambloom.util.annotation.Unchecked;

@Unchecked(LongBinaryOperator.class)
@FunctionalInterface
public interface LongBinaryOperatorThrowsException {
    long applyAsLong(long left, long right) throws Exception;
}
