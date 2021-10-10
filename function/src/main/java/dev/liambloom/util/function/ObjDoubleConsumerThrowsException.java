package dev.liambloom.util.function;

import java.util.function.ObjDoubleConsumer;
import dev.liambloom.util.annotation.Unchecked;

@Unchecked(ObjDoubleConsumer.class)
@FunctionalInterface
public interface ObjDoubleConsumerThrowsException<T> {
    void accept(T t, double value) throws Exception;
}
