package edu.sussex.coms223.lab6;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class StackTest {

	@Test
	void testEmpty() {
		Stack<Integer> s = new Stack<>();
		assertEquals(0, s.size());
		assertNull(s.first());
		assertTrue(s.isEmpty());
		assertThrows(NoSuchElementException.class, () -> {
			s.pop();
		});
	}

	@Test
	void testPushPop() {
		Stack<Integer> s = new Stack<>();
		s.push(999);
		assertEquals(1, s.size());
		assertEquals(999, s.first());
		assertNull(s.next());
		assertEquals(999, s.pop());
		assertEquals(0, s.size());
		assertNull(s.first());
		assertTrue(s.isEmpty());
		s.push(997);
		s.push(998);
		assertEquals(2, s.size());
		assertEquals(998, s.first());
		assertEquals(997, s.next());
		assertNull(s.next());
		assertEquals(998, s.pop());
		assertEquals(1, s.size());
		assertEquals(997, s.pop());
		assertTrue(s.isEmpty());
	}

}
