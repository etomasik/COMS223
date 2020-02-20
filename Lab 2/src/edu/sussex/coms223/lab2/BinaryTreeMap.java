package edu.sussex.coms223.lab2;

/**
 * The class BinaryTreeMap implements the Map interface using a binary tree
 * structure for efficient searching of key values.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public class BinaryTreeMap<K extends Comparable<K>, V> implements Map<K, V> {
	// The lone data field of BinaryTreeMap is a reference to the root node of the
	// tree which is null in the case of an empty Map.
	private Node root = null;

	/**
	 * The class Node is a private class within BinaryTreeMap which holds a
	 * key/value pair along with left and right Node references implementing a
	 * binary tree structure.
	 */
	private class Node {
		K key;
		V value;
		Node left, right;

		Node(K key, V value) {
			this.key = key;
			this.value = value;
			left = right = null;
		}
	}

	/**
	 * Put a key/value pair into a Map. Neither the key nor value may be null. If a
	 * mapping already exists, the existing value is replaced with the new value and
	 * returned, otherwise null is returned.
	 *
	 * @param key   the key
	 * @param value the value associated with the key
	 * @return any existing value mapping or null
	 * @throws IllegalArgumentException if null key or value is passed
	 */
	@Override
	public V put(K key, V value) {
		// If key or value is null, throw IllegalArgumentException.
		if (key == null || value == null)
			throw new IllegalArgumentException("null key or value");

		// Declare local variables, one to store and return any existing value mapped to
		// key, and one to hold a new binary tree node.
		@SuppressWarnings("unchecked")
		V[] existing = (V[]) new Object[1];
		Node node = new Node(key, value);

		// Call recursive function to add new node to the tree and return any existing
		// value.
		root = add(root, node, existing);

		// Return any existing value mapped to key. This will be null if no existing
		// mapping exists.
		return existing[0];
	}

	/**
	 * Adds a new key/value Node to the binary tree, storing any existing value into
	 * existing array.
	 *
	 * @param current  the current node to check for key match
	 * @param node     the node to be added to the tree
	 * @param existing an array of size 1 to store any existing value mapped to key
	 * @return the current search node or the new node if we
	 */
	private Node add(Node current, Node node, V[] existing) {
		// If current branch of the tree is null, then return the new node to be
		// attached.
		if (current == null)
			return node;
		else {
			// Determine using compareTo function whether we have a key match or which side
			// of tree should be searched next.
			int comparison = node.key.compareTo(current.key);

			// If search key is less than node key, continue search on the left branch.
			if (comparison < 0)
				current.left = add(current.left, node, existing);

			// If search key is greater than node key, continue search on the right branch.
			else if (comparison > 0)
				current.right = add(current.right, node, existing);

			// We have found an exact key match so store existing value, replace existing
			// value with new value, and stop searching.
			else {
				existing[0] = current.value;
				current.value = node.value;
			}

			// Return current branch.
			return current;
		}
	}

	/**
	 * Removes the node associated with the key argument and returns the mapped
	 * value. Returns null if key not found.s
	 *
	 * @param key the key of the key/value pair to be removed.
	 * @return the existing value mapped to key or null
	 */
	@Override
	public V remove(K key) {
		// Declare local variable to store and return any existing value mapped to key.
		@SuppressWarnings("unchecked")
		V[] existing = (V[]) new Object[1];

		// Call recursive remove method passing root node, key, and existing value
		// holder.
		root = remove(root, key, existing);

		// Return existing value mapped to key which will still be null if key not
		// found.
		return existing[0];
	}

	/**
	 * Removes current node if key matches and returns existing value. Otherwise,
	 * searches recursively down the tree for a match.
	 *
	 * @param current  the current tree node being examined for a key match
	 * @param key      the key of the key/value pair to be removed
	 * @param existing the existing value holder
	 * @return the current node if no match otherwise
	 */
	private Node remove(Node current, K key, V[] existing) {
		if (current != null) {
			int comparison = key.compareTo(current.key);

			// Current node matches key.
			if (comparison == 0) {
				// If existing value holder is not null, it means the current node is to be
				// removed and existing value stored in the holder.
				if (existing != null)
					existing[0] = current.value;

				// If the node to be removed is a leaf node with no children, then
				// simply return null which will replace the reference to the removed node.
				if (current.left == null && current.right == null)
					return null;

				// If the removed node only has a right branch, return it to replace the
				// reference to the removed node.
				else if (current.left == null)
					return current.right;

				// If the removed node only has a left branch, return it to replace the
				// reference to the removed node.
				else if (current.right == null)
					return current.left;

				// If the removed node has both a left and right branch, we replace the current
				// nodes key and value with the minimum from the right branch and then remove
				// that node recursively.
				else {
					Node min = minkey(current.right);
					current.key = min.key;
					current.value = min.value;
					current.right = remove(current.right, min.key, null);
				}

				// Search key is less than current node key so search left branch.
			} else if (comparison < 0)
				current.left = remove(current.left, key, existing);
			else
				// Search key is greater than current node key so search right branch.
				current.right = remove(current.right, key, existing);
		}

		// Return current node to be attached by caller.
		return current;
	}

	/**
	 * Minkey returns the node with the smallest key by recursively traversing down
	 * the left branch of each node.
	 *
	 * @param node the node to descend left branch is not null
	 * @return the passed node if no left branch or minkey of left branch.
	 */
	private Node minkey(Node node) {
		return node.left == null ? node : minkey(node.left);
	}

	/**
	 * Gets the value mapped to the given key or null if no mapping exists.
	 *
	 * @param key the search key
	 * @return the value mapped to the key or null
	 */
	@Override
	public V get(K key) {
		// Declare a local variable to hold the existing mapped value and initialize to
		// null.
		V value = null;

		// Find the node with a matching key.
		Node node = get(root, key);

		// If a matching node is found, save the value.
		if (node != null)
			value = node.value;

		// Return the value mapped to key or null if none was found.
		return value;
	}

	/**
	 * Gets the node that matches given key starting from current node.
	 *
	 * @param current the current node
	 * @param key     the key to match
	 * @return the matching node or null
	 */
	private Node get(Node current, K key) {
		if (current != null) {
			int comparison = key.compareTo(current.key);

			// If the given key is less than the current node key, continue searching the
			// left branch.
			if (comparison < 0)
				current = get(current.left, key);

			// If the given key is greater than the current node key, continue searching the
			// right branch.
			else if (comparison > 0)
				current = get(current.right, key);
		}

		return current;
	}

	/**
	 * Return the number of nodes in the tree.
	 *
	 * @return the number of nodes in the tree.
	 */
	@Override
	public int size() {
		// Call recursive size function passing root node.
		return size(root);
	}

	/**
	 * Return the number of nodes rooted at node.
	 *
	 * @param node the node from which to count nodes.
	 * @return the number of nodes rooted at node including the node itself.
	 */
	private int size(Node node) {
		// TODO: implement
		return 0;
	}

	@Override
	public void clear() {
		root = null;
	}

	private void print(Node root, int space) {
		final int COUNT = 10;

		if (root == null)
			return;

		space += COUNT;

		print(root.right, space);

		System.out.print("\n");
		for (int i = COUNT; i < space; i++)
			System.out.print(" ");
		System.out.print(root.key + " -> " + root.value + "\n");

		print(root.left, space);
	}

	public void print() {
		print(root, 0);
	}

}
