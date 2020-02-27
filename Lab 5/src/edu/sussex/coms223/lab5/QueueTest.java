package edu.sussex.coms223.lab5;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.Test;

class QueueTest {

	@Test
	void testEmptyQueue() {
		Queue<Integer> q = new Queue<>();
		assertEquals(0, q.size());
		assertNull(q.first());
		assertTrue(q.isEmpty());
		assertThrows(NoSuchElementException.class, () -> {
			q.dequeue();
		});
	}

	@Test
	void testEnqueueDequeue() {
		Queue<Integer> q = new Queue<>();
		assertThrows(IllegalArgumentException.class, () -> {
			q.enqueue(null);
		});
		q.enqueue(0);
		assertEquals(1, q.size());
		assertEquals(0, q.first());
		assertNull(q.next());
		assertEquals(0, q.dequeue());
		assertEquals(0, q.size());
		q.enqueue(0);
		q.enqueue(1);
		q.enqueue(2);
		assertEquals(3, q.size());
		assertEquals(0, q.first());
		assertEquals(1, q.next());
		assertEquals(2, q.next());
		assertNull(q.next());
		assertEquals(0, q.positionOf(0));
		assertEquals(1, q.positionOf(1));
		assertEquals(2, q.positionOf(2));
		assertEquals(0, q.dequeue());
		assertEquals(1, q.dequeue());
		assertEquals(2, q.dequeue());
	}

	@Test
	void testRemove() {
		Queue<Integer> q = new Queue<>();
		q.enqueue(0);
		q.enqueue(1);
		q.enqueue(2);
		q.remove(1);
		assertEquals(2, q.size());
		assertEquals(0, q.first());
		assertEquals(2, q.next());
		assertNull(q.next());
		q.clear();
		q.enqueue(0);
		q.enqueue(1);
		q.enqueue(2);
		q.enqueue(1);
		q.removeAll(1);
		assertEquals(2, q.size());
		assertEquals(0, q.first());
		assertEquals(2, q.next());
		assertNull(q.next());
	}

	@Test
	void testNext() {
		Queue<Integer> q = new Queue<>();
		q.enqueue(0);
		assertThrows(IllegalStateException.class, () -> {
			q.next();
		});
	}

}
