package org.processmining.ltl2automaton.plugins;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.processmining.ltl2automaton.plugins.formula.Formula;
import org.processmining.ltl2automaton.plugins.ltl.LTLFormula;

class Graph implements NodeFactory {

	private int id;
	private final Formula formula;
	private int expandCount;
	private boolean collapsed;
	private final AutomatonFactory automatonFactory;
	/**
	 * Field "nodes" is a Map because we want to map each node to its NEXT field! This makes it
	 * faster to search for the node with given NEXT field in the method exists(Node node).
	 */
	private final Map<Collection<Formula>, Node> nodes;

	public Graph(final Formula frm, final AutomatonFactory factory) {
		super();
		nodes = new HashMap<Collection<Formula>, Node>();
		formula = frm;
		automatonFactory = factory;
		id = 0;
		expandCount = 0;
		collapsed = false;
	}

	public void add(final Node node) {
		nodes.put(node.getNext(), node);
	}

	@Override
	public void collapse() {
		collapsed = true;
	}

	@Override
	public Node create() {
		return createNode(generateNextId());
	}

	@Override
	public Node create(final Node node) {
		return createNode(generateNextId(), node);
	}

	public void createAutomaton() {
		final Node init = createInitial();
		init.expand(this);
		final int size = getSize();
		final Object[] states = new Object[size];
		final Node[] equivalence = new Node[size];

		// System.out.println("=====================================");
		for (final Node node : nodes.values()) { // first process all nodes
			node.setEquivalenceId(index_equivalence(node, equivalence));
			createStates(node, states);
			// System.out.println(node + " (" + node.getEquivalenceId() + "):\t" +
			// node.getIncomming() + "\t" + node.getAlternatives() + "\t" + node.getNext() + "\t" +
			// node.getOld() + "\t" + node.isAccepting());
		}
		boolean initialSet = false;
		for (int i = 0; i < states.length; i++) {
			if (states[i] != null && i == automatonFactory.getStateId(states[i])) {
				if (!initialSet) {
					automatonFactory.initialState(states[i]);
					initialSet = true;
				}
				automatonFactory.addState(states[i]);
			}
		}
		automatonFactory.finished();
		automatonFactory.setExpandCount(getExpandCount());
	}

	@Override
	public Node createInitial() {
		final Node node = create();
		node.init(formula);
		return node;
	}

	public Node exists(final Node node) {
		return nodes.get(node.getNext());
	}

	@Override
	public int generateNextId() {
		return id++;
	}

	@Override
	public int getExpandCount() {
		return expandCount;
	}

	public Formula getFormula() {
		return formula;
	}

	@Override
	public int getSize() {
		return id;
	}

	public int index_equivalence(final Node node, final Node[] equivalence) {
		// all nodes with the same NEXT field have the same equivalenceId
		int index;
		final int size = getSize();
		for (index = 0; index < size; index++) {
			if (equivalence[index] == null) {
				break;
			} else if (equivalence[index].getNext().equals(node.getNext()))
				return equivalence[index].getId();
		}
		equivalence[index] = node;
		return equivalence[index].getId();
	}

	@Override
	public boolean isCollapsed() {
		return collapsed;
	}

	@Override
	public void newExpand() {
		expandCount++;
	}

	protected Node createNode(final int id) {
		return new Node(id, this);
	}

	protected Node createNode(final int id, final Node node) {
		return new Node(id, this, node);
	}

	private void createStates(final Node node, final Object[] states) {
		final int id = node.getId();
		if (states[id] == null) {
			states[id] = automatonFactory.createState();
		}
		automatonFactory.updateState(states[id], node.getEquivalenceId(), node.isAccepting());

		for (final Node alternative : node.getAlternatives()) {
			for (final Node incomingNode : alternative.getIncomming()) {
				final int sourceId = incomingNode.getId();
				if (states[sourceId] == null) {
					states[sourceId] = automatonFactory.createState();
				}

				final int targetId = node.getEquivalenceId();
				if (states[targetId] == null) {
					states[targetId] = automatonFactory.createState();
				}
				createTransition(alternative, states[sourceId], states[targetId]);
			}
		}
	}

	private void createTransition(final Node node, final Object source, final Object target) {
		final Iterable<Formula> old = node.getOld();
		if (!old.iterator().hasNext()) { // all propositions
			automatonFactory.addAllTransition(source, target);
		} else {
			final Formula positive = getPositiveProposition(old);
			if (positive != null) { // one positive proposition
				automatonFactory.addPropositionTransition(source, target, positive.getName());
			} else { // one or more negative propositions
				final ArrayList<String> negative = new ArrayList<String>();
				for (final Formula formula : old) {
					negative.add(formula.getSub1().getName());
				}
				automatonFactory.addNegativePropositionsTransition(source, target, negative);
			}
		}
	}

	private Formula getPositiveProposition(final Iterable<Formula> propositions) {
		for (final Formula f : propositions) {
			if (f.isLiteral() && f.getType() == LTLFormula.PROPOSITION) return f;
		}
		return null;
	}
}
