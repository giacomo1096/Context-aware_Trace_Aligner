package org.processmining.ltl2automaton.plugins.automaton;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.processmining.ltl2automaton.plugins.AutomatonFactory;

public class DFAOperations extends NDFAOperations {
	private static final DFAOperations instance = new DFAOperations(DefaultAutomatonFactory.class);

	public static DFAOperations getInstance() {
		return DFAOperations.instance;
	}

	public DFAOperations(final Class<? extends AutomatonFactory> factoryClass) {
		super(factoryClass);
	}

	/**
	 * Return an automaton accepting the same language as a, but where every transition is fully
	 * specified. The parameter is not modified.
	 * 
	 * @param a
	 * @return
	 */
	public DeterministicAutomaton complete(final DeterministicAutomaton a) {
		if (a.isCompleted()) return a;
		final AutomatonFactory factory = newFactory();
		final Map<State, Object> stateMap = copyStates(a, factory);
		final int id = getMaxId(a);

		Object dead = null;
		for (final State s : a) {
			final Set<String> labels = getLabels(Collections.singleton(s));
			final Set<String> nlabels = new HashSet<String>(labels);
			boolean negativeSeen = false;
			for (final Transition t : s.getOutput()) {
				if (t.isAll()) {
					labels.clear();
					nlabels.clear();
					factory.addTransition(stateMap.get(s), stateMap.get(t.getTarget()),
							t.getLabel());
				} else if (t.isPositive()) {
					labels.remove(t.getPositiveLabel());
					factory.addTransition(stateMap.get(s), stateMap.get(t.getTarget()),
							t.getLabel());
				} else if (t.isNegative()) {
					assert !negativeSeen;
					nlabels.removeAll(t.getNegativeLabels());
					factory.addTransition(stateMap.get(s), stateMap.get(t.getTarget()),
							t.getLabel());
					negativeSeen = true;
				}
			}

			if (!labels.isEmpty()) {
				assert negativeSeen; // If we have any positive left, we must already have a single
				// negative transition
				assert nlabels.isEmpty();
				for (final String label : labels) {
					factory.addPropositionTransition(stateMap.get(s),
							dead = getDead(dead, factory, id), label);
				}
			}
			if (!nlabels.isEmpty()) {
				assert labels.isEmpty(); // If we have negative transitions left, we must have seen
				// all positive and have no negative transition
				assert !negativeSeen;
				factory.addNegativePropositionsTransition(stateMap.get(s),
						dead = getDead(dead, factory, id), nlabels);
			}
			if (s.getOutputSize() == 0) {
				assert labels.isEmpty();
				assert nlabels.isEmpty();
				assert !negativeSeen;
				factory.addAllTransition(stateMap.get(s), dead = getDead(dead, factory, id));
			}
		}
		return new DeterministicAutomaton(factory.getAutomaton(), true);
	}

	/**
	 * Return transition matrix (state# -> transition# -> state#). Input must be completed.
	 * 
	 * @param a
	 * @return
	 */
	public TransitionMatrix getTransitionMatrix(final DeterministicAutomaton aa) {
		final DeterministicAutomaton a = aa.op.complete();
		int i = 1;
		final Map<String, Integer> transitionNumbers = new HashMap<String, Integer>();
		for (final String label : getLabels(a)) {
			transitionNumbers.put(label, i++);
		}

		final Map<State, Integer> stateNumbers = new HashMap<State, Integer>();
		final Set<Integer> accepting = new HashSet<Integer>();
		i = 1;
		for (final State s : a) {
			if (s == a.getInit()) {
				stateNumbers.put(s, 0);
			} else {
				stateNumbers.put(s, i++);
			}
			if (s.isAccepting()) {
				accepting.add(stateNumbers.get(s));
			}
		}

		final int[][] result = new int[stateNumbers.size()][transitionNumbers.size() + 1];
		for (i = 0; i < result.length; i++) {
			final int[] row = result[i];
			for (int j = 0; j < row.length; j++) {
				row[j] = -1;
			}
		}
		for (final Transition t : a.transitions()) {
			final Integer source = stateNumbers.get(t.getSource());
			final Integer target = stateNumbers.get(t.getTarget());
			if (t.isAll()) {
				final int[] row = result[source];
				for (int j = 0; j <= transitionNumbers.size(); j++) {
					row[j] = target;
				}
			} else if (t.isPositive()) {
				final int transition = transitionNumbers.get(t.getPositiveLabel());
				result[source][transition] = target;
			} else if (t.isNegative()) {
				result[source][0] = target;
			}
		}
		return new TransitionMatrix(transitionNumbers, stateNumbers, accepting, result);
	}

