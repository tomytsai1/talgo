package tomy.array;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tomy.TestBase;
import tomy.random.RandomArray;
import tomy.random.RandomGen;


public class TestRangeMinMax extends TestBase {
  private static final int NUM_TEST = 10;
  private static final int MIN_LEN = 0;
  private static final int MAX_LEN = 10;
  private static final int MIN_VAL = 1;
  private static final int MAX_VAL = 10;

  @DataProvider(name = "dataFromRand")
  private Object[][] dataFromRand() {
    Object[][] result = new Object [NUM_TEST][];

    for (int i = 0; i < NUM_TEST; ++i) {
      int len = MIN_LEN + RandomGen.getRandom().nextInt(MAX_LEN - MIN_LEN + 1);
      int[] a = RandomArray.genRandInt(len, MIN_VAL, MAX_VAL);
      result[i] = new Object[] {a};
    }
    return result;
  }

  @Test(dataProvider = "dataFromRand")
  public void testRngeMinMax(int[] a) {
    LOGGER.debug("a[] = " + ArrayPrint.toString(a));

    int[] range = RangeMinMax.findLongestRangeMin2LargerThanMax(a);
    LOGGER.debug("contRange start " + range[0] + " len " + range[1]);
  }

}
