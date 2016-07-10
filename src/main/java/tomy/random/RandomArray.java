package tomy.random;

import com.google.common.base.Preconditions;

import static tomy.random.RandomGen.RANDOM;


public class RandomArray {
  public static int[] genRandIntPerm(int size) {
    Preconditions.checkArgument(size >= 0);

    int[] result = new int[size];
    for (int i = 0; i < size; ++i) {
      int swapIndex = RANDOM.nextInt(i + 1);
      if (swapIndex == i) {
        result[i] = i;
      } else {
        result[i] = result[swapIndex];
        result[swapIndex] = i;
      }
    }
    return result;
  }
}
