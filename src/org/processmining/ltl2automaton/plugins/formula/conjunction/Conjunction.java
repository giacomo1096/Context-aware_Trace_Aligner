package org.processmining.ltl2automaton.plugins.formula.conjunction;

import java.util.Collection;

import javax.naming.OperationNotSupportedException;

import org.processmining.ltl2automaton.plugins.automaton.Automaton;
import org.processmining.ltl2automaton.plugins.formula.Formula;

public interface Conjunction extends Iterable<Formula> {
	void add(Formula term) throws OperationNotSupportedException;

	void balance();

	Automaton getAutomaton();

	void remove(Formula term) throws OperationNotSupportedException;

	void setAll(Collection<Formula> terms);

	void setAll(Formula terms);
}
