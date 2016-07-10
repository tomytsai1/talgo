package tomy.array;

import org.apache.commons.lang3.ArrayUtils;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tomy.TestBase;
import tomy.random.RandomArray;

import static tomy.random.RandomGen.RANDOM;


public class TestRadixSort extends TestBase {
  private static final int NUM_TEST = 10;
  private static final int MIN_LEN = 0;
  private static final int MAX_LEN = 10;

  @DataProvider(name = "dataFromRand")
  private Object[][] dataFromRand() {
    Object[][] result = new Object [NUM_TEST][];

    for (int i = 0; i < NUM_TEST; ++i) {
      int len = MIN_LEN + RANDOM.nextInt(MAX_LEN - MIN_LEN + 1);
      int[] a = RandomArray.genRandIntPerm(len);
      result[i] = new Object[] {a};
    }
    return result;
  }

  @Test(dataProvider = "dataFromRand")
  public void testRadixMsb(int[] a) {
    LOGGER.debug("a org: " + ArrayPrint.toString(a));

    RadixSort.radixMsb(a);
    LOGGER.debug("a new: " + ArrayPrint.toString(a));

    Assert.assertTrue(ArrayUtils.isSorted(a));
  }

  @Test(dataProvider = "dataFromRand")
  public void testRadixLsb(int[] a) {
    LOGGER.debug("a org: " + ArrayPrint.toString(a));

    RadixSort.radixLsb(a);
    LOGGER.debug("a new: " + ArrayPrint.toString(a));

    Assert.assertTrue(ArrayUtils.isSorted(a));
  }
}
