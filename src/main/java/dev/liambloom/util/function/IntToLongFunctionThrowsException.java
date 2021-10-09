package dev.liambloom.util.function;

import java.util.function.IntToLongFunction;

@Unchecked(IntToLongFunction.class)
@FunctionalInterface
public interface IntToLongFunctionThrowsException {
    long applyAsLong(int value) throws Exception;
}
