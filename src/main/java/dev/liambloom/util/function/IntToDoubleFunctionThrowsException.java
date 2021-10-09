package dev.liambloom.util.function;

import java.util.function.IntToDoubleFunction;

@Unchecked(IntToDoubleFunction.class)
public interface IntToDoubleFunctionThrowsException {
    double applyAsInt(int value) throws Exception;
}
