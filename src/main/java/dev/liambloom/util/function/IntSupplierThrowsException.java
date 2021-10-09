package dev.liambloom.util.function;

import java.util.function.IntSupplier;

@Unchecked(IntSupplier.class)
@FunctionalInterface
public interface IntSupplierThrowsException {
    int getAsInt() throws Exception;
}
