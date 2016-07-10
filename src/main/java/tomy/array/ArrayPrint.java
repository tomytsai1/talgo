package tomy.array;

import com.google.common.primitives.Ints;
import org.testng.log4testng.Logger;


public class ArrayPrint {
  private static final Logger LOGGER = Logger.getLogger(ArrayPrint.class);
  public static final String VALUE_FORMAT = "%d";

  public static void print(int[] array) {
    LOGGER.debug(toString(array));
  }

  public static String toString(int[] array) {
    return Ints.asList(array).toString();
  }

}
