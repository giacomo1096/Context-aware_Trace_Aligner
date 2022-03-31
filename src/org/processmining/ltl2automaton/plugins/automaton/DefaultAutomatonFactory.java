package org.processmining.ltl2automaton.plugins.automaton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.processmining.ltl2automaton.plugins.AutomatonFactory;

public class DefaultAutomatonFactory implements AutomatonFactory {

	private final Automaton automaton;

	public DefaultAutomatonFactory() {
		super();
		automaton = new Automaton();
	}

	@Override
	public void addAllTransition(final Object sourceState, final Object tragetState) {
		final State source = convertState(sourceState);
		final State target = convertState(tragetState);
		if (source != null && target != null) {
			new Transition(source, target);
		}
	}

	@Override
	public void addNegativePropositionsTransition(final Object sourceState,
			final Object tragetState, final Collection<String> propositions) {
		final State source = convertState(sourceState);
		final State target = convertState(tragetState);
		if (source != null && target != null) {
			new Transition(source, target, propositions);
		}
	}

	@Override
	public void addPropositionTransition(final Object sourceState, final Object tragetState,
			final String proposition) {
		final State source = convertState(sourceState);
		final State target = convertState(tragetState);
		if (source != null && target != null) {
			new Transition(source, target, proposition);
		}
	}

	@Override
	public void addState(final Object state) {
		final State st = convertState(state);
		if (st != null) {
			automaton.addState(st);
		}
	}

	@Override
	public void addTransition(final Object sourceState, final Object tragetState,
			final TransitionLabel label) {
		final State source = convertState(sourceState);
		final State target = convertState(tragetState);
		if (source != null && target != null) {
			new Transition(source, target, label);
		}

	}

	@Override
	public void addTransition(final Transition t) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object createState() {
		return new State();
	}

	@Override
	public void finished() {
	}

	@Override
	public Automaton getAutomaton() {
		return automaton;
	}

	@Override
	public int getStateId(final Object state) {
		int id = -1;
		final State st = convertState(state);
		if (st != null) {
			id = st.getId();
		}
		return id;
	}

	@Override
	public void initialState(final Object state) {
		final State st = convertState(state);
		if (st != null) {
			automaton.setInitial(st);
		}
	}

	@Override
	public void removeOutgoingTransitions(final Object s) {
		final State source = convertState(s);
		if (source != null) {
			final List<Transition> transitions = new ArrayList<Transition>();
			for (final Transition t : source.getOutput()) {
				transitions.add(t);
			}
			for (final Transition t : transitions) {
				t.remove();
			}
		}
	}

	@Override
	public void setExpandCount(final int expand) {
		automaton.setExpandCount(expand);
	}

	@Override
	public void updateState(final Object state, final int id, final boolean accepting) {
		final State st = convertState(state);
		if (st != null) {
			st.setId(id);
			st.setAccepting(accepting);
		}
	}

	private State convertState(final Object o) {
		State state = null;
		if (o instanceof State) {
			state = (State) o;
		}
		return state;
	}

}
