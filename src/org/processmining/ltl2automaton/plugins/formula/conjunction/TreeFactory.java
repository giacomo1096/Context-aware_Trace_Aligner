package org.processmining.ltl2automaton.plugins.formula.conjunction;

import org.processmining.ltl2automaton.plugins.formula.Formula;


public interface TreeFactory<N, L extends N> {
	L createLeaf(Formula f, char name);

	N createNode(ConjunctionTreeNode left, ConjunctionTreeNode right);
}
