package tomy.tree;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tomy.TestBase;
import tomy.array.ArrayPrint;
import tomy.random.RandomGen;
import tomy.random.RandomTree;


public class TestTreeWithPreIn extends TestBase {
  private static final int NUM_TEST = 10;
  private static final int MIN_LEN = 0;
  private static final int MAX_LEN = 10;

  @DataProvider(name = "dataFromRand")
  private Object[][] dataFromRand() {
    Object[][] result = new Object [NUM_TEST][];

    for (int i = 0; i < NUM_TEST; ++i) {
      int len = MIN_LEN + RandomGen.getRandom().nextInt(MAX_LEN - MIN_LEN + 1);

      result[i] = new Object[] {len};
    }
    return result;
  }

  @Test(dataProvider = "dataFromRand")
  public void testTreeFromPreIn(int len) {
    Tree tree  = RandomTree.genRandBT(len);

    LOGGER.debug("tree org:");
    TreePrint.print(tree);

    int[] pre = TreeToPreIn.treeToPre(tree);
    LOGGER.debug("pre: " + ArrayPrint.toString(pre));

    int[] in = TreeToPreIn.treeToIn(tree);
    LOGGER.debug("in:  " + ArrayPrint.toString(in));

    Tree treeNew = TreeFromPreIn.buildBTFromPreIn(pre, in);
    LOGGER.debug("tree new:");
    TreePrint.print(tree);

    Assert.assertTrue(Tree.equals(tree, treeNew));
  }

  @Test(dataProvider = "dataFromRand")
  public void testTreeFromPre(int len) {
    Tree tree  = RandomTree.genRandBST(len);

    LOGGER.debug("tree org:");
    TreePrint.print(tree);

    int[] pre = TreeToPreIn.treeToPre(tree);
    LOGGER.debug("pre: " + ArrayPrint.toString(pre));

    int[] in = TreeToPreIn.treeToIn(tree);
    LOGGER.debug("in:  " + ArrayPrint.toString(in));

    Tree treeNew = TreeFromPreIn.buildBSTFromPre(pre);
    LOGGER.debug("tree new:");
    TreePrint.print(tree);

    Assert.assertTrue(Tree.equals(tree, treeNew));
  }
}
