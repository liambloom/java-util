package dev.liambloom.util.function;

import java.util.function.BiConsumer;

@Unchecked(BiConsumer.class)
@FunctionalInterface
public interface BiConsumerThrowsException<T, U> {
    void accept(T t, U u) throws Exception;

    default BiConsumerThrowsException<T, U> andThen(BiConsumerThrowsException<? super T, ? super U> after) {
        return (t, u) -> {
            accept(t, u);
            after.accept(t, u);
        };
    }
}
