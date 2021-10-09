package dev.liambloom.util.function;

import java.util.function.Predicate;

@Unchecked(Predicate.class)
@FunctionalInterface
public interface PredicateThrowsException<T> {
    boolean test(T t) throws Exception;

    default PredicateThrowsException<T> and(PredicateThrowsException<? super T> other) {
        return t -> test(t) && other.test(t);
    }

    default PredicateThrowsException<T> or(PredicateThrowsException<? super T> other) {
        return t -> test(t) || other.test(t);
    }

    default PredicateThrowsException<T> negate() {
        return t -> !test(t);
    }
}
