package com.codingkiddo.api.util;

import java.util.Objects;

import com.codingkiddo.api.util.streams.function.Predicate;

public interface Collection<E>  extends Iterable<E> {

	int size();
	boolean isEmpty();
	boolean contains(Object o);
	Iterator<E> iterator();
	
	Object[] toArray();
	<T> T[] toArray(T[] a);
	
	boolean add(E e);
	boolean remove(Object o);
	
	boolean containsAll(Collection<?> c);
	
	boolean addAll(Collection<? extends E> c);
	boolean removeAll(Collection<?> c);
	
	
	default boolean removeIf(Predicate<? super E> filter) {
		Objects.requireNonNull(filter);
		boolean removed = false;
		final Iterator<E> each = iterator();
		while(each.hasNext()) {
			if (filter.test(each.next())) {
				each.remove();
				removed = true;
			}
		}
		return removed;
	}
	
	
	boolean retainAll(Collection<?> c);
	void clear();
	
	boolean equals(Object o);
	int hashCode();
}
