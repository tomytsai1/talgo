package tomy.array;

import com.google.common.primitives.Ints;
import java.util.stream.IntStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tomy.TestBase;

import static tomy.random.RandomGen.RANDOM;


public class TestArrayReverse extends TestBase {
  private static final int NUM_TEST = 10;
  private static final int MIN_LEN = 0;
  private static final int MAX_LEN = 10;

  @DataProvider(name = "dataFromRand")
  private Object[][] dataFromRand() {
    Object[][] result = new Object [NUM_TEST][];

    for (int i = 0; i < NUM_TEST; ++i) {
      int len = MIN_LEN + RANDOM.nextInt(MAX_LEN - MIN_LEN + 1);

      int[] a = IntStream.range(0, len).toArray();

      result[i] = new Object[] {a};
    }
    return result;
  }

  @Test(dataProvider = "dataFromRand")
  public void testReverse(int[] a) {
    int[] aOrg = a.clone();
    ArrayReverse.reverse(a, 0, a.length);
    LOGGER.debug("aOrg[] = " + Ints.asList(aOrg) + "  a[] = " + Ints.asList(a));
  }

}
