package dev.liambloom.util.function;

import java.util.function.UnaryOperator;
import dev.liambloom.util.annotation.Unchecked;

@Unchecked(UnaryOperator.class)
@FunctionalInterface
public interface UnaryOperatorThrowsException<T> extends FunctionThrowsException<T, T> {
    static <T> UnaryOperator<T> indenty() {
        return t -> t;
    }
}
