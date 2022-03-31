package org.processmining.ltl2automaton.plugins;

import org.processmining.ltl2automaton.plugins.formula.Formula;


public class SinglePropertyGraph extends Graph {

	public SinglePropertyGraph(final Formula frm, final AutomatonFactory factory) {
		super(frm, factory);
	}

	@Override
	protected Node createNode(final int id) {
		return new SinglePropertyNode(id, this);
	}

	@Override
	protected Node createNode(final int id, final Node node) {
		return new SinglePropertyNode(id, this, node);
	}

}
