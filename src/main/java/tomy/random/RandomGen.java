package tomy.random;

import java.util.Random;

import org.testng.log4testng.Logger;


public class RandomGen {
  private static final Logger LOGGER = Logger.getLogger(RandomGen.class);

  //public static final long RANDOM_SEED = System.nanoTime() % 1241217197L;
  public static final long RANDOM_SEED = 983380914;
  public static final Random RANDOM = new Random(RANDOM_SEED);
}
