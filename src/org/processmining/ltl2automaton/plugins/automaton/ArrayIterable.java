package org.processmining.ltl2automaton.plugins.automaton;

import java.util.Iterator;

public class ArrayIterable<T> implements Iterable<T> {
	final T[] array;

	public ArrayIterable(final T[] array) {
		this.array = array;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			int pos = 0;

			@Override
			public boolean hasNext() {
				return pos < array.length;
			}

			@Override
			public T next() {
				return array[pos++];
			}

			@Override
			public void remove() {
				new Exception();
			}
		};
	}

}
