package dev.liambloom.util.function;

import java.util.function.DoubleSupplier;

@Unchecked(DoubleSupplier.class)
@FunctionalInterface
public interface DoubleSupplierThrowsException {
    double getAsDouble() throws Exception;
}
