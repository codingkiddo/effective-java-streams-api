package com.codingkiddo.api.streams.function;

import java.util.Objects;

import org.jetbrains.annotations.NotNull;

public interface Function<T, R> {

	R apply(T t);
	
	class Util {
	
		private Util() { }
		
		public static <T, R, V> Function<T, V> andThen(
                @NotNull final Function<? super T, ? extends R> f1,
                @NotNull final Function<? super R, ? extends V> f2) {
            Objects.requireNonNull(f1, "f1");
            Objects.requireNonNull(f2, "f2");
            return new Function<T, V>() {
                @Override
                public V apply(T t) {
                    return f2.apply(f1.apply(t));
                }
            };
        }
	}
	
}
