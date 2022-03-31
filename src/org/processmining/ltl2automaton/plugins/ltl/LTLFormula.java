package org.processmining.ltl2automaton.plugins.ltl;

import org.processmining.ltl2automaton.plugins.formula.Formula;


public class LTLFormula implements Cloneable {

	public static final char AND = 'A';
	public static final char OR = 'O';
	public static final char UNTIL = 'U';
	public static final char WUNTIL = 'W';
	public static final char RELEASE = 'V';
	public static final char WRELEASE = 'M';
	public static final char NEXT = 'X';
	public static final char WNEXT = 'Y';
	public static final char NOT = 'N';
	public static final char FALSE = 'f';
	public static final char TRUE = 't';
	public static final char PROPOSITION = 'p';
	public static final char IMPLIES = '-';
	public static final char EQUAL = '=';
	public static final char EVENTUALLY = 'E';
	public static final char ALWAYS = 'G';

	protected char type;
	protected LTLFormula left;
	protected LTLFormula right;
	protected String name;

	public LTLFormula(final char c, final LTLFormula lf, final LTLFormula rf, final String n) {
		super();
		type = c;
		left = lf;
		right = rf;
		setName(n);
	}

	@Override
	public LTLFormula clone() {
		Formula clone;
		try {
			clone = (Formula) super.clone();
		} catch (final CloneNotSupportedException e) {
			assert false; // Clone should not fail
			throw new Error("Clone failed");
		}
		return clone;
	}

	@Override
	public boolean equals(final Object o) {
		if (o == null || !(o instanceof LTLFormula)) { return false; }
		final LTLFormula f = (LTLFormula) o;
		if (type != f.type) { return false; }
		if (left == null && !(f.left == null)) { return false; }
		if (right == null && !(f.right == null)) { return false; }
		if (left != null && !left.equals(f.left)) { return false; }
		if (right != null && !right.equals(f.right)) { return false; }
		if (name == null && !(f.name == null)) { return false; }
		if (name != null && !name.equals(f.name)) { return false; }
		return true;
	}

	public LTLFormula getLeft() {
		return left;
	}

	public int getLength() {
		switch (type) {
		case AND:
		case OR:
		case UNTIL:
		case RELEASE:
		case WUNTIL:
		case WRELEASE:
		case IMPLIES:
			return left.getLength() + right.getLength() + 1;
		case WNEXT:
		case NEXT:
		case NOT:
		case ALWAYS:
		case EVENTUALLY:
			return left.getLength() + 1;
		default:
			return 1;
		}
	}

	public String getName() {
		return name;
	}

	public LTLFormula getRight() {
		return right;
	}

	public char getType() {
		return type;
	}

	@Override
	public int hashCode() {
		int hashCode = 17;
		hashCode += type * 13;
		if (left != null) {
			hashCode = hashCode * 19 + left.hashCode();
		}
		if (right != null) {
			hashCode = hashCode * 23 + right.hashCode();
		}
		if (name != null) {
			hashCode = hashCode * 29 + name.hashCode();
		}
		return hashCode;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		toString(sb);
		return sb.toString();
	}

	public void toString(final StringBuilder sb) {
		switch (type) {
		case AND:
			sb.append('(');
			left.toString(sb);
			sb.append(" /\\ ");
			right.toString(sb);
			sb.append(')');
			break;
		case OR:
			sb.append('(');
			left.toString(sb);
			sb.append(" \\/ ");
			right.toString(sb);
			sb.append(')');
			break;
		case UNTIL:
			sb.append('(');
			left.toString(sb);
			sb.append(" U ");
			right.toString(sb);
			sb.append(')');
			break;
		case RELEASE:
			sb.append('(');
			left.toString(sb);
			sb.append(" V ");
			right.toString(sb);
			sb.append(')');
			break;
		case WUNTIL:
			sb.append('(');
			left.toString(sb);
			sb.append(" W ");
			right.toString(sb);
			sb.append(')');
			break;
		case IMPLIES:
			sb.append('(');
			left.toString(sb);
			sb.append(" -> ");
			right.toString(sb);
			sb.append(')');
			break;
		case EQUAL:
			sb.append('(');
			left.toString(sb);
			sb.append(" <-> ");
			right.toString(sb);
			sb.append(')');
			break;
		case NEXT:
			sb.append("(X ");
			left.toString(sb);
			sb.append(')');
			break;
		case WNEXT:
			sb.append("(Y ");
			left.toString(sb);
			sb.append(')');
			break;
		case NOT:
			sb.append("(!");
			left.toString(sb);
			sb.append(')');
			break;
		case ALWAYS:
			sb.append("([] ");
			left.toString(sb);
			sb.append(')');
			break;
		case EVENTUALLY:
			sb.append("(<> ");
			left.toString(sb);
			sb.append(')');
			break;
		case TRUE:
			sb.append("true");
			break;
		case FALSE:
			sb.append("false");
			break;
		case PROPOSITION:
			sb.append('"');
			sb.append(getName());
			sb.append('"');
			break;
		default:
			sb.append(type);
			break;
		}

	}

	protected void setLeft(final LTLFormula left) {
		this.left = left;
	}

	protected void setRight(final LTLFormula right) {
		this.right = right;
	}

	protected void setType(final char type) {
		this.type = type;
	}
}
