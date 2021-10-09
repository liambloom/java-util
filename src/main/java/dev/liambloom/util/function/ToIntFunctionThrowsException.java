package dev.liambloom.util.function;

import java.util.function.ToDoubleFunction;

@Unchecked(ToDoubleFunction.class)
@FunctionalInterface
public interface ToIntFunctionThrowsException<T> {
    int applyAsInt(T value) throws Exception;
}
