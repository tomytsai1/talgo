package tomy.interval;

import org.testng.log4testng.Logger;


public class IntervalPrint {
  private static final Logger LOGGER = Logger.getLogger(IntervalPrint.class);
  public static final String VALUE_FORMAT = "%d";

  public static void print(Interval interval) {
    LOGGER.debug(toString(interval));
  }

  public static String toString(Interval interval) {
    return interval.toString();
  }

}
