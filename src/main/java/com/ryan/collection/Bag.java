package com.ryan.collection;

import java.util.Collection;
import java.util.Set;

/**
 * A collection that supports order-independent equality, like {@link Set}, but,
 * which may have duplicate elements.
 *
 * @param <E> the type of the elements in the bag.
 *
 * @see Collection
 * @see AbstractBag
 * @see ArrayBag
 * @see HashBag
 * @see ConcurrentHashBag
 * @see CopyOnWriteArrayBag
 * @see ImmutableBag
 * @see Bags
 *
 * @author Osman KOCAK
 */
public interface Bag<E> extends Collection<E>
{
	/**
	 * Returns the count of the given element in this bag.
	 *
	 * @param e the object to count.
	 *
	 * @return the number of occurrences of {@code e} in this bag.
	 */
	int count(E e);
}
