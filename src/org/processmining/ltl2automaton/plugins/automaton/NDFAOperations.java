package org.processmining.ltl2automaton.plugins.automaton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.processmining.ltl2automaton.plugins.AutomatonFactory;

/**
 * Operations on non-deterministic finite automata
 * 
 * @author mwesterg
 */
public class NDFAOperations {
	private static final NDFAOperations instance = new NDFAOperations(DefaultAutomatonFactory.class);
	static {
		final AutomatonFactory factory = NDFAOperations.instance.newFactory();
		final Object state = factory.createState();
		factory.addState(state);
		factory.updateState(state, 0, false);
		factory.addAllTransition(state, state);
		factory.initialState(state);
	}

	public static NDFAOperations getInstance() {
		return NDFAOperations.instance;
	}

	private final Class<? extends AutomatonFactory> factoryClass;

	public NDFAOperations(final Class<? extends AutomatonFactory> factoryClass) {
		this.factoryClass = factoryClass;
	}

	/**
	 * Return a deterministic automaton accepting the same language as a. The parameter is not
	 * modified. Beware that in the worst case the size of the result is exponential in the size of
	 * the input. Assumes the labels are either a single positive proposition, a list of negative
	 * propositions only, or a true proposition. This is the format emitted by LTL2BA (setting
	 * single to true). The automaton returned also satisfies this requirement.
	 * 
	 * @param a
	 * @return
	 */
	public DeterministicAutomaton determinize(final Automaton a) {
		if (a instanceof DeterministicAutomaton) return (DeterministicAutomaton) a;
		final AutomatonFactory factory = newFactory();
		final LinkedList<Set<State>> waiting = new LinkedList<Set<State>>();
		final Map<Set<State>, Object> storage = new HashMap<Set<State>, Object>();
		final Map<Set<State>, Object> accepting = new HashMap<Set<State>, Object>();
		final Set<State> init = new HashSet<State>();
		int i = 0;
		factory.updateState(accepting, -1, true);
		if (a.getInit() != null) {
			init.add(a.getInit());
			waiting.add(init);
			Object s = registerNewState(factory, storage, accepting, init, i++);
			factory.initialState(s);
			while (!waiting.isEmpty()) {
				final Set<State> states = waiting.removeFirst();
				s = getStored(storage, accepting, states);
				final Map<Object, List<String>> targets = new HashMap<Object, List<String>>();
				final Set<String> labels = getLabels(states);

				// Process positive occurences of all propositions (i.e., calculate union of
				// successors where the proposition occurs positively, does not occur negatively or
				// the label is true)
				for (final String label : labels) {
					final Set<State> successor = new HashSet<State>();
					for (final State state : states) {
						for (final Transition transition : state.getOutput()) {
							if (transition.isAll()) {
								successor.add(transition.getTarget());
							} else if (transition.isPositive()
									&& transition.getPositiveLabel().equals(label)) {
								successor.add(transition.getTarget());
							} else if (transition.isNegative()
									&& !transition.getNegativeLabels().contains(label)) {
								successor.add(transition.getTarget());
							}
						}
					}
					if (!successor.isEmpty()) {
						Object ss = getStored(storage, accepting, successor);
						if (ss == null) {
							ss = registerNewState(factory, storage, accepting, successor, i++);
							waiting.addLast(successor);
						}
						factory.addPropositionTransition(s, ss, label);
						List<String> target = targets.get(ss);
						if (target == null) {
							target = new ArrayList<String>();
							targets.put(ss, target);
						}
						target.add(label);
					}
				}

				// process "else" transition (i.e., all transitions with negative (and hence not
				// positive) occurences and all true transitions)
				final Set<State> successor = new HashSet<State>();
				for (final State state : states) {
					for (final Transition transition : state.getOutput()) {
						if (transition.isAll() || transition.isNegative()) {
							successor.add(transition.getTarget());
						}
					}
				}
				if (!successor.isEmpty()) {
					Object ss = getStored(storage, accepting, successor);
					if (ss == null) {
						ss = registerNewState(factory, storage, accepting, successor, i++);
						waiting.addLast(successor);
					}
					final List<String> target = targets.get(ss);
					if (target != null) {
						labels.removeAll(target);
						factory.removeOutgoingTransitions(s);
						targets.remove(ss);
						for (final Map.Entry<Object, List<String>> t : targets.entrySet()) {
							for (final String l : t.getValue()) {
								factory.addPropositionTransition(s, t.getKey(), l);
							}
						}
					}
					if (labels.isEmpty()) {
						factory.addAllTransition(s, ss);
					} else {
						factory.addNegativePropositionsTransition(s, ss, labels);
					}

				}

			}
		}
		return new DeterministicAutomaton(factory.getAutomaton(), false);
	}

