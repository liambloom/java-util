package dev.liambloom.util.function;

import java.util.function.IntBinaryOperator;
import dev.liambloom.util.annotation.Unchecked;

@Unchecked(IntBinaryOperator.class)
@FunctionalInterface
public interface IntBinaryOperatorThrowsException {
    int applyAsInt(int left, int right) throws Exception;
}
