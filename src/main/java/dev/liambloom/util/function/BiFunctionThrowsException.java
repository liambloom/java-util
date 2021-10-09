package dev.liambloom.util.function;

import java.util.function.BiFunction;

@Unchecked(BiFunction.class)
@FunctionalInterface
public interface BiFunctionThrowsException<T, U, R> {
    R apply(T t, U u) throws Exception;

    default <V> BiFunctionThrowsException<T, U, V> andThen(FunctionThrowsException<? super R, ? extends V> after) {
        return (t, u) -> after.apply(apply(t, u));
    }
}
