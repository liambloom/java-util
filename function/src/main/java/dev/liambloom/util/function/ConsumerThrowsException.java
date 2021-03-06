package dev.liambloom.util.function;

import java.util.function.Consumer;
import dev.liambloom.util.annotation.Unchecked;

@Unchecked(Consumer.class)
@FunctionalInterface
public interface ConsumerThrowsException<T> {
    void accept(T t) throws Exception;

    default ConsumerThrowsException<T> andThen(Consumer<? super T> after) {
        return t -> {
            accept(t);
            after.accept(t);
        };
    }
}
