package tomy.matrix;

import com.google.common.primitives.Chars;
import com.google.common.primitives.Ints;
import org.testng.log4testng.Logger;


public class MatrixPrint {
  private static final Logger LOGGER = Logger.getLogger(MatrixPrint.class);
  public static final String VALUE_FORMAT = "%d";

  public static void print(char[][] char2D) {
    LOGGER.debug(toString(char2D));
  }

  public static String toString(char[][] char2D) {
    if (char2D == null) {
      return "null";
    }

    StringBuffer sb = new StringBuffer();
    sb.append("\n");
    for (int i = 0; i < char2D.length; ++i) {
      sb.append(Chars.asList(char2D[i]).toString());
      sb.append("\n");
    }
    return sb.toString();
  }

  public static void print(int[][] int2D) {
    LOGGER.debug(toString(int2D));
  }

  public static String toString(int[][] int2D) {
    if (int2D == null) {
      return "null";
    }

    StringBuffer sb = new StringBuffer();
    sb.append("\n");
    for (int i = 0; i < int2D.length; ++i) {
      sb.append(Ints.asList(int2D[i]).toString());
      sb.append("\n");
    }
    return sb.toString();
  }

}
