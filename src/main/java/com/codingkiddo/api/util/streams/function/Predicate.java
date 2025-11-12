package com.codingkiddo.api.util.streams.function;

import java.util.Objects;

public interface Predicate<T> {

	boolean test(T t);
	
	default Predicate<T> and(Predicate<? super T> other) {
		Objects.requireNonNull(other);
		return (t) -> test(t) && other.test(t);
	}
	
	default Predicate<T> negate() {
		return (t) -> !test(t);
	}
	
	default Predicate<T> or(Predicate<? super T> other) {
		Objects.requireNonNull(other);
		return (t) -> (test(t) || other.test(t));
	}
	
	static <T> Predicate<T> isEquals(Object targetRef) {
		return ( null == targetRef) ? Objects::isNull : object -> targetRef.equals(object);
	}
}
