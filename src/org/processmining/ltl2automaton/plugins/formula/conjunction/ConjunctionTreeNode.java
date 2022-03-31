package org.processmining.ltl2automaton.plugins.formula.conjunction;

import java.lang.ref.SoftReference;
import java.util.Iterator;

import org.processmining.ltl2automaton.plugins.automaton.Automaton;
import org.processmining.ltl2automaton.plugins.formula.Formula;

public class ConjunctionTreeNode implements Iterable<Formula> {
	SoftReference<Automaton> cache;
	protected ConjunctionTreeNode left;
	protected ConjunctionTreeNode right;
	protected ConjunctionTreeNode parent;
	private int hashCode;

	public ConjunctionTreeNode(final ConjunctionTreeNode left, final ConjunctionTreeNode right) {
		setLeft(left);
		setRight(right);
	}

	protected ConjunctionTreeNode() {
		// Nothing
	}

	public int conjunctions() {
		return left.conjunctions() + right.conjunctions();
	}

	public void detach() {
		parent = null;
	}

	@Override
	public synchronized boolean equals(final Object o) {
		if (o == null) return false;
		if (!(o instanceof ConjunctionTreeNode)) return false;
		final ConjunctionTreeNode other = (ConjunctionTreeNode) o;
		if (left == null && other.left != null) return false;
		if (right == null && other.right != null) return false;
		if (hashCode != other.hashCode) return false;
		if (left != null && !left.equals(other.left)) return false;
		if (right != null && !right.equals(other.right)) return false;
		return true;
	}

	public synchronized Automaton getAutomaton() {
		Automaton automaton = null;
		if (cache == null || (automaton = cache.get()) == null) {
			if (getLeft() != null) {
				if (getRight() != null) {
					automaton = process(calculateProduct(new Lambda<Automaton>() {
						@Override
						public Automaton get() {
							return getLeft().getAutomaton();
						}
					}, new Lambda<Automaton>() {
						@Override
						public Automaton get() {
							return getRight().getAutomaton();
						}
					}));
					cache = new SoftReference<Automaton>(automaton);
					return automaton;
				} else {
					automaton = getLeft().getAutomaton();
					cache = new SoftReference<Automaton>(automaton);
					return automaton;
				}
			} else {
				if (getRight() != null) {
					automaton = getRight().getAutomaton();
					cache = new SoftReference<Automaton>(automaton);
					return automaton;
				}
			}
		}
		assert automaton != null;
		return automaton;
	}

	public int getHeight() {
		return Math.max(left.getHeight(), right.getHeight()) + 1;
	}

	/**
	 * @return the left
	 */
	public ConjunctionTreeNode getLeft() {
		return left;
	}

	/**
	 * @return the parent
	 */
	public ConjunctionTreeNode getParent() {
		return parent;
	}

	/**
	 * @return the right
	 */
	public ConjunctionTreeNode getRight() {
		return right;
	}

	@Override
	public int hashCode() {
		return hashCode;
	}

	public synchronized void invalidate() {
		if (cache == null) return;
		cache = null;
		if (getParent() != null) {
			getParent().invalidate();
		}
	}

	@Override
	public Iterator<Formula> iterator() {
		return new Iterator<Formula>() {
			Iterator<Formula> leftIt = left.iterator();
			Iterator<Formula> rightIt = right.iterator();

			@Override
			public boolean hasNext() {
				return leftIt.hasNext() || rightIt.hasNext();
			}

			@Override
			public Formula next() {
				if (leftIt.hasNext()) return leftIt.next();
				return rightIt.next();
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

	/**
	 * @param left
	 *            the left to set
	 */
	public synchronized void setLeft(final ConjunctionTreeNode left) {
		this.left = left;
		if (left != null) {
			left.parent = this;
			hashCode = 7 * left.hashCode() + 11 * (right == null ? 0 : right.hashCode());
		} else {
			hashCode = 11 * (right == null ? 0 : right.hashCode());
		}
		invalidate();
	}

	/**
	 * @param right
	 *            the right to set
	 */
	public synchronized void setRight(final ConjunctionTreeNode right) {
		this.right = right;
		if (right != null) {
			right.parent = this;
			hashCode = 7 * (left == null ? 0 : left.hashCode()) + 11 * right.hashCode();
		} else {
			hashCode = 7 * (left == null ? 0 : left.hashCode());
		}
		invalidate();
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder();
		// if (cache != null) {
		// sb.append('<');
		// }
		// final String leftString = left.toString();
		// if (leftString.length() > 1) {
		// sb.append('(');
		// }
		// sb.append(leftString);
		// if (leftString.length() > 1) {
		// sb.append(')');
		// }
		// sb.append(" & ");
		// final String rightString = right.toString();
		// if (rightString.length() > 1) {
		// sb.append('(');
		// }
		// sb.append(rightString);
		// if (rightString.length() > 1) {
		// sb.append(')');
		// }
		// if (cache != null) {
		// sb.append('>');
		// }
		if (cache != null) {
			final Automaton automaton = cache.get();
			if (automaton != null) {
				sb.append("<&>\t\t");
				sb.append(automaton.getStateCount());
				sb.append(" - ");
				sb.append(automaton.getTransitionCount());
				sb.append("\t\t - ");
				sb.append(Integer.toHexString(cache.hashCode()));
			} else {
				sb.append("&");
			}
		} else {
			sb.append("&");
		}
		sb.append("\n+- ");
		sb.append(left.toString().replaceAll("\n", "\n|  "));
		sb.append("\n+- ");
		sb.append(right.toString().replaceAll("\n", "\n   "));
		return sb.toString();
	}

	protected Automaton calculateProduct(final Lambda<Automaton> a1, final Lambda<Automaton> a2) {
		final Automaton a1v = a1.get();
		if (a1v.op.isEmpty()) return a1v;
		final Automaton a2v = a2.get();
		if (a2v.op.isEmpty()) return a2v;
		return a1v.op.determinize().op.intersect(a2v.op.determinize());
	}

	protected Automaton process(final Automaton a) {
		return a.op.determinize().op.minimize();
	}

}
