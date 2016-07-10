package tomy.array;

import com.google.common.base.Preconditions;


public class RadixSort {
  private static final int NUM_BITS_INT = 32;

  public static void radixMsb(int[] a) {
    Preconditions.checkArgument(a != null);

    radixMsbHelper(a, 0, a.length, 1 << (NUM_BITS_INT - 1));
  }

  private static void radixMsbHelper(int[] a, int from, int to, int mask) {
    // while mask != 0 && from < to
      // pos = from
      // neg = to - 1
      // while pos < neg
        // while pos < to && a[pos] & mask == 0
          // ++pos

        // while neg >= from && a[neg] & mask != 0
          // --neg

        // if pos >= neg
          // assert pos <= neg + 1
          // break

        // swap a[pos] , a[neg]
        // ++pos , --neg

      // mask >>>= 1
      // radixMsbHelper(a, from, pos, mask)

      // from = pos

    while (mask != 0 && from < to) {
      int pos = from;
      int neg = to - 1;

      //!!! if we use "while (pos < neg)" , an ambiguous case is, after some iterations, if pos == neg - 2,
      // after swapping, we do (++pos, --neg) => new pos == new neg, and then a[pos] may belong to either 0 or 1 side
      // then, if a[pos] is actually on the 0 side, we miss this element (because we treat pos as ((last 0 side) + 1)
      for (;;) {
        while (pos < to && (a[pos] & mask) == 0) {
          ++pos;
        }

        while (neg >= from && (a[neg] & mask) != 0) {
          --neg;
        }

        if (pos >= neg) {
          Preconditions.checkState(pos <= neg + 1);
          break;
        }

        ArrayUtil.swap(a, pos++, neg--);
      }

      mask >>>= 1; //!!! need to use zero-filled shift (>>>) instead of sign-filled shift (>>)
      radixMsbHelper(a, from, pos, mask);

      from = pos;
    }
  }

  public static void radixLsb(int[] a) {
    Preconditions.checkArgument(a != null);

    //!!! here we need a *stable 0-1 sort*, so we can not use the quick sort
    // Also, we need to swap a[]/b[] for even times, so the final values will remain in a[]
    // Fortunately number of bits in int (32) is even, so we don't worry about this problem 
    int[] b = new int[a.length];
    for (int mask = 1; mask != 0; mask <<= 1) {
      int index = 0;
      for (int v: a) {
        if ((v & mask) == 0) {
          b[index++] = v;
        }
      }

      for (int v: a) {
        if ((v & mask) != 0) {
          b[index++] = v;
        }
      }

      int[] temp = a;
      a = b;
      b = temp;
    }
  }

}
