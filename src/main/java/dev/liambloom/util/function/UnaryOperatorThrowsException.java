package dev.liambloom.util.function;

import java.util.function.UnaryOperator;

@Unchecked(UnaryOperator.class)
@FunctionalInterface
public interface UnaryOperatorThrowsException<T> extends FunctionThrowsException<T, T> {
    static <T> UnaryOperator<T> indenty() {
        return t -> t;
    }
}
