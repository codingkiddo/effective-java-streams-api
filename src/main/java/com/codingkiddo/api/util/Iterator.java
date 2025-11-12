package com.codingkiddo.api.util;

import java.util.Objects;

import com.codingkiddo.api.util.streams.function.Consumer;

public interface Iterator<E> {

	boolean hasNext();
	E next();
	default void remove() {
        throw new UnsupportedOperationException("remove");
    }
	default void forEachRemaining(Consumer<? super E> action) {
		Objects.requireNonNull(action);
		while(hasNext()) {
			action.accept(next());
		}
	}
}
