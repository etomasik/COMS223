package edu.sussex.coms223.lab4;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BubbleSort {

	public static void main(String[] args) {
		final int COUNT = 10;
		Integer[] array = new Integer[COUNT];

		for (int j = 0; j < COUNT; j++)
			array[j] = j;

		// Best case
		Arrays.sort(array);
		int comparisons = sort(array);
		System.out.println("best case (" + (COUNT - 1) + "): comparisons = " + comparisons);

		// Worst case
		Arrays.sort(array, Collections.reverseOrder());
		comparisons = sort(array);
		System.out.println("worst case (" + COUNT * (COUNT - 1) / 2 + "): comparisons = " + comparisons);

		// Random
		List<?> list = Arrays.asList(array);
		Collections.shuffle(list);
		list.toArray(array);
		comparisons = sort(array);
		System.out.println("random case: comparisons = " + comparisons);
	}

	public static <T extends Comparable<? super T>> int sort(T[] array) {
		int comparisons = 0;
		boolean sorted = false;

		for (int i = 0; i < array.length && !sorted; i++) {
			sorted = true;

			for (int j = 1; j < array.length - i; j++) {
				if (array[j - 1].compareTo(array[j]) > 0) {
					sorted = false;
					T tmp = array[j - 1];
					array[j - 1] = array[j];
					array[j] = tmp;
				}
			}
		}

		return comparisons;
	}

}
