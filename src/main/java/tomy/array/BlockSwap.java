package tomy.array;

import com.google.common.base.Preconditions;
import tomy.Gcd;


public class BlockSwap {
  public static void blockSwap(int[] a, int middle) {
    // 0 - middle
    // middle - a.length

    Preconditions.checkArgument(middle >= 0 && middle <= a.length);

    if (middle == 0 || a.length == middle) {
      return;
    }

    for (int i = 0; i < Gcd.gcd(middle, a.length - middle); ++i) {
      int firstIndex = i;
      int firstValue = a[i];
      int prevIndex = firstIndex;
      for (;;) {
        int nextIndex = prevIndex < a.length - middle ? middle + prevIndex : prevIndex - a.length + middle;
        if (nextIndex == firstIndex) {
          break;
        }

        a[prevIndex] = a[nextIndex];
        prevIndex = nextIndex;
      }
      a[prevIndex] = firstValue;
    }

    return;
  }

  public static void blockSwap2(int[] a, int middle) {
    ArrayReverse.reverse(a, 0, middle);
    ArrayReverse.reverse(a, middle, a.length);
    ArrayReverse.reverse(a, 0, a.length);
  }
}
