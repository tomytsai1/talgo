package tomy.array;

import java.util.stream.IntStream;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tomy.TestBase;
import tomy.random.RandomGen;


public class TestBitonic extends TestBase {
  private static final int NUM_TEST = 10;
  private static final int MIN_LEN = 0;
  private static final int MAX_LEN = 10;

  @DataProvider(name = "dataFromRand")
  private Object[][] dataFromRand() {
    Object[][] result = new Object [NUM_TEST][];

    for (int i = 0; i < NUM_TEST; ++i) {
      int len = MIN_LEN + RandomGen.getRandom().nextInt(MAX_LEN - MIN_LEN + 1);
      int[] a = IntStream.range(0, len).toArray();

      int k = 0;
      if (len > 0) {
        //@@ nextInt(x) , x must be > 0
        int lenReverse = RandomGen.getRandom().nextInt(len);
        ArrayReverse.reverse(a, a.length - lenReverse, a.length);
        k = RandomGen.getRandom().nextInt(len);
      }
      result[i] = new Object[] {a, k};
    }
    result[0] = result[2];
    return result;
  }

  @Test(dataProvider = "dataFromRand")
  public void testBitonic(int[] a, int k) {
    LOGGER.debug("a[] = " + ArrayPrint.toString(a) + " k = " + k);

    int index = Bitonic.findKinBitonic(a, k);
    LOGGER.debug("index = " + index);
  }

}
