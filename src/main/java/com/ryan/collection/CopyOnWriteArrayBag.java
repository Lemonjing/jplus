package com.ryan.collection;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * A thread-safe variant of {@link ArrayBag} based on {@link CopyOnWriteArrayList}.
 * This implementation provides a predictable iteration order which is the order
 * in which elements were inserted into the bag (insertion-order). Also, this
 * implementation accepts {@code null} elements.
 *
 * @param <E> the type of the elements in the bag.
 * @author Osman KOCAK
 * @since 0.3
 */
public final class CopyOnWriteArrayBag<E>
        extends AbstractBag<E> implements ConcurrentBag<E>, Serializable {
    private static final long serialVersionUID = 8401510673920814526L;

    private final CopyOnWriteArrayList<E> entries;

    /**
     * Creates a new empty {@code CopyOnWriteArrayBag}.
     */
    public CopyOnWriteArrayBag() {
        this.entries = new CopyOnWriteArrayList<E>();
    }

    /**
     * Creates a new {@code CopyOnWriteArrayBag} using the elements
     * contained in the given {@code Collection}.
     *
     * @param c the collection to use to populate the created bag.
     * @throws NullPointerException if {@code c} is {@code null}.
     */
    public CopyOnWriteArrayBag(Collection<? extends E> c) {
        this.entries = new CopyOnWriteArrayList<E>(c);
    }

    /**
     * Creates a new {@code CopyOnWriteArrayBag} using the elements
     * contained in the given {@code Iterable}.
     *
     * @param i the iterable to use to populate the created bag.
     * @throws NullPointerException if {@code i} is {@code null}.
     */
    public CopyOnWriteArrayBag(Iterable<? extends E> i) {
        this();
        for (E e : i) {
            add(e);
        }
    }

    /**
     * Creates a new {@code CopyOnWriteArrayBag} using the elements
     * contained in the given {@code Iterator}.
     *
     * @param i the iterator to use to populate the created bag.
     * @throws NullPointerException if {@code i} is {@code null}.
     */
    public CopyOnWriteArrayBag(Iterator<? extends E> i) {
        this();
        while (i.hasNext()) {
            add(i.next());
        }
    }

    /**
     * Creates a new {@code CopyOnWriteArrayBag} using the elements
     * contained in the given array.
     *
     * @param elements the elements to use to populate the created bag.
     * @throws NullPointerException if {@code elements} is {@code null}.
     */
    public CopyOnWriteArrayBag(E... elements) {
        this(Arrays.asList(elements));
    }

    @Override
    public Iterator<E> iterator() {
        return entries.iterator();
    }

    @Override
    public int size() {
        return entries.size();
    }

    @Override
    public void clear() {
        entries.clear();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return entries.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return entries.removeAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return entries.addAll(c);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return entries.containsAll(c);
    }

    @Override
    public boolean remove(Object o) {
        return entries.remove(o);
    }

    @Override
    public boolean add(E e) {
        return entries.add(e);
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return entries.toArray(a);
    }

    @Override
    public Object[] toArray() {
        return entries.toArray();
    }

    @Override
    public boolean contains(Object o) {
        return entries.contains(o);
    }

    @Override
    public boolean isEmpty() {
        return entries.isEmpty();
    }

    @Override
    public int addAllAbsent(Collection<? extends E> c) {
        return entries.addAllAbsent(c);
    }

    @Override
    public boolean addIfAbsent(E e) {
        return entries.addIfAbsent(e);
    }
}
