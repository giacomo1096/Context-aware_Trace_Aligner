package org.processmining.ltl2automaton.plugins.formula.conjunction;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

import org.processmining.ltl2automaton.plugins.automaton.Automaton;

public class CacheTools {
	public static Map<ConjunctionTreeNode, Automaton> getCached(final ConjunctionTreeNode node) {
		final Map<ConjunctionTreeNode, Automaton> result = new HashMap<ConjunctionTreeNode, Automaton>();
		set(result, node);
		return result;
	}

	public static void set(final Map<ConjunctionTreeNode, Automaton> oldCaches,
			final ConjunctionTreeNode node) {
		if (node != null) {
			final Automaton oldCache = oldCaches.get(node);
			if (oldCache != null) {
				node.cache = new SoftReference<Automaton>(oldCache);
			}
			set(oldCaches, node.left);
			set(oldCaches, node.right);
		}
	}

	public static void setCaches(final Map<ConjunctionTreeNode, Automaton> oldCaches,
			final ConjunctionTreeNode node) {
		if (node != null) {
			Automaton cached = null;
			try {
				cached = node.cache.get();
			} catch (final NullPointerException e) {
				// cached remains null
			}
			if (cached != null) {
				oldCaches.put(node, cached);
			}
			setCaches(oldCaches, node.left);
			setCaches(oldCaches, node.right);
		}
	}

}
