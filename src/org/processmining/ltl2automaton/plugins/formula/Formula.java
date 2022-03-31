package org.processmining.ltl2automaton.plugins.formula;

import java.util.Collection;
import java.util.Iterator;

import org.processmining.ltl2automaton.plugins.ltl.LTLFormula;

public class Formula extends LTLFormula implements Iterable<String> {
	final int hashCode;

	public Formula(final char c, final Formula sx, final Formula dx, final String n) {
		super(c, sx, dx, n);
		int hashCode = 8009 * c;
		if (sx != null) {
			hashCode *= 313;
			hashCode += sx.hashCode;
		}
		if (dx != null) {
			hashCode *= 299;
			hashCode += dx.hashCode;
		}
		if (n != null) {
			hashCode *= 197;
			hashCode += n.hashCode();
		}
		this.hashCode = hashCode;
	}

	@Override
	public Formula clone() {
		return (Formula) super.clone();
	}

	public boolean containsUntil() {
		switch (getType()) {
		case AND:
			return getLeft().containsUntil() || getRight().containsUntil();

		case OR:
			return getLeft().containsUntil() && getRight().containsUntil();

		case RELEASE:
			return getRight().containsUntil();
		case EVENTUALLY:
		case ALWAYS:
			return getLeft().containsUntil();

		case UNTIL:
			return true;

		default:
			return false;
		}
	}

	@Override
	public boolean equals(final Object o) {
		if (o == null) { return false; }
		if (!(o instanceof Formula)) { return false; }
		final Formula f = (Formula) o;
		if (hashCode != f.hashCode) { return false; }
		if (type != f.type) { return false; }
		if (left == null && f.left != null) { return false; }
		if (right == null && f.right != null) { return false; }
		if (name == null && f.name != null) { return false; }
		if (name != null && !name.equals(f.name)) { return false; }
		if (left != null && !left.equals(f.left)) { return false; }
		if (right != null && !right.equals(f.right)) { return false; }
		return true;
	}

	@Override
	public Formula getLeft() {
		return (Formula) super.getLeft();
	}

	@Override
	public int getLength() {
		switch (getType()) {
		case AND:
		case OR:
		case UNTIL:
		case RELEASE:
		case WUNTIL:
		case WRELEASE:
			return getLeft().getLength() + getRight().getLength() + 1;
		case NEXT:
		case WNEXT:
		case NOT:
			return getLeft().getLength() + 1;
		default:
			return 1;
		}
	}

	public Formula getNext() {
		switch (getType()) {
		case UNTIL:
		case WUNTIL:
			return this;
		case RELEASE:
			return this;
		case OR:
			return null;
		default:
			return null;
		}
	}

	@Override
	public Formula getRight() {
		return (Formula) super.getRight();
	}

	public Formula getSub1() {
		if (getType() == LTLFormula.RELEASE) {
			return getRight();
		} else {
			return getLeft();
		}
	}

	public Formula getSub2() {
		if (getType() == LTLFormula.RELEASE) {
			return getLeft();
		} else {
			return getRight();
		}
	}

	@Override
	public int hashCode() {
		return hashCode;
	}

	public boolean isLiteral() {
		switch (getType()) {
		case FALSE:
		case TRUE:
		case PROPOSITION:
			return true;
		case NOT:
			return getSub1().isLiteral();
		default:
			return false;
		}
	}

	public boolean isPositiveProposition() {
		return getType() == LTLFormula.PROPOSITION;
	}

	public boolean isSpecialRelease(final Collection<Formula> formulas) {
		final Formula form = new Formula(LTLFormula.RELEASE, new Formula(LTLFormula.FALSE, null, null, null), this,
		        null);

		if (formulas.contains(form)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isSyntacticallyImplied(final Collection<Formula> old, final Collection<Formula> next) {
		if (getType() == LTLFormula.TRUE) { return true; }

		if (old.contains(this)) { return true; }

		if (!isLiteral()) {
			final Formula form1 = getSub1();
			final Formula form2 = getSub2();
			final Formula form3 = getNext();

			boolean condition1;
			boolean condition2;
			boolean condition3;

			if (form2 != null) {
				condition2 = form2.isSyntacticallyImplied(old, next);
			} else {
				condition2 = true;
			}

			if (form1 != null) {
				condition1 = form1.isSyntacticallyImplied(old, next);
			} else {
				condition1 = true;
			}

			if (form3 != null) {
				if (next != null) {
					condition3 = next.contains(form3);
				} else {
					condition3 = false;
				}
			} else {
				condition3 = true;
			}

			switch (getType()) {
			case UNTIL:
			case WUNTIL:
			case OR:
				return condition2 || condition1 && condition3;

			case RELEASE:
				return condition1 && condition2 || condition1 && condition3;

			case WNEXT:
			case NEXT:

				if (form1 != null) {
					if (next != null) {
						return next.contains(form1);
					} else {
						return false;
					}
				} else {
					return true;
				}

			case AND:
				return condition2 && condition1;

			default:
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public Iterator<String> iterator() {
		if (getType() == PROPOSITION) { return new Iterator<String>() {
			boolean done = false;

			@Override
			public boolean hasNext() {
				return !done;
			}

			@Override
			public String next() {
				done = true;
				return getName();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		}; }
		return new Iterator<String>() {
			Iterator<String> left = getLeft() != null ? getLeft().iterator() : null;
			Iterator<String> right = getRight() != null ? getRight().iterator() : null;

			@Override
			public boolean hasNext() {
				if (left != null && left.hasNext()) { return true; }
				if (right != null && right.hasNext()) { return true; }
				return false;
			}

			@Override
			public String next() {
				if (left != null && left.hasNext()) { return left.next(); }
				if (right != null && right.hasNext()) { return right.next(); }
				return null;
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	public boolean mustSatisfyProposition() {
		switch (getType()) {
		case AND:
			return getLeft().mustSatisfyProposition() || getRight().mustSatisfyProposition();

		case OR:
			return getLeft().mustSatisfyProposition() && getRight().mustSatisfyProposition();

		case RELEASE:
			return getRight().containsUntil();
		case ALWAYS:
			return getLeft().containsUntil();
		case NEXT:
		case WNEXT: // TODO What should be the rule for weak/strong next
			return getLeft().mustSatisfyProposition();
		case UNTIL:
		case EVENTUALLY:
		case TRUE:
		case FALSE:
		case PROPOSITION:
			return true;
		case WUNTIL:
			return getRight().containsUntil();
		case WRELEASE:
		case IMPLIES:
		case EQUAL:
			System.err.println("Should not contain " + getType());
		case NOT: // Can only contain a proposition
		default:
			return false;
		}
	}

	public Formula negate() {
		return DefaultFormulaFactory.instance.Not(this);
	}

	public void relpaceParameter(final String formal, final String real) {
		if (getType() == LTLFormula.PROPOSITION && getName().equals(formal)) {
			setName(real);
		} else {
			if (getLeft() != null) {
				getLeft().relpaceParameter(formal, real);
			}
			if (getRight() != null) {
				getRight().relpaceParameter(formal, real);
			}
		}
	}

}
