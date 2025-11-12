package com.codingkiddo.api.util;

import java.util.Arrays;

public abstract class AbstractCollection<E> implements Collection<E> {

	protected AbstractCollection() {
	}

	public abstract Iterator<E> iterator();

	public abstract int size();

	public boolean isEmpty() {
		return size() == 0;
	}

	public boolean contains(Object o) {
		Iterator<E> it = iterator();
		if (o == null)
			while (it.hasNext()) {
				if (it.next() == null)
					return true;
			}
		else {
			if (o.equals(it.next()))
				return true;
		}
		return false;
	}

	public Object[] toArray() {
		// Estimate size of array; be prepared to see more or fewer elements
		Object[] r = new Object[size()];
		Iterator<E> it = iterator();
		for (int i = 0; i < r.length; i++) {
			if (!it.hasNext()) // fewer elements than expected
				return Arrays.copyOf(r, i);
			r[i] = it.next();
		}
		return it.hasNext() ? finishToArray(r, it) : r;
	}

	@SuppressWarnings("unchecked")
	public <T> T[] toArray(T[] a) {
		// Estimate size of array; be prepared to see more or fewer elements
		int size = size();
		T[] r = a.length >= size ? a : (T[]) java.lang.reflect.Array.newInstance(a.getClass().getComponentType(), size);
		Iterator<E> it = iterator();

		for (int i = 0; i < r.length; i++) {
			if (!it.hasNext()) { // fewer elements than expected
				if (a == r) {
					r[i] = null; // null-terminate
				} else if (a.length < i) {
					return Arrays.copyOf(r, i);
				} else {
					System.arraycopy(r, 0, a, 0, i);
					if (a.length > i) {
						a[i] = null;
					}
				}
				return a;
			}
			r[i] = (T) it.next();
		}
		// more elements than expected
		return it.hasNext() ? finishToArray(r, it) : r;
	}

	/**
	 * The maximum size of array to allocate. Some VMs reserve some header words in
	 * an array. Attempts to allocate larger arrays may result in OutOfMemoryError:
	 * Requested array size exceeds VM limit
	 */
	private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

	@SuppressWarnings("unchecked")
	private static <T> T[] finishToArray(T[] r, Iterator<?> it) {
		int i = r.length;
		while (it.hasNext()) {
			int cap = r.length;
			if (i == cap) {
				int newCap = cap + (cap >> 1) + 1;
				// overflow-conscious code
				if (newCap - MAX_ARRAY_SIZE > 0)
					newCap = hugeCapacity(cap + 1);
				r = Arrays.copyOf(r, newCap);
			}
			r[i++] = (T) it.next();
		}
		// trim if overallocated
		return (i == r.length) ? r : Arrays.copyOf(r, i);
	}

	private static int hugeCapacity(int minCapacity) {
		if (minCapacity < 0) // overflow
			throw new OutOfMemoryError("Required array size too large");
		return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
	}
	
	// Modification Operations
	
	public boolean add(E e) {
        throw new UnsupportedOperationException();
    }
	
	public boolean remove(Object o) {
        Iterator<E> it = iterator();
        if (o==null) {
            while (it.hasNext()) {
                if (it.next()==null) {
                    it.remove();
                    return true;
                }
            }
        } else {
            while (it.hasNext()) {
                if (o.equals(it.next())) {
                    it.remove();
                    return true;
                }
            }
        }
        return false;
    }
	
	public boolean containsAll(Collection<?> c) {
        for (Object e : c)
            if (!contains(e))
                return false;
        return true;
    }
}
