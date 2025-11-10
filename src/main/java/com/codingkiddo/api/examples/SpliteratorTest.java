package com.codingkiddo.api.examples;

import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SpliteratorTest {

	private static Article article = new Article(Arrays.asList(new Author("Ahmad", 0), new Author("Eugen", 0), new Author("Alice", 1),
			new Author("Alice", 1), new Author("Mike", 0), new Author("Alice", 1), new Author("Mike", 0),
			new Author("Alice", 1), new Author("Mike", 0), new Author("Alice", 1), new Author("Mike", 0),
			new Author("Mike", 0), new Author("Alice", 1), new Author("Mike", 0), new Author("Alice", 1),
			new Author("Mike", 0), new Author("Alice", 1), new Author("Mike", 0), new Author("Alice", 1),
			new Author("Mike", 0), new Author("Michał", 0), new Author("Loredana", 1)), 0);;

	public static void main(String[] args) {
		givenAStreamOfArticles_whenProcessedSequentiallyWithSpliterator_ProducessRightOutput();
		givenAStreamOfArticle_whenProcessedUsingTrySplit_thenSplitIntoEqualHalf();
	}

	private static void givenAStreamOfArticles_whenProcessedSequentiallyWithSpliterator_ProducessRightOutput() {
		List<Article> articles = Stream.generate(() -> new Article("Java"))
	            .limit(35000)
	            .collect(Collectors.toList());

	        Spliterator<Article> spliterator = articles.spliterator();
	        spliterator.tryAdvance(a -> a.getName().concat("-21"));

	}
	
	private static void givenAStreamOfArticle_whenProcessedUsingTrySplit_thenSplitIntoEqualHalf() {
		List<Article> articles = Stream.generate(()-> new Article("Java")).limit(4000).collect(Collectors.toList());
		Spliterator<Article> split1 = articles.spliterator();
		System.out.println("Size: " + split1.estimateSize());
		
		Spliterator<Article> split2 = split1.trySplit();
		System.out.println("Characteristics: " + split1.characteristics());
		System.out.println("Characteristics: " + split2.characteristics());
	}
}