	/**
	 * Construct the synchronized product of the two automata (i.e., the automaton accepting the
	 * intersection of the languages accepted by the two automata).
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public DeterministicAutomaton intersect(final DeterministicAutomaton a,
			final DeterministicAutomaton b) {
		return buildProduct(a, b, true);
	}

	/**
	 * Return a (possibly smaller) automaton accepting the same language as a. The parameter is not
	 * modified. TODO - use minimizeSimple for now (worse execution time). Input must be completed.
	 * 
	 * @param a
	 * @return
	 */
	public DeterministicAutomaton minimize(final DeterministicAutomaton a) {
		return minimizeSimple(a);
	}

	/**
	 * Return a (possibly smaller) automaton accepting the same language as a. The parameter is not
	 * modified. Input must be completed.
	 * 
	 * @param a
	 * @return
	 */
	public DeterministicAutomaton minimizeSimple(final DeterministicAutomaton aa) {
		final DeterministicAutomaton a = aa.op.complete();
		final AutomatonFactory factory = newFactory();
		final TransitionMatrix matrix = getTransitionMatrix(a);
		final int[] classes = new int[a.getStateCount()];
		final List<List<Integer>> reverseMap = new ArrayList<List<Integer>>();
		reverseMap.add(new LinkedList<Integer>());
		reverseMap.add(new LinkedList<Integer>());
		int nextClass = 2;
		for (final State s : a) {
			final int id = matrix.getStateNumber(s);
			if (s.isAccepting()) {
				classes[id] = 0;
				reverseMap.get(0).add(id);
			} else {
				classes[id] = 1;
				reverseMap.get(1).add(id);
			}
		}

		if (reverseMap.get(0).isEmpty() || reverseMap.get(1).isEmpty()) {
			final Object state = factory.createState();
			factory.addState(state);
			factory.initialState(state);
			factory.addAllTransition(state, state);
			factory.updateState(state, 0, !reverseMap.get(0).isEmpty());
			return new DeterministicAutomaton(factory.getAutomaton(), true);
		}

		final List<Map<Integer, Integer>> successorClasses = new ArrayList<Map<Integer, Integer>>();
		for (int i = 0; i < matrix.getNumberOfStates(); i++) {
			successorClasses.add(new HashMap<Integer, Integer>());
			final int def = classes[matrix.nextState(i, 0)];
			successorClasses.get(i).put(0, def);
			for (int j = 1; j <= matrix.getNumberOfTransitions(); j++) {
				final int successor = classes[matrix.nextState(i, j)];
				if (successor != def) {
					successorClasses.get(i).put(j, successor);
				}
			}
		}

		// System.out.println(matrix);
		boolean changed = false;
		do {
			changed = false;
			// System.out.println(Arrays.toString(classes));
			// System.out.println(reverseMap);
			// for (int i = 0; i < matrix.getNumberOfStates(); i++) {
			// System.out.println("" + i + ": \t" + successorClasses.get(i));
			// }
			int currentClass = 0;
			for (final List<Integer> classMembers : new ArrayList<List<Integer>>(reverseMap)) {
				final Map<Map<Integer, Integer>, List<Integer>> subClasses = new HashMap<Map<Integer, Integer>, List<Integer>>();
				for (final int classMember : classMembers) {
					List<Integer> subClass = subClasses.get(successorClasses.get(classMember));
					if (subClass == null) {
						subClass = new ArrayList<Integer>();
						subClasses.put(successorClasses.get(classMember), subClass);
					}
					subClass.add(classMember);
				}
				if (subClasses.size() > 1) {
					// System.out.println("Splitting " + subClasses);
					// System.out.println("Before " + reverseMap);
					boolean first = true;
					for (final Map.Entry<Map<Integer, Integer>, List<Integer>> subClass : subClasses
							.entrySet()) {
						if (!first) {
							final int newClass = nextClass++;
							for (final int member : subClass.getValue()) {
								classes[member] = newClass;
							}
							reverseMap.add(subClass.getValue());
						} else {
							first = false;
							reverseMap.set(currentClass, subClass.getValue());
						}
					}
					// System.out.println("After  " + reverseMap);
					changed = true;
				}
				currentClass++;
			}
			for (int i = 0; i < matrix.getNumberOfStates(); i++) {
				final int def = classes[matrix.nextState(i, 0)];
				successorClasses.get(i).put(0, def);
				for (int j = 1; j <= matrix.getNumberOfTransitions(); j++) {
					final int successor = classes[matrix.nextState(i, j)];
					if (successor != def) {
						successorClasses.get(i).put(j, successor);
					}
				}
			}
		} while (changed);

		final Object[] newStates = new Object[nextClass];
		for (int i = 0; i < nextClass; i++) {
			newStates[i] = factory.createState();
			factory.updateState(newStates[i], i, matrix.isAccepting(reverseMap.get(i).get(0))); // Set
			// accepting
			// equal
			// to
			// accepting
			// of
			// any
			// member
			// of
			// class
			factory.addState(newStates[i]);
		}
		factory.initialState(newStates[classes[matrix.getInitialState()]]);
		for (int i = 0; i < nextClass; i++) {
			final int representant = reverseMap.get(i).get(0);
			final Map<Integer, Integer> transitions = successorClasses.get(representant);
			final List<String> labels = new ArrayList<String>();
			for (final Map.Entry<Integer, Integer> transition : transitions.entrySet()) {
				if (transition.getKey() != 0) {
					final String label = matrix.getTransitionName(transition.getKey());
					labels.add(label);
					factory.addPropositionTransition(newStates[i],
							newStates[transition.getValue()], label);
				}
			}
			final int def = classes[matrix.nextState(representant, 0)];
			if (labels.isEmpty()) {
				factory.addAllTransition(newStates[i], newStates[def]);
			} else {
				factory.addNegativePropositionsTransition(newStates[i], newStates[def], labels);
			}
		}
		return new DeterministicAutomaton(factory.getAutomaton(), true);
	}

