package tomy.string;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tomy.TestBase;
import tomy.random.RandomGen;
import tomy.random.RandomString;


public class TestSearch extends TestBase {
  private static final int NUM_TEST = 10;
  private static final int MIN_SRC_LEN = 0;
  private static final int MAX_SRC_LEN = 10;
  private static final int MIN_PAT_LEN = 1;
  private static final int MAX_PAT_LEN = 4;
  private static final char MIN_CHAR = 'a';
  private static final char MAX_CHAR = 'c';

  @DataProvider(name = "dataFromRand")
  private Object[][] dataFromRand() {
    Object[][] result = new Object [NUM_TEST][];

    for (int i = 0; i < NUM_TEST; ++i) {
      int srcLen = MIN_SRC_LEN + RandomGen.getRandom().nextInt(MAX_SRC_LEN - MIN_SRC_LEN + 1);
      String source = RandomString.genRandStr(srcLen, MIN_CHAR, MAX_CHAR);

      int patLen = MIN_PAT_LEN + RandomGen.getRandom().nextInt(MAX_PAT_LEN - MIN_PAT_LEN + 1);
      String target = RandomString.genRandStr(patLen, MIN_CHAR, MAX_CHAR);

      result[i] = new Object[] {source, target};
    }
    result[0] = new Object[] {"abcabcabcabcabdabcab", "abcabda"};
    return result;
  }

  @Test(dataProvider = "dataFromRand")
  public void testSearchKMP(String source, String target) {
    LOGGER.debug("source = " + source + "  target = " + target);

    int pos = Search.searchKmp(source, target);
    LOGGER.debug("start pos = " + pos);
  }

}
