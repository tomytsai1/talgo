package tomy.tree;


public class TreeN {
  int v;
  TreeN l;
  TreeN r;
  TreeN n;
  TreeN p;
  int pos;
  boolean visited;

  public TreeN(int v) {
    this.v = v;
  }

  public static int size(TreeN tree) {
    if (tree == null) {
      return 0;
    }
    return 1 + size(tree.l) + size(tree.r);
  }

  public static boolean equals(TreeN treeN, Tree tree) {
    if (treeN == null && tree == null) {
      return true;
    }

    if (treeN == null || tree == null) {
      return false;
    }

    return treeN.v == tree.v
        && equals(treeN.l, tree.l)
        && equals(treeN.r, tree.r);
  }

  private static TreeN cloneTreeToTreeNwithPwithoutN(Tree tree) {
    if (tree == null) {
      return null;
    }

    TreeN treeN = new TreeN(tree.v);

    TreeN l = cloneTreeToTreeNwithPwithoutN(tree.l);
    if (l != null) {
      treeN.l = l;
      l.p = treeN;
    }

    TreeN r = cloneTreeToTreeNwithPwithoutN(tree.r);
    if (r != null) {
      treeN.r = r;
      r.p = treeN;
    }

    return treeN;
  }

  public static TreeN convertTreeToTreeN(Tree tree) {
    // head = root
    // while head != null

      // prev = null
      // cur = head
      // head = null // for storing next head
      // while cur != null
        // if cur.l != null - (1)
          // if prev != null
            // prev.n = cur.l
          // prev = cur.l
          // if head == null
            // head = prev

        // apply (1) for cur.r

        // cur = cur.n

    if (tree == null) {
      return null;
    }

    TreeN treeN = cloneTreeToTreeNwithPwithoutN(tree);

    TreeN head = treeN;
    while (head != null) {
      TreeN prev = null;
      TreeN cur = head;
      head = null;
      do {
        for (TreeN next: new TreeN[] {cur.l, cur.r}) {
          if (next != null) {
            if (prev != null) {
              prev.n = next;
            }
            prev = next;
            if (head == null) {
              head = prev;
            }
          }
        }

        cur = cur.n;
      } while (cur != null);
    }

    return treeN;
  }

  @Override
  public String toString() {
    final String VALUE_FORMAT = TreePrint.VALUE_FORMAT;
    String leftString = this.l == null ? "  L  *" : "  L " + String.format(VALUE_FORMAT, this.l.v);
    String rightString = this.r == null ? "  R  *" : "  R " + String.format(VALUE_FORMAT, this.r.v);
    String nextString = this.n == null ? "  N  *" : "  N " + String.format(VALUE_FORMAT, this.n.v);
    String parentString = this.p == null ? "  P  *" : "  P " + String.format(VALUE_FORMAT, this.p.v);
    String posString = "  pos " + String.format(VALUE_FORMAT, this.pos);

    return String.format("%2d", this.v) + leftString + rightString + nextString + parentString + posString;
  }
}
