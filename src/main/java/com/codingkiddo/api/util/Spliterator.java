package com.codingkiddo.api.util;

import java.util.Comparator;

import com.codingkiddo.api.util.streams.function.Consumer;

public interface Spliterator<T> {

	public static final int ORDERED    = 0x00000010;
    public static final int DISTINCT   = 0x00000001;
    public static final int SORTED     = 0x00000004;
    public static final int SIZED      = 0x00000040;
    public static final int NONNULL    = 0x00000100;
    public static final int IMMUTABLE  = 0x00000400;
    public static final int CONCURRENT = 0x00001000;
    public static final int SUBSIZED = 0x00004000;
    
	boolean tryAdvance(Consumer<? super T> action);
	
	default void forEachRemaining(Consumer<? super T> action) {
		do {} while(tryAdvance(action));
	}
	
	Spliterator<T> trySplit();
	
	long estimateSize();
	
	int characteristics();
	
	default boolean hasCharacteristics(int characteristics) {
		return ( characteristics() & characteristics) == characteristics;
	}
	
	default Comparator<? super T> getComparator() {
		throw new IllegalStateException();
	}
	
	public interface OfPrimitive<T, T_CONSUMEER, 
									T_SPLITT extends Spliterator.OfPrimitive<T, T_CONSUMEER, T_SPLITT>> 
			extends Spliterator<T> {
		
		
	}
}
