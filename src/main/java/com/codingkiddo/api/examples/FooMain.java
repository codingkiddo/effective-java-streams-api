//package com.codingkiddo.api.examples;
//
//import java.util.function.Function;
//import java.util.function.UnaryOperator;
//
//public abstract class FooMain<T> {
//	public abstract <R> FooMain<R> apply(Function<T, R> fn);
//	public abstract FooMain<T> apply(UnaryOperator<T> uo);
//}


package com.codingkiddo.api.examples;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class FooMain {
	public static void main(String[] args) {
		for ( int i=0; i< 10; i++) {
			System.out.println("================================================================");
			Foo<Integer> foo = new Foo(100);
			Function<Integer, String> fn = t -> "";
			foo.apply(fn);
			foo.apply(t -> "");
			UnaryOperator<Integer> uo = UnaryOperator.identity();
			foo.apply(uo);
			System.out.println("================================================================");
		}
	}
}

class Foo<T> {
	
	T t;
	
	Foo(T t) {
		this.t = t;
	}
	
	public <R> Foo<R> apply(Function<T, R> fn) {
		System.out.println("apply(Function<T, R> fn) called !!!");
		return new Foo(fn.apply(t));
	}
	
//	public Foo<T> apply(UnaryOperator<T> uo) {
//		System.out.println("apply(UnaryOperator<T> uo) called !!!");
//		return new Foo(UnaryOperator.identity());
//	}
}
