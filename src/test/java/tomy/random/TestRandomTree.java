package tomy.random;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tomy.TestBase;
import tomy.tree.Tree;
import tomy.tree.TreePrint;


public class TestRandomTree extends TestBase {
  private static final int NUM_TEST = 10;
  private static final int MIN_LEN = 1;
  private static final int MAX_LEN = 5;

  @DataProvider(name = "dataFromRand")
  private Object[][] dataFromRand() {
    Object[][] result = new Object [NUM_TEST][];

    for (int i = 0; i < NUM_TEST; ++i) {
      int size = MIN_LEN + RandomGen.getRandom().nextInt(MAX_LEN - MIN_LEN + 1);
      result[i] = new Object[] {size};
    }
    return result;
  }

  @Test(dataProvider = "dataFromRand")
  public void testRandomBT(int size) {
    LOGGER.debug(String.format("size = %2d ", size));
    Tree tree = RandomTree.genRandBT(size);
    TreePrint.printBfs(tree);
  }

  @Test(dataProvider = "dataFromRand")
  public void testRandomBST(int size) {
    LOGGER.debug(String.format("size = %2d ", size));
    Tree tree = RandomTree.genRandBST(size);
    TreePrint.printBfs(tree);
  }

}
