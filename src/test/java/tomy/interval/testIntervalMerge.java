package tomy.interval;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tomy.TestBase;
import tomy.random.RandomInterval;

import static tomy.random.RandomGen.RANDOM;


public class TestIntervalMerge extends TestBase {
  private static final int NUM_TEST = 10;
  private static final int MIN_LEN = 0;
  private static final int MAX_LEN = 5;

  @DataProvider(name = "dataFromRand")
  private Object[][] dataFromRand() {
    Object[][] result = new Object[NUM_TEST][];

    for (int i = 0; i < NUM_TEST; ++i) {
      int len1 = MIN_LEN + RANDOM.nextInt(MAX_LEN - MIN_LEN + 1);
      List<Interval> list1 = RandomInterval.genRandNonOverlappedInterval(len1, 1, 5, 1, 5);

      int len2 = MIN_LEN + RANDOM.nextInt(MAX_LEN - MIN_LEN + 1);
      List<Interval> list2 = RandomInterval.genRandNonOverlappedInterval(len2, 1, 5, 1, 5);
      result[i] = new Object[]{list1, list2};
    }
    return result;
  }

  @Test(dataProvider = "dataFromRand")
  public void testIntervalMerge(List<Interval> list1, List<Interval> list2) {
    LOGGER.debug("list1: " + list1);
    LOGGER.debug("list2: " + list2);

    List<Interval> merged = IntervalMerge.mergeIntervals(list1, list2);
    LOGGER.debug("merged list : " + merged);

    List<Interval> merged2 = IntervalMerge.mergeIntervalsWithPQ(list1, list2);
    LOGGER.debug("merged list2: " + merged2);

    List<Interval> merged3 = IntervalMerge.mergeIntervalsWithPQ2(list1, list2);
    LOGGER.debug("merged list3: " + merged3);

    Assert.assertEquals(merged, merged2);
    Assert.assertEquals(merged, merged3);
  }

}
