package tomy.matrix;

import java.util.Arrays;


public final class MatrixUtil {
  public static int[][] matrixCopy(int[][] matrix) {
    if (matrix == null) {
      return null;
    }

    int[][] result = new int[matrix.length][];
    for (int i = 0; i < matrix.length; ++i) {
      result[i] = Arrays.copyOf(matrix[i], matrix[i].length);
    }

    return result;
  }

  private MatrixUtil() {
  }
}
