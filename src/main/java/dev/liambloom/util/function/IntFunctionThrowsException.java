package dev.liambloom.util.function;

import java.util.function.IntFunction;

@Unchecked(IntFunction.class)
@FunctionalInterface
public interface IntFunctionThrowsException<R> {
    R apply(int value) throws Exception;
}
