package com.ryan.collection;

import com.ryan.util.Parameters;

import java.util.Collection;
import java.util.Iterator;

/**
 * Static utility methods that operate on or return {@link Bag}s.
 *
 * @author Osman KOCAK
 */
public final class Bags {
    /**
     * The empty {@code Bag}.
     */
    public static final Bag EMPTY_BAG = new EmptyBag();

    /**
     * Returns the empty {@code Bag} for a particular type (type-safe). Note
     * that unlike this method, the like-named static field does not provide
     * type safety.
     *
     * @param <E> the type of the bag's elements.
     * @return the empty {@code Bag}.
     */
    public static <E> Bag<E> emptyBag() {
        return (Bag<E>) EMPTY_BAG;
    }

    private static final class EmptyBag<E> extends AbstractBag<E> {
        @Override
        public int count(E e) {
            return 0;
        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return true;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<E> iterator() {
            return Iterators.emptyIterator();
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            if (a.length > 0) {
                a[0] = null;
            }
            return a;
        }

        @Override
        public boolean add(E e) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean remove(Object o) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            Parameters.checkNotNull(c);
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends E> c) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void clear() {
            throw new UnsupportedOperationException();
        }
    }

    private Bags() {
        /* ... */
    }
}
