package dev.liambloom.util.function;

import java.util.function.BooleanSupplier;

@Unchecked(BooleanSupplier.class)
@FunctionalInterface
public interface BooleanSupplierThrowsException {
    boolean getAsBoolean() throws Exception;
}
