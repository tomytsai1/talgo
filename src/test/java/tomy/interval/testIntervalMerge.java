package tomy.interval;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tomy.TestBase;
import tomy.random.RandomGen;
import tomy.random.RandomInterval;


public class TestIntervalMerge extends TestBase {
  private static final int NUM_TEST = 10;
  private static final int MIN_LEN = 0;
  private static final int MAX_LEN = 5;

  @DataProvider(name = "dataTwoListFromRand")
  private Object[][] dataTwoListFromRand() {
    Object[][] result = new Object[NUM_TEST][];

    for (int i = 0; i < NUM_TEST; ++i) {
      int len1 = MIN_LEN + RandomGen.getRandom().nextInt(MAX_LEN - MIN_LEN + 1);
      List<Interval> list1 = RandomInterval.genRandNonOverlappedInterval(len1, 1, 5, 1, 5);

      int len2 = MIN_LEN + RandomGen.getRandom().nextInt(MAX_LEN - MIN_LEN + 1);
      List<Interval> list2 = RandomInterval.genRandNonOverlappedInterval(len2, 1, 5, 1, 5);
      result[i] = new Object[]{list1, list2};
    }
    return result;
  }

  @Test(dataProvider = "dataTwoListFromRand")
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

  @DataProvider(name = "dataTwoListWithHeightFromRand")
  private Object[][] dataTwoListWithHeightFromRand() {
    //RandomGen.setRandomSeed(203413206);
    Object[][] result = new Object[NUM_TEST][];

    for (int i = 0; i < NUM_TEST; ++i) {
      int len1 = MIN_LEN + RandomGen.getRandom().nextInt(MAX_LEN - MIN_LEN + 1);
      List<IntervalWithHeight> list1 = RandomInterval.genRandNonOverlappedIntervalWithHeight(len1, 1, 5, 1, 5, 1, 5);

      int len2 = MIN_LEN + RandomGen.getRandom().nextInt(MAX_LEN - MIN_LEN + 1);
      List<IntervalWithHeight> list2 = RandomInterval.genRandNonOverlappedIntervalWithHeight(len2, 1, 5, 1, 5, 1, 5);
      result[i] = new Object[]{list1, list2};
    }
    //result[0] = result[8];
    return result;
  }

  @Test(dataProvider = "dataTwoListWithHeightFromRand")
  public void testIntervalWithHeightMerge(List<IntervalWithHeight> list1, List<IntervalWithHeight> list2) {
    LOGGER.debug("list1: " + list1);
    LOGGER.debug("list2: " + list2);

    List<IntervalWithHeight> merged = IntervalMerge.mergeIntervalsWithHeight(list1, list2);
    LOGGER.debug("merged list : " + merged);

    List<IntervalWithHeight> merged2 = IntervalMerge.mergeIntervalsWithHeight2(list1, list2);
    LOGGER.debug("merged list2: " + merged2);

    List<IntervalWithHeight> merged3 = IntervalMerge.mergeIntervalsWithHeight3(list1, list2);
    LOGGER.debug("merged list3: " + merged3);

    Assert.assertEquals(merged, merged2);
    Assert.assertEquals(merged, merged3);
  }

}