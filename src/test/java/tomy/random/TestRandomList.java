package tomy.random;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tomy.TestBase;
import tomy.list.DList;
import tomy.list.ListPrint;
import tomy.list.SList;

import static tomy.random.RandomGen.RANDOM;


public class TestRandomList extends TestBase {
  private static final int NUM_TEST = 10;
  private static final int MIN_LEN = 0;
  private static final int MAX_LEN = 10;

  @DataProvider(name = "dataFromRand")
  private Object[][] dataFromRand() {
    Object[][] result = new Object [NUM_TEST][];

    for (int i = 0; i < NUM_TEST; ++i) {
      int size = MIN_LEN + RANDOM.nextInt(MAX_LEN - MIN_LEN + 1);
      result[i] = new Object[] {size};
    }
    return result;
  }

  @Test(dataProvider = "dataFromRand")
  public void testRandomSList(int size) {
    SList<Integer> slist = RandomList.genRandSList(size);
    LOGGER.debug("size = " + size + " " + ListPrint.toString(slist));
  }

  @Test(dataProvider = "dataFromRand")
  public void testRandomDList(int size) {
    DList<Integer> dlist = RandomList.genRandDList(size);
    LOGGER.debug("size = " + size + " " + ListPrint.toString(dlist));
  }

  @Test(dataProvider = "dataFromRand")
  public void testRandomCList(int size) {
    DList<Integer> clist = RandomList.genRandCList(size);
    LOGGER.debug("size = " + size + " " + ListPrint.toString(clist));
  }

}
