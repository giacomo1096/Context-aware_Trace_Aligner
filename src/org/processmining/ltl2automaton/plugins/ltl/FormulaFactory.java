package org.processmining.ltl2automaton.plugins.ltl;

public interface FormulaFactory<F extends LTLFormula> {

	public abstract F Always(F f);

	public abstract F And(F sx, F dx);

	public abstract F Equal(F lf, F rf);

	public abstract F Eventually(F f);

	public abstract F False();

	public abstract F Implies(F sx, F dx);

	public abstract F Next(F f);

	public abstract F Not(F f);

	public abstract F Or(F sx, F dx);

	public abstract F Proposition(String name);

	public abstract F Release(F sx, F dx);

	public abstract F True();

	public abstract F Until(F sx, F dx);

	public abstract F WNext(F f);

	public abstract F WRelease(F sx, F dx);

	public abstract F WUntil(F sx, F dx);

	public abstract F create(char type, F left, F right, String label);
}