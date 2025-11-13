package com.codingkiddo.api.util.streams.function;

import java.util.Objects;

public interface Function<T, R> {

	R apply(T t);
	
	default <V> Function<V, R> compose(Function<? super V, ? extends T> before) {
		Objects.requireNonNull(before);
		return (V v) -> apply(before.apply(v));
	}
	
	
}
