package dev.liambloom.util.function;

import java.util.function.Supplier;

@Unchecked(Supplier.class)
@FunctionalInterface
public interface SupplierThrowsException<T> {
    T get() throws Exception;
}
