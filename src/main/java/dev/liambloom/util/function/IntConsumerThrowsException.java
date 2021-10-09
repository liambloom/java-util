package dev.liambloom.util.function;

import java.util.function.IntConsumer;

@Unchecked(IntConsumer.class)
@FunctionalInterface
public interface IntConsumerThrowsException {
    void accept(int value) throws Exception;

    default IntConsumerThrowsException andThen(IntConsumerThrowsException after) {
        return value -> {
            accept(value);
            after.accept(value);
        };
    }
}
