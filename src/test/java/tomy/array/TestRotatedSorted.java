package tomy.array;

import java.util.stream.IntStream;

import com.google.common.primitives.Ints;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tomy.TestBase;

import static tomy.random.RandomGen.RANDOM;


public class TestRotatedSorted extends TestBase {
  private static final int NUM_TEST = 10;
  private static final int MIN_LEN = 0;
  private static final int MAX_LEN = 10;

  @DataProvider(name = "dataFromRand")
  private Object[][] dataFromRand() {
    Object[][] result = new Object [NUM_TEST][];

    for (int i = 0; i < NUM_TEST; ++i) {
      int len = MIN_LEN + RANDOM.nextInt(MAX_LEN - MIN_LEN + 1);
      int[] a = IntStream.range(0, len).toArray();

      int k = 0;
      if (len > 0) {
        int lenRotate = RANDOM.nextInt(len);
        BlockSwap.blockSwap(a, lenRotate);
        k = RANDOM.nextInt(len);
      }
      result[i] = new Object[] {a, k};
    }
    result[0] = result[3];
    return result;
  }

  @Test(dataProvider = "dataFromRand")
  public void testFindKinRotatedSorted(int[] a, int k) {
    LOGGER.debug("a[] = " + ArrayPrint.toString(a) + " k = " + k);

    int index = RotatedSorted.findKinRotatedSortedArray(a, k);
    LOGGER.debug("index = " + index + " a[index] = " + (index >= 0 ? a[index] : index));

    Assert.assertTrue((a.length == 0 && index == -1) || a[index] == k);
  }

  @Test(dataProvider = "dataFromRand")
  public void testFinMaxInRotatedSorted(int[] a, int k) {
    LOGGER.debug("a[] = " + ArrayPrint.toString(a));

    int index = RotatedSorted.findMaxInRotatedSortedArray(a);
    LOGGER.debug("index = " + index + " a[index] = " + (index >= 0 ? a[index] : index));

    Assert.assertTrue((a.length == 0 && index == -1) || a[index] == Ints.max(a));
  }

}
