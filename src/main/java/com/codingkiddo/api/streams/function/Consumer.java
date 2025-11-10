package com.codingkiddo.api.streams.function;

import java.util.Objects;

import org.jetbrains.annotations.NotNull;

public interface Consumer<T> {

	void accept(T t);
	
	class Util {
		private Util() {
		}
		public static <T> Consumer<T> andThen(
				@NotNull final Consumer<? super T> c1, 
				@NotNull final Consumer<? super T> c2) {
			Objects.requireNonNull(c1, "c1");
			Objects.requireNonNull(c2, "c2");
			return new Consumer<T>() {
				@Override
				public void accept(T t) {
					c1.accept(t);
					c2.accept(t);
				}
			};
		}
		
		public static <T> Consumer<T> safe(
				@NotNull final ThrowableConsumer<? super T, Throwable> throwableConsumer
				) {
			return safe(throwableConsumer, null);
		}
		
		public static <T> Consumer<T> safe(
				@NotNull final ThrowableConsumer<? super T, Throwable> throwableConsumer, 
				@NotNull final Consumer<? super T> onFailuerConsumer) {
			Objects.requireNonNull(throwableConsumer);
			return new Consumer<T>() {
				@Override
				public void accept(T t) {
					Objects.requireNonNull(throwableConsumer);
					try {
						throwableConsumer.accept(t);
					} catch (Throwable e) {
						if ( onFailuerConsumer != null ) {
							onFailuerConsumer.accept(t);
						}
					}
				}
			};
		}
	}
	
}
