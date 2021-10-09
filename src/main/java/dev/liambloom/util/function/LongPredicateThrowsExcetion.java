package dev.liambloom.util.function;

import java.util.function.LongPredicate;

@Unchecked(LongPredicate.class)
@FunctionalInterface
public interface LongPredicateThrowsExcetion {
    boolean test(long value) throws Exception;

    default LongPredicateThrowsExcetion and(LongPredicateThrowsExcetion other) {
        return value -> test(value) && other.test(value);
    }

    default LongPredicateThrowsExcetion or(LongPredicateThrowsExcetion other) {
        return value -> test(value) || other.test(value);
    }

    default LongPredicateThrowsExcetion negate() {
        return value -> !test(value);
    }
}
