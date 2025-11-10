package com.codingkiddo.api.streams.function;

import org.jetbrains.annotations.NotNull;

public interface BiConsumer<T, U> {

	void accept(T value1, U value2);
	
	class Util {
		private Util() {}
		
		public static <T, U> BiConsumer<T, U> andThen(
				@NotNull final BiConsumer<? super T, ? super U> c1,
				@NotNull final BiConsumer<? super T, ? super U> c2
				) {
			return new BiConsumer<T, U>() {
				@Override
				public void accept(T value1, U value2) {
					c1.accept(value1, value2);
					c2.accept(value1, value2);
				}
			};
		}
	}
}
