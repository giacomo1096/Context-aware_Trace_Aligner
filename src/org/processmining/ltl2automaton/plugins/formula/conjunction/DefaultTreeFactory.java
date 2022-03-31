package org.processmining.ltl2automaton.plugins.formula.conjunction;

import org.processmining.ltl2automaton.plugins.formula.Formula;


public class DefaultTreeFactory implements TreeFactory<ConjunctionTreeNode, ConjunctionTreeLeaf> {
	private final static DefaultTreeFactory instance = new DefaultTreeFactory();

	/**
	 * @return the instance
	 */
	public static TreeFactory<ConjunctionTreeNode, ConjunctionTreeLeaf> getInstance() {
		return instance;
	}

	@Override
	public ConjunctionTreeLeaf createLeaf(final Formula f, final char name) {
		return new ConjunctionTreeLeaf(f, name);
	}

	@Override
	public ConjunctionTreeNode createNode(final ConjunctionTreeNode left,
			final ConjunctionTreeNode right) {
		return new ConjunctionTreeNode(left, right);
	}

}
