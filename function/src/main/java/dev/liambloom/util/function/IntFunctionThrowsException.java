package dev.liambloom.util.function;

import java.util.function.IntFunction;
import dev.liambloom.util.annotation.Unchecked;

@Unchecked(IntFunction.class)
@FunctionalInterface
public interface IntFunctionThrowsException<R> {
    R apply(int value) throws Exception;
}
