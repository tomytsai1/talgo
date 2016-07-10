package tomy;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.log4testng.Logger;

import static tomy.random.RandomGen.RANDOM_SEED;


public class TestBase {
  public static final Logger LOGGER = Logger.getLogger(TestBase.class);
  public static int numTest = 0;

  @BeforeTest
  public void printRandom() {
    LOGGER.debug("RANDOM SEED = " + RANDOM_SEED);
  }

  @BeforeMethod
  public void printNumTest() {
    LOGGER.debug("\nTest: " + numTest++);
  }

}
