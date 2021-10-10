package dev.liambloom.util.function;

import java.util.function.LongToIntFunction;
import dev.liambloom.util.annotation.Unchecked;

@Unchecked(LongToIntFunction.class)
@FunctionalInterface
public interface LongToIntFunctionThrowsException {
    int applyAsInt(long value) throws Exception;
}
