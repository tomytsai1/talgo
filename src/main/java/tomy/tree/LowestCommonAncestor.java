package tomy.tree;

public class LowestCommonAncestor {
  public static Tree findLowestCommonAncestor(Tree root, Tree node1, Tree node2) {
    //!! node1 || node2 == null
    //!! root == null

    //@@ we need deal with root == null case, unless we don't do recursive call on null cases
    if (root == null || root == node1 || root == node2) {
      return root;
    }

    Tree findLeft = findLowestCommonAncestor(root.l, node1, node2);
    Tree findRight = findLowestCommonAncestor(root.r, node1, node2);

    if (findLeft != null && findRight != null) {
      return root;
    }

    return findLeft != null ? findLeft : findRight;
  }

  public static class LcaRet {
    Tree tree;
    int numFound;

    public LcaRet(Tree tree, int numFound) {
      this.tree = tree;
      this.numFound = numFound;
    }
  }

  public static LcaRet findLowestCommonAncestorWithTwoNodes(Tree root, Tree node1, Tree node2) {
    if (root == null) {
      return new LcaRet(null, 0);
    }

    int curFound = root == node1 || root == node2 ? 1 : 0;

    LcaRet findLeft = findLowestCommonAncestorWithTwoNodes(root.l, node1, node2);
    LcaRet findRight = findLowestCommonAncestorWithTwoNodes(root.r, node1, node2);

    if (findLeft.numFound == 2 || findRight.numFound == 2) {
      return findLeft.numFound == 2 ? findLeft : findRight;
    }

    if (findLeft.numFound + findRight.numFound + curFound == 2) {
      return new LcaRet(root, 2);
    }

    return curFound == 1
        ? new LcaRet(root, 1)
        : (findLeft.numFound == 1 ? findLeft : findRight);
  }

}
