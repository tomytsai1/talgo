package tomy.list;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tomy.TestBase;
import tomy.random.RandomList;

import static tomy.random.RandomGen.RANDOM;


public class TestListReverse extends TestBase {
  private static final int NUM_TEST = 10;
  private static final int MIN_LEN = 0;
  private static final int MAX_LEN = 10;
  private static final int MIN_K = 1;
  private static final int MAX_K = 3;

  @DataProvider(name = "slistDataFromRand")
  private Object[][] slistDataFromRand() {
    Object[][] result = new Object [NUM_TEST][];

    for (int i = 0; i < NUM_TEST; ++i) {
      int size = MIN_LEN + RANDOM.nextInt(MAX_LEN - MIN_LEN + 1);
      SList<Integer> slist = RandomList.genRandSList(size);
      result[i] = new Object[] {slist};
    }
    return result;
  }

  @Test(dataProvider = "slistDataFromRand")
  public void testReverseSList(SList<Integer> slist) {
    LOGGER.debug("size = " + SList.size(slist));
    LOGGER.debug(ListPrint.toString(slist));

    SList<Integer> reversedList = ListReverse.reverseSList(slist);
    LOGGER.debug(ListPrint.toString(reversedList));
  }

  @DataProvider(name = "dlistDataFromRand")
  private Object[][] dlistDataFromRand() {
    Object[][] result = new Object [NUM_TEST][];

    for (int i = 0; i < NUM_TEST; ++i) {
      int size = MIN_LEN + RANDOM.nextInt(MAX_LEN - MIN_LEN + 1);
      DList<Integer> dlist = RandomList.genRandDList(size);
      result[i] = new Object[] {dlist};
    }
    return result;
  }

  @Test(dataProvider = "dlistDataFromRand")
  public void testReverseDList(DList<Integer> dlist) {
    LOGGER.debug("size = " + DList.size(dlist));
    LOGGER.debug(ListPrint.toString(dlist));

    DList<Integer> reversedList = ListReverse.reverseDList(dlist);
    LOGGER.debug(ListPrint.toString(reversedList));
  }

  @DataProvider(name = "clistDataFromRand")
  private Object[][] clistDataFromRand() {
    Object[][] result = new Object [NUM_TEST][];

    for (int i = 0; i < NUM_TEST; ++i) {
      int size = MIN_LEN + RANDOM.nextInt(MAX_LEN - MIN_LEN + 1);
      DList<Integer> clist = RandomList.genRandCList(size);
      result[i] = new Object[] {clist};
    }
    return result;
  }

  @Test(dataProvider = "clistDataFromRand")
  public void testReverseCList(DList<Integer> clist) {
    LOGGER.debug("size = " + DList.size(clist));
    LOGGER.debug(ListPrint.toString(clist));

    DList<Integer> reversedList = ListReverse.reverseCList(clist);
    LOGGER.debug(ListPrint.toString(reversedList));
  }

  @DataProvider(name = "slistDataAndKFromRand")
  private Object[][] slistDataAndKFromRand() {
    Object[][] result = new Object [NUM_TEST][];

    for (int i = 0; i < NUM_TEST; ++i) {
      int size = MIN_LEN + RANDOM.nextInt(MAX_LEN - MIN_LEN + 1);
      int k = MIN_K + RANDOM.nextInt(MAX_K - MIN_K + 1);
      SList<Integer> slist = RandomList.genRandSList(size);
      result[i] = new Object[] {slist, k};
    }
    return result;
  }

  @Test(dataProvider = "slistDataAndKFromRand")
  public void testReverseSListEveryK(SList<Integer> slist, int k) {
    LOGGER.debug("size = " + SList.size(slist) + " k = " + k);
    LOGGER.debug(ListPrint.toString(slist));

    SList<Integer> reversedList = ListReverse.reverseSListEveryK(slist, k);
    LOGGER.debug(ListPrint.toString(reversedList));
  }

  @Test(dataProvider = "slistDataAndKFromRand")
  public void testReverseSListEveryAltK(SList<Integer> slist, int k) {
    LOGGER.debug("size = " + SList.size(slist) + " k = " + k);
    LOGGER.debug(ListPrint.toString(slist));

    SList<Integer> reversedList = ListReverse.reverseSListEveryAltK(slist, k);
    LOGGER.debug(ListPrint.toString(reversedList));
  }

}
