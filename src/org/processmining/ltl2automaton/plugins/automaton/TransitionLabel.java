package org.processmining.ltl2automaton.plugins.automaton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class TransitionLabel {

	/**
	 * This interface represents one element of TransitionLabel.
	 * 
	 * @author mpesic
	 */
	static interface ILiteral {
		String getImage();

		boolean parsesImage(String img);
	}

	/**
	 * This class represents a negative proposition in the TransitionLabel. For example: "!A", "!B",
	 * "!A.completed". The proposition parses only the images that are not like the proposition,
	 * e.g., "!A" does not parse "A", but does parse "B" or "C.completed", etc.
	 * 
	 * @author mpesic
	 */
	static class NegativeProposition extends Proposition {

		private String image;

		NegativeProposition(final String prop) {
			super(prop);
		}

		@Override
		public boolean equals(final Object o) {
			if (o == null) return false;
			if (o instanceof NegativeProposition)
				return prop.equals(((NegativeProposition) o).prop);
			return false;
		}

		@Override
		public String getImage() {
			if (image == null) {
				image = "!" + prop;
			}
			return image;
		}

		@Override
		public int hashCode() {
			return 0x12345678 + prop.hashCode();
		}

		@Override
		public boolean parsesImage(final String img) {
			return !super.parsesImage(img);
		}

	}

	/**
	 * This class represents a positive proposition in the TransitionLabel. For example: "A", "B",
	 * "A.completed". The proposition parses only the images that are exactly the same like the
	 * proposition, e.g., "A" parses only "A", and does not parse "B" or "C.completed".
	 * 
	 * @author mpesic
	 */
	static class Proposition implements ILiteral {

		protected final String prop;

		Proposition(final String prop) {
			super();
			this.prop = prop;
		}

		@Override
		public boolean equals(final Object o) {
			if (o == null) return false;
			if (o instanceof Proposition) return prop.equals(((Proposition) o).prop);
			return false;
		}

		@Override
		public String getImage() {
			return prop;
		}

		public String getProp() {
			return prop;
		}

		@Override
		public int hashCode() {
			return prop.hashCode();
		}

		@Override
		public boolean parsesImage(final String img) {
			return prop.equals(img);
		}

		@Override
		public String toString() {
			return getImage();
		}
	}

	/**
	 * This class represents a TRUE proposition in the TransitionLabel. The proposition parses all
	 * images, e.g., "A" parses only "A", "B", "C.completed", or any other.
	 * 
	 * @author mpesic
	 */
	static class TrueProposition extends Proposition {

		TrueProposition() {
			super(TransitionLabel.TRUE);
		}

		@Override
		public boolean equals(final Object o) {
			if (o == null) return false;
			return o instanceof TrueProposition;
		}

		@Override
		public int hashCode() {
			return 7;
		}

		@Override
		public boolean parsesImage(final String img) {
			return true;
		}
	}

	public static final String TRUE = "-";

	private final Set<ILiteral> literals;

	private final Set<ILiteral> u_literals;
	private final Collection<String> negativeLabels;

	private ILiteral aLiteral = null;

	private final boolean negative;

	private final boolean positive;

	TransitionLabel() {
		literals = new HashSet<ILiteral>();
		u_literals = Collections.unmodifiableSet(literals);
		negativeLabels = null;
		addTrue();
		positive = false;
		negative = false;
	}

	TransitionLabel(final Collection<String> propositions) {
		literals = new HashSet<ILiteral>();
		u_literals = Collections.unmodifiableSet(literals);
		negativeLabels = Collections.unmodifiableCollection(new ArrayList<String>(propositions));
		for (final String proposition : propositions) {
			addNegativeProposition(proposition);
		}
		positive = false;
		negative = true;
	}

	TransitionLabel(final String proposition) {
		literals = new HashSet<ILiteral>();
		u_literals = Collections.unmodifiableSet(literals);
		negativeLabels = null;
		addProposition(proposition);
		positive = true;
		negative = false;
	}

	TransitionLabel(final TransitionLabel other) {
		literals = other.u_literals;
		u_literals = other.u_literals;
		negativeLabels = other.negativeLabels;
		positive = other.positive;
		negative = other.negative;
	}

	@Override
	public boolean equals(final Object o) {
		if (o == null) return false;
		if (o instanceof TransitionLabel) return literals.equals(((TransitionLabel) o).literals);
		return false;
	}

	public ILiteral getaLiteral() {
		return aLiteral;
	}

	public Set<ILiteral> getLiterals() {
		return u_literals;
	}

	public Collection<String> getNegativeLabels() {
		return negativeLabels;
	}

	@Override
	public int hashCode() {
		return literals.hashCode();
	}

	public boolean isAll() {
		return !(negative || positive);
	}

	public boolean isNegative() {
		return negative;
	}

	public boolean isPositive() {
		return positive;
	}

	private synchronized void addLiteral(final ILiteral literal) {
		aLiteral = literal;
		literals.add(literal);
	}

	private void addNegativeProposition(final String prop) {
		addLiteral(new NegativeProposition(prop));
	}

	private void addProposition(final String prop) {
		addLiteral(new Proposition(prop));
	}

	private void addTrue() {
		addLiteral(new TrueProposition());
	}

	String getImage() {
		final StringBuilder sb = new StringBuilder();
		boolean and = false;

		for (final ILiteral lit : literals) {
			if (and) {
				sb.append('&');
			}
			and = true;
			sb.append(lit);
		}
		return sb.toString();
	}

	boolean parsesImage(final String img) {
		for (final ILiteral literal : literals) {
			if (!literal.parsesImage(img)) return false;
		}
		return true;
	}

}
