package dev.liambloom.util.function;

import java.util.function.BiPredicate;
import java.util.function.DoublePredicate;

@Unchecked(DoublePredicate.class)
@FunctionalInterface
public interface DoublePredicateThrowsExcetion {
    boolean test(double value) throws Exception;

    default DoublePredicateThrowsExcetion and(DoublePredicateThrowsExcetion other) {
        return value -> test(value) && other.test(value);
    }

    default DoublePredicateThrowsExcetion or(DoublePredicateThrowsExcetion other) {
        return value -> test(value) || other.test(value);
    }

    default DoublePredicateThrowsExcetion negate() {
        return value -> !test(value);
    }
}
