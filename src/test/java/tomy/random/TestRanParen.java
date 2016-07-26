package tomy.random;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tomy.TestBase;


public class TestRanParen extends TestBase {
  private static final int NUM_TEST = 10;
  private static final int MIN_LEN = 4;
  private static final int MAX_LEN = 8;

  @DataProvider(name = "dataFromRand")
  private Object[][] dataFromRand() {
    Object[][] result = new Object [NUM_TEST][];

    for (int i = 0; i < NUM_TEST; ++i) {
      int len = MIN_LEN / 2 + RandomGen.getRandom().nextInt(MAX_LEN / 2 - MIN_LEN / 2 + 1);
      result[i] = new Object[] {len * 2};
    }
    return result;
  }

  @Test(dataProvider = "dataFromRand")
  public void testRandomParen(int len) {
    String paren = RandomParen.genRandParen(len);
    LOGGER.debug(String.format("totalHeight = %2d  pren = %s", len, paren));
  }

}
