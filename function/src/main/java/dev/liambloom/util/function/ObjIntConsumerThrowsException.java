package dev.liambloom.util.function;

import java.util.function.ObjIntConsumer;
import dev.liambloom.util.annotation.Unchecked;

@Unchecked(ObjIntConsumer.class)
@FunctionalInterface
public interface ObjIntConsumerThrowsException<T> {
    void accept(T t, int value) throws Exception;
}
