package org.processmining.ltl2automaton.plugins.ltl;

class EndOfFormulaException extends SyntaxParserException {
	private static final long serialVersionUID = 6236945050430254464L;

	public EndOfFormulaException() {
		super("Unexpected end of formula.");
	}

}
