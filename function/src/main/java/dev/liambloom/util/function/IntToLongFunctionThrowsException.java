package dev.liambloom.util.function;

import java.util.function.IntToLongFunction;
import dev.liambloom.util.annotation.Unchecked;

@Unchecked(IntToLongFunction.class)
@FunctionalInterface
public interface IntToLongFunctionThrowsException {
    long applyAsLong(int value) throws Exception;
}