	/**
	 * Return an automaton accepting the complement of the language accepted by a. The parameter is
	 * not modified. Assumes the automaton is completed (i.e., call complete if you are not sure).
	 * 
	 * @param a
	 * @return
	 */
	public DeterministicAutomaton negate(final DeterministicAutomaton aa) {
		final DeterministicAutomaton a = aa.op.complete();
		final AutomatonFactory factory = newFactory();
		final Map<State, Object> stateMap = copyStates(a, factory);
		copyTransitions(a, factory, stateMap);
		final Automaton result = factory.getAutomaton();
		for (final State s : result) {
			s.setAccepting(!s.isAccepting());
		}
		return new DeterministicAutomaton(result, true);
	}

	/**
	 * Construct the synchronized product of the two automata (i.e., the automaton accepting the
	 * union of the languages accepted by the two automata).
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public DeterministicAutomaton union(final DeterministicAutomaton a,
			final DeterministicAutomaton b) {
		return buildProduct(a.op.complete(), b.op.complete(), false);
	}

	private DeterministicAutomaton buildProduct(final DeterministicAutomaton a,
			final DeterministicAutomaton b, final boolean conjunction) {
		final AutomatonFactory factory = newFactory();
		final HashMap<Pair, Object> storage = new HashMap<Pair, Object>();
		final LinkedList<Pair> waiting = new LinkedList<Pair>();
		int id = 0;
		final Pair init = new Pair(a.getInit(), b.getInit());
		final Object initState = createState(factory, id++, init, conjunction);
		storage.put(init, initState);
		factory.initialState(initState);
		waiting.addLast(init);

		while (!waiting.isEmpty()) {
			final Pair s = waiting.removeFirst();
			final Object ss = storage.get(s);

			final Set<String> labels = getLabels(s.getA(), s.getB());
			for (final String label : new HashSet<String>(labels)) {
				State newA = null, newB = null;
				for (final Transition t : s.getA().getOutput()) {
					if (t.isPositive() && t.getPositiveLabel().equals(label)) {
						newA = t.getTarget();
						break;
					} else if (t.isNegative() && !t.getNegativeLabels().contains(label)) {
						newA = t.getTarget();
						break;
					} else if (t.isAll()) {
						newA = t.getTarget();
						break;
					}
				}

				if (newA != null) {
					for (final Transition t : s.getB().getOutput()) {
						if (t.isPositive() && t.getPositiveLabel().equals(label)) {
							newB = t.getTarget();
							break;
						} else if (t.isNegative() && !t.getNegativeLabels().contains(label)) {
							newB = t.getTarget();
							break;
						} else if (t.isAll()) {
							newB = t.getTarget();
							break;
						}
					}

					if (newB != null) {
						final Pair t = new Pair(newA, newB);
						Object tt = storage.get(t);
						if (tt == null) {
							tt = createState(factory, id++, t, conjunction);
							storage.put(t, tt);
							waiting.addLast(t);
						}
						factory.addPropositionTransition(ss, tt, label);
					}
				}
			}
			State newA = null, newB = null;
			for (final Transition t : s.getA().getOutput()) {
				if (t.isNegative() || t.isAll()) {
					newA = t.getTarget();
					break;
				}
			}

			if (newA != null) {
				for (final Transition t : s.getB().getOutput()) {
					if (t.isNegative() || t.isAll()) {
						newB = t.getTarget();
						break;
					}
				}

				if (newB != null) {
					final Pair t = new Pair(newA, newB);
					Object tt = storage.get(t);
					if (tt == null) {
						tt = createState(factory, id++, t, conjunction);
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
		return new DeterministicAutomaton(factory.getAutomaton(), false);
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

	private Object getDead(final Object dead, final AutomatonFactory factory, final int id) {
		if (dead != null) return dead;
		final Object newDead = factory.createState();
		factory.updateState(newDead, id + 1, false);
		factory.addState(newDead);
		factory.addAllTransition(newDead, newDead);
		return newDead;
	}
}