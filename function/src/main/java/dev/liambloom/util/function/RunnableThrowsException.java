package dev.liambloom.util.function;

import dev.liambloom.util.annotation.Unchecked;

@FunctionalInterface
@Unchecked(Runnable.class)
public interface RunnableThrowsException {
    void run() throws Exception;
}
