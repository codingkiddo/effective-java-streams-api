package com.codingkiddo.api.streams.iterator;

import java.util.Iterator;

public class PrimitiveIterator {

	private PrimitiveIterator() { }
	
	public abstract static class OfInt implements Iterator<Integer> {
		public abstract int nextInt();
		@Override
        public Integer next() {
            return nextInt();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove");
        }
	}
	public abstract static class OfLong implements Iterator<Long> {

        public abstract long nextLong();

        @Override
        public Long next() {
            return nextLong();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove");
        }
    }
	public abstract static class OfDouble implements Iterator<Double> {

        public abstract double nextDouble();

        @Override
        public Double next() {
            return nextDouble();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException("remove");
        }
    }
}
