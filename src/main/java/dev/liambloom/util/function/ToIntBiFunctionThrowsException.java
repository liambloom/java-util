package dev.liambloom.util.function;

import java.util.function.ToDoubleBiFunction;

@Unchecked(ToDoubleBiFunction.class)
@FunctionalInterface
public interface ToIntBiFunctionThrowsException<T, U> {
    int applyAsInt(T t, U u) throws Exception;
}