	/**
	 * Return an automaton accepting the intersection of the languages of a1 and a2. The parameters
	 * are modified. Creates labels, l1, l2, .., ln and !l1 && !l2 && .. && !ln
	 * 
	 * @param a1
	 * @param a2
	 * @return
	 */
	public Automaton intersect(final Automaton a1, final Automaton a2) {
		final AutomatonFactory factory = newFactory();
		final HashMap<Pair, Object> storage = new HashMap<Pair, Object>();
		final LinkedList<Pair> waiting = new LinkedList<Pair>();
		int id = 0;
		final Pair init = new Pair(a1.getInit(), a2.getInit());
		final Object initState = createState(factory, id++, init, true);
		storage.put(init, initState);
		factory.initialState(initState);
		waiting.addLast(init);

		while (!waiting.isEmpty()) {
			final Pair s = waiting.removeFirst();
			final Object ss = storage.get(s);
			final Set<String> labels = getLabels(s.getA(), s.getB());
			for (final String label : new HashSet<String>(labels)) {
				final List<State> newA = new ArrayList<State>(), newB = new ArrayList<State>();
				for (final Transition t : s.getA().getOutput()) {
					if (t.isPositive() && t.getPositiveLabel().equals(label)) {
						newA.add(t.getTarget());
					} else if (t.isNegative() && !t.getNegativeLabels().contains(label)) {
						newA.add(t.getTarget());
					} else if (t.isAll()) {
						newA.add(t.getTarget());
					}
				}

				if (!newA.isEmpty()) {
					for (final Transition t : s.getB().getOutput()) {
						if (t.isPositive() && t.getPositiveLabel().equals(label)) {
							newB.add(t.getTarget());
						} else if (t.isNegative() && !t.getNegativeLabels().contains(label)) {
							newB.add(t.getTarget());
						} else if (t.isAll()) {
							newB.add(t.getTarget());
						}
					}

					if (!newB.isEmpty()) {
						for (final State a : newA) {
							for (final State b : newB) {
								final Pair t = new Pair(a, b);
								Object tt = storage.get(t);
								if (tt == null) {
									tt = createState(factory, id++, t, true);
									storage.put(t, tt);
									waiting.addLast(t);
								}
								factory.addPropositionTransition(ss, tt, label);
							}
						}
					}
				}
			}
			final List<State> newA = new ArrayList<State>(), newB = new ArrayList<State>();
			for (final Transition t : s.getA().getOutput()) {
				if (t.isNegative() || t.isAll()) {
					newA.add(t.getTarget());
				}
			}

			if (!newA.isEmpty()) {
				for (final Transition t : s.getB().getOutput()) {
					if (t.isNegative() || t.isAll()) {
						newB.add(t.getTarget());
					}
				}

				if (!newB.isEmpty()) {
					for (final State a : newA) {
						for (final State b : newB) {
							final Pair t = new Pair(a, b);
							Object tt = storage.get(t);
							if (tt == null) {
								tt = createState(factory, id++, t, true);
								storage.put(t, tt);
								waiting.addLast(t);
							}
							if (labels.isEmpty()) {
								factory.addAllTransition(ss, tt);
							} else {
								factory.addNegativePropositionsTransition(ss, tt, labels);
							}
						}
					}
				}
			}
		}
		return factory.getAutomaton();
	}

