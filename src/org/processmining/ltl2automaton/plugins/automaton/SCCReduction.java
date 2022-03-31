package org.processmining.ltl2automaton.plugins.automaton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class SCCReduction {

	private class SCC {

		private int nCounter;
		private int sccCounter;
		private final LinkedList<State> states;

		SCC() {
			super();
			nCounter = 0;
			sccCounter = 0;
			states = new LinkedList<State>();
		}

		void add(final State state) {
			states.add(0, state);
		}

		boolean contains(final State state) {
			return states.contains(state);
		}

		int getN() {
			return nCounter;
		}

		int getScc() {
			return sccCounter;
		}

		void incrementN() {
			nCounter++;
		}

		void incrementScc() {
			sccCounter++;
		}

		State remove() {
			return states.remove(0);
		}
	}

	private final Map<State, Integer> dfsnum;
	private final Map<State, Integer> low;
	private final Map<State, Integer> scc;

	private final Map<State, Boolean> reached;

	SCCReduction() {
		super();
		dfsnum = new HashMap<State, Integer>();
		low = new HashMap<State, Integer>();
		scc = new HashMap<State, Integer>();
		reached = new HashMap<State, Boolean>();
	}

	private int getDfsnum(final State state) {
		final Integer d = dfsnum.get(state);
		if (d == null)
			return -1;
		else
			return d.intValue();
	}

	private int getLow(final State state) {
		final Integer l = low.get(state);
		if (l == null)
			return -1;
		else
			return l.intValue();
	}

	private int getScc(final State state) {
		final Integer s = scc.get(state);
		if (s == null)
			return -1;
		else
			return s.intValue();
	}

	private Collection<Collection<State>> getSCCs(final Automaton automaton) {
		final State init = automaton.getInit();
		final Collection<Collection<State>> list = new LinkedList<Collection<State>>();

		if (init == null) return list;

		putReached(init, true);

		final SCC scc = new SCC();
		processState(init, scc);

		final ArrayList<List<State>> sccs = new ArrayList<List<State>>();

		for (int i = 0; i < scc.getScc(); i++) {
			sccs.add(new LinkedList<State>());
		}

		for (final State state : automaton) {
			final int sccNr = getScc(state);
			if (sccNr >= 0) {
				sccs.get(sccNr).add(state);
				putReached(state, false);
			};
		}

		for (int i = 0; i < scc.getScc(); i++) {
			list.add(sccs.get(i));
		}

		return list;
	}

	private boolean isAccepting(final Iterable<State> scc) {
		for (final State state : scc) {
			if (state.isAccepting()) return true;
		}
		return false;
	}

	private boolean isReached(final State state) {
		final Boolean r = reached.get(state);
		if (r == null)
			return false;
		else
			return r.booleanValue();
	}

	private boolean isTerminal(final Collection<State> scc) {
		for (final State state : scc) {
			for (final Transition out : state.getOutput()) {
				if (!scc.contains(out.getTarget())) return false;
			}
		}
		return true;
	}

	private void processState(final State state, final SCC scc) {
		scc.add(state);
		putDfsnum(state, scc.getN());
		putLow(state, scc.getN());
		scc.incrementN();

		for (final Transition transition : state.getOutput()) {
			final State target = transition.getTarget();

			if (!isReached(target)) {
				putReached(target, true);
				processState(target, scc);
				putLow(state, Math.min(getLow(state), getLow(target)));
			} else if (getDfsnum(target) < getDfsnum(state)) {
				if (scc.contains(target)) {
					putLow(state, Math.min(getLow(state), getDfsnum(target)));
				}
			}
		}

		if (getLow(state) == getDfsnum(state)) {
			State temp;
			do {
				temp = scc.remove();
				putScc(temp, scc.getScc());
			} while (temp != state);

			scc.incrementScc();
		}
	}

	private void putDfsnum(final State state, final int d) {
		dfsnum.put(state, d);
	}

	private void putLow(final State state, final int l) {
		low.put(state, l);
	}

	private void putReached(final State state, final boolean r) {
		reached.put(state, new Boolean(r));
	}

	private void putScc(final State state, final int c) {
		scc.put(state, c);
	}

	Automaton reduce(final Automaton automaton) {
		boolean changed;
		final Map<State, List<Transition>> reverseMap = new HashMap<State, List<Transition>>();
		for (final Transition t : automaton.transitions()) {
			List<Transition> list = reverseMap.get(t.getTarget());
			if (list == null) {
				list = new ArrayList<Transition>();
				reverseMap.put(t.getTarget(), list);
			}
			list.add(t);
		}
		do {
			changed = false;

			final Collection<Collection<State>> sccs = getSCCs(automaton);

			for (final Collection<State> scc : sccs) {
				final boolean accepting = isAccepting(scc);

				if (!accepting && isTerminal(scc)) {
					changed = true;

					for (final State state : scc) {
						state.removeAllTransitions();
						final List<Transition> transitions = reverseMap.remove(state);
						if (transitions != null) {
							for (final Transition t : transitions) {
								t.remove();
							}
						}
						automaton.removeState(state);
					}
				}
			}
		} while (changed);

		return automaton;
	}
}
