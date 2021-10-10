package dev.liambloom.util;

import java.util.Iterator;

public interface AdvancedIterator<T> extends Iterator<T>, Iterable<T> {
    @Override
    default Iterator<T> iterator() {
        return this;
    }

    T peek();

    static <T> AdvancedIterator<T> of(Iterator<T> iter) {
        return new AdvancedIterator<T>() {
            private T peeked = null;

            @Override
            public T peek() {
                return peeked = iter.next();
            }

            @Override
            public boolean hasNext() {
                return peeked != null || iter.hasNext();
            }

            @Override
            public T next() {
                T r = peek();
                peeked = null;
                return r;
            }
        };
    }
}
