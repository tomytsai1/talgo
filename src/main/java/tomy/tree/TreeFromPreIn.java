package tomy.tree;

import java.util.Objects;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.ArrayUtils;


public class TreeFromPreIn {
  public static Tree buildBTFromPreIn(int[] preorder, int[] inorder) {
    Objects.nonNull(preorder);
    Objects.nonNull(inorder);
    Preconditions.checkArgument(preorder.length == inorder.length);

    return buildBTFromPreInHelper(preorder, 0, preorder.length, inorder, 0, inorder.length);
  }

  private static Tree buildBTFromPreInHelper(int[] preorder, int preFrom, int preTo, int[] inorder, int inFrom, int inTo) {
    if (preFrom == preTo) {
      return null;
    }

    int v = preorder[preFrom];
    int lenL = ArrayUtils.indexOf(inorder, v, inFrom) - inFrom;
    Preconditions.checkState(lenL >= 0 && lenL < inTo - inFrom);

    return new Tree(v,
        buildBTFromPreInHelper(preorder, preFrom + 1, preFrom + 1 + lenL, inorder, inFrom, inFrom + lenL),
        buildBTFromPreInHelper(preorder, preFrom + lenL + 1, preTo, inorder, inFrom + lenL + 1, inTo));
  }

  public static Tree buildBSTFromPre(int[] preorder) {
    Objects.nonNull(preorder);

    return buildBSTFromPreHelper(preorder, 0, preorder.length, 0, preorder.length);
  }

  private static Tree buildBSTFromPreHelper(int[] preorder, int preFrom, int preTo, int inFrom, int inTo) {
    if (preFrom == preTo) {
      return null;
    }

    int v = preorder[preFrom];
    int lenL = v - inFrom;

    Preconditions.checkState(lenL >= 0 && lenL < inTo - inFrom);

    return new Tree(v,
        buildBSTFromPreHelper(preorder, preFrom + 1, preFrom + 1 + lenL, inFrom, inFrom + lenL),
        buildBSTFromPreHelper(preorder, preFrom + lenL + 1, preTo, inFrom + lenL + 1, inTo));
  }
}
