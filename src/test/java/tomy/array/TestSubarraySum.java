package tomy.array;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tomy.TestBase;
import tomy.random.RandomArray;
import tomy.random.RandomGen;


public class TestSubarraySum extends TestBase {
  private static final int NUM_TEST = 10;
  private static final int MIN_LEN = 0;
  private static final int MAX_LEN = 10;
  private static final int MIN_VAL = 0;
  private static final int MIN_VAL_NEG = -10;
  private static final int MAX_VAL = 10;

  @DataProvider(name = "dataFromRand")
  private Object[][] dataFromRand() {
    //RandomGen.setRandomSeed(704993566);
    Object[][] result = new Object [NUM_TEST][];

    for (int i = 0; i < NUM_TEST; ++i) {
      int len = MIN_LEN + RandomGen.getRandom().nextInt(MAX_LEN - MIN_LEN + 1);
      int[] a = RandomArray.genRandInt(len, MIN_VAL, MAX_VAL);
      int sum = Arrays.stream(a).sum();
      int k = RandomGen.getRandom().nextInt(sum + 1);
      result[i] = new Object[] {a, k};
    }
    //result[0] = result[1];
    return result;
  }

  @Test(dataProvider = "dataFromRand")
  public void testShortestubarraySum(int[] a, int k) {
    LOGGER.debug("a[] = " + ArrayPrint.toString(a));
    LOGGER.debug("k = " + k);

    int[] range0 = SubarraySum.findShortestSubarrayWithSumLargerThanKBruteForce(a, k);
    LOGGER.debug("shortest subarray (B-F) = " + ArrayPrint.toString(range0));

    int[] range = SubarraySum.findShortestSubarrayWithSumLargerThanK(a, k);
    LOGGER.debug("shortest subarray = " + ArrayPrint.toString(range));

    int[] range2 = SubarraySum.findShortestSubarrayWithNegValuesAndSumLargerThanK(a, k);
    LOGGER.debug("shortest subarray (supporting neg value) = " + ArrayPrint.toString(range2));

    Assert.assertEquals(range0, range);
    Assert.assertEquals(range0, range2);
  }

  @Test(dataProvider = "dataFromRand")
  public void testLongestSubarraySum(int[] a, int k) {
    LOGGER.debug("a[] = " + ArrayPrint.toString(a));
    LOGGER.debug("k = " + k);

    int[] range0 = SubarraySum.findLongestSubarrayWithSumSmallerThanKBruteForce(a, k);
    LOGGER.debug("longest subarray (B-F) = " + ArrayPrint.toString(range0));

    int[] range = SubarraySum.findLongestSubarrayWithSumSmallerThanK(a, k);
    LOGGER.debug("longest subarray = " + ArrayPrint.toString(range));

    Assert.assertEquals(range0, range);
  }

  @DataProvider(name = "dataFromRand2")
  private Object[][] dataFromRand2() {
    RandomGen.setRandomSeed(482588616);
    Object[][] result = new Object [NUM_TEST][];

    for (int i = 0; i < NUM_TEST; ++i) {
      int len = MIN_LEN + RandomGen.getRandom().nextInt(MAX_LEN - MIN_LEN + 1);
      int[] a = RandomArray.genRandInt(len, MIN_VAL_NEG, MAX_VAL);
      int sum = Arrays.stream(a).filter(n -> n >= 0).sum();
      int k = RandomGen.getRandom().nextInt(sum + 1);
      result[i] = new Object[] {a, k};
    }
    //result[0] = result[1];
    return result;
  }

  @Test(dataProvider = "dataFromRand2")
  public void testShortestubarraySum2(int[] a, int k) {
    LOGGER.debug("a[] = " + ArrayPrint.toString(a));
    LOGGER.debug("k = " + k);

    int[] range0 = SubarraySum.findShortestSubarrayWithSumLargerThanKBruteForce(a, k);
    LOGGER.debug("shortest subarray (B-F) = " + ArrayPrint.toString(range0));

    int[] range2 = SubarraySum.findShortestSubarrayWithNegValuesAndSumLargerThanK(a, k);
    LOGGER.debug("shortest subarray (supporting neg value) = " + ArrayPrint.toString(range2));

    int[] range = SubarraySum.findShortestSubarrayWithSumLargerThanK(a, k);
    LOGGER.debug("shortest subarray (may be different from above) = " + ArrayPrint.toString(range));

    /**
     * example input that needs the neg algo:
     *   [-2, 3, -10, -4, 6, -3, 8]
     *
     * shortest subarray (supporting neg value) = [4, 6]
     * shortest subarray (may be different from above) = [-1, -1]
     */
    Assert.assertEquals(range0, range2);
  }


}
