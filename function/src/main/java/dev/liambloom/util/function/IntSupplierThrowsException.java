package dev.liambloom.util.function;

import java.util.function.IntSupplier;
import dev.liambloom.util.annotation.Unchecked;

@Unchecked(IntSupplier.class)
@FunctionalInterface
public interface IntSupplierThrowsException {
    int getAsInt() throws Exception;
}
