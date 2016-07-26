package tomy.random;

import com.google.common.base.Preconditions;
import tomy.dp.Box;


public class RandomBox {
  public static Box[] genRandBox(int size, int minLen, int maxLen, int minWidth, int maxWidth,
      int minHeight, int maxHeight) {
    Preconditions.checkArgument(size >= 0 && minLen <= maxLen);

    Box[] result = new Box[size];
    for (int i = 0; i < size; ++i) {
      result[i] = new Box(RandomGen.getRandom().nextInt(maxLen - minLen + 1) + minLen,
          RandomGen.getRandom().nextInt(maxWidth - minWidth + 1) + minWidth,
          RandomGen.getRandom().nextInt(maxHeight - minHeight + 1) + minHeight);
    }
    return result;
  }
}
