package tomy.random;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

import tomy.tree.Tree;


public class RandomTree {
  private static Tree genRandBTStruct(int size) {
    if (size == 0) {
      return null;
    }

    String paren = RandomParen.genRandParen(size * 2);

    Tree root = new Tree();
    boolean leftChild = true;
    Stack<Tree> stack = new Stack<>();
    stack.push(root);
    Tree cur = root;
    for (int i = 1; i < size * 2; ++i) {
      if (paren.charAt(i) == '(') {
        Tree tree = new Tree();

        if (leftChild) {
          cur.l = tree;
        } else {
          cur.r = tree;
        }
        stack.push(tree);

        cur = tree;
        leftChild = true;
      } else {
        cur = stack.peek();
        leftChild = false;
        stack.pop();
      }
    }

    return root;
  }

  public static Tree genRandBT(int size) {
    Tree tree = genRandBTStruct(size);
    int[] values = RandomArray.genRandIntPerm(size);

    fillTreeWithPreValue(tree, values, 0);
    return tree;
  }

  private static int fillTreeWithPreValue(Tree tree, int[] randPreValue, int pos) {
    if (tree == null) {
      return pos;
    }

    tree.v = randPreValue[pos];
    ++pos;

    pos = fillTreeWithPreValue(tree.l, randPreValue, pos);
    pos = fillTreeWithPreValue(tree.r, randPreValue, pos);
    return pos;
  }

  public static Tree genRandBST(int size) {
    Tree tree = genRandBTStruct(size);

    fillTreeWithOrderedInValue(tree, 0);
    return tree;
  }


  private static int fillTreeWithOrderedInValue(Tree tree, int pos) {
    if (tree == null) {
      return pos;
    }

    pos = fillTreeWithOrderedInValue(tree.l, pos);

    tree.v = pos;
    ++pos;

    pos = fillTreeWithOrderedInValue(tree.r, pos);
    return pos;
  }

  public static Tree getRandTreeNode(Tree tree) {
    Objects.requireNonNull(tree);

    Stack<Tree> stackTree = new Stack<>();
    Tree cur = tree;

    List<Tree> listAllNodes = new ArrayList<>();
    while (cur != null || !stackTree.isEmpty()) {
      if (cur != null) {
        stackTree.push(cur);
        cur = cur.l;
      } else {
        cur = stackTree.pop();
        listAllNodes.add(cur);

        cur = cur.r;
      }
    }

    return listAllNodes.get(RandomGen.getRandom().nextInt(listAllNodes.size()));
  }

}
