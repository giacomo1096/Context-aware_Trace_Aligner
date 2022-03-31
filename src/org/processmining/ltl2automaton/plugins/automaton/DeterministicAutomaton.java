package org.processmining.ltl2automaton.plugins.automaton;

/**
 * Tagging subclass to distinguish between deterministic and non-deterministic autonmata
 * 
 * @author mwesterg
 */
public class DeterministicAutomaton extends Automaton {
	public class DeterministicOperations extends Operations {
		public DeterministicAutomaton complete() {
			return DFAOperations.getInstance().complete(DeterministicAutomaton.this);
		}

		public TransitionMatrix getTransitionMatrix() {
			return DFAOperations.getInstance().getTransitionMatrix(DeterministicAutomaton.this);
		}

		public DeterministicAutomaton intersect(final DeterministicAutomaton b) {
			return DFAOperations.getInstance().intersect(DeterministicAutomaton.this, b);
		}

		public DeterministicAutomaton minimize() {
			return DFAOperations.getInstance().minimize(DeterministicAutomaton.this);
		}

		public DeterministicAutomaton negate() {
			return DFAOperations.getInstance().negate(DeterministicAutomaton.this);
		}

		@Override
		public DeterministicAutomaton reduce() {
			return new DeterministicAutomaton(super.reduce(), false);
		}

		@Override
		public DeterministicAutomaton renumber() {
			super.renumber();
			return DeterministicAutomaton.this;
		}

		public DeterministicAutomaton union(final DeterministicAutomaton b) {
			return DFAOperations.getInstance().union(DeterministicAutomaton.this, b);
		}
	}

	private final boolean completed;

	public final DeterministicOperations op = new DeterministicOperations();

	public DeterministicAutomaton(final Automaton a, final boolean completed) {
		this.completed = completed;
		scount = a.scount;
		init = a.init;
		expandCount = a.expandCount;
	}

	protected DeterministicAutomaton(final boolean complated) {
		completed = complated;
	}

	public boolean isCompleted() {
		return completed;
	}
}
