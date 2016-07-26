package tomy.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tomy.TestBase;
import tomy.random.RandomGen;


public class TestArrayMerge extends TestBase {
  private static final int NUM_TEST = 10;
  private static final int MIN_LEN = 1;
  private static final int MAX_LEN = 5;
  private static final int MIN_NUM_SOURCE = 1;
  private static final int MAX_NUM_SOURCE = 5;
  private static final int MIN_VALUE = 1;
  private static final int MAX_VALUE = 10;

  @DataProvider(name = "dataSourcesFromRand")
  private Object[][] dataSourcesFromRand() {
    Object[][] result = new Object[NUM_TEST][];

    for (int i = 0; i < NUM_TEST; ++i) {
      int numSources = MIN_NUM_SOURCE + RandomGen.getRandom().nextInt(MAX_NUM_SOURCE - MIN_NUM_SOURCE + 1);

      List<List<Integer>> sources = new ArrayList<>();
      for (int sourceIndex = 0; sourceIndex < numSources; ++sourceIndex) {
        int len = MIN_LEN + RandomGen.getRandom().nextInt(MAX_LEN - MIN_LEN + 1);
        List<Integer> source = new ArrayList<>();

        for (int elementIndex = 0; elementIndex < len; ++elementIndex) {
          source.add(MIN_VALUE + RandomGen.getRandom().nextInt(MAX_VALUE - MIN_VALUE + 1));
        }
        Collections.sort(source);
        sources.add(source);
      }
      result[i] = new Object[]{sources};
    }
    return result;
  }

  @Test(dataProvider = "dataSourcesFromRand")
  public void TestArrayMerge(List<List<Integer>> sources) {
    LOGGER.debug("sources:");
    sources.stream().forEach(LOGGER::debug);

    List<Integer> merged0 = new ArrayList<>();
    sources.stream().forEach(merged0::addAll);
    Collections.sort(merged0);
    LOGGER.debug("merged0 :");
    LOGGER.debug(merged0);

    List<Integer> merged1 = ArrayMerge.arrayMerge(sources);
    LOGGER.debug("merged1 :");
    LOGGER.debug(merged1);

    List<Integer> merged2 = ArrayMerge.arrayMerge2(sources);
    LOGGER.debug("merged2 :");
    LOGGER.debug(merged2);

    Assert.assertEquals(merged1, merged0);
    Assert.assertEquals(merged1, merged2);
  }
}