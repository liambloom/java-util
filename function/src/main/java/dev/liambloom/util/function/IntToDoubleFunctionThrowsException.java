package dev.liambloom.util.function;

import java.util.function.IntToDoubleFunction;
import dev.liambloom.util.annotation.Unchecked;

@Unchecked(IntToDoubleFunction.class)
@FunctionalInterface
public interface IntToDoubleFunctionThrowsException {
    double applyAsInt(int value) throws Exception;
}
