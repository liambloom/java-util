package dev.liambloom.util.function;

import java.util.function.LongConsumer;
import dev.liambloom.util.annotation.Unchecked;

@Unchecked(LongConsumer.class)
@FunctionalInterface
public interface LongConsumerThrowsException {
    void accept(long value) throws Exception;

    default LongConsumerThrowsException andThen(LongConsumerThrowsException after) {
        return value -> {
            accept(value);
            after.accept(value);
        };
    }
}
