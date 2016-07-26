package tomy.array;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tomy.TestBase;
import tomy.random.RandomArray;
import tomy.random.RandomGen;


public class TestLongestIncreasingSubsequence extends TestBase {
  private static final int NUM_TEST = 10;
  private static final int MIN_LEN = 0;
  private static final int MAX_LEN = 10;
  private static final int MIN_VAL = 0;
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
  public void testContgenRandIntinuousRange(int[] a) {
    LOGGER.debug("a[] = " + ArrayPrint.toString(a));

    int[] lis = LongestIncreasingSubsequence.findLongestIncreasingSubsequence(a);
    LOGGER.debug("LIS = " + ArrayPrint.toString(lis));

    int[] lisBf = LongestIncreasingSubsequence.findLongestIncreasingSubsequenceBruteForce(a, true);
    LOGGER.debug("LIS (B-F) = " + ArrayPrint.toString(lisBf));

    Assert.assertEquals(lis.length, lisBf.length);

    int[] lnds = LongestIncreasingSubsequence.findLongestNondcreasingSubsequence(a);
    LOGGER.debug("LNDS = " + ArrayPrint.toString(lnds));

    int[] lndsBf = LongestIncreasingSubsequence.findLongestIncreasingSubsequenceBruteForce(a, false);
    LOGGER.debug("LNDS (B-F) = " + ArrayPrint.toString(lndsBf));

    Assert.assertEquals(lnds.length, lndsBf.length);
  }

}
