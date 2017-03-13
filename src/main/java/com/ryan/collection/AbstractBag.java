package com.ryan.collection;

import java.util.AbstractCollection;

/**
 * Abstract skeleton implementation of the {@link Bag} interface. If you want to
 * provide your own {@code Bag} implementation, it is more than highly adviced
 * that you extend this abstract base class.
 *
 * @param <E> the type of the elements in the bag.
 *
 * @author Osman KOCAK
 */
public abstract class AbstractBag<E> extends AbstractCollection<E> implements Bag<E>
{
	@Override
	public int count(E e)
	{
		int count = 0;
		for (E entry : this) {
			if (e == null ? entry == null : e.equals(entry)) {
				count++;
			}
		}
		return count;
	}

	@Override
	public boolean equals(Object o)
	{
		if (o == this) {
			return true;
		}
		if (!(o instanceof Bag)) {
			return false;
		}
		Bag bag = (Bag) o;
		if (size() == bag.size()) {
			for (E entry : this) {
				if (count(entry) != bag.count(entry)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	@Override
	public int hashCode()
	{
		int hash = 1;
		for (E entry : this) {
			hash = 31 * hash + count(entry);
		}
		return hash;
	}
}
