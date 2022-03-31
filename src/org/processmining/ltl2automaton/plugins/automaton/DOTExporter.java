package org.processmining.ltl2automaton.plugins.automaton;

import java.io.PrintWriter;
import java.io.Writer;

public class DOTExporter {
	public static void exportToDot(final Automaton a, final String name, final Writer w) {
		final PrintWriter pw = new PrintWriter(w);
		pw.print("digraph \"");
		pw.print(name);
		pw.println("\" {");

		pw.println("\tinit [shape=none, label=\"\"];");
		pw.print("\tname [shape=note, label=\"");
		pw.print(name);
		pw.println("\"];");
		pw.println("\trankdir=LR;");
		pw.println();

		for (final State s : a) {
			pw.print("\ts");
			pw.print(s.getId());
			if (s.isAccepting()) {
				pw.print(" [shape=doublecircle]");
			} else {
				pw.print(" [shape=circle]");
			}
			pw.println(';');
		}
		pw.println();

		for (final State s : a) {
			for (final Transition t : s.getOutput()) {
				pw.print("\ts");
				pw.print(s.getId());
				pw.print(" -> s");
				pw.print(t.getTarget().getId());
				pw.print(" [label=\"");
				pw.print(t.getLabel().getImage().replaceAll("\\\\", "\\\\")
						.replaceAll("\"", "\\\"").replaceAll("STARTED", "S")
						.replaceAll("COMPLETED", "C").replaceAll("CANCELLED", "X"));

				pw.println("\"];");
			}
		}
		if (a.getInit() != null) {
			pw.print("\tinit -> s");
			pw.print(a.getInit().getId());
			pw.println(';');
		}

		pw.println("}");
		pw.flush();
	}
}
