package tomy.random;

import com.google.common.base.Preconditions;

import static tomy.random.RandomGen.RANDOM;

public class RandomParen {
  public static String genRandParen(int len) {
    Preconditions.checkArgument(len % 2 == 0, "Input len must be an even number");
    final int maxNumLeft = len / 2;

    StringBuilder sb = new StringBuilder(len);
    int numRight = 0;
    for (int i = 0; i < len; ++i) {
      if (numRight * 2 >= i || (RANDOM.nextBoolean() && (i - numRight < maxNumLeft))) {
        sb.append('(');
      } else {
        ++numRight;
        sb.append(')');
      }
    }
    return sb.toString();
  }
}
