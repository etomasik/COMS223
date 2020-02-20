package edu.sussex.coms223.lab2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class MapTest {

	public static Stream<Map<String, Integer>> maps() {
		return Stream.of(new HashMap<String, Integer>(), new BinaryTreeMap<String, Integer>());
	}

	@ParameterizedTest
	@MethodSource("maps")
	void testPut(Map<String, Integer> m) {
		assertEquals(0, m.size());
		assertEquals(null, m.put("1", 1));
		assertEquals(null, m.put("0", 0));
		assertEquals(null, m.put("2", 2));
		assertEquals(0, m.put("0", 0));
		assertEquals(3, m.size());
		assertEquals(0, m.get("0"));
		assertThrows(IllegalArgumentException.class, () -> {
			m.put(null, 0);
		});
		assertThrows(IllegalArgumentException.class, () -> {
			m.put("" + 0, null);
		});
	}

	@ParameterizedTest
	@MethodSource("maps")
	void testRemove(Map<String, Integer> m) {
		assertEquals(0, m.size());
		Random rng = new Random();
		Set<Integer> mapped = new HashSet<Integer>();
		int COUNT = 100;
		while (mapped.size() != COUNT) {
			Integer value = rng.nextInt(COUNT);
			m.put(value.toString(), value);
			mapped.add(value);
		}
		while (mapped.size() != 0) {
			Integer value = rng.nextInt(COUNT);
			Integer existing = m.remove(value.toString());
			if (mapped.contains(existing)) {
				assertEquals(value, existing);
				mapped.remove(value);
			} else
				assertNull(existing);
		}
		assertEquals(0, m.size());
	}

	@ParameterizedTest
	@MethodSource("maps")
	void testPerformance(Map<String, Integer> m) {
		final int ITERATIONS = 5000;
		Random rng = new Random();
		for (int i = 0; i < ITERATIONS; i++) {
			m.clear();
			assertEquals(0, m.size());
			final int COUNT = 2000;
			for (int j = 0; j < COUNT; j++) {
				int value = rng.nextInt(COUNT);
				Integer existing = m.put("" + value, value);
				assertTrue(existing == null || existing == value);
			}
			for (int j = 0; j < COUNT; j++) {
				int value = rng.nextInt(COUNT);
				Integer existing = m.get("" + value);
				assertTrue(existing == null || existing == value);
			}
			for (int j = 0; j < COUNT; j++) {
				int value = rng.nextInt(COUNT);
				Integer existing = m.remove("" + value);
				assertTrue(existing == null || existing == value);
			}
		}
	}

}
