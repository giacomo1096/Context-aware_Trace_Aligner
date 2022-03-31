package org.processmining.ltl2automaton.plugins.formula.conjunction;

import java.util.concurrent.ExecutorService;

import org.processmining.ltl2automaton.plugins.automaton.Automaton;

public class ThreadedConjunctionTreeNode extends ConjunctionTreeNode {
	@SuppressWarnings("unused")
	private final ExecutorService threadPool;

	public ThreadedConjunctionTreeNode(final ExecutorService threadPool) {
		super();
		this.threadPool = threadPool;
	}

	public ThreadedConjunctionTreeNode(final ExecutorService threadPool,
			final ConjunctionTreeNode left, final ConjunctionTreeNode right) {
		super(left, right);
		this.threadPool = threadPool;
	}

	@Override
	protected Automaton calculateProduct(final Lambda<Automaton> a1, final Lambda<Automaton> a2) {
		final BlockingQueue<Automaton> automata = new BlockingQueue<Automaton>();
		new Thread() {
			@Override
			public void run() {
				automata.put(a1.get());
			}
		}.start();
		new Thread() {
			@Override
			public void run() {
				automata.put(a2.get());
			}
		}.start();
		final Automaton a1v = automata.get();
		if (a1v.op.isEmpty()) return a1v;
		final Automaton a2v = automata.get();
		if (a2v.op.isEmpty()) return a2v;
		return a1v.op.determinize().op.intersect(a2v.op.determinize());
	}

}
