package edu.sussex.coms223.lab2;

import edu.sussex.coms223.lab1.LinkedList;
import edu.sussex.coms223.lab1.List;

/**
 * The Class HashMap. Implement the Map interface using a hash table. Implement
 * hash table slots using the LinkedList data structure from Lab 1.
 *
 * @param <K> the key type
 * @param <V> the value type
 */
public class HashMap<K, V> implements Map<K, V> {

	/** The Constant HASHTABLE_SIZE. */
	final static int HASHTABLE_SIZE = 1000;

	/**
	 * The hash table. Each array element (i.e. hash table slot) is either null or
	 * contains a reference to a LinkedList of values whose keys hash to the slot.
	 */
	Object[] hashtable = new Object[HASHTABLE_SIZE];

	/**
	 * The Class Entry. This is a private class which implements the key/value pair
	 * that are stored as values in LinkedLists.
	 */
	private class Entry {
		K key;
		V value;

		Entry(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	/**
	 * Hash function. Given a key of generic type K, return the key hash code modulo
	 * the size of the hash table. The result will be used to index into the hash
	 * table array.
	 *
	 * @param key the key
	 * @return an index into the hash table array
	 */
	private int hash(K key) {
		return key.hashCode() % HASHTABLE_SIZE;
	}

	/**
	 * Put a new value of type V in the Map associated with the key of type K.
	 * 
	 * If an entry already exists for the key then the value is replaced and the
	 * existing value is returned. If an entry does not exist for the key then a new
	 * entry is created and null is returned.
	 * 
	 * Neither key nor value may be null.
	 *
	 * @param key   the key of generic type K
	 * @param value the value of generic type V associated with the key
	 * @return any existing value mapped to key or null
	 * @throws IllegalArgumentException if either key or value is null
	 */
	@Override
	public V put(K key, V value) {
		// If either key or value is null, throw an IllegalArgumentException exception.
		if (key == null || value == null)
			throw new IllegalArgumentException("null key or value");

		// Declare and initialize to null existing value which will be returned.
		V existing = null;

		// Declare hash table index and initialize to the hash value of the key modulo
		// hash table size.
		int index = hash(key);

		// If the hash table slot for this key is empty, initialize it with a new
		// LinkedList which will be used to store values whose key hashes to the same
		// slot.
		if (hashtable[index] == null)
			hashtable[index] = new LinkedList<Entry>();

		// Get a reference to the List of entries in the hash table whose key hashes to
		// the same slot.
		@SuppressWarnings("unchecked")
		List<Entry> entries = (List<HashMap<K, V>.Entry>) hashtable[index];

		// Search the LinkedList for any entries with a matching key until a match is
		// found or the list is exhausted.
		for (int i = 0; i < entries.size() && existing == null; i++) {
			Entry entry = entries.get(i);

			// If a matching key is found, store the existing value which will be returned
			// and replace the value with the new one.
			if (entry.key.equals(key)) {
				existing = entry.value;
				entry.value = value;
			}
		}

		// If no matching entry is found whose key matches, then add a new entry to the
		// List of entries whose key hashes to the same slot.
		if (existing == null)
			entries.add(new Entry(key, value));

		// Return any previous existing value that mapped to the key or null if there
		// was none.
		return existing;
	}

	/**
	 * Gets the value of generic type V associated with the key of generic type K.
	 * Return null if no value is associated with the key.
	 *
	 * @param key the key associated with the desired value
	 * @return the value associated with key or null if none is found
	 */
	@Override
	public V get(K key) {
		// Declare and initialize to null a variable to store any existing value
		// associated with the key.
		V existing = null;

		// Declare hash table index and initialize to the hash value of the key modulo
		// hash table size.
		int index = hash(key);

		// Get a reference to the List of entries in the hash table whose key hashes to
		// the same slot.
		@SuppressWarnings("unchecked")
		List<Entry> entries = (List<HashMap<K, V>.Entry>) hashtable[index];

		// If the slot is empty, nothing to search.
		if (entries != null) {
			// Search the LinkedList for any entries with a matching key until a match is
			// found or the list is exhausted.
			for (int i = 0; i < entries.size() && existing == null; i++) {
				Entry entry = entries.get(i);

				// If a matching key is found, store the existing value which will be returned.
				if (entry.key.equals(key))
					existing = entry.value;
			}
		}

		// Return any existing value that is mapped to the key or null if there is none.
		return existing;
	}

	/**
	 * Removes the value mapping for the specified key if it exists and return the
	 * mapped value.
	 *
	 * @param key the key of the map entry to be removed
	 * @return the value associated with the removed key or null if no mapping was
	 *         found
	 */
	@Override
	public V remove(K key) {
		// Declare and initialize to null a variable to store any existing value
		// associated with the key to be removed.
		V existing = null;

		// Declare hash table index and initialize to the hash value of the key modulo
		// hash table size.
		int index = hash(key);

		// Get a reference to the List of entries in the hash table whose key hashes to
		// the same slot.
		@SuppressWarnings("unchecked")
		List<Entry> entries = (List<HashMap<K, V>.Entry>) hashtable[index];

		// If the slot is empty, nothing to search.
		if (entries != null) {
			// Search the LinkedList for any entries with a matching key until a match is
			// found or the list is exhausted.
			for (int i = 0; i < entries.size() && existing == null; i++) {
				Entry entry = entries.get(i);

				// If a matching key is found, store the existing value which will be returned
				// and remove the entry from the list.
				if (entry.key.equals(key)) {
					existing = entry.value;
					entries.remove(entry);
				}
			}
		}

		// Return any existing value that is mapped to the key or null if there is none.
		return existing;
	}

	/**
	 * Return the number of entries in the Map.
	 *
	 * @return the count of entries in the Map.
	 */
	@Override
	public int size() {
		// Declare an integer variable to store the count of entries and initialize to
		// zero.
		int count = 0;

		// Search through all the hash table slots.
		for (int i = 0; i < hashtable.length; i++) {
			// Each hash table slot will either be null or contain a reference to a
			// List<Entry> instance.
			@SuppressWarnings("unchecked")
			List<Entry> entries = (List<HashMap<K, V>.Entry>) hashtable[i];

			// If a List of entries is associated with the hash table slot, add the size of
			// the List to the entry count.
			if (entries != null)
				count += entries.size();
		}

		// Return the count of Map entries.
		return count;
	}

	/**
	 * Clear (reset) the Map by re-allocating and initializing the hash table field.
	 */
	@Override
	public void clear() {
		hashtable = new Object[HASHTABLE_SIZE];
	}

	@Override
	public void print() {
		// TODO Auto-generated method stub
		
	}

}
