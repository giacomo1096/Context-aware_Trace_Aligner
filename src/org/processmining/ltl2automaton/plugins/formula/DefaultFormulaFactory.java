package org.processmining.ltl2automaton.plugins.formula;

import org.processmining.ltl2automaton.plugins.ltl.FormulaFactory;
import org.processmining.ltl2automaton.plugins.ltl.LTLFormula;

public class DefaultFormulaFactory implements FormulaFactory<Formula> {

	public static final DefaultFormulaFactory instance = new DefaultFormulaFactory();

	private DefaultFormulaFactory() {
		// Hide
	}

	/*
	 * (non-Javadoc)
	 * @see ltl2aut.formula.FormulaFactory#Always(ltl2aut.formula.Formula)
	 */
	@Override
	public Formula Always(final Formula f) {
		return create(LTLFormula.RELEASE, False(), f, null);
	}

	/*
	 * (non-Javadoc)
	 * @see ltl2aut.formula.FormulaFactory#And(ltl2aut.formula.Formula, ltl2aut.formula.Formula)
	 */
	@Override
	public Formula And(final Formula sx, final Formula dx) {
		if (sx.hashCode < dx.hashCode) {
			return create(LTLFormula.AND, sx, dx, null);
		} else {
			return create(LTLFormula.AND, dx, sx, null);
		}
	}

	@Override
	public Formula Equal(final Formula lf, final Formula rf) {
		return And(Implies(lf, rf), Implies(rf, lf));
	}

	/*
	 * (non-Javadoc)
	 * @see ltl2aut.formula.FormulaFactory#Eventually(ltl2aut.formula.Formula)
	 */
	@Override
	public Formula Eventually(final Formula f) {
		return create(LTLFormula.UNTIL, True(), f, null);
	}

	/*
	 * (non-Javadoc)
	 * @see ltl2aut.formula.FormulaFactory#False()
	 */
	@Override
	public Formula False() {
		return create(LTLFormula.FALSE, null, null, null);
	}

	/*
	 * (non-Javadoc)
	 * @see ltl2aut.formula.FormulaFactory#Implies(ltl2aut.formula.Formula, ltl2aut.formula.Formula)
	 */
	@Override
	public Formula Implies(final Formula sx, final Formula dx) {
		return Or(Not(sx), dx);
	}

	/*
	 * (non-Javadoc)
	 * @see ltl2aut.formula.FormulaFactory#Next(ltl2aut.formula.Formula)
	 */
	@Override
	public Formula Next(final Formula f) {
		return create(LTLFormula.NEXT, f, null, null);
	}

	/*
	 * (non-Javadoc)
	 * @see ltl2aut.formula.FormulaFactory#Not(ltl2aut.formula.Formula)
	 */
	@Override
	public Formula Not(final Formula f) {
		if (f.isLiteral()) {
			switch (f.getType()) {
			case LTLFormula.TRUE:
				return False();

			case LTLFormula.FALSE:
				return True();

			case LTLFormula.NOT:
				return f.getLeft();

			default:
				return create(LTLFormula.NOT, f, null, null);
			}
		}

		// f is not a literal, so go on...
		switch (f.getType()) {
		case LTLFormula.AND:
			return Or(Not(f.getLeft()), Not(f.getRight()));

		case LTLFormula.OR:
			return And(Not(f.getLeft()), Not(f.getRight()));

		case LTLFormula.UNTIL:
			return Release(Not(f.getLeft()), Not(f.getRight()));

		case LTLFormula.RELEASE:
			return Until(Not(f.getLeft()), Not(f.getRight()));

		case LTLFormula.WUNTIL:
			return WRelease(Not(f.getLeft()), Not(f.getRight()));

		case LTLFormula.WRELEASE:
			return WUntil(Not(f.getLeft()), Not(f.getRight()));
		case LTLFormula.NOT:
			return f.getLeft();

		case LTLFormula.NEXT:
			return Next(Not(f.getLeft()));

		case LTLFormula.WNEXT:
			// TODO implement Weak/strong next
		default:
			throw new RuntimeException("Unknown formula.");
		}
	}

	/*
	 * (non-Javadoc)
	 * @see ltl2aut.formula.FormulaFactory#Or(ltl2aut.formula.Formula, ltl2aut.formula.Formula)
	 */
	@Override
	public Formula Or(final Formula sx, final Formula dx) {
		if (sx.hashCode < dx.hashCode) {
			return create(LTLFormula.OR, sx, dx, null);
		} else {
			return create(LTLFormula.OR, dx, sx, null);
		}
	}

	/*
	 * (non-Javadoc)
	 * @see ltl2aut.formula.FormulaFactory#Proposition(java.lang.String)
	 */
	@Override
	public Formula Proposition(final String name) {
		return create(LTLFormula.PROPOSITION, null, null, name);
	}

	/*
	 * (non-Javadoc)
	 * @see ltl2aut.formula.FormulaFactory#Release(ltl2aut.formula.Formula, ltl2aut.formula.Formula)
	 */
	@Override
	public Formula Release(final Formula sx, final Formula dx) {
		return create(LTLFormula.RELEASE, sx, dx, null);
	}

	/*
	 * (non-Javadoc)
	 * @see ltl2aut.formula.FormulaFactory#True()
	 */
	@Override
	public Formula True() {
		return create(LTLFormula.TRUE, null, null, null);
	}

	/*
	 * (non-Javadoc)
	 * @see ltl2aut.formula.FormulaFactory#Until(ltl2aut.formula.Formula, ltl2aut.formula.Formula)
	 */
	@Override
	public Formula Until(final Formula sx, final Formula dx) {
		return create(LTLFormula.UNTIL, sx, dx, null);
	}

	/*
	 * (non-Javadoc)
	 * @see ltl2aut.formula.FormulaFactory#Next(ltl2aut.formula.Formula)
	 */
	@Override
	public Formula WNext(final Formula f) {
		return create(LTLFormula.WNEXT, f, null, null);
	}

	/*
	 * (non-Javadoc)
	 * @see ltl2aut.formula.FormulaFactory#WRelease(ltl2aut.formula.Formula, ltl2aut.formula.Formula)
	 */
	@Override
	public Formula WRelease(final Formula sx, final Formula dx) {
		return create(LTLFormula.UNTIL, dx, And(sx, dx), null);
	}

	/*
	 * (non-Javadoc)
	 * @see ltl2aut.formula.FormulaFactory#WUntil(ltl2aut.formula.Formula, ltl2aut.formula.Formula)
	 */
	@Override
	public Formula WUntil(final Formula sx, final Formula dx) {
		return create(LTLFormula.WUNTIL, sx, dx, null);
	}

	public Formula create(final char c, final Formula sx, final Formula dx, final String n) {
		final Formula f = new Formula(c, sx, dx, n);
		return f;
	}

}
