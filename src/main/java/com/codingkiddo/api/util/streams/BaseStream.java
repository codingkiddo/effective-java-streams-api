package com.codingkiddo.api.util.streams;

import java.util.Iterator;
import java.util.Spliterator;

public interface BaseStream<T, S extends BaseStream<T, S>> extends AutoCloseable {

	Iterator<T> iterator();
	Spliterator<T> spliterator();
}
