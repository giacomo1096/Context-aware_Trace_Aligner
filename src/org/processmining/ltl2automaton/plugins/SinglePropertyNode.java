package org.processmining.ltl2automaton.plugins;

import org.processmining.ltl2automaton.plugins.formula.Formula;
import org.processmining.ltl2automaton.plugins.ltl.LTLFormula;

class SinglePropertyNode extends Node {

	SinglePropertyNode(final int id, final NodeFactory factory) {
		super(id, factory);
	}

	SinglePropertyNode(final int id, final NodeFactory factory, final Node node) {
		super(id, factory, node);
	}

	@Override
	public String toString() {
		return "n" + getId();
	}

	@Override
	protected boolean hasContradictions(final Formula formula) {
		if (super.hasContradictions(formula))
			return true;
		else
			return hasSinglePropertyContradiction(formula);
	}

	private boolean hasPositiveProp() {
		for (final Formula f : getOld()) {
			if (f.isPositiveProposition()) return true;
		}
		return false;
	}

	private boolean hasSinglePropertyContradiction(final Formula formula) {
		if (formula.isPositiveProposition())
			return hasPositiveProp(); // already has positive prop. in old
		else {
			if (formula.getType() == LTLFormula.AND) {
				if (formula.getSub1().isPositiveProposition()
						&& formula.getSub2().isPositiveProposition()) return true;
			}
			return false;
		}
	}
}
