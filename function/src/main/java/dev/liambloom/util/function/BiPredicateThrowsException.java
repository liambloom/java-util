package dev.liambloom.util.function;

import java.util.function.BiPredicate;
import dev.liambloom.util.annotation.Unchecked;

@Unchecked(BiPredicate.class)
@FunctionalInterface
public interface BiPredicateThrowsException<T, U> {
    boolean test(T t, U u) throws Exception;

    default BiPredicateThrowsException<T, U> and(BiPredicateThrowsException<? super T, ? super U> other) {
        return (t, u) -> test(t, u) && other.test(t, u);
    }

    default BiPredicateThrowsException<T, U> or(BiPredicateThrowsException<? super T, ? super U> other) {
        return (t, u) -> test(t, u) || other.test(t, u);
    }

    default BiPredicateThrowsException<T, U> negate() {
        return (t, u) -> !test(t, u);
    }
}
