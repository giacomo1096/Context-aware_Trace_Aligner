package org.processmining.ltl2automaton.plugins.ltl;

public class Parser<F extends LTLFormula> {

	private static final int PRIORITY = 0;
	private static final int PRIORITY_IMPLIES = 1;
	private static final int PRIORITY_EQUAL = 1;
	private static final int PRIORITY_OR = 2;
	private static final int PRIORITY_AND = 3;
	private static final int PRIORITY_UNTIL = 4;
	private static final int PRIORITY_WUNTIL = 4;
	private static final int PRIORITY_RELEASE = 5;
	private static final int PRIORITY_WRELEASE = 5;
	private static final int PRIORITY_NOT = 6;
	private static final int PRIORITY_NEXT = 6;
	private static final int PRIORITY_WNEXT = 6;
	private static final int PRIORITY_ALWAYS = 6;
	private static final int PRIORITY_EVENTUALLY = 6;

	private final FormulaFactory<F> factory;
	private final StringBuffer sb;

	/*
	 * public Parser(String ltl) { this(ltl,new DefaultFormulaFactory()); }
	 */

	public Parser(final String ltl, final FormulaFactory<F> fact) {
		super();
		factory = fact;
		sb = new StringBuffer(ltl);
	}

	public F parse() throws SyntaxParserException {
		return parse(Parser.PRIORITY);
	}

	private void delete() throws EndOfFormulaException {
		try {
			sb.deleteCharAt(0);
		} catch (final StringIndexOutOfBoundsException e) {
			throw new EndOfFormulaException();
		}
	}

	private void deleteAllEmpty() throws EndOfFormulaException {
		while (get() == ' ') {
			delete();
		}
	}

	private char get() throws EndOfFormulaException {
		try {
			return sb.charAt(0);
		} catch (final StringIndexOutOfBoundsException e) {
			throw new EndOfFormulaException();
		}
	}

	private boolean is_reserved_char(final char ch) {
		switch (ch) {
			case LTLFormula.UNTIL:
			case LTLFormula.RELEASE:
			case LTLFormula.WUNTIL:
			case LTLFormula.WRELEASE:
			case LTLFormula.NEXT:
			case LTLFormula.WNEXT:
			case ' ':
			case '<':
			case '>':
			case '(':
			case ')':
			case '[':
			case ']':
			case '-':
				return true;

			default:
				return false;
		}
	}

