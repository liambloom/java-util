package dev.liambloom.util.function;

import java.util.function.LongToIntFunction;

@Unchecked(LongToIntFunction.class)
public interface LongToIntFunctionThrowsException {
    int applyAsInt(long value) throws Exception;
}
