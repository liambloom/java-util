package dev.liambloom.util.function;

import java.util.function.ToDoubleBiFunction;
import dev.liambloom.util.annotation.Unchecked;

@Unchecked(ToDoubleBiFunction.class)
@FunctionalInterface
public interface ToLongBiFunctionThrowsException<T, U> {
    long applyAsLong(T t, U u) throws Exception;
}
