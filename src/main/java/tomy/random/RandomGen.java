package tomy.random;

import java.util.Random;

import org.testng.log4testng.Logger;


public class RandomGen {
  public static final Logger LOGGER = Logger.getLogger(RandomGen.class);

  private static long _randomSeed = System.nanoTime() % 1241217197L;
  private static Random _random = new Random(getRandomSeed());

  public static long getRandomSeed() {
    return _randomSeed;
  }

  public static void setRandomSeed(long _randomSeed) {
    RandomGen._randomSeed = _randomSeed;
    LOGGER.debug("RANDOM SEED = " + RandomGen.getRandomSeed());
    _random = null;
  }

  public static Random getRandom() {
    return _random != null ? _random : (_random = new Random(getRandomSeed()));
  }
}
