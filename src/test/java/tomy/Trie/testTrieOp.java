package tomy.trie;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tomy.TestBase;
import tomy.random.RandomGen;
import tomy.random.RandomString;


public class TestTrieOp extends TestBase {
  private static final int NUM_TEST = 1;
  private static final int MIN_NUM_STR = 0;
  private static final int MAX_NUM_STR = 5;
  private static final int MIN_STR_LEN = 0;
  private static final int MAX_STR_LEN = 4;
  private static final char CHAR_MIN = 'a';
  private static final char CHAR_MAX = 'c';

  @DataProvider(name = "dataFromRand")
  private Object[][] dataFromRand() {
    Object[][] result = new Object [NUM_TEST][];

    for (int i = 0; i < NUM_TEST; ++i) {
      int numString = MIN_NUM_STR + RandomGen.getRandom().nextInt(MAX_NUM_STR - MIN_NUM_STR + 1);

      String[] stringList = new String[numString];
      for (int j = 0; j < numString; ++j) {
        int len = MIN_STR_LEN + RandomGen.getRandom().nextInt(MAX_STR_LEN - MIN_STR_LEN + 1);
        stringList[j] = RandomString.genRandStr(len, CHAR_MIN, CHAR_MAX);
      }
      result[i] = stringList;
    }
    return result;
  }

  @Test(dataProvider = "dataFromRand")
  public void testTrieOp(String... stringList) {
    Trie trie = new Trie(' ');
    LOGGER.debug("NumStr " + stringList.length);

    for (String s: stringList) {
      LOGGER.debug("+ " + s + " -> " + TrieOp.insert(trie, s));

      TriePrint.printBfs(trie);
    }

    for (String s: stringList) {
      LOGGER.debug("+ " + s + " -> " + TrieOp.insert(trie, s));
      LOGGER.debug("? " + s + " -> " + TrieOp.search(trie, s, false));

      String s2 = s.replace('a', 'd').replace('b', 'c');
      LOGGER.debug("? " + s2 + " -> " + TrieOp.search(trie, s2, false));

      if (s.length() > 1) {
        String s3 = s.substring(0, s.length() - 1);
        LOGGER.debug("? " + s3 + " -> " + TrieOp.search(trie, s3, false));
        LOGGER.debug("P? " + s3 + " -> " + TrieOp.search(trie, s3, true));
      }
    }

    for (String s: stringList) {
      String s2 = s.replace('a', 'd').replace('b', 'c');
      LOGGER.debug("- " + s2 + " -> " + TrieOp.remove(trie, s2));

      LOGGER.debug("? " + s + " -> " + TrieOp.search(trie, s, false));

      LOGGER.debug("- " + s + " -> " + TrieOp.remove(trie, s));
      LOGGER.debug("? " + s + " -> " + TrieOp.search(trie, s, false));

      TriePrint.printBfs(trie);
    }
  }

}
