package edu.sussex.coms223.lab5;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * The class Queue implements the interface described in section 6.3 of the
 * textbook using the java.util.LinkedList class.
 *
 * @param <T> the generic type
 */
public class Queue<T> {
	// Declare and initialize a LinkedList data structure to implement the queue
	private List<T> list = new LinkedList<>();

	// Declare and initialize an Iterator to null which will be used to implement
	// first and next methods
	private Iterator<T> it = null;

	/**
	 * Enqueue a new item. Throw an IllegalArgumentException if the passed item is
	 * null.
	 *
	 * @param item the item to be added to the queue.
	 */
	public void enqueue(T item) {
		// check if the item is null and if it is throw an
		// IllegalArgumentException.
		if (item == null)
			throw new IllegalArgumentException();
		//add the item to the data list.
		list.add(item);
		
	}

	/**
	 * Dequeue the oldest item in the queue (the item at element 0 in the data
	 * list). If the data list is empty, throw a NoSuchElementException.
	 *
	 * @return the oldest item in the queue (element 0).
	 */
	public T dequeue() {
		//check if the data list is empty and if it is throw a
		// NoSuchElementException.
		if (list.isEmpty())
			throw new NoSuchElementException();
		// declare a variable of type T named first and initialize it to element
		// 0 of the data list    
		T first = list.get(0);
		//remove element 0 from the data list
		list.remove(0);
		//return variable first
		return first;
	}

	/**
	 * Return the size of the queue
	 *
	 * @return the size of the queue
	 */
	public int size() {
		return list.size();
	}

	/**
	 * Checks if queue is empty.
	 *
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		return list.isEmpty();
	}

	/**
	 * Position of first queue element that is equal to item
	 *
	 * @param item the item to search for
	 * @return the queue index of the matched item or -1 if not found
	 */
	public int positionOf(T item) {
		return list.indexOf(item);
	}

	/**
	 * Clear the queue
	 */
	public void clear() {
		list.clear();
	}

	/**
	 * Removes the first queue element that is equal to item
	 *
	 * @param item the item to be removed
	 */
	public void remove(T item) {
		list.remove(item);
	}

	/**
	 * Removes all the queue elements that are equal to item
	 *
	 * @param item the item
	 */
	public void removeAll(T item) {
		while (list.remove(item))
			;
	}

	/**
	 * Return first element in the queue (next to be dequeued)
	 *
	 * @return the next element to be dequeued or null if queue is empty
	 */
	public T first() {
		it = list.iterator();

		if (it.hasNext())
			return it.next();
		else
			return null;
	}

	/**
	 * Return the next queue element or null if no more left. Throw
	 * IllegalStateException if iterator is null implying first was not called yet.
	 *
	 * @return the next successive item on the queue
	 */
	public T next() {
		if (it == null)
			throw new IllegalStateException("first must be called before next");

		if (it.hasNext())
			return it.next();
		else
			return null;
	}
}
