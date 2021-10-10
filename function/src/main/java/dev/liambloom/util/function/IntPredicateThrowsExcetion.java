package dev.liambloom.util.function;

import java.util.function.IntPredicate;
import dev.liambloom.util.annotation.Unchecked;

@Unchecked(IntPredicate.class)
@FunctionalInterface
public interface IntPredicateThrowsExcetion {
    boolean test(int value) throws Exception;

    default IntPredicateThrowsExcetion and(IntPredicateThrowsExcetion other) {
        return value -> test(value) && other.test(value);
    }

    default IntPredicateThrowsExcetion or(IntPredicateThrowsExcetion other) {
        return value -> test(value) || other.test(value);
    }

    default IntPredicateThrowsExcetion negate() {
        return value -> !test(value);
    }
}
