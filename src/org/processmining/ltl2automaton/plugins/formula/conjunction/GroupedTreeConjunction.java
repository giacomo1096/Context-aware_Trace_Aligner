package org.processmining.ltl2automaton.plugins.formula.conjunction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.processmining.ltl2automaton.plugins.formula.Formula;
import org.processmining.ltl2automaton.plugins.ltl.LTLFormula;

/**
 * @author michael
 */
public class GroupedTreeConjunction extends UnsortedTreeConjunction {
	class PartitionResult {
		private final int weight;

		private final Collection<Formula> left, right;

		public PartitionResult(final int weight, final Collection<Formula> left,
				final Collection<Formula> right) {
			super();
			this.weight = weight;
			this.left = left;
			this.right = right;
		}

		public Collection<Formula> getLeft() {
			return left;
		}

		public Collection<Formula> getRight() {
			return right;
		}

		public int getWeight() {
			return weight;
		}
	}

	public static ConjunctionFactory<? extends GroupedTreeConjunction> getFactory(
			final TreeFactory<ConjunctionTreeNode, ConjunctionTreeLeaf> treefactory) {
		return new ConjunctionFactory<GroupedTreeConjunction>() {

			@Override
			public GroupedTreeConjunction instance() {
				return new GroupedTreeConjunction(treefactory);
			}

			@Override
			public GroupedTreeConjunction instance(final Collection<Formula> terms) {
				return new GroupedTreeConjunction(treefactory, terms);
			}

			@Override
			public GroupedTreeConjunction instance(final Formula terms) {
				return new GroupedTreeConjunction(treefactory, terms);
			}
		};
	}

	/**
	 * 
	 */
	public GroupedTreeConjunction(
			final TreeFactory<ConjunctionTreeNode, ConjunctionTreeLeaf> treefactory) {
		super(treefactory);
	}

	/**
	 * @param formulas
	 */
	public GroupedTreeConjunction(
			final TreeFactory<ConjunctionTreeNode, ConjunctionTreeLeaf> treefactory,
			final Collection<Formula> formulas) {
		super(treefactory, formulas);
	}

	/**
	 * @param f
	 */
	public GroupedTreeConjunction(
			final TreeFactory<ConjunctionTreeNode, ConjunctionTreeLeaf> treefactory, final Formula f) {
		super(treefactory, f);
	}

	@Override
	public synchronized void balance() {
		final Collection<Formula> formulas = new ArrayList<Formula>(formulaMap.keySet());
		setAll(formulas);
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
		root = buildTree(new HashSet<Formula>(formulas));
	}

	private ConjunctionTreeNode buildTree(final Collection<Formula> formulas) {
		final int c = formulas.size();
		assert c > 0;
		if (c == 1) {
			final ConjunctionTreeLeaf leaf = getTreeFactory().createLeaf(
					formulas.iterator().next(), nextName++);
			formulaMap.put(formulas.iterator().next(), leaf);
			leaves.add(leaf);
			return leaf;
		}
		int i = 1;
		while (i < c) {
			i *= 2;
		}
		final int b = i - c;
		final int a = i - 2 * b;
		PartitionResult partition;
		if (2 * a >= i) {
			partition = partition(formulas, i / 2, 10);
		} else { // We can only get in this case if c > 4 => i >= 8
			partition = partition(formulas, c - i / 4, 10);
		}
		return getTreeFactory().createNode(buildTree(partition.getLeft()),
				buildTree(partition.getRight()));
	}

	private Map<String, boolean[]> computeAPs(final Collection<Formula> formulas,
			final Map<Formula, Integer> formulaId, final int count) {
		final Map<String, boolean[]> aps = new HashMap<String, boolean[]>();
		// aps.put("::next::", new boolean[count]);
		for (final Formula f : formulas) {
			final int pos = formulaId.get(f);
			final List<String> atomic = new ArrayList<String>();
			getAtomic(f, atomic);
			for (final String a : atomic) {
				boolean[] ap = aps.get(a);
				if (ap == null) {
					ap = new boolean[count];
					aps.put(a, ap);
				}
				ap[pos] = true;
			}
			// if (FormulaTools.containsNext(f)) {
			// aps.get("::next::")[pos] = true;
			// }
		}
		return aps;
	}

