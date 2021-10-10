package dev.liambloom.util.function;

import java.util.function.Supplier;
import dev.liambloom.util.annotation.Unchecked;

@Unchecked(Supplier.class)
@FunctionalInterface
public interface SupplierThrowsException<T> {
    T get() throws Exception;
}
