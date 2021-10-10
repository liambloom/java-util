package dev.liambloom.util.function;

import java.util.function.DoubleConsumer;
import dev.liambloom.util.annotation.Unchecked;

@Unchecked(DoubleConsumer.class)
@FunctionalInterface
public interface DoubleConsumerThrowsException {
    void accept(double value) throws Exception;

    default DoubleConsumerThrowsException andThen(DoubleConsumerThrowsException after) {
        return value -> {
            accept(value);
            after.accept(value);
        };
    }
}
