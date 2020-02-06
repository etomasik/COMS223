package edu.sussex.coms223.lab1;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class ListTest {

	public static Stream<List<Integer>> lists() {
		return Stream.of(new ArrayList<>(), new LinkedList<>());
	}

	@ParameterizedTest
	@MethodSource("lists")
	void testAdd(List<Integer> l) {
		assertEquals(0, l.size());
		assertEquals(true, l.add(0));
		assertEquals(1, l.size());
		assertEquals(0, l.get(0));
		assertThrows(IllegalArgumentException.class, () -> {
			l.add(null);
		});
	}

	@ParameterizedTest
	@MethodSource("lists")
	void testGet(List<Integer> l) {
		assertEquals(0, l.size());
		assertEquals(true, l.add(0));
		assertEquals(1, l.size());
		assertEquals(0, l.get(0));
		assertThrows(IllegalArgumentException.class, () -> {
			l.add(null);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			l.get(-1);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			l.get(1);
		});
		l.clear();
		assertThrows(IllegalArgumentException.class, () -> {
			l.get(0);
		});
	}

	@ParameterizedTest
	@MethodSource("lists")
	void testRemove(List<Integer> l) {
		assertEquals(0, l.size());
		assertEquals(true, l.add(0));
		assertEquals(1, l.size());
		assertEquals(0, l.get(0));
		assertEquals(true, l.remove(0));
		assertEquals(0, l.size());
		assertEquals(false, l.remove(0));
	}

	@ParameterizedTest
	@MethodSource("lists")
	void testPerformance(List<Integer> l) {
		final int ITERATIONS = 1000;
		for (int i = 0; i < ITERATIONS; i++) {
			final int COUNT = 1000;
			l.clear();

			for (int j = 0; j < COUNT; j++)
				assertEquals(true, l.add(j));

			Random rng = new Random();

			for (int j = 0; j < COUNT; j++) {
				int index = rng.nextInt(COUNT);
				assertEquals(index, l.get(index));
			}

			int removed = 0;
			for (int j = 0; j < COUNT; j++) {
				int index = rng.nextInt(COUNT);
				if (l.remove(index))
					removed++;
			}
			assertEquals(COUNT - removed, l.size());
		}
	}
}
