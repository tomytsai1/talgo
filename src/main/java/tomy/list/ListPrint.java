package tomy.list;

import org.testng.log4testng.Logger;


public class ListPrint {
  private static final Logger LOGGER = Logger.getLogger(ListPrint.class);
  public static final String VALUE_FORMAT = "%d";

  public static void print(SList<?> list) {
    LOGGER.debug(toString(list));
  }

  public static String toString(SList<?> list) {
    StringBuilder result = new StringBuilder();
    boolean first = false;
    for (;list != null; list = list.n) {
      if (!first) {
        result.append(" ");
      }
      result.append(list.toString());
      first = true;
    }

    return result.toString();
  }

  public static String toString(DList<?> list) {
    StringBuilder result = new StringBuilder();
    boolean first = false;
    DList head = null;
    for (;list != null && list != head; list = list.n) {
      if (first) {
        head = list;
      } else {
        result.append(" ");
      }
      result.append(list.toString());
      first = true;
    }

    return result.toString();
  }
}
