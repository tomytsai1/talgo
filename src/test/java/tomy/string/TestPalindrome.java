package tomy.string;

import java.util.stream.IntStream;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tomy.TestBase;

import static tomy.random.RandomGen.RANDOM;


public class TestPalindrome extends TestBase {
  private static final int NUM_TEST = 10;
  private static final int MIN_LEN = 0;
  private static final int MAX_LEN = 10;

  @DataProvider(name = "dataFromRand")
  private Object[][] dataFromRand() {
    Object[][] result = new Object [NUM_TEST][];

    for (int i = 0; i < NUM_TEST; ++i) {
      int len = MIN_LEN + RANDOM.nextInt(MAX_LEN - MIN_LEN + 1);

      int[] a = IntStream.range(0, len).toArray();
      int middle = RANDOM.nextInt(len + 1);

      result[i] = new Object[] {a, middle};
    }
    return result;
  }

  @DataProvider(name = "data1")
  private Object[][] data1() {
    return new Object [][] {
        {"abba"},
        {"cdbab"},
        {"caassdafadc"},
        {""},
        {"q"},
        {"zq"},
    };
  }

  @Test(dataProvider = "data1")
  public void testLongestPalindromeSubstringBasic(String str) {
    LOGGER.debug("str = " + str);

    String palin = Palindrome.longestPalindromSubstringBasic(str);
    LOGGER.debug("palin substr = " + palin);
  }

  @Test(dataProvider = "data1")
  public void testLongestPalindromeSubstringDp(String str) {
    LOGGER.debug("str = " + str);

    String palin = Palindrome.longestPalindromSubstringDp(str);
    LOGGER.debug("palin substr = " + palin);
  }


}
