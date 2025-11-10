package com.codingkiddo.api.streams.function;

public interface ThrowableConsumer<T, E extends Throwable> {
	void accept(T value) throws E;
}
