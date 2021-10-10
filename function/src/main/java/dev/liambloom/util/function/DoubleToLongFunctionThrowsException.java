package dev.liambloom.util.function;

import java.util.function.DoubleToLongFunction;
import dev.liambloom.util.annotation.Unchecked;

@Unchecked(DoubleToLongFunction.class)
@FunctionalInterface
public interface DoubleToLongFunctionThrowsException {
    long applyAsLong(double value) throws Exception;
}
