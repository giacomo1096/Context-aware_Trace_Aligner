package org.processmining.ltl2automaton.plugins.cup_parser;

import java.io.StringReader;

import org.processmining.ltl2automaton.plugins.formula.Formula;

public class CupParser {
	public static Formula parse(final String ltl) throws Exception {
		final Scanner scanner = new Scanner(new StringReader(ltl));
		final ParserCup parser = new ParserCup(scanner);
		return (Formula) parser.parse().value;
	}
}