	private F parse(final int precedence) throws SyntaxParserException {
		try {
			F formula;
			char ch;

			deleteAllEmpty();

			switch (ch = get()) {
				case '/': // /\
				case '&': // &&
				case '\\': // \/
				case '|': // ||
				case LTLFormula.UNTIL: // U
				case LTLFormula.WUNTIL: // W
				case LTLFormula.RELEASE: // V
				case LTLFormula.WRELEASE: // R
				case ')':
					throw new SyntaxParserException("invalid character: " + ch);

				case '!': // not
					delete();
					formula = factory.Not(parse(Parser.PRIORITY_NOT));

					break;

				case LTLFormula.NEXT: // next
					delete();
					formula = factory.Next(parse(Parser.PRIORITY_NEXT));

					break;

				case LTLFormula.WNEXT: // next
					delete();
					formula = factory.WNext(parse(Parser.PRIORITY_WNEXT));

					break;

				case '[': // always
					delete();

					if (get() != ']') throw new SyntaxParserException("expected ]");

					delete();
					formula = factory.Always(parse(Parser.PRIORITY_ALWAYS));

					break;

				case '<': // eventually
					delete();

					if (get() != '>') throw new SyntaxParserException("expected >");

					delete();
					formula = factory.Eventually(parse(Parser.PRIORITY_EVENTUALLY));

					break;

				case '(':
					delete();
					formula = parse(Parser.PRIORITY);

					if (get() != ')') throw new SyntaxParserException("invalid character: " + ch);

					delete();

					break;

				case '"':

					final StringBuffer sb = new StringBuffer();
					delete();

					while ((ch = get()) != '"') {
						sb.append(ch);
						delete();
					}

					delete();

					formula = factory.Proposition(sb.toString());

					break;

				default:

					if (Character.isJavaIdentifierStart(ch)) {
						final StringBuffer sbf = new StringBuffer();

						sbf.append(ch);
						delete();

						try {
							while (Character.isJavaIdentifierPart(ch = get())
									&& !is_reserved_char(ch)) {
								sbf.append(ch);
								delete();
							}
						} catch (final EndOfFormulaException e) {
							// ignore
						}

						final String id = sbf.toString();

						if (id.equals("true")) {
							formula = factory.True();
						} else if (id.equals("false")) {
							formula = factory.False();
						} else {
							formula = factory.Proposition(sbf.toString());
						}
					} else
						throw new SyntaxParserException("invalid character: " + ch);

					break;
			}

			try {
				deleteAllEmpty();

				ch = get();
			} catch (final EndOfFormulaException e) {
				return formula;
			}

			while (true) {
				switch (ch) {
					case '/': // /\

						if (precedence > Parser.PRIORITY_AND) return formula;

						delete();

						if (get() != '\\') throw new SyntaxParserException("expected \\");

						delete();
						formula = factory.And(formula, parse(Parser.PRIORITY_AND));

						break;

					case '&': // &&

						if (precedence > Parser.PRIORITY_AND) return formula;

						delete();

						if (get() != '&') throw new SyntaxParserException("expected &&");

						delete();
						formula = factory.And(formula, parse(Parser.PRIORITY_AND));

						break;

					case '\\': // \/

						if (precedence > Parser.PRIORITY_OR) return formula;

						delete();

						if (get() != '/') throw new SyntaxParserException("expected /");

						delete();
						formula = factory.Or(formula, parse(Parser.PRIORITY_OR));

						break;

					case '|': // ||

						if (precedence > Parser.PRIORITY_OR) return formula;

						delete();

						if (get() != '|') throw new SyntaxParserException("expected ||");

						delete();
						formula = factory.Or(formula, parse(Parser.PRIORITY_OR));

						break;

					case LTLFormula.UNTIL: // until

						if (precedence > Parser.PRIORITY_UNTIL) return formula;

						delete();
						formula = factory.Until(formula, parse(Parser.PRIORITY_UNTIL));

						break;

					case LTLFormula.WUNTIL: // weak until

						if (precedence > Parser.PRIORITY_WUNTIL) return formula;

						delete();
						formula = factory.WUntil(formula, parse(Parser.PRIORITY_WUNTIL));

						break;

					case LTLFormula.RELEASE: // release

						if (precedence > Parser.PRIORITY_RELEASE) return formula;

						delete();
						formula = factory.Release(formula, parse(Parser.PRIORITY_RELEASE));

						break;

					case LTLFormula.WRELEASE: // weak release

						if (precedence > Parser.PRIORITY_WRELEASE) return formula;

						delete();
						formula = factory.WRelease(formula, parse(Parser.PRIORITY_WRELEASE));

						break;

					case '-': // implies

						if (precedence > Parser.PRIORITY_IMPLIES) return formula;

						delete();

						if (get() != '>') throw new SyntaxParserException("expected >");

						delete();
						formula = factory.Implies(formula, parse(Parser.PRIORITY_IMPLIES));

						break;
					case '=': // implies

						if (precedence > Parser.PRIORITY_EQUAL) return formula;

						delete();
						formula = factory.Equal(formula, parse(Parser.PRIORITY_EQUAL));

						break;

					case ')':
						return formula;

					case '!':
					case LTLFormula.NEXT:
					case '[':
					case '<':
					case '(':
					default:
						throw new SyntaxParserException("invalid character: " + ch);
				}

				try {
					deleteAllEmpty();
					ch = get();
				} catch (final EndOfFormulaException e) {
					break;
				}
			}

			return formula;
		} catch (final EndOfFormulaException e) {
			throw new SyntaxParserException("unexpected end of formula");
		}
	}
}