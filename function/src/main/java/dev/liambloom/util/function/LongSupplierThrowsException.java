package dev.liambloom.util.function;

import java.util.function.LongSupplier;
import dev.liambloom.util.annotation.Unchecked;

@Unchecked(LongSupplier.class)
@FunctionalInterface
public interface LongSupplierThrowsException {
    long getAsLong() throws Exception;
}
