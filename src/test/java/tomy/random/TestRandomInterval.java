package tomy.random;

import java.util.List;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tomy.TestBase;
import tomy.interval.Interval;


public class TestRandomInterval extends TestBase {
  private static final int NUM_TEST = 10;
  private static final int MIN_LEN = 0;
  private static final int MAX_LEN = 10;

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
  public void testRandomInterval(int size) {
    List<Interval> list = RandomInterval.genRandNonOverlappedInterval(size, 1, 5, 1, 10);
    LOGGER.debug(String.format("size = %2d  list of interval: %s", size, list.toString()));
  }

}