	private int[][] computeNeighbours(final int count, final Map<String, boolean[]> aps) {
		final int[][] neighbours = new int[count][count];
		for (final boolean[] ap : aps.values()) {
			for (int i = 0; i < count - 1; i++) {
				if (ap[i]) {
					for (int j = i + 1; j < count; j++) {
						if (ap[j]) {
							neighbours[i][j]++;
							neighbours[j][i]++;
						}
					}
				}
			}
		}
		return neighbours;
	}

	private boolean[] createRandomPartition(final int size, final int count, final Random r) {
		final boolean[] partition = new boolean[count];
		for (int i = 0; i < size; i++) {
			int pos;
			do {
				pos = r.nextInt(count);
			} while (partition[pos]);
			partition[pos] = true;
		}
		return partition;
	}

	private int evaluatePartition(final int count, final int[][] neighbours,
			final boolean[] partition) {
		int cost = 0;
		for (int i = 0; i < count - 1; i++) {
			if (partition[i]) {
				for (int j = i + 1; j < count; j++) {
					if (!partition[j]) {
						cost += neighbours[i][j];
					}
				}
			}
		}
		return cost;
	}

	private void getAtomic(final Formula f, final List<String> ap) {
		if (f == null) return;
		if (f.getType() == LTLFormula.PROPOSITION) {
			ap.add(f.getName());
		}
		getAtomic(f.getLeft(), ap);
		getAtomic(f.getRight(), ap);
	}

	private int improve(final Random r, final int[][] neighbours, final int count,
			final boolean[] partition, final int cost, final int repetitions) {
		int i = 0;
		int resultCost = cost;
		do {
			i++;
			final int from = r.nextInt(count);
			int to;
			do {
				to = r.nextInt(count);
			} while (from == to || partition[from] == partition[to]);
			final int tmpCost = updateCost(neighbours, count, partition, resultCost, from, to);
			assert tmpCost >= 0;
			if (tmpCost < resultCost) {
				resultCost = tmpCost;
				partition[from] = !partition[from];
				partition[to] = !partition[to];
				i = 0;
			}
		} while (i < repetitions);
		return resultCost;
	}

	private int updateCost(final int[][] neighbours, final int count, final boolean[] partition,
			final int resultCost, final int from, final int to) {
		int tmpCost = resultCost;
		for (int j = 0; j < count; j++) {
			if (j != to) {
				if (partition[j] == partition[from]) {
					tmpCost += neighbours[j][from];
				} else {
					tmpCost -= neighbours[j][from];
				}
			}
			if (j != from) {
				if (partition[j] == partition[to]) {
					tmpCost += neighbours[j][to];
				} else {
					tmpCost -= neighbours[j][to];
				}
			}
		}
		return tmpCost;
	}

	PartitionResult partition(final Collection<Formula> formulas, final int size,
			final int repetitions) {
		assert formulas.size() >= 2;
		assert 0 < size;
		assert size < formulas.size();
		assert repetitions > 0;
		if (formulas.size() == 2) {
			final Iterator<Formula> it = formulas.iterator();
			return new PartitionResult(0, Collections.singletonList(it.next()),
					Collections.singletonList(it.next()));
		}
		final Map<Formula, Integer> formulaId = new HashMap<Formula, Integer>();
		int count = 0;
		for (final Formula f : formulas) {
			formulaId.put(f, count++);
		}
		assert size < count;

		final Map<String, boolean[]> aps = computeAPs(formulas, formulaId, count);
		final int[][] neighbours = computeNeighbours(count, aps);

		final Random r = new Random();
		boolean[] partition = null;
		int cost = Integer.MAX_VALUE;

		for (int i = 0; i < repetitions && cost > 0; i++) {
			final boolean[] tmpPartition = createRandomPartition(size, count, r);
			final int tmpCost = improve(r, neighbours, count, tmpPartition,
					evaluatePartition(count, neighbours, tmpPartition), repetitions * 2);
			if (tmpCost < cost) {
				cost = tmpCost;
				partition = tmpPartition;
			}
		}

		final Collection<Formula> left = new ArrayList<Formula>(size);
		final Collection<Formula> right = new ArrayList<Formula>(count - size);
		for (final Formula f : formulas) {
			if (partition[formulaId.get(f)]) {
				left.add(f);
			} else {
				right.add(f);
			}
		}
		return new PartitionResult(cost, left, right);
	}
}
