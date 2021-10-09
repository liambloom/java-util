package dev.liambloom.util.function;

@FunctionalInterface
@Unchecked(Runnable.class)
public interface RunnableThrowsException {
    void run() throws Exception;
}
