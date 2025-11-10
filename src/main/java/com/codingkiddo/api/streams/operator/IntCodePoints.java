package com.codingkiddo.api.streams.operator;

import org.jetbrains.annotations.NotNull;

import com.codingkiddo.api.streams.iterator.PrimitiveIterator;


public class IntCodePoints extends PrimitiveIterator.OfInt {

	private final CharSequence charSequence;
	private final boolean isString;
	
	private int current;
	private int length;
	
	public IntCodePoints(@NotNull CharSequence charSequence) {
		this.charSequence = charSequence;
		this.isString = ( charSequence instanceof String );
		this.current = 0;
		this.length = -1;
    }
	
	private int ensureLength() {
		if ( isString ) {
			if ( length == -1 ) {
				length = charSequence.length();
			}
			return length;
		}
		return charSequence.length();
	}
	
	@Override
	public boolean hasNext() {
		return current < ensureLength();
	}

	@Override
	public int nextInt() {
		return 0;
	}

}
