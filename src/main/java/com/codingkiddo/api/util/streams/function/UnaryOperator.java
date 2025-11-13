package com.codingkiddo.api.util.streams.function;

@FunctionalInterface
public interface UnaryOperator<T> extends Function<T, T> {
	static <T> UnaryOperator<T> identity() {
		return t -> t;
	}
}
