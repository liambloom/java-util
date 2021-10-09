package dev.liambloom.util.function;

import java.util.function.ObjLongConsumer;

@Unchecked(ObjLongConsumer.class)
@FunctionalInterface
public interface ObjLongConsumerThrowsException<T> {
    void accept(T t, long value) throws Exception;
}
