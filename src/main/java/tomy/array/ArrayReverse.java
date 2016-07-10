package tomy.array;

import java.util.List;

import com.google.common.base.Preconditions;


public class ArrayReverse {
  public static void reverse(int[] a, int from, int to) {
    Preconditions.checkArgument(from >= 0 && to <= a.length);
    Preconditions.checkArgument(from <= to);

    --to;
    while (from < to) {
      ArrayUtil.swap(a, from++, to--);
    }
  }

  public static <T> void reverse(T[] a) {
    reverse(a, 0, a.length);
  }

  private static <T> void reverse(T[] a, int from, int to) {
    Preconditions.checkArgument(from >= 0 && to <= a.length);
    Preconditions.checkArgument(from <= to);

    --to;
    while (from < to) {
      ArrayUtil.swap(a, from++, to--);
    }
  }

  public static <T> void reverse(List<T> a) {
    reverse(a, 0, a.size());
  }

  private static <T> void reverse(List<T> a, int from, int to) {
    Preconditions.checkArgument(from >= 0 && to <= a.size());
    Preconditions.checkArgument(from <= to);

    --to;
    while (from < to) {
      T temp = a.get(from);
      a.set(from, a.get(to));
      a.set(to, temp);

      ++from;
      --to;
    }
  }

}
