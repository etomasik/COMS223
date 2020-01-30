package edu.sussex.coms223.lab1;

public interface List<E> {
	boolean add(E e);

	boolean remove(E e);

	E get(int index);

	int size();

	void clear();
}
