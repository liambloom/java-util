package dev.liambloom.util.function;

import java.util.function.LongSupplier;

@Unchecked(LongSupplier.class)
@FunctionalInterface
public interface LongSupplierThrowsException {
    long getAsLong() throws Exception;
}
