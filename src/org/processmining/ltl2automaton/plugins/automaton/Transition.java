package org.processmining.ltl2automaton.plugins.automaton;

import java.util.ArrayList;
import java.util.Collection;

public class Transition {
	private State source;
	private State target;
	private final TransitionLabel label;
	
	private Collection<String> negativeLabels_2;

	public Transition(final State source, final State target) {
		this(source, target, new TransitionLabel());
	}

	public Transition(final State source, final State target, final Collection<String> propositions) {
		this(source, target, new TransitionLabel(propositions));
	}

	public Transition(final State source, final State target, final String proposition) {
		this(source, target, new TransitionLabel(proposition));
	}

	Transition(final State source, final State target, final TransitionLabel label) {
		this.source = source;
		this.target = target;
		source.addOutput(this);
		this.label = label;
		this.negativeLabels_2 = new ArrayList<String>();
	}
	
	public Collection<String> getNegativeLabels_2() {
		return negativeLabels_2;
	}

	public void setNegativeLabels_2(Collection<String> negativeLabels_2) {
		this.negativeLabels_2 = negativeLabels_2;
	}

	public Collection<String> getNegativeLabels() {
		return label.getNegativeLabels();
	}

	public String getPositiveLabel() {
		return label.getaLiteral().getImage();
	}

	public State getSource() {
		return source;
	}

	public State getTarget() {
		return target;
	}

	public boolean isAll() {
		return label.isAll();
	}

	public boolean isNegative() {
		return label.isNegative();
	}

	public boolean isPositive() {
		return label.isPositive();
	}

	public boolean parses(final String img) {
		return label.parsesImage(img);
	}

	public synchronized void remove() {
		source.removeOutput(this);
		source = null;
		target = null;
	}

	@Override
	public String toString() {
		// return source + " - " + label.getImage() + " -> " + target;
		return label.getImage();
	}

	public TransitionLabel getLabel() {
		return label;
	}

}