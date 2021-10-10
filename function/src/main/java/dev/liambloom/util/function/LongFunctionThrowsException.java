package dev.liambloom.util.function;

import java.util.function.LongFunction;
import dev.liambloom.util.annotation.Unchecked;

@Unchecked(LongFunction.class)
@FunctionalInterface
public interface LongFunctionThrowsException<R> {
    R apply(long value) throws Exception;
}
