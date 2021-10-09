package dev.liambloom.util.function;

import java.util.function.ToDoubleBiFunction;

@Unchecked(ToDoubleBiFunction.class)
@FunctionalInterface
public interface ToDoubleBiFunctionThrowsException<T, U> {
    double applyAsDouble(T t, U u) throws Exception;
}
