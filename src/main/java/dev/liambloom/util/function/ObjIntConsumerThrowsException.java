package dev.liambloom.util.function;

import java.util.function.ObjIntConsumer;

@Unchecked(ObjIntConsumer.class)
@FunctionalInterface
public interface ObjIntConsumerThrowsException<T> {
    void accept(T t, int value) throws Exception;
}
