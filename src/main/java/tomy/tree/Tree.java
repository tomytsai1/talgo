package tomy.tree;

import java.util.Stack;


public class Tree {
  public int v;
  public Tree l;
  public Tree r;

  public Tree() {
  }

  public Tree(int v) {
    this.v = v;
  }

  public Tree(int v, Tree l, Tree r) {
    this.v = v;
    this.l = l;
    this.r = r;
  }

  public static int size(Tree tree) {
    if (tree == null) {
      return 0;
    }
    return 1 + size(tree.l) + size(tree.r);
  }

  public static boolean equals(Tree tree1, Tree tree2) {
    if (tree1 == null && tree2 == null) {
      return true;
    }

    if (tree1 == null || tree2 == null) {
      return false;
    }

    return tree1.v == tree2.v
        && equals(tree1.l, tree2.l)
        && equals(tree1.r, tree2.r);
  }

  @Override
  public String toString() {
    final String VALUE_FORMAT = TreePrint.VALUE_FORMAT;
    String leftString = this.l == null ? "  L  *" : "  L " + String.format(VALUE_FORMAT, this.l.v);
    String rightString = this.r == null ? "  R  *" : "  R " + String.format(VALUE_FORMAT, this.r.v);

    return String.format("%2d", this.v) + leftString + rightString;
  }
}
