package com.codingkiddo.api.streams;

import java.io.Closeable;
import java.io.IOException;
import java.util.Objects;

import org.jetbrains.annotations.NotNull;

import com.codingkiddo.api.streams.internal.Parameters;
import com.codingkiddo.api.streams.iterator.PrimitiveIterator;

public class IntStream implements  Closeable {

	private final PrimitiveIterator.OfInt iterator;
    private final Parameters params;

    private IntStream(PrimitiveIterator.OfInt iterator) {
        this(null, iterator);
    }

    IntStream(Parameters params, PrimitiveIterator.OfInt iterator) {
        this.params = params;
        this.iterator = iterator;
    }
    
	private static final IntStream EMPTY = new IntStream(new PrimitiveIterator.OfInt() {
        @Override
        public int nextInt() {
            return 0;
        }

        @Override
        public boolean hasNext() {
            return false;
        }
    });
	
	@NotNull
    public static IntStream empty() {
        return EMPTY;
    }
	
	@NotNull
	public static IntStream of(@NotNull PrimitiveIterator.OfInt iterator) {
		Objects.requireNonNull(iterator);
		return new IntStream(iterator);
	}
	
	@NotNull 
	public static IntStream of(@NotNull final int... values) {
		Objects.requireNonNull(values);
		if ( values.length == 0 ) {
			return IntStream.empty();
		}
		return new IntStream(new IntArray(values));
	}
	
	@NotNull
	public static IntStream of(final int t) {
		return new IntStream(new IntArray(new int[] {t}));
	}
	
	
	
	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

}
