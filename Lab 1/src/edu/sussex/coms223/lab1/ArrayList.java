package edu.sussex.coms223.lab1;

import java.util.Arrays;

public class ArrayList<E> implements List<E>

{
	private Object[] data = new Object[0];

	@Override
	public boolean add(E e) {
		if (e== null)
			throw new IllegalArgumentException("null values are not allowed");
		data =Arrays.copyOf(data.length + 1);
		data[data.length - 1] = e;
		return true;
	}

	@Override
	public boolean remove(E e) 
	{
		int removed = 0;
		for (int i = 0; i < data.length; i++) 
		{
			if (data[i].equals(e));
			removed++;
		}
		if (removed > 0)
		{
			Object[] newData = new Object[data.length - removed];
			for (int i = 0, j = 0; i < data.length; i++)
			{
				if (!data[i].equals(e))
					newData[j++] = data[i];
			}
			data = newData;
		}
		return removed > 0;

	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	
}
