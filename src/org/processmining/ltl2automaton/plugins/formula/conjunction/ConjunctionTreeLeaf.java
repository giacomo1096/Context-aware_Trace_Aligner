package org.processmining.ltl2automaton.plugins.formula.conjunction;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.processmining.ltl2automaton.plugins.LTL2Automaton;
import org.processmining.ltl2automaton.plugins.automaton.Automaton;
import org.processmining.ltl2automaton.plugins.automaton.DeterministicAutomaton;
import org.processmining.ltl2automaton.plugins.formula.Formula;

public class ConjunctionTreeLeaf extends ConjunctionTreeNode {
	private Formula f;
	private final char name;

	public ConjunctionTreeLeaf(final Formula f, final char name) {
		this.name = name;
		setFormula(f);
	}

	@Override
	public int conjunctions() {
		return 1;
	}

	@Override
	public synchronized boolean equals(final Object o) {
		if (o == null) return false;
		if (!(o instanceof ConjunctionTreeLeaf)) return false;
		final ConjunctionTreeLeaf other = (ConjunctionTreeLeaf) o;
		if (f == null && other.f != null) return false;
		if (f != null && !f.equals(other.f)) return false;
		return true;
	}

	@Override
	public synchronized Automaton getAutomaton() {
		if (cache == null || cache.get() == null) {
			cache = new SoftReference<Automaton>(process(translate(f)));
		}
		final Automaton automaton = cache.get();
		if (automaton == null) return getAutomaton();
		return automaton;
	}

	/**
	 * @return the f
	 */
	public Formula getFormula() {
		return f;
	}

	@Override
	public int getHeight() {
		return 1;
	}

	@Override
	public int hashCode() {
		return f == null ? 0 : f.hashCode();
	}

	@Override
	public Iterator<Formula> iterator() {
		return new Iterator<Formula>() {
			boolean done = false;

			@Override
			public boolean hasNext() {
				return !done;
			}

			@Override
			public Formula next() {
				if (done) throw new NoSuchElementException();
				done = true;
				return f;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	/**
	 * @param f
	 *            the f to set
	 */
	public void setFormula(final Formula f) {
		this.f = f;
		invalidate();
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		if (cache != null) {
			sb.append('<');
		}
		sb.append(name);
		if (cache != null) {
			final Automaton automaton = cache.get();
			if (automaton != null) {
				sb.append(">\t");
				sb.append(automaton.getStateCount());
				sb.append(" - ");
				sb.append(automaton.getTransitionCount());
				sb.append("\t\t - ");
				sb.append(Integer.toHexString(cache.hashCode()));
				sb.append(" - ");
				sb.append(Integer.toHexString(f.hashCode()));

			} else {
				sb.append("\t\t -            ");
				sb.append(Integer.toHexString(f.hashCode()));
			}
		} else {
			sb.append("\t\t -            ");
			sb.append(Integer.toHexString(f.hashCode()));
		}

		return sb.toString();

	}

	private Automaton translate(final Formula f) {
		try {
			return LTL2Automaton.getInstance().translateDeterministicAutomatonOperation(f, true,
					false, new HashMap<String, DeterministicAutomaton>());
		} catch (final Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
