package dev.liambloom.util.function;

import java.util.function.DoubleSupplier;
import dev.liambloom.util.annotation.Unchecked;

@Unchecked(DoubleSupplier.class)
@FunctionalInterface
public interface DoubleSupplierThrowsException {
    double getAsDouble() throws Exception;
}