	/**
	 * Returns if this automaton definitely accepts all strings (i.e., if it return true, the
	 * automaton accepts all strings, if it returns false, it most likely doesn't).
	 * 
	 * @param a
	 * @return
	 */
	public boolean isAll(final Automaton a) {
		if (a.getStateCount() > 1) return false;
		if (a.getStateCount() == 0) return false;
		final State state = a.iterator().next();
		if (state != a.getInit()) return false;
		if (state.getOutputSize() != 1) return false;
		if (!state.getOutput().iterator().next().isAll()) return false;
		return state.isAccepting();
	}

	/**
	 * Returns if this automaton definitely accepts the empty language (i.e., if it return true, the
	 * automaton accepts the ampty language, if it returns false, it most likely doesn't).
	 * 
	 * @param a
	 * @return
	 */
	public boolean isEmpty(final Automaton a) {
		if (a.getStateCount() > 1) return false;
		if (a.getStateCount() == 0) return true;
		if (a.getInit() == null) return true;
		final State state = a.getInit();
		if (state.getOutputSize() != 1) return false;
		if (!state.getOutput().iterator().next().isAll()) return false;
		return !state.isAccepting();
	}

	/**
	 * Return a (possibly smaller) automaton accepting the same language as a. The parameter is not
	 * modified. TODO
	 * 
	 * @param a
	 * @return
	 */
	// public Automaton minimize(final Automaton a) {
	// return a.op.reverse().op.determinize().op.minimize().op.reverse();
	// }

	/**
	 * Return an automaton accepting the complement of the language accepted by a. The parameter is
	 * modified. TODO - determinize and use deterministic operation for now
	 * 
	 * @param a
	 * @return
	 */
	public Automaton negate(final Automaton a) {
		return null;
	}

	public Automaton reduce(final Automaton a) {
		return new SCCReduction().reduce(a);
	}

	/**
	 * Return an automaton which accepts the reverse strings of a.
	 * 
	 * @param a
	 * @return
	 */
	// public Automaton reverse(final Automaton a) {
	// final AutomatonFactory factory = newFactory();
	// if (a.getInit() == null) { return NDFAOperations.EMPTY; }
	// final Map<State, Object> stateMap = new HashMap<State, Object>();
	// final List<State> accepting = new ArrayList<State>();
	// int maxId = 0;
	// for (final State s : a.getStates()) {
	// final Object o = factory.createState();
	// stateMap.put(s, o);
	// maxId = Math.max(maxId, s.getId());
	// factory.addState(o);
	// factory.updateState(o, s.getId(), s == a.getInit());
	// if (s.isAccepting()) {
	// accepting.add(s);
	// }
	// }
	//
	// for (final State s : a.getStates()) {
	// for (final Transition t : s.getOutput()) {
	// if (t.isPositive()) {
	// factory
	// .addPropositionTransition(stateMap.get(t.getTarget()), stateMap.get(s), t
	// .getPositiveLabel());
	// } else if (t.isNegative()) {
	// factory.addNegativePropositionsTransition(stateMap.get(t.getTarget()), stateMap.get(s), t
	// .getNegativeLabels());
	// } else if (t.isAll()) {
	// factory.addAllTransition(stateMap.get(t.getTarget()), stateMap.get(s));
	// } else {
	// assert false;
	// }
	// }
	// }
	//
	// if (accepting.size() > 1) {
	// final Object init = factory.createState();
	// factory.addState(init);
	// factory.updateState(init, ++maxId, a.getInit().isAccepting());
	// factory.initialState(init);
	// for (final State s : accepting) {
	// for (final Transition t : s.getInput()) {
	// if (t.isPositive()) {
	// factory.addPropositionTransition(init, stateMap.get(t.getSource()), t.getPositiveLabel());
	// } else if (t.isNegative()) {
	// factory.addNegativePropositionsTransition(init, stateMap.get(t.getSource()), t
	// .getNegativeLabels());
	// } else if (t.isAll()) {
	// factory.addAllTransition(init, stateMap.get(t.getSource()));
	// } else {
	// assert false;
	// }
	//
	// }
	// }
	// } else if (!accepting.isEmpty()) {
	// factory.initialState(stateMap.get(accepting.get(0)));
	// }
	//
	// return factory.getAutomaton();
	// }

