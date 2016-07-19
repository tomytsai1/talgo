package tomy.array;

import java.util.Arrays;
import java.util.Objects;


public class Bitonic {
  private static int binarySearchDescending(int[] nums, int begin, int end, int k) {
    while (begin < end) {
      int m = (begin + end) >> 1;
      if (nums[m] == k) {
        return m;
      } else if (nums[m] < k) {
        end = m - 1;
      } else {
        begin = m + 1;
      }
    }
    return nums[begin] == k ? begin : -1;
  }

  public static int findKinBitonic(int[] nums, int k) {
    // example:
    // 1 2 3 4 2 1
    //     * inc    k = 2  k < *  left binsearch , right bsearch
    //     * inc    k = 5  k > *  right bitonic
    //       * dec  k = 2  k < *  left binsearch, right binsearch
    //       * dec  k = 5  k > *  left bitonic

    Objects.requireNonNull(nums);
    if (nums.length == 0) {
      return -1;
    }

    int l = 0;
    int r = nums.length - 1;
    while (l < r) {
      int m = (l + r) >> 1;

      if (k == nums[m]) {
        return m;
      } else if (k < nums[m]) {
        //@@ binarySearch use [left, right) (right bound is exclusive)
        int leftIndex = Arrays.binarySearch(nums, l, m, k);
        if (leftIndex >= 0) {
          return leftIndex;
        }
        //@@ we use [m, r] (right bound is inclusive)
        int rightIndex = binarySearchDescending(nums, m, r, k);
        if (rightIndex >= 0) {
          return rightIndex;
        }
        return -1;
      }

      if (nums[m] < nums[m + 1]) {
        l = m + 1;
      } else {
        r = m - 1;
      }
    }

    return nums[l] == k ? l : -1;

    // check num != null
    // l = 0, r = num.len - 1
    // while l < r
      // m = (l + r) / 2
      // if k == num[m]
        // return m
      // if k < num[m]
        // binary search left & right
      // if num[m] < num[m + 1]
        // l = m + 1
      // else
        // r = m - 1
    // return num[l] == k ? l : -1;

  }
}
