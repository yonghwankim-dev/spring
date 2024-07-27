package com.ch13.class02.step02;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class MyArrayList<T> implements MyCollection<T>{
	private List<T> list;

	public MyArrayList(List<T> list) {
		this.list = list;
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public boolean contains(Object o) {
		return list.contains(o);
	}

	@Override
	public Iterator<T> iterator() {
		return list.iterator();
	}

	@Override
	public Object[] toArray() {
		Object[] result = new Object[size()];
		for (int i = 0; i < size(); i++) {
			result[i] = list.get(i);
		}
		return result;
	}

	@Override
	public <T1> T1[] toArray(T1[] a) {
		return null;
	}

	@Override
	public boolean add(T t) {
		return list.add(t);
	}

	@Override
	public boolean remove(Object o) {
		return list.remove(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return new HashSet<>(list).containsAll(c);
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		return list.addAll(c);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		return list.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return retainAll(c);
	}

	@Override
	public void clear() {
		list.clear();
	}
}
