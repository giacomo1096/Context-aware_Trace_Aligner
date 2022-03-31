package org.processmining.ltl2automaton.plugins.formula.conjunction;

import java.util.Collection;

import org.processmining.ltl2automaton.plugins.formula.Formula;

public interface ConjunctionFactory<T extends Conjunction> {
	public T instance();

	public T instance(Collection<Formula> terms);

	public T instance(Formula terms);
}
