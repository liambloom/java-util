package dev.liambloom.util.function;

import java.util.function.ToDoubleBiFunction;

@Unchecked(ToDoubleBiFunction.class)
@FunctionalInterface
public interface ToLongBiFunctionThrowsException<T, U> {
    long applyAsLong(T t, U u) throws Exception;
}
