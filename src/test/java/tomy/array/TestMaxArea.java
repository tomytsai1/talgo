package tomy.array;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tomy.TestBase;
import tomy.random.RandomArray;
import tomy.random.RandomGen;


public class TestMaxArea extends TestBase {
  private static final int NUM_TEST = 10;
  private static final int MIN_LEN = 0;
  private static final int MAX_LEN = 10;

  @DataProvider(name = "dataFromRand")
  private Object[][] dataFromRand() {
    RandomGen.setRandomSeed(15749669);
    Object[][] result = new Object [NUM_TEST][];

    for (int i = 0; i < NUM_TEST; ++i) {
      int len = MIN_LEN + RandomGen.getRandom().nextInt(MAX_LEN - MIN_LEN + 1);
      int[] a = RandomArray.genRandInt(len, 0, len * 2);
      result[i] = new Object[] {a};
    }
    result[0] = result[3];
    return result;
  }


  @Test(dataProvider = "dataFromRand")
  public void testFindMaxRectArea(int[] a) {
    LOGGER.debug("a[] = " + ArrayPrint.toString(a));

    int[] result = MaxArea.findMaxRectArea(a);
    LOGGER.debug("max area = " + result[0] + " range " + result[1] + " , " + result[2]);
  }

}
