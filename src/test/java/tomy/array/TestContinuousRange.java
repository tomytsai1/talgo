package tomy.array;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tomy.TestBase;
import tomy.random.RandomArray;

import static tomy.random.RandomGen.RANDOM;


public class TestContinuousRange extends TestBase {
  private static final int NUM_TEST = 10;
  private static final int MIN_LEN = 0;
  private static final int MAX_LEN = 10;

  @DataProvider(name = "dataFromRand")
  private Object[][] dataFromRand() {
    Object[][] result = new Object [NUM_TEST][];

    for (int i = 0; i < NUM_TEST; ++i) {
      int len = MIN_LEN + RANDOM.nextInt(MAX_LEN - MIN_LEN + 1);
      int[] a = RandomArray.genRandInt(len, 0, len * 2);
      result[i] = new Object[] {a};
    }
    return result;
  }

  @Test(dataProvider = "dataFromRand")
  public void testContgenRandIntinuousRange(int[] a) {
    LOGGER.debug("a[] = " + ArrayPrint.toString(a));

    int[] contRange = ContinuousRange.findContinuousRange(a);
    LOGGER.debug("contRange start " + contRange[0] + " len " + contRange[1]);
  }

}
