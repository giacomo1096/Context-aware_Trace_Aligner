package org.processmining.ltl2automaton.plugins.formula.conjunction;

import java.util.Collection;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import org.processmining.ltl2automaton.plugins.formula.Formula;

/**
 * Note: All the hard work happens when inserting into the sorted set (the comparator extracts the
 * automaton).
 * 
 * @author mwesterg
 */
public class SizeSortedTreeConjunction extends UnsortedTreeConjunction {
	private static class NodeComparator implements Comparator<ConjunctionTreeNode> {
		@Override
		public int compare(final ConjunctionTreeNode arg0, final ConjunctionTreeNode arg1) {
			int result = arg0.getAutomaton().getStateCount() - arg1.getAutomaton().getStateCount();
			if (result == 0 && arg0 != arg1) {
				result = arg0.getAutomaton().getTransitionCount()
						- arg1.getAutomaton().getTransitionCount();
				if (result == 0)
					return arg0.getAutomaton().hashCode() - arg1.getAutomaton().hashCode();
			}
			return result;
		}
	}

	public static ConjunctionFactory<? extends SizeSortedTreeConjunction> getFactory(
			final TreeFactory<ConjunctionTreeNode, ConjunctionTreeLeaf> treefactory) {
		return new ConjunctionFactory<SizeSortedTreeConjunction>() {

			@Override
			public SizeSortedTreeConjunction instance() {
				return new SizeSortedTreeConjunction(treefactory);
			}

			@Override
			public SizeSortedTreeConjunction instance(final Collection<Formula> terms) {
				return new SizeSortedTreeConjunction(treefactory, terms);
			}

			@Override
			public SizeSortedTreeConjunction instance(final Formula terms) {
				return new SizeSortedTreeConjunction(treefactory, terms);
			}
		};
	}

	public SizeSortedTreeConjunction(
			final TreeFactory<ConjunctionTreeNode, ConjunctionTreeLeaf> treefactory) {
		super(treefactory);
	}

	public SizeSortedTreeConjunction(
			final TreeFactory<ConjunctionTreeNode, ConjunctionTreeLeaf> treefactory,
			final Collection<Formula> formulas) {
		super(treefactory, formulas);
	}

	public SizeSortedTreeConjunction(
			final TreeFactory<ConjunctionTreeNode, ConjunctionTreeLeaf> treefactory, final Formula f) {
		super(treefactory, f);
	}

	@Override
	public synchronized void remove(final Formula term) {
		removeNode(formulaMap.remove(term));
	}

	/**
	 * @see ltl2aut.formula.conjunction.UnsortedTreeConjunction#setAll(java.util.Collection)
	 */
	@Override
	public synchronized void setAll(final Collection<Formula> formulas) {
		reset();
		if (formulas.isEmpty()) return;
		final SortedSet<ConjunctionTreeNode> f = new TreeSet<ConjunctionTreeNode>(
				new NodeComparator());
		for (final Formula formula : formulas) {
			if (!formulaMap.containsKey(formula)) {
				final ConjunctionTreeLeaf leaf = getTreeFactory().createLeaf(formula, nextName++);
				formulaMap.put(formula, leaf);
				f.add(leaf);
			}
		}
		root = buildTree(makePowerOfTwo(f));
	}

	private ConjunctionTreeNode buildOne(final SortedSet<ConjunctionTreeNode> f,
			final SortedSet<ConjunctionTreeNode> result) {
		assert f.size() >= 2;
		final ConjunctionTreeNode first = f.first();
		f.remove(first);
		final ConjunctionTreeNode last = f.last();
		f.remove(last);
		final ConjunctionTreeNode node = getTreeFactory().createNode(first, last);
		result.add(node);
		return node;
	}

	private ConjunctionTreeNode buildTree(final SortedSet<ConjunctionTreeNode> f) {
		SortedSet<ConjunctionTreeNode> input = f;
		SortedSet<ConjunctionTreeNode> result;

		while (input.size() > 1) {
			result = new TreeSet<ConjunctionTreeNode>(new NodeComparator());
			while (!input.isEmpty()) {
				buildOne(input, result);
			}
			input = result;
		}

		return input.first();
	}

	private SortedSet<ConjunctionTreeNode> makePowerOfTwo(final SortedSet<ConjunctionTreeNode> f) {
		final SortedSet<ConjunctionTreeNode> result = new TreeSet<ConjunctionTreeNode>(
				new NodeComparator());
		int i = 1;
		while (i < f.size()) {
			i *= 2;
		}
		if (f.size() == i) return f;
		i /= 2;

		while (result.size() + f.size() != i) {
			final ConjunctionTreeNode node = buildOne(f, result);
			leaves.add(node.getLeft());
			leaves.add(node.getRight());
		}

		leaves.addAll(f);
		result.addAll(f);
		return result;
	}

}
