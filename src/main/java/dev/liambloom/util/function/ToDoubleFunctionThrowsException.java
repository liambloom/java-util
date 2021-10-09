package dev.liambloom.util.function;

import java.util.function.ToDoubleFunction;

@Unchecked(ToDoubleFunction.class)
@FunctionalInterface
public interface ToDoubleFunctionThrowsException<T> {
    double applyAsDouble(T value) throws Exception;
}
