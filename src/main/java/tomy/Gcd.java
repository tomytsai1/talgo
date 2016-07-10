package tomy;

import com.google.common.base.Preconditions;


public class Gcd {
  public static int gcd(int a, int b) {
    Preconditions.checkArgument(a > 0);
    Preconditions.checkArgument(b > 0);

    while (b != 0) {
      int t = b;
      b = a % b;
      a = t;
    }

    return a;
  }
}