	/**
	 * Return an automaton accepting the union of the languages of a1 and a2. The parameters are
	 * modified. FIXME merges init states; should create new init state which is merged. THIS
	 * IMPLEMENTATION DOES NOT WORK!
	 * 
	 * @param a1
	 * @param a2
	 * @return
	 */
	public Automaton union(final Automaton a1, final Automaton a2) {
		final AutomatonFactory factory = newFactory();
		final Map<State, Object> stateMap = copyStates(a1, factory);
		copyTransitions(a1, factory, stateMap);
		int id = getMaxId(a1);
		for (final State s : a2) {
			if (s == a2.getInit()) { // Merge this init with other init
				stateMap.put(s, stateMap.get(a1.getInit()));
			} else {
				copyState(factory, stateMap, s, id++);
			}
		}
		copyTransitions(a2, factory, stateMap);
		return factory.getAutomaton();
	}

	protected void copyState(final AutomatonFactory factory, final Map<State, Object> stateMap,
			final State s, final int id) {
		final Object ss = factory.createState();
		factory.updateState(ss, id, s.isAccepting());
		factory.addState(ss);
		stateMap.put(s, ss);
	}

	protected Map<State, Object> copyStates(final Automaton a, final AutomatonFactory factory) {
		final Map<State, Object> stateMap = new HashMap<State, Object>();
		for (final State s : a) {
			copyState(factory, stateMap, s, s.getId());
		}
		factory.initialState(stateMap.get(a.getInit()));
		return stateMap;
	}

	protected void copyTransition(final AutomatonFactory factory,
			final Map<State, Object> stateMap, final Transition t) {
		factory.addTransition(stateMap.get(t.getSource()), stateMap.get(t.getTarget()),
				t.getLabel());
	}

	protected void copyTransitions(final Automaton a, final AutomatonFactory factory,
			final Map<State, Object> stateMap) {
		for (final Transition t : a.transitions()) {
			factory.addTransition(stateMap.get(t.getSource()), stateMap.get(t.getTarget()),
					t.getLabel());
		}
	}

	protected Set<String> getLabels(final Iterable<State> states) {
		final Set<String> labels = new HashSet<String>();
		for (final State state : states) {
			for (final Transition transition : state.getOutput()) {
				if (transition.isNegative()) {
					labels.addAll(transition.getNegativeLabels());
				}
				if (transition.isPositive()) {
					labels.add(transition.getPositiveLabel());
				}
			}
		}
		return labels;
	}

	protected Set<String> getLabels(final State... states) {
		return getLabels(new ArrayIterable<State>(states));
	}

	protected int getMaxId(final Automaton a) {
		int id = 0;
		for (final State s : a) {
			id = Math.max(id, s.getId());
		}
		return id;
	}

	protected AutomatonFactory newFactory() {
		try {
			return factoryClass.newInstance();
		} catch (final Exception e) {
			return null;
		}
	}

	private Object createState(final AutomatonFactory factory, final int id, final Pair pair,
			final boolean conjunction) {
		final Object state = factory.createState();
		boolean accepting;
		if (conjunction) {
			accepting = pair.getA().isAccepting() && pair.getB().isAccepting();
		} else {
			accepting = pair.getA().isAccepting() || pair.getB().isAccepting();
		}
		factory.updateState(state, id, accepting);
		factory.addState(state);
		return state;
	}

	private Object createState(final AutomatonFactory factory, final Set<State> successor,
			final int id) {
		final Object ss = factory.createState();
		factory.updateState(ss, id, isAccepting(successor));
		factory.addState(ss);
		return ss;
	}

	private Object getStored(final Map<Set<State>, Object> storage,
			final Map<Set<State>, Object> accepting, final Set<State> states) {
		if (isAccepting(states))
			return accepting.get(states);
		else
			return storage.get(states);
	}

	private boolean isAccepting(final Set<State> successor) {
		for (final State sss : successor) {
			if (sss.isAccepting()) return true;
		}
		return false;
	}

	private Object registerNewState(final AutomatonFactory factory,
			final Map<Set<State>, Object> storage, final Map<Set<State>, Object> accepting,
			final Set<State> state, final int id) {
		final Object s = createState(factory, state, id);
		if (isAccepting(state)) {
			accepting.put(state, s);
		} else {
			storage.put(state, s);
		}
		return s;
	}
}
