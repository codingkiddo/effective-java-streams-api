package com.codingkiddo.api.streams.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public abstract class LsaIterator<T> implements Iterator<T> {

	@Override
	public void remove() {
		throw new UnsupportedOperationException("remove not supported");
	}

	@Override
	public T next() {
		if ( !hasNext() ) {
			throw new NoSuchElementException();
		}
		return nextIteration();
	}

	public abstract T nextIteration();
}
