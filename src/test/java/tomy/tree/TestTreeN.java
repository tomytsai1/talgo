package tomy.tree;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tomy.TestBase;
import tomy.random.RandomGen;
import tomy.random.RandomTree;


public class TestTreeN extends TestBase {
  private static final int NUM_TEST = 10;
  private static final int MIN_LEN = 0;
  private static final int MAX_LEN = 16;

  @DataProvider(name = "dataFromRand")
  private Object[][] dataFromRand() {
    Object[][] result = new Object [NUM_TEST][];

    for (int i = 0; i < NUM_TEST; ++i) {
      int len = MIN_LEN + RandomGen.getRandom().nextInt(MAX_LEN - MIN_LEN + 1);

      Tree tree = RandomTree.genRandBT(len);
      result[i] = new Object[] {tree};
    }
    return result;
  }

  @Test(dataProvider = "dataFromRand")
  public void testTreeN(Tree tree) {
    LOGGER.debug("Original tree:");
    TreePrint.print(tree);

    LOGGER.debug("New tree:");
    TreeN treeN = TreeN.convertTreeToTreeN(tree);
    TreePrint.print(treeN);

    LOGGER.debug("equal: " + TreeN.equals(treeN, tree));
    Assert.assertTrue(TreeN.equals(treeN, tree));

  }
}
