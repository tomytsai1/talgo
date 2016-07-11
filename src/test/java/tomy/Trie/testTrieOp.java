package tomy.Trie;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tomy.TestBase;
import tomy.random.RandomString;
import tomy.trie.Trie;
import tomy.trie.TrieOp;
import tomy.trie.TriePrint;

import static tomy.random.RandomGen.RANDOM;


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
      int numString = MIN_NUM_STR + RANDOM.nextInt(MAX_NUM_STR - MIN_NUM_STR + 1);

      String[] stringList = new String[numString];
      for (int j = 0; j < numString; ++j) {
        int len = MIN_STR_LEN + RANDOM.nextInt(MAX_STR_LEN - MIN_STR_LEN + 1);
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
      LOGGER.debug("? " + s + " -> " + TrieOp.search(trie, s));

//      String s2 = s.replace('a', 'd').replace('b', 'c');
//      LOGGER.debug("? " + s2 + " -> " + TrieOp.search(trie, s2));
    }

    for (String s: stringList) {
//      String s2 = s.replace('a', 'd').replace('b', 'c');
//      LOGGER.debug("- " + s2 + " -> " + TrieOp.remove(trie, s2));

      LOGGER.debug("? " + s + " -> " + TrieOp.search(trie, s));

      LOGGER.debug("- " + s + " -> " + TrieOp.remove(trie, s));
      LOGGER.debug("? " + s + " -> " + TrieOp.search(trie, s));

      TriePrint.printBfs(trie);
    }
  }

}
