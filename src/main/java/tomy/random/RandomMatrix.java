package tomy.random;

import com.google.common.base.Preconditions;


public class RandomMatrix {
  public static int[][] genRandIntMatrix(int width, int height, int min, int max) {
    Preconditions.checkArgument(width >= 0 && height >= 0 && min <= max);

    int[][] result = new int[height][width];
    for (int i = 0; i < height; ++i) {
      for (int j = 0; j < width; ++j) {
        result[i][j] = RandomGen.getRandom().nextInt(max - min + 1) + min;
      }
    }
    return result;
  }

  public static char[][] genRandCharMatrix(int width, int height, char min, char max) {
    Preconditions.checkArgument(width >= 0 && height >= 0 && min <= max);

    char[][] result = new char[height][width];
    for (int i = 0; i < height; ++i) {
      for (int j = 0; j < width; ++j) {
        result[i][j] = (char) (RandomGen.getRandom().nextInt(max - min + 1) + min);
      }
    }
    return result;
  }
}
