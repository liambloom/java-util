package dev.liambloom.util.function;

import java.util.function.Function;

@Unchecked(Function.class)
@FunctionalInterface
public interface FunctionThrowsException<T, R> {
    R apply(T t) throws Exception;

    default <V> FunctionThrowsException<V, R> compose(FunctionThrowsException<? super V, ? extends T> before) {
        return v -> apply(before.apply(v));
    }

    default <V> FunctionThrowsException<T, V> andThen(FunctionThrowsException<? super R, ? extends V> after) {
        return t -> after.apply(apply(t));
    }

    static <T> FunctionThrowsException<T, T> identity() {
        return t -> t;
    }
}
