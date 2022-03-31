package org.processmining.ltl2automaton.plugins.formula.conjunction;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.naming.OperationNotSupportedException;

import org.processmining.ltl2automaton.plugins.automaton.Automaton;
import org.processmining.ltl2automaton.plugins.formula.Formula;
import org.processmining.ltl2automaton.plugins.ltl.LTLFormula;

public class UnsortedTreeConjunction implements Conjunction {
	public static ConjunctionFactory<? extends UnsortedTreeConjunction> getFactory(
			final TreeFactory<ConjunctionTreeNode, ConjunctionTreeLeaf> treefactory) {
		return new ConjunctionFactory<UnsortedTreeConjunction>() {

			@Override
			public UnsortedTreeConjunction instance() {
				return new UnsortedTreeConjunction(treefactory);
			}

			@Override
			public UnsortedTreeConjunction instance(final Collection<Formula> terms) {
				return new UnsortedTreeConjunction(treefactory, terms);
			}

			@Override
			public UnsortedTreeConjunction instance(final Formula terms) {
				return new UnsortedTreeConjunction(treefactory, terms);
			}
		};
	}

	ConjunctionTreeNode root;
	protected LinkedList<ConjunctionTreeNode> leaves = new LinkedList<ConjunctionTreeNode>();
	protected Map<Formula, ConjunctionTreeLeaf> formulaMap = new HashMap<Formula, ConjunctionTreeLeaf>();
	protected char nextName = 'A';

	private final TreeFactory<ConjunctionTreeNode, ConjunctionTreeLeaf> treefactory;

	public UnsortedTreeConjunction(
			final TreeFactory<ConjunctionTreeNode, ConjunctionTreeLeaf> treefactory) {
		this.treefactory = treefactory;
		// Nothing to do here
	}

	public UnsortedTreeConjunction(
			final TreeFactory<ConjunctionTreeNode, ConjunctionTreeLeaf> treefactory,
			final Collection<Formula> formulas) {
		this.treefactory = treefactory;
		setAll(formulas);
	}

	public UnsortedTreeConjunction(
			final TreeFactory<ConjunctionTreeNode, ConjunctionTreeLeaf> treefactory, final Formula f) {
		this.treefactory = treefactory;
		setAll(f);
	}

	@Override
	public synchronized void add(final Formula term) throws OperationNotSupportedException {
		final ConjunctionTreeLeaf newLeaf = getTreeFactory().createLeaf(term, nextName++);
		final ConjunctionTreeLeaf oldLeaf = formulaMap.put(term, newLeaf);
		if (oldLeaf != null) {
			formulaMap.put(term, oldLeaf);
			return;
		}
		if (leaves.isEmpty()) {
			if (root == null) {
				root = newLeaf;
			} else {
				assert root instanceof ConjunctionTreeLeaf;
				leaves.add(root);
				leaves.add(newLeaf);
				root = getTreeFactory().createNode(root, newLeaf);
			}
		} else {
			final ConjunctionTreeNode original = leaves.removeFirst();
			if (root == original) {
				root = getTreeFactory().createNode(root, newLeaf);
			} else {
				leaves.add(original);
				leaves.add(newLeaf);
				replace(original.getParent(), original,
						getTreeFactory().createNode(original, newLeaf));
			}
		}
	}

	@Override
	public void balance() {
		// TODO Auto-generated method stub

	}

	public int conjunctions() {
		if (root == null) return 0;
		return root.conjunctions();
	}

	@Override
	public Automaton getAutomaton() {
		if (root != null) return root.getAutomaton();
		return null;
	}

	/**
	 * @return
	 */
	public int getMaxEvents() {
		return getMaxEvents(root);
	}

	public int getMaxEventsSansRoot() {
		if (root == null) return 0;
		return Math.max(getMaxEvents(root.left), getMaxEvents(root.right));
	}

	/**
	 * @return
	 */
	public int getMaxStates() {
		return getMaxStates(root);
	}

	public int getMaxStatesSansRoot() {
		if (root == null) return 0;
		return Math.max(getMaxStates(root.left), getMaxStates(root.right));
	}

	/**
	 * @return
	 */
	public int getNumberCachedAutomata() {
		return getNumberCachedAutomata(root);
	}

	/**
	 * @return
	 */
	public int getTotalEvents() {
		return getTotalEvents(root);
	}

	/**
	 * @return
	 */
	public int getTotalStates() {
		return getTotalStates(root);
	}

	/**
	 * @return the treefactory
	 */
	public TreeFactory<ConjunctionTreeNode, ConjunctionTreeLeaf> getTreeFactory() {
		return treefactory;
	}

	public int height() {
		if (root == null) return 0;
		return root.getHeight();
	}

