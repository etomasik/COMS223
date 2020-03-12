package edu.sussex.coms223.lab7;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * The class Main benchmarks worst-case search performance of an OrderedList vs.
 * ArrayList in terms of elapsed time. Note that the ratio of computational
 * efficiency O(n)/O(log n) is different than the ratio of elapsed time.
 */
public class Main {
	enum ListType {
		array, linked
	};

	public static void main(String[] args) {
		benchmark();
		merge();
	}

	static void benchmark() {
		final int ITERATIONS = 10000;
		final int COUNT = 1000;
		final int LAST_VALUE = COUNT - 1; // This is the worst-case value to search for.

		OrderedList<Integer> olist = new OrderedList<>();

		for (int j = 0; j < COUNT; j++) {
			olist.add(j);
		}

		long startMS = System.currentTimeMillis();

		for (int i = 0; i < ITERATIONS; i++) {
			for (int j = 0; j < COUNT; j++) {
				olist.binarySearch(LAST_VALUE);
			}
		}

		final long oltime = System.currentTimeMillis() - startMS;
		System.out.println("OrderedList search elapsed time: " + oltime + " ms");

		for (ListType listType : ListType.values()) {

			List<Integer> list = listType == ListType.array ? new ArrayList<>() : new LinkedList<>();

			for (int j = 0; j < COUNT; j++) {
				list.add(j);
			}

			startMS = System.currentTimeMillis();

			for (int i = 0; i < ITERATIONS; i++) {
				for (int j = 0; j < COUNT; j++) {
					list.contains(LAST_VALUE);
				}
			}

			final long ltime = System.currentTimeMillis() - startMS;

			System.out.println(list.getClass().getName() + " search elapsed time: " + ltime + " ms");
			System.out.println("elapsed time acceleration: " + ltime / oltime + "x");
		}

		System.out.println("N/LOG N: " + COUNT / log2(COUNT));
	}

	static int log2(int n) {
		int sum = 0;
		while (n != 0) {
			n /= 2;
			sum++;
		}
		return sum;
	}

	static void dump(OrderedList<Integer> l, String name) {
		System.out.println("list " + name);
		Integer i = l.first();
		while (i != null) {
			System.out.println("\t" + i);
			i = l.next();
		}
	}

	static void merge() {
		final int COUNT = 3;

		OrderedList<Integer> l1 = new OrderedList<Integer>();
		OrderedList<Integer> l2 = new OrderedList<Integer>();

		Random rng = new Random();

		for (int i = 0; i < COUNT; i++) {
			l1.insert(rng.nextInt(100));
			l2.insert(rng.nextInt(100));
		}

		dump(l1, "l1");
		dump(l2, "l2");

		OrderedList<Integer> merged = new OrderedList<>();
		int l1i = 0, l2i = 0;

		while (l1i < l1.size() && l2i < l2.size()) {
			int comparison = l1.get(l1i).compareTo(l2.get(l2i));
			if (comparison == 0) {
				merged.add(l1.get(l1i));
				l1i++;
				l2i++;
			} else if (comparison < 0) // l1 < l2
				merged.add(l1.get(l1i++));
			else // l1 > l2
				merged.add(l2.get(l2i++));
		}

		while (l1i < l1.size())
			merged.add(l1.get(l1i++));

		while (l2i < l2.size())
			merged.add(l2.get(l2i++));

		dump(merged, "merged");
	}

}
