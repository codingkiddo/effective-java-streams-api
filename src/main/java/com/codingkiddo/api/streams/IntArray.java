package com.codingkiddo.api.streams;

import org.jetbrains.annotations.NotNull;

import com.codingkiddo.api.streams.iterator.PrimitiveIterator;

public class IntArray extends PrimitiveIterator.OfInt {

	private final int[] values;
	private int index;
	
	public IntArray(@NotNull int[] values) {
		this.values = values;
		index = 0;
	}
	
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int nextInt() {
		// TODO Auto-generated method stub
		return 0;
	}

}
