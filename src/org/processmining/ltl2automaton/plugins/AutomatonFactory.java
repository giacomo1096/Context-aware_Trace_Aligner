package org.processmining.ltl2automaton.plugins;

import java.util.Collection;

import org.processmining.ltl2automaton.plugins.automaton.Automaton;
import org.processmining.ltl2automaton.plugins.automaton.Transition;
import org.processmining.ltl2automaton.plugins.automaton.TransitionLabel;

public interface AutomatonFactory {
	public void addTransition(Object source, Object target, TransitionLabel label);

	public void addTransition(Transition t);

	public Automaton getAutomaton();

	void addAllTransition(Object sourceState, Object tragetState);

	void addNegativePropositionsTransition(Object sourceState, Object tragetState,
			Collection<String> propositions);

	void addPropositionTransition(Object sourceState, Object tragetState, String proposition);

	void addState(Object state);

	Object createState();

	void finished();

	int getStateId(Object state);

	void initialState(Object state);

	void removeOutgoingTransitions(Object s);

	void setExpandCount(int expand);

	void updateState(Object state, int id, boolean accepting);
}
