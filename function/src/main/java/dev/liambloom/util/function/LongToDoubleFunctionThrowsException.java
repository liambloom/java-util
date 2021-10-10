package dev.liambloom.util.function;

import java.util.function.LongToDoubleFunction;
import dev.liambloom.util.annotation.Unchecked;

@Unchecked(LongToDoubleFunction.class)
@FunctionalInterface
public interface LongToDoubleFunctionThrowsException {
    double applyAsLong(long value) throws Exception;
}
