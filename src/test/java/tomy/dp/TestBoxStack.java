package tomy.dp;

import java.util.Arrays;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tomy.TestBase;
import tomy.random.RandomBox;


public class TestBoxStack extends TestBase {
  private static final int NUM_TEST = 10;
  private static final int MIN_LEN = 1;
  private static final int MAX_LEN = 10;
  private static final int MIN_WIDTH = 1;
  private static final int MAX_WIDTH = 10;
  private static final int MIN_HEIGHT = 0;
  private static final int MAX_HEIGHT = 10;

  @DataProvider(name = "dataFromRand")
  private Object[][] dataFromRand() {
    //RandomGen.setRandomSeed(15749669);
    Object[][] result = new Object [NUM_TEST][];

    for (int i = 0; i < result.length; ++i) {
      result[i] = new Object[] {RandomBox.genRandBox(NUM_TEST, MIN_LEN, MAX_LEN, MIN_WIDTH, MAX_WIDTH, MIN_HEIGHT, MAX_HEIGHT)};
    }
    return result;
  }


  @Test(dataProvider = "dataFromRand")
  public void testBoxStack(Box[] boxes) {
    LOGGER.debug("a[] = " + Arrays.asList(boxes));

    BoxStack.BoxStackInfo boxStackInfo = BoxStack.computeMaxBoxStackHeight(boxes);
    LOGGER.debug("max total height = " + boxStackInfo.totalHeight + "  selected : " +
        Arrays.asList(boxStackInfo.selectedBoxes));
  }


}
