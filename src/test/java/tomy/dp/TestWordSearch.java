package tomy.dp;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tomy.TestBase;
import tomy.matrix.MatrixPrint;
import tomy.random.RandomGen;
import tomy.random.RandomMatrix;
import tomy.random.RandomString;


public class TestWordSearch extends TestBase {
  private static final int NUM_TEST = 10;
  private static final int MIN_PAT_LEN = 1;
  private static final int MAX_PAT_LEN = 4;
  private static final char MIN_CHAR = 'a';
  private static final char MAX_CHAR = 'c';

  @DataProvider(name = "dataFromRand")
  private Object[][] dataFromRand() {
    Object[][] result = new Object [NUM_TEST][];

    for (int i = 0; i < NUM_TEST; ++i) {
      int patLen = MIN_PAT_LEN + RandomGen.getRandom().nextInt(MAX_PAT_LEN - MIN_PAT_LEN + 1);
      char[][] puzzle = RandomMatrix.genRandCharMatrix(patLen, patLen, MIN_CHAR, MAX_CHAR);
      String target = RandomString.genRandStr(patLen, MIN_CHAR, MAX_CHAR);

      result[i] = new Object[] {puzzle, target};
    }
    return result;
  }

  @Test(dataProvider = "dataFromRand")
  public void testWordSearch(char[][] puzzle, String target) {
    LOGGER.debug("puzzle =");
    MatrixPrint.print(puzzle);
    LOGGER.debug("target = " + target);

    int[][] trace = WordSearch.wordSearchDfs(puzzle, target);
    LOGGER.debug("trace  = ");
    MatrixPrint.print(trace);

    int[][] trace2 = WordSearch.wordSearchBfs(puzzle, target);
    LOGGER.debug("trace2 = ");
    MatrixPrint.print(trace2);
  }

}
