package edu.sussex.coms223.lab1;

/**
 * Declare generic class LinkedList which implements the List interface using a
 * singly-linked circular list data structure.
 *
 * @param <E> the element type
 */

public class LinkedList<E> implements List<E> {
	/**
	 * Declare a single private data field tail which when the List is empty will be
	 * null otherwise tail will reference the last Node added to the list.
	 */
	private Node tail = null;

	/**
	 * Declare a local private class Node to represent each value in the
	 * singly-linked circular list.
	 */
	private class Node {
		E data;
		Node next;

		Node(E data) {
			this.data = data;
			next = null;
		}
	}

	/**
	 * Add a value to a List. Duplicates are permitted but null values will cause an
	 * exception to be thrown. Values are added to the tail of the implementing
	 * linked list.
	 *
	 * @param e the value of generic type E to be added to the list.
	 * @return true, indicating the list has been successfully modified.
	 * @throws IllegalArgumentException if a null value is passed.
	 */
	@Override
	public boolean add(E e) {
		// First, check for a null value and throw exception if detected.
		if (e == null)
			throw new IllegalArgumentException("null values not allowed");

		// Create a new Node instance containing the value. The Node will be added to
		// the end of the list and become the new tail.
		Node node = new Node(e);

		// If the list is not empty, set the current tail to
		// reference the new node and the new node to reference
		// the current head (tail.next).
		if (tail != null) {
			node.next = tail.next;
			tail.next = node;
		} else
			// Otherwise if list is empty, the new Node
			// represents both the head and the tail of the
			// list so link it to itself.
			node.next = node;

		// The new Node is now at the tail of the list so
		// make the tail field reference the new Node.
		tail = node;

		// Return true as the list has been modified.
		return true;
	}

	/**
	 * Removes the first element of the list which equals the value of the parameter
	 * e.
	 *
	 * @param e the value to be removed.
	 * @return true, if the value is found and removed, otherwise false if the list
	 *         remains unchanged.
	 */
	@Override
	public boolean remove(E e) {
		// Declare local boolean variable representing whether the value was found and
		// removed. This is the method return value.
		boolean removed = false;

		// If tail is null then the list is empty and there's nothing to do but return.
		if (tail != null) {
			// If the list is non-empty, search for value starting from the head of the list
			// (tail.next).
			Node prev = tail, curr = tail.next;

			do {
				// Check for match of Node value compared to parameter e.
				if (curr.data.equals(e)) {
					// If match is found, link node before matching node to matching node successor.
					prev.next = curr.next;

					// If matching node to be removed is the tail,
					// we need to update the tail field value.
					if (curr == tail) {
						// If removed node is the last node on the list, set tail to null.
						if (curr.next == curr)
							tail = null;
						// Otherwise set tail to reference the node before the removed node.
						else
							tail = prev;
					}

					// Set removed flag to true since we modified the list.
					removed = true;
				} else {
					// Keep searching for match, set previous node reference to current node and
					// advance current node to next node on the list.
					prev = curr;
					curr = curr.next;
				}

				// Continue searching until we're back to the head of the list or we have found
				// a match and removed it.
			} while (tail != null && curr != tail.next && !removed);
		}

		// Return removed flag which will be true iff a match was found and removed.
		return removed;
	}

	/**
	 * Get the List element at index.
	 *
	 * @param index the index of the element to return
	 * @return the list element at index
	 * @throws IllegalArgumentException is index is out of range 0 to size - 1
	 */
	@Override
	public E get(int index) {
		// Check that index is not negative and that the list is non-empty. Later we'll
		// check if the index is past the last element on the list.
		if (index < 0 || tail == null)
			throw new IllegalArgumentException("index out of range");

		// The variable count will track how many nodes are visited and node will be
		// used to traverse the linked list starting at the head of the list.
		int count = 0;
		Node node = tail.next;

		// Traverse the list while count is less than index and we haven't exhausted the
		// list.
		while (count < index && node != tail) {
			count++;
			node = node.next;
		}

		// If count is less than index then the index is greater than size - 1.
		if (count < index)
			throw new IllegalArgumentException("index out of range");

		// Index is in range so return node data.
		return node.data;
	}

	/**
	 * Return the number of elements in the List.
	 *
	 * @return the list size.
	 */
	@Override
	public int size() {
		// Initialize our element count to 0.
		int count = 0;

		// If list is empty, nothing to do.
		if (tail != null) {
			// Starting at the head of the list, count nodes until we're back at the head.
			Node node = tail.next;
			do {
				node = node.next;
				count++;
			} while (node != tail.next);
		}

		// Return count of list elements.
		return count;
	}

	/**
	 * Clear the List by resetting tail to null.
	 */
	@Override
	public void clear() {
		tail = null;
	}

}
