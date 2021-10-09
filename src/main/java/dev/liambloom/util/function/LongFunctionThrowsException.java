package dev.liambloom.util.function;

import java.util.function.LongFunction;

@Unchecked(LongFunction.class)
@FunctionalInterface
public interface LongFunctionThrowsException<R> {
    R apply(long value) throws Exception;
}
