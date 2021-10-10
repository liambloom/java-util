package dev.liambloom.util.function;

import java.util.Comparator;
import java.util.function.BinaryOperator;
import dev.liambloom.util.annotation.Unchecked;

@Unchecked(BinaryOperator.class)
@FunctionalInterface
public interface BinaryOperatorThorwsException<T> extends BiFunctionThrowsException<T, T, T> {
    static <T> BinaryOperatorThorwsException<T> minBy(Comparator<? super T> comparator) {
        return (t, u) -> comparator.compare(t, u) <= 0 ? t : u;
    }

    static <T> BinaryOperatorThorwsException<T> maxBy(Comparator<? super T> comparator) {
        return (t, u) -> comparator.compare(t, u) >= 0 ? t : u;
    }
}
