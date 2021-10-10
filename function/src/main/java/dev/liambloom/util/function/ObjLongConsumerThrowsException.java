package dev.liambloom.util.function;

import java.util.function.ObjLongConsumer;
import dev.liambloom.util.annotation.Unchecked;

@Unchecked(ObjLongConsumer.class)
@FunctionalInterface
public interface ObjLongConsumerThrowsException<T> {
    void accept(T t, long value) throws Exception;
}
