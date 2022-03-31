package org.processmining.ltl2automaton.plugins.formula.conjunction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.naming.OperationNotSupportedException;

import org.processmining.ltl2automaton.plugins.automaton.Automaton;
import org.processmining.ltl2automaton.plugins.formula.Formula;

public class PartitionedConjunction extends UnsortedTreeConjunction {
	public static ConjunctionFactory<? extends PartitionedConjunction> getFactory(
			final TreeFactory<ConjunctionTreeNode, ConjunctionTreeLeaf> treefactory,
			final ConjunctionFactory<? extends UnsortedTreeConjunction> factory) {
		return new ConjunctionFactory<PartitionedConjunction>() {

			@Override
			public PartitionedConjunction instance() {
				return new PartitionedConjunction(treefactory, factory);
			}

			@Override
			public PartitionedConjunction instance(final Collection<Formula> terms) {
				return new PartitionedConjunction(treefactory, factory, terms);
			}

			@Override
			public PartitionedConjunction instance(final Formula terms) {
				return new PartitionedConjunction(treefactory, factory, terms);
			}
		};
	}

	private final ConjunctionFactory<? extends UnsortedTreeConjunction> factory;

	Map<String, Set<Formula>> partitions = new HashMap<String, Set<Formula>>();
	Map<Set<Formula>, UnsortedTreeConjunction> children = new HashMap<Set<Formula>, UnsortedTreeConjunction>();
	Map<ConjunctionTreeNode, ConjunctionTreeNode> roots = new HashMap<ConjunctionTreeNode, ConjunctionTreeNode>();

	public PartitionedConjunction(
			final TreeFactory<ConjunctionTreeNode, ConjunctionTreeLeaf> treefactory,
			final ConjunctionFactory<? extends UnsortedTreeConjunction> factory) {
		super(treefactory);
		this.factory = factory;
	}

	public PartitionedConjunction(
			final TreeFactory<ConjunctionTreeNode, ConjunctionTreeLeaf> treefactory,
			final ConjunctionFactory<? extends UnsortedTreeConjunction> factory,
			final Collection<Formula> terms) {
		super(treefactory);
		this.factory = factory;
		setAll(terms);
	}

	public PartitionedConjunction(
			final TreeFactory<ConjunctionTreeNode, ConjunctionTreeLeaf> treefactory,
			final ConjunctionFactory<? extends UnsortedTreeConjunction> factory, final Formula f) {
		super(treefactory);
		this.factory = factory;
		setAll(f);
	}

	@Override
	public synchronized void add(final Formula term) throws OperationNotSupportedException {
		if (root == null) {
			setAll(Collections.singleton(term));
			return;
		}
		final Map<ConjunctionTreeNode, Automaton> cache = CacheTools.getCached(root);
		final List<Set<Formula>> oldPartitions = mergePartitions(cache, term);
		if (oldPartitions.size() > 1) {
			final Set<Formula> newPartition = new HashSet<Formula>();
			newPartition.add(term);
			for (final Set<Formula> partition : oldPartitions) {
				final UnsortedTreeConjunction oldChild = children.remove(partition);
				if (oldChild != null) {
					replaceRoot(null, oldChild.root);
				}
				newPartition.addAll(partition);
			}
			final UnsortedTreeConjunction newChild = factory.instance(newPartition);
			CacheTools.setCaches(cache, newChild.root);
			children.put(newPartition, newChild);
			addChild(newChild);
		} else {
			assert oldPartitions.size() == 1;
			final Set<Formula> partition = oldPartitions.get(0);
			UnsortedTreeConjunction child = children.get(partition);
			if (child != null) {
				final ConjunctionTreeNode oldRoot = child.root;
				child.add(term);
				replaceRoot(child, oldRoot);
			} else {
				child = factory.instance(term);
				children.put(partition, child);
				addChild(child);
			}
		}
	}

	public Collection<Automaton> getAutomata() {
		final List<Automaton> result = new ArrayList<Automaton>();
		for (final UnsortedTreeConjunction child : children.values()) {
			result.add(child.getAutomaton());
		}
		return result;
	}

	public int getNumberOfPartitions() {
		return children.size();
	}

	public Collection<Set<Formula>> getPartitions() {
		return new HashSet<Set<Formula>>(partitions.values());
	}

	@Override
	public synchronized void remove(final Formula term) throws OperationNotSupportedException {
		// This does not split partitions if possible
		final Set<Formula> partition = partitions.get(FormulaTools.getAtomicPropositions(term)
				.iterator().next());
		if (partition != null) { // Formula is not here
			final UnsortedTreeConjunction child = children.remove(partition);
			partition.remove(term);
			final ConjunctionTreeNode oldRoot = child.root;
			child.remove(term);
			children.put(partition, child);
			replaceRoot(child, oldRoot);
		}
	}

