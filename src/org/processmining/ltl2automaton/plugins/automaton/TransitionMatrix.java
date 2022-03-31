package org.processmining.ltl2automaton.plugins.automaton;

import java.util.Map;
import java.util.Set;

public class TransitionMatrix {

	private final Map<String, Integer> transitionNumbers;
	private final String[] transitionNames;
	private final Set<Integer> accepting;
	private final int[][] matrix;
	private final Map<State, Integer> stateNumbers;

	public TransitionMatrix(final Map<String, Integer> transitionNumbers,
			final Map<State, Integer> stateNumbers, final Set<Integer> accepting,
			final int[][] matrix) {
		this.transitionNumbers = transitionNumbers;
		this.stateNumbers = stateNumbers;
		this.accepting = accepting;
		this.matrix = matrix;

		transitionNames = new String[transitionNumbers.size() + 1];
		for (final Map.Entry<String, Integer> entry : transitionNumbers.entrySet()) {
			transitionNames[entry.getValue()] = entry.getKey();
		}
	}

	public int getInitialState() {
		return 0;
	}

	public int getNumberOfStates() {
		return matrix.length;
	}

	public int getNumberOfTransitions() {
		return transitionNumbers.size();
	}

	public int getStateNumber(final State s) {
		return stateNumbers.get(s);
	}

	public String getTransitionName(final int number) {
		return transitionNames[number];
	}

	public int getTransitionNumber(final String transition) {
		if (transitionNumbers.containsKey(transition)) return transitionNumbers.get(transition);
		return 0;
	}

	public boolean isAccepting(final int state) {
		return accepting.contains(state);
	}

	public int nextState(final int state, final int transition) {
		final int t = matrix[state][transition];
		if (t >= 0) return t;
		return matrix[state][0];
	}

	public int nextState(final int state, final String transition) {
		return nextState(state, getTransitionNumber(transition));
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		sb.append("\t.");
		for (final String s : transitionNames) {
			if (s != null) {
				sb.append('\t');
				sb.append(s);
			}
		}
		for (int i = 0; i < getNumberOfStates(); i++) {
			sb.append('\n');
			if (isAccepting(i)) {
				sb.append('(');
				sb.append(i);
				sb.append(')');
			} else {
				sb.append(i);
			}
			sb.append(':');
			for (int j = 0; j <= getNumberOfTransitions(); j++) {
				sb.append('\t');
				sb.append(nextState(i, j));
			}
		}

		return sb.toString();
	}
}
