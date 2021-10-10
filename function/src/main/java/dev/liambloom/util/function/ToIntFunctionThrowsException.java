package dev.liambloom.util.function;

import java.util.function.ToDoubleFunction;
import dev.liambloom.util.annotation.Unchecked;

@Unchecked(ToDoubleFunction.class)
@FunctionalInterface
public interface ToIntFunctionThrowsException<T> {
    int applyAsInt(T value) throws Exception;
}