	@Override
	public void reset() {
		super.reset();
		partitions.clear();
		children.clear();
	}

	@Override
	public synchronized void setAll(final Collection<Formula> formulas) {
		reset();
		final Map<ConjunctionTreeNode, Automaton> cache = new HashMap<ConjunctionTreeNode, Automaton>();
		for (final Formula formula : formulas) {
			mergePartitions(cache, formula);
		}
		for (final Set<Formula> formulaSet : new HashSet<Set<Formula>>(partitions.values())) {
			final UnsortedTreeConjunction child = factory.instance(formulaSet);
			children.put(formulaSet, child);
			addChild(child);
		}
		CacheTools.setCaches(cache, root);
	}

	private void addChild(final UnsortedTreeConjunction child) {
		assert child.root != null;
		if (root == null) {
			root = child.root;
		} else {
			final ConjunctionTreeNode newRoot = getTreeFactory().createNode(root, child.root);
			roots.put(root, newRoot);
			roots.put(child.root, newRoot);
			root = newRoot;
		}
	}

	private Collection<String> getAtomicPropositions(
			final Map<ConjunctionTreeNode, Automaton> cache, final Formula formula) {
		final Collection<String> atomicPropositions = FormulaTools.getAtomicPropositions(formula);
		// if (FormulaTools.containsNext(formula)) {
		// atomicPropositions.add("::next::");
		// }
		if (!FormulaTools.acceptsAllInit(cache, formula)) {
			atomicPropositions.add("::!initall::");
		}
		return atomicPropositions;
	}

	private Set<Formula> getPartition(final String ap) {
		Set<Formula> partition = partitions.get(ap);
		if (partition == null) {
			partition = new HashSet<Formula>();
			partitions.put(ap, partition);
		}
		return partition;
	}

	private List<Set<Formula>> mergePartitions(final Map<ConjunctionTreeNode, Automaton> cache,
			final Formula formula) {
		final Collection<String> atomicPropositions = getAtomicPropositions(cache, formula);
		final List<Set<Formula>> oldPartitions = new ArrayList<Set<Formula>>();
		Set<Formula> last = null;
		for (final String ap : atomicPropositions) {
			final Set<Formula> partition = getPartition(ap);
			if (last == null) {
				oldPartitions.add(partition);
				last = partition;
			} else if (last != partition) {
				if (!partition.isEmpty()) {
					oldPartitions.add(partition);
				}
				mergePartitions(cache, last, partition);
			}
			final UnsortedTreeConjunction oldChild = children.remove(last);
			last.add(formula);
			if (oldChild != null) {
				children.put(last, oldChild); // We need this to not change the key
			}
			partitions.put(ap, last);
		}
		return oldPartitions;
	}

	private void mergePartitions(final Map<ConjunctionTreeNode, Automaton> cache,
			final Set<Formula> last, final Set<Formula> partition) {
		final UnsortedTreeConjunction oldChild = children.remove(last);
		last.addAll(partition);
		if (oldChild != null) {
			children.put(last, oldChild); // We need this to not change the key
		}
		for (final Formula f : partition) {
			final Collection<String> atomicPropositions = getAtomicPropositions(cache, f);
			for (final String p : atomicPropositions) {
				assert partitions.get(p) == last || partitions.get(p) == partition;
				partitions.put(p, last);
			}
		}
	}

	private void replaceRoot(final UnsortedTreeConjunction child, final ConjunctionTreeNode oldRoot) {
		final ConjunctionTreeNode newRoot = child != null ? child.root : null;
		if (oldRoot != newRoot) {
			final ConjunctionTreeNode parent = roots.get(oldRoot);
			if (parent == null) {
				if (root == oldRoot) {
					root = newRoot;
				} else if (newRoot != null) {
					addChild(child);
				}
				assert roots.isEmpty();
			} else {
				if (newRoot == null) {
					final ConjunctionTreeNode gparent = parent.getParent();
					final ConjunctionTreeNode other = other(oldRoot);
					if (gparent != null) {
						replace(gparent, parent, other);
						roots.put(other, gparent);
					} else {
						root = other;
						roots.remove(other);
					}
				} else {
					replace(parent, oldRoot, newRoot);
					roots.remove(oldRoot);
					roots.put(newRoot, parent);
				}
			}
		}
	}
}
