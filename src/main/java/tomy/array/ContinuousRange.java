package tomy.array;

import java.util.HashSet;
import java.util.Set;


public class ContinuousRange {
  public static int[] findContinuousRange(int[] nums) {
    // for each num
      // put into hash

    // for each num in hash
      // find left bound
      // find right bound

      // update optimal range

    // return optimal range

    int optLen = -1; // optLen = 1
    int optStart = -1;
    Set<Integer> numSet = new HashSet<>(nums.length);

    for (int n : nums) {
      numSet.add(n);
    }

    while (!numSet.isEmpty()) {
      int n = numSet.iterator().next();
      int lb = n - 1;
      while (numSet.contains(lb)) {
        numSet.remove(lb);
        --lb;
      }

      int rb = n + 1;
      while (numSet.contains(rb)) {
        numSet.remove(rb);
        ++rb;
      }

      numSet.remove(n); //@@ need to remove current number

      if (rb - lb - 1 > optLen) {
        optLen = rb - lb - 1;
        optStart = lb + 1;
      }
    }

    return new int[] {optStart, optLen};
  }
}
