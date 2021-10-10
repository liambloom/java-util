package dev.liambloom.util.function;

import java.util.function.ToDoubleBiFunction;
import dev.liambloom.util.annotation.Unchecked;

@Unchecked(ToDoubleBiFunction.class)
@FunctionalInterface
public interface ToDoubleBiFunctionThrowsException<T, U> {
    double applyAsDouble(T t, U u) throws Exception;
}
