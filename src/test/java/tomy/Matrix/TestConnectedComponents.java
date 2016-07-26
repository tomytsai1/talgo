package tomy.Matrix;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tomy.TestBase;
import tomy.array.ArrayPrint;
import tomy.matrix.ConnectedComponents;
import tomy.matrix.MatrixPrint;
import tomy.random.RandomGen;
import tomy.random.RandomMatrix;


public class TestConnectedComponents extends TestBase {
  private static final int NUM_TEST = 10;
  private static final int MIN_WIDTH = 1;
  private static final int MAX_WIDTH = 8;
  private static final int MIN_HEIGHT = 1;
  private static final int MAX_HEIGHT = 8;

  @DataProvider(name = "dataFromRand")
  private Object[][] dataFromRand() {
    //RandomGen.setRandomSeed(478742247);
    Object[][] result = new Object[NUM_TEST][];

    for (int i = 0; i < NUM_TEST; ++i) {
      int areaWidth = MIN_WIDTH + RandomGen.getRandom().nextInt(MAX_WIDTH - MIN_WIDTH + 1);
      int areaHeight = MIN_HEIGHT + RandomGen.getRandom().nextInt(MAX_HEIGHT - MIN_HEIGHT + 1);
      int[][] area = RandomMatrix.genRandIntMatrix(areaWidth, areaHeight, 0, 1);

      result[i] = new Object[]{area};
    }
    //result[0] = result[5];
    return result;
  }

  @Test(dataProvider = "dataFromRand")
  public void testConnectedComponents(int[][] area) {
    LOGGER.debug("area =");
    MatrixPrint.print(area);

    int[] areaCountsDfs = ConnectedComponents.getComponentSizesDfs(area);
    LOGGER.debug("area count (dfs) = " + ArrayPrint.toString(areaCountsDfs));

    int[] areaCountBfs = ConnectedComponents.getComponentSizesBfs(area);
    LOGGER.debug("area count (bfs) = " + ArrayPrint.toString(areaCountBfs));

    int[] areaCountUf = ConnectedComponents.getComponentSizesUnionFind(area);
    LOGGER.debug("area count (u-f) = " + ArrayPrint.toString(areaCountUf));

    Assert.assertEquals(areaCountsDfs, areaCountBfs);
    Assert.assertEquals(areaCountsDfs, areaCountUf);
  }
}
