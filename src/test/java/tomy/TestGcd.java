package tomy;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static tomy.random.RandomGen.RANDOM;


public class TestGcd extends TestBase {
  private static final int NUM_TEST = 5;
  private static final int MIN_VALUE = 1;
  private static final int MAX_VALUE = 100;

  @DataProvider(name = "dataFromRand")
  private Object[][] dataFromRand() {
    Object[][] result = new Object [NUM_TEST][];

    for (int i = 0; i < NUM_TEST; ++i) {
      result[i] = new Object[] {
          MIN_VALUE + RANDOM.nextInt(MAX_VALUE - MIN_VALUE),
          MIN_VALUE + RANDOM.nextInt(MAX_VALUE - MIN_VALUE)};
    }
    return result;
  }

  @Test(dataProvider = "dataFromRand")
  public void testGcd(int a, int b) {
    LOGGER.debug("a = " + a + " b = " + b );

    double gcd = Gcd.gcd(a, b);
    LOGGER.debug("             * gcd = " + (int) gcd + "   a/gcd = " + a / gcd + " b/gcd = " + b / gcd);
  }

}
