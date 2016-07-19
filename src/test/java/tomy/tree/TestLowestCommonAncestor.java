package tomy.tree;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tomy.TestBase;
import tomy.random.RandomTree;

import static tomy.random.RandomGen.RANDOM;


public class TestLowestCommonAncestor extends TestBase {
  private static final int NUM_TEST = 10;
  private static final int MIN_LEN = 1;
  private static final int MAX_LEN = 16;

  @DataProvider(name = "dataFromRand")
  private Object[][] dataFromRand() {
    Object[][] result = new Object [NUM_TEST][];

    for (int i = 0; i < NUM_TEST; ++i) {
      int len = MIN_LEN + RANDOM.nextInt(MAX_LEN - MIN_LEN + 1);

      Tree tree = RandomTree.genRandBT(len);
      Tree node1 = RandomTree.getRandTreeNode(tree);
      Tree node2 = RandomTree.getRandTreeNode(tree);
      result[i] = new Object[] {tree, node1, node2};
    }
    return result;
  }

  @Test(dataProvider = "dataFromRand")
  public void testTreeN(Tree tree, Tree node1, Tree node2) {
    LOGGER.debug("Original tree:");
    TreePrint.print(tree);

    LOGGER.debug("Node1:" + node1);
    LOGGER.debug("Node2:" + node2);

    Tree lca = LowestCommonAncestor.findLowestCommonAncestor(tree, node1, node2);
    LOGGER.debug("LCA :" + lca);

    LowestCommonAncestor.LcaRet lcaRet2 = LowestCommonAncestor.findLowestCommonAncestorWithTwoNodes(tree, node1, node2);
    LOGGER.debug("LCA2:" + lcaRet2.tree + " numFound: " + lcaRet2.numFound);

    Tree node1a = new Tree();
    LowestCommonAncestor.LcaRet lcaRet3 = LowestCommonAncestor.findLowestCommonAncestorWithTwoNodes(tree, node1, node1a);
    LOGGER.debug("LCA3:" + lcaRet3.tree + " numFound: " + lcaRet3.numFound);

    LowestCommonAncestor.LcaRet lcaRet4 = LowestCommonAncestor.findLowestCommonAncestorWithTwoNodes(tree, node1a, node2);
    LOGGER.debug("LCA4:" + lcaRet4.tree + " numFound: " + lcaRet4.numFound);

    LowestCommonAncestor.LcaRet lcaRet5 = LowestCommonAncestor.findLowestCommonAncestorWithTwoNodes(tree, node1a, node1a);
    LOGGER.debug("LCA4:" + lcaRet5.tree + " numFound: " + lcaRet5.numFound);
  }

}
