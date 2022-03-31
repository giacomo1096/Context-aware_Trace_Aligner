package org.processmining.ltl2automaton.plugins;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.processmining.ltl2automaton.plugins.formula.Formula;
import org.processmining.ltl2automaton.plugins.ltl.LTLFormula;

class Node implements Comparable<Node> {

	private class Formulas extends HashSet<Formula> {

		private static final long serialVersionUID = 1L;

		public Formulas() {
			super();
		}

		public Formulas(final Formulas formulas) {
			super(formulas);
		}
	}

	private class Nodes extends HashSet<Node> {

		private static final long serialVersionUID = 1L;

		public Nodes() {
			super();
		}

		public Nodes(final Nodes nodes) {
			super(nodes);
		}
	}

	private final int id;
	private final Nodes incoming;
	private final Formulas neew;
	private final Formulas old;
	private final Formulas next;
	private Node otherTransitionSource;

	private int equivalenceId;

	private final NodeFactory factory;

	Node(final int id, final NodeFactory factory) {
		super();
		this.id = id;
		incoming = new Nodes();
		neew = new Formulas();
		old = new Formulas();
		next = new Formulas();
		otherTransitionSource = null;
		this.factory = factory;
	}

	Node(final int id, final NodeFactory factory, final Node node) {
		super();
		this.id = id;
		incoming = new Nodes(node.incoming);
		neew = new Formulas(node.neew);
		old = new Formulas(node.old);
		next = new Formulas(node.next);
		otherTransitionSource = null;
		this.factory = factory;
	}

	@Override
	public int compareTo(final Node f) {
		return this == f ? 0 : 1;
	}

	protected boolean hasContradictions(final Formula formula) {
		return formula.negate().isSyntacticallyImplied(old, next);
		/*
		 * if (formula.negate().isSyntacticallyImplied(old, next)) { return true; } else { if
		 * (formula.isPositiveProposition()) { return hasPositiveProp(); // already has positive prop. in old } else {
		 * if (formula.getType() == Formula.AND) { if (formula.getSub1().isPositiveProposition() &&
		 * formula.getSub2().isPositiveProposition()) { return true; } } } return false; }
		 */
	}

	private void decomposeANDs(final Formula formula) {
		if (formula.getType() == LTLFormula.AND) {
			decomposeANDs(formula.getSub1());
			decomposeANDs(formula.getSub2());
		} else if (isRedundant(next, null, formula) == false) {
			next.add(formula);
		}
	}

	private boolean isRedundant(final Collection<Formula> main, final Collection<Formula> next,
			final Formula formula) {
		if (formula.isSpecialRelease(main)
				|| formula.isSyntacticallyImplied(main, next)
				&& (!(formula.getType() == LTLFormula.UNTIL) || formula.getSub2()
						.isSyntacticallyImplied(main, next))) return true;
		return false;
	}

	private void modify(final Node node) {
		boolean matched = false;
		Node previous = this;
		Node alternative = this;

		if (id == 0 && !factory.isCollapsed()) {
			factory.collapse();
		}

		while (alternative != null) {
			if (alternative.old.equals(node.old)) {
				alternative.incoming.addAll(node.incoming);
				matched = true;
			}

			previous = alternative;
			alternative = alternative.otherTransitionSource;
		}

		if (!matched) {
			previous.otherTransitionSource = node;
		}
	}

	private Node split(final Formula formula) {
		final Node newNode = factory.create(this);

		Formula temp = formula.getSub2();

		if (!old.contains(temp)) {
			newNode.neew.add(temp);
		}

		if (formula.getType() == LTLFormula.RELEASE) {
			temp = formula.getSub1();
			if (!old.contains(temp)) {
				newNode.neew.add(temp);
			}
		}

		temp = formula.getSub1();

		if (!old.contains(temp)) {
			neew.add(temp);
		}

		temp = formula.getNext();

		if (temp != null) {
			decomposeANDs(temp);
		}
		return newNode;
	}

	Graph expand(final Graph graph) {
		factory.newExpand();

		if (neew.isEmpty()) {

			final Node exists = graph.exists(this);

			if (exists != null) {
				exists.modify(this);
				return graph;
			} else {
				final Node newNode = factory.create();
				newNode.incoming.add(this);
				newNode.neew.addAll(next);

				graph.add(this);

				return newNode.expand(graph);
			}
		} else {
			final Formula formula = neew.iterator().next();
			neew.remove(formula);
			// Formula formula = neew.remove(0);

			if (hasContradictions(formula)) return graph;

			final Set<Formula> main = new HashSet<Formula>();
			main.addAll(old);
			main.addAll(neew);

			if (isRedundant(main, next, formula)) return expand(graph);

			if (!formula.isLiteral()) {
				switch (formula.getType()) {
					case LTLFormula.UNTIL:
					case LTLFormula.WUNTIL:
					case LTLFormula.RELEASE:
					case LTLFormula.OR:
						return split(formula).expand(expand(graph));
					case LTLFormula.WNEXT:
					case LTLFormula.NEXT:
						decomposeANDs(formula.getSub1());
						return expand(graph);
					case LTLFormula.AND:
						Formula temp = formula.getSub1();

						if (!old.contains(temp)) {
							neew.add(temp);
						}

						temp = formula.getSub2();

						if (!old.contains(temp)) {
							neew.add(temp);
						}

						return expand(graph);
					default:
						return null;
				}
			} else {
				if (formula.getType() != LTLFormula.TRUE) {
					old.add(formula);
				}
				return expand(graph);
			}
		}
	}

	Iterable<Node> getAlternatives() {
		final ArrayList<Node> alternatives = new ArrayList<Node>();
		Node alternative = this;

		while (alternative != null) {
			alternatives.add(alternative);
			alternative = alternative.otherTransitionSource;
		}
		return alternatives;
	}

	int getEquivalenceId() {
		return equivalenceId;
	}

	int getId() {
		return id;
	}

	Iterable<Node> getIncomming() {
		return incoming;
	}

	Collection<Formula> getNext() {
		return next;
	}

	Iterable<Formula> getOld() {
		return old;
	}

	void init(final Formula form) {
		if (form.getType() != 't') {
			decomposeANDs(form);
		}
	}

	boolean isAccepting() {
		for (final Formula f : next) {
			if (f.mustSatisfyProposition()) return false;
		}
		return true;
	}

	boolean isInitial() {
		return id == 0;
	}

	void setEquivalenceId(final int id) {
		equivalenceId = id;
	}
}