	@Override
	public Iterator<Formula> iterator() {
		if (root != null) return root.iterator();
		return new Iterator<Formula>() {
			@Override
			public boolean hasNext() {
				return false;
			}

			@Override
			public Formula next() {
				throw new NoSuchElementException();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	@Override
	public synchronized void remove(final Formula term) throws OperationNotSupportedException {
		final ConjunctionTreeLeaf removedLeaf = formulaMap.remove(term);
		if (removedLeaf == null || root == null) return;
		if (leaves.isEmpty()) {
			assert root == removedLeaf;
			root = null;
		} else {
			final ConjunctionTreeNode lastLeaf = leaves.removeLast();
			if (lastLeaf.getParent().getParent() == null) {
				leaves.removeLast();
				root = other(removedLeaf);
				root.detach();
			} else {
				leaves.addFirst(leaves.removeLast());
				replace(lastLeaf.getParent().getParent(), lastLeaf.getParent(), other(lastLeaf));
				if (removedLeaf != lastLeaf) {
					leaves.set(leaves.indexOf(removedLeaf), lastLeaf);
					replace(removedLeaf.getParent(), removedLeaf, lastLeaf);
				}
			}
		}
	}

	@Override
	public synchronized void setAll(final Collection<Formula> formulas) {
		reset();
		for (final Formula f : formulas) {
			try {
				add(f);
			} catch (final OperationNotSupportedException e) {
				assert false;
				// If subclasses remove implementation of add, they should add another
				// implementation of setAll
			}
		}
	}

	@Override
	public void setAll(final Formula f) {
		final Set<Formula> formulas = new HashSet<Formula>();
		addSubformulae(formulas, f);
		setAll(formulas);
	}

	@Override
	public String toString() {
		if (root != null) return root.toString();
		return "TRUE";
	}

	protected ConjunctionTreeNode other(final ConjunctionTreeNode node) {
		if (node.getParent() == null) return null;
		if (node.getParent().getLeft() == node) return node.getParent().getRight();
		if (node.getParent().getRight() == node) return node.getParent().getLeft();
		assert false;
		return null;
	}

	protected synchronized void removeNode(final ConjunctionTreeNode node) {
		if (node == null || root == null) return;
		if (node.getParent() == null) {
			root = null;
		} else if (node.getParent().getParent() == null) {
			root = other(node);
		} else {
			replace(node.getParent().getParent(), node.getParent(), other(node));
		}
		leaves.remove(node);
	}

	protected void replace(final ConjunctionTreeNode parent, final ConjunctionTreeNode original,
			final ConjunctionTreeNode newNode) {
		assert parent != null;
		if (parent.getLeft() == original) {
			parent.setLeft(newNode);
		} else if (parent.getRight() == original) {
			parent.setRight(newNode);
		} else {
			assert false;
		}
	}

	protected void reset() {
		root = null;
		leaves.clear();
		formulaMap.clear();
		nextName = 'A';
	}

	private void addSubformulae(final Set<Formula> formulas, final Formula f) {
		if (f.getType() == LTLFormula.AND) {
			addSubformulae(formulas, f.getLeft());
			addSubformulae(formulas, f.getRight());
		} else {
			formulas.add(f);
		}
	}

	private int getMaxEvents(final ConjunctionTreeNode elm) {
		if (elm == null) return 0;
		Automaton a;
		if (elm.cache != null && (a = elm.cache.get()) != null)
			return Math.max(a.getTransitionCount(),
					Math.max(getMaxEvents(elm.getLeft()), getMaxEvents(elm.getRight())));
		return Math.max(getMaxEvents(elm.getLeft()), getMaxEvents(elm.getRight()));
	}

	private int getMaxStates(final ConjunctionTreeNode elm) {
		if (elm == null) return 0;
		Automaton a;
		if (elm.cache != null && (a = elm.cache.get()) != null)
			return Math.max(a.getStateCount(),
					Math.max(getMaxStates(elm.getLeft()), getMaxStates(elm.getRight())));
		return Math.max(getMaxStates(elm.getLeft()), getMaxStates(elm.getRight()));
	}

	private int getNumberCachedAutomata(final ConjunctionTreeNode elm) {
		if (elm == null) return 0;
		if (elm.cache != null && elm.cache.get() != null)
			return 1 + getNumberCachedAutomata(elm.getLeft())
					+ getNumberCachedAutomata(elm.getRight());
		return getNumberCachedAutomata(elm.getLeft()) + getNumberCachedAutomata(elm.getRight());
	}

	private int getTotalEvents(final ConjunctionTreeNode elm) {
		if (elm == null) return 0;
		Automaton a;
		if (elm.cache != null && (a = elm.cache.get()) != null)
			return a.getTransitionCount() + getTotalEvents(elm.getLeft())
					+ getTotalEvents(elm.getRight());
		return getTotalEvents(elm.getLeft()) + getTotalEvents(elm.getRight());
	}

	private int getTotalStates(final ConjunctionTreeNode elm) {
		if (elm == null) return 0;
		Automaton a;
		if (elm.cache != null && (a = elm.cache.get()) != null)
			return a.getStateCount() + getTotalStates(elm.getLeft())
					+ getTotalStates(elm.getRight());
		return getTotalStates(elm.getLeft()) + getTotalStates(elm.getRight());
	}
}
