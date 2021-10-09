package dev.liambloom.util.function;

import java.util.function.DoubleToIntFunction;

@Unchecked(DoubleToIntFunction.class)
public interface DoubleToIntFunctionThrowsException {
    int applyAsInt(double value) throws Exception;
}
