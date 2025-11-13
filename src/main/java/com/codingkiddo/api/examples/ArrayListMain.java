package com.codingkiddo.api.examples;

import com.codingkiddo.api.util.ArrayList;
import com.codingkiddo.api.util.List;

public class ArrayListMain {

	public static final int ORDERED    = 0x00000010;
    public static final int DISTINCT   = 0x00000001;
    public static final int SORTED     = 0x00000004;
    public static final int SIZED      = 0x00000040;
    public static final int NONNULL    = 0x00000100;
    public static final int IMMUTABLE  = 0x00000400;
    public static final int CONCURRENT = 0x00001000;
    public static final int SUBSIZED = 0x00004000;

    
	public static void main(String[] args) {

		List<Integer> list = new ArrayList<>();
		list.add(100); list.add(200); list.add(300); list.add(400);
 		System.out.println(list);
 		
 		
 		System.out.println("DISTINCT " + DISTINCT);
 		System.out.println("SORTED " + SORTED);
 		System.out.println("ORDERED " + ORDERED);
 		System.out.println("SIZED " + SIZED);
 		System.out.println("NONNULL " + NONNULL);
 		System.out.println("IMMUTABLE " + IMMUTABLE);
 		System.out.println("CONCURRENT " + CONCURRENT);
 		System.out.println("SUBSIZED " + SUBSIZED);
 		
	}

}
