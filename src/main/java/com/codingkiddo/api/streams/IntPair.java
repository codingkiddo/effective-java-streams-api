package com.codingkiddo.api.streams;

public class IntPair<T> {

	private final int first;
    private final T second;

    public IntPair(int first, T second) {
        this.first = first;
        this.second = second;
    }
    
    public int getFirst() {
        return first;
    }
    
    public T getSecond() {
        return second;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.first;
        hash = 97 * hash + (this.second != null ? this.second.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (getClass() != obj.getClass())
            return false;
        final IntPair<?> other = (IntPair<?>) obj;
        if (this.first != other.first)
            return false;
        return !(this.second != other.second &&
                (this.second == null || !this.second.equals(other.second)));
    }

    @Override
    public String toString() {
        return "IntPair[" + first + ", " + second + ']';
    }
	
}
