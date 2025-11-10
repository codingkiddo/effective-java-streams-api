package com.codingkiddo.api.streams;

import com.codingkiddo.api.streams.internal.Parameters;
import com.codingkiddo.api.streams.iterator.LazyIterator;
import com.codingkiddo.api.streams.operator.ObjectArray;

import java.io.Closeable;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Nullable;

import org.jetbrains.annotations.NotNull;


public class Stream<T> implements Closeable {

    private final Iterator<? extends T> iterator;
    private final Parameters params;

    private Stream(Iterator<? extends T> iterator) {
        this(null, iterator);
    }

    private Stream(Iterable<? extends T> iterator) {
        this(null, new LazyIterator<>(iterator));
    }

    Stream(Parameters params, Iterator<? extends T> iterator) {
        this.params = params;
        this.iterator = iterator;
    }

    @NotNull
    public static <T> Stream<T> empty() {
        return of(Collections.<T>emptyList());
    }
    
    @SafeVarargs
	@NotNull
    public static <T> Stream<T> of( @NotNull final T... elements  ) {
    	Objects.requireNonNull(elements); 
    	if ( elements.length == 0 ) {
    		return Stream.<T>empty();
    	}
    	return new Stream<T>(new ObjectArray<T>(elements));
    }

    @NotNull
    public static <T> Stream<T> ofNullable(@Nullable T element) {
    	return ( element == null) ? Stream.<T>empty() : Stream.of(element);
    }
    
    @NotNull
    public static <T> Stream<T> ofNullable(@Nullable T[] array) {
    	return ( array == null) ? Stream.<T>empty() : Stream.of(array);
    }
    
    @NotNull
    public static <K, V> Stream<Map.Entry<K, V>> ofNullable(@Nullable Map<K, V> map) { 
    	return (map == null) ? Stream.<Map.Entry<K, V>>empty() : Stream.of(map);
    }
    
    @NotNull
    public static <T> Stream<T> ofNullable(@Nullable Iterator<? extends T> iterator) {
    	return ( iterator == null ) ? Stream.<T>empty() : Stream.of(iterator);
    }
    
    @NotNull
    public static <T> Stream<T> ofNullable(@Nullable Iterable<? extends T> iterable) {
    	return ( iterable == null ) ? Stream.<T>empty() : Stream.of(iterable);
    }
    
    @NotNull
    public static <K, V> Stream<Map.Entry<K, V>> of(@NotNull Map<K, V> map) {
        Objects.requireNonNull(map);
        return new Stream<Map.Entry<K, V>>(map.entrySet());
    }

    @NotNull
    public static <T> Stream<T> of(@NotNull Iterable<? extends T> iterable) {
        Objects.requireNonNull(iterable);
        return new Stream<T>(iterable);
    }

    @NotNull
    public static <T> Stream<T> of(@NotNull Iterator<? extends T> iterator) {
        Objects.requireNonNull(iterator);
        return new Stream<T>(iterator);
    }

    @Override
    public void close() throws IOException {}
}
