package dev.liambloom.util.function;

import java.util.function.LongToDoubleFunction;

@Unchecked(LongToDoubleFunction.class)
@FunctionalInterface
public interface LongToDoubleFunctionThrowsException {
    double applyAsLong(long value) throws Exception;
}
