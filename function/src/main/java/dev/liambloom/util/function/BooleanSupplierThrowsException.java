package dev.liambloom.util.function;

import java.util.function.BooleanSupplier;
import dev.liambloom.util.annotation.Unchecked;

@Unchecked(BooleanSupplier.class)
@FunctionalInterface
public interface BooleanSupplierThrowsException {
    boolean getAsBoolean() throws Exception;
}
