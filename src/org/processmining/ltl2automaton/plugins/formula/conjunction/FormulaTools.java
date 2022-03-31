package org.processmining.ltl2automaton.plugins.formula.conjunction;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.processmining.ltl2automaton.plugins.LTL2Automaton;
import org.processmining.ltl2automaton.plugins.automaton.Automaton;
import org.processmining.ltl2automaton.plugins.automaton.State;
import org.processmining.ltl2automaton.plugins.automaton.Transition;
import org.processmining.ltl2automaton.plugins.formula.Formula;
import org.processmining.ltl2automaton.plugins.ltl.LTLFormula;

public class FormulaTools {
	public static boolean acceptsAllInit(final Map<ConjunctionTreeNode, Automaton> cache,
			final Formula formula) {
		try {
			final ConjunctionTreeNode leaf = new ConjunctionTreeLeaf(formula, 'a');
			Automaton automaton = cache.get(leaf);
			if (automaton == null) {
				automaton = LTL2Automaton.getInstance().translate(formula).op.determinize().op
						.minimize();
				cache.put(leaf, automaton);
			}
			final State init = automaton.getInit();
			for (final Transition t : init.getOutput()) {
				if (t.getTarget() == init && (t.isAll() || t.isNegative())) return true;
			}
		} catch (final Exception e) {
		}
		return false;
	}

	public static boolean containsNext(final Formula f) {
		if (f == null) return false;
		if (f.getType() == LTLFormula.NEXT) return true;
		if (containsNext(f.getLeft())) return true;
		return containsNext(f.getRight());
	}

	public static Collection<String> getAtomicPropositions(final Formula f) {
		final Set<String> result = new HashSet<String>();
		getAtomicPropositions(result, f);
		return result;
	}

	private static void getAtomicPropositions(final Set<String> result, final Formula f) {
		if (f == null) return;
		if (f.getType() == LTLFormula.PROPOSITION) {
			result.add(f.getName());
		}
		getAtomicPropositions(result, f.getLeft());
		getAtomicPropositions(result, f.getRight());
	}
}
