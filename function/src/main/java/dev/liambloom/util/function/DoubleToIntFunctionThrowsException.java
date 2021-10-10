package dev.liambloom.util.function;

import java.util.function.DoubleToIntFunction;
import dev.liambloom.util.annotation.Unchecked;

@Unchecked(DoubleToIntFunction.class)
@FunctionalInterface
public interface DoubleToIntFunctionThrowsException {
    int applyAsInt(double value) throws Exception;
}
