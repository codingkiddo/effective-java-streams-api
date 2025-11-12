package com.codingkiddo.api.util.streams.function;

import java.util.Objects;

import org.jetbrains.annotations.NotNull;

public interface Function<T, R> {

	R apply(T t);
	
	
	
}
