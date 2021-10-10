package dev.liambloom.util.function;

import java.util.function.ToDoubleBiFunction;
import dev.liambloom.util.annotation.Unchecked;

@Unchecked(ToDoubleBiFunction.class)
@FunctionalInterface
public interface ToIntBiFunctionThrowsException<T, U> {
    int applyAsInt(T t, U u) throws Exception;
}
