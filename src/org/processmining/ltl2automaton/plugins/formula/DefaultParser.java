package org.processmining.ltl2automaton.plugins.formula;

import org.processmining.ltl2automaton.plugins.ltl.Parser;


public class DefaultParser extends Parser<Formula> {

	public DefaultParser(final String ltl) {
		super(ltl, DefaultFormulaFactory.instance);
	}

}
