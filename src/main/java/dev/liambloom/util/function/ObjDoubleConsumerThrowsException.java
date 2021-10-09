package dev.liambloom.util.function;

import java.util.function.ObjDoubleConsumer;

@Unchecked(ObjDoubleConsumer.class)
public interface ObjDoubleConsumerThrowsException<T> {
    void accept(T t, double value) throws Exception;
}
