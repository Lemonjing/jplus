package com.ryan.collection;

import java.util.Iterator;

/**
 * Unmodifiable {@code Iterator}. Its {@link Iterator#remove()} method always
 * throws an {@link UnsupportedOperationException}).
 *
 * @param <E> the type of the {@code Iterator}'s elements.
 * @author Osman KOCAK
 */
final class UnmodifiableIterator<E> implements Iterator<E> {
    private final Iterator<E> inner;

    /**
     * Creates a new {@code UnmodifiableIterator}.
     *
     * @param inner the inner iterator.
     */
    UnmodifiableIterator(Iterator<E> inner) {
        this.inner = inner;
    }

    @Override
    public boolean hasNext() {
        return inner.hasNext();
    }

    @Override
    public E next() {
        return inner.next();
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
