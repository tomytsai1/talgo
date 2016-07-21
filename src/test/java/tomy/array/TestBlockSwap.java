package tomy.array;

import java.util.stream.IntStream;

import com.google.common.primitives.Ints;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tomy.TestBase;
import tomy.random.RandomGen;


public class TestBlockSwap extends TestBase {
  private static final int NUM_TEST = 10;
  private static final int MIN_LEN = 0;
  private static final int MAX_LEN = 10;

  @DataProvider(name = "dataFromRand")
  private Object[][] dataFromRand() {
    Object[][] result = new Object [NUM_TEST][];

    for (int i = 0; i < NUM_TEST; ++i) {
      int len = MIN_LEN + RandomGen.getRandom().nextInt(MAX_LEN - MIN_LEN + 1);

      int[] a = IntStream.range(0, len).toArray();
      int middle = RandomGen.getRandom().nextInt(len + 1);

      result[i] = new Object[] {a, middle};
    }
    return result;
  }

  @Test(dataProvider = "dataFromRand")
  public void testBlockSwap(int[] a, int middle) {
    int[] aOrg = a.clone();
    BlockSwap.blockSwap(a, middle);
    LOGGER.debug("aOrg[] = " + Ints.asList(aOrg) + " m = " + middle + "  a[] = " + Ints.asList(a));
  }

  @Test(dataProvider = "dataFromRand")
  public void testBlockSwap2(int[] a, int middle) {
    int[] aOrg = a.clone();
    BlockSwap.blockSwap2(a, middle);
    LOGGER.debug("aOrg[] = " + Ints.asList(aOrg) + " m = " + middle + "  a[] = " + Ints.asList(a));
  }

}
