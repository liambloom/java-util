package dev.liambloom.util.function;

import java.util.function.ToDoubleFunction;

@Unchecked(ToDoubleFunction.class)
@FunctionalInterface
public interface ToLongFunctionThrowsException<T> {
    long applyAsLong(T value) throws Exception;
}
