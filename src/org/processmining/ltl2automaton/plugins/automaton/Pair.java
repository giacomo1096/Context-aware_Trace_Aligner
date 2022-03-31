/**
 * 
 */
package org.processmining.ltl2automaton.plugins.automaton;

class Pair {
	private final State a, b;

	public Pair(final State a, final State b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public boolean equals(final Object o) {
		if (o == null) return false;
		if (!(o instanceof Pair)) return false;
		return getA().equals(((Pair) o).getA()) && getB().equals(((Pair) o).getB());
	}

	public State getA() {
		return a;
	}

	public State getB() {
		return b;
	}

	@Override
	public int hashCode() {
		return getA().hashCode() * 8009 + getB().hashCode();
	}
}