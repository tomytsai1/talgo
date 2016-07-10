package tomy.tree;

import java.util.Stack;


public class TreeToPreIn {
  public static int[] treeToPre(Tree tree) {
    if (tree == null) {
      return new int[0];
    }

    int[] result = new int[Tree.size(tree)];
    int i = 0;

    Stack<Tree> stack = new Stack<>();
    stack.push(tree);

    while (!stack.isEmpty()) {
      Tree cur = stack.pop();
      result[i++] = cur.v;

      if (cur.r != null) {
        stack.push(cur.r);
      }

      if (cur.l != null) {
        stack.push(cur.l);
      }
    }

    return result;
  }

  public static int[] treeToIn(Tree tree) {
    if (tree == null) {
      return new int[0];
    }

    int[] result = new int[Tree.size(tree)];
    treeToInHelper(tree, result, 0);

    return result;
  }

  private static int treeToInHelper(Tree tree, int[] result, int i) {
    if (tree.l != null) {
      i = treeToInHelper(tree.l, result, i);
    }

    result[i++] = tree.v;

    if (tree.r != null) {
      i = treeToInHelper(tree.r, result, i);
    }

    return i;
  }
}
