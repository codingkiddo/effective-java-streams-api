package com.codingkiddo.api.streams.operator;

import org.jetbrains.annotations.NotNull;

import com.codingkiddo.api.streams.iterator.LsaIterator;

public class ObjectArray<T> extends LsaIterator<T> {

	private final T[] elements;
	private int index;
	
	public ObjectArray(@NotNull T[] elements) {
		this.elements = elements;
		index = 0;
	}
	
	@Override
	public boolean hasNext() {
		return index < elements.length;
	}

	@Override
	public T nextIteration() {
		return elements[index++];
	}

}
