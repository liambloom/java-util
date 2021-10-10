package dev.liambloom.util.function;

import java.util.function.DoubleFunction;
import dev.liambloom.util.annotation.Unchecked;

@Unchecked(DoubleFunction.class)
@FunctionalInterface
public interface DoubleFunctionThrowsException<R> {
    R apply(double value) throws Exception;
}
