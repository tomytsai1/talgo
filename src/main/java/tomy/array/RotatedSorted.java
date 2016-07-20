package tomy.array;

public class RotatedSorted {
  public static int findKinRotatedSortedArray(int[] nums, int k) {
    if (nums == null || nums.length == 0) {
      return -1;
    }

    int l = 0;
    int r = nums.length - 1;
    while (l + 2 <= r) { // size >= 3
      //@@ note we cannot write l + (r - l) >> 1 : "+" is higher than ">>"
      int m = l + ((r - l) >> 1);
      if (nums[m] == k) {
        return m;
      }
      if (nums[m] > nums[l]) {
        if (nums[l] <= k && k < nums[m]) {
          r = m - 1;
        } else {
          l = m + 1;
        }
      } else {
        if (nums[m] < k && k < nums[l]) {
          l = m + 1;
        } else {
          r = m - 1;
        }
      }
    }

    if (l + 1 == r) {
      return nums[l] == k
          ? l
          : (nums[r] == k ? r : -1);
    }

    return nums[l] == k ? l : -1;

    // example:
    //   * > l :
    //
    //   3 4 5 6 7 0 1 2   find k = 5
    //         * Max       l <= k < *     -> left
    //
    //   3 4 5 6 7 0 1 2   find k = 1
    //         * Max       !(l <= k < *)  -> right
    //
    //   0 1 2 3 4 5 6 7   find k = 1     -- special: non-rotated
    //         * Max       same rule as above
    //
    //   * < l
    //
    //   3 4 5 6   0 1 2 3 find k = 5
    //         Max *       * < k < l      -> right
    //
    //   3 4 5 6   0 1 2 3 find k = 4
    //         Max *       !(* < k < l)   -> left
    //
    //@@ special case is useful for both starting and end condition, so we put it at the end
    //   0   find k = 1    -- special: len = 1
    //                     check with nums[0]
    //
    //   0 1 find k = 2    -- special: len = 2, sorted
    //                     check with nums[0] and nums[1]
    //
  }

  public static int findMaxInRotatedSortedArray(int[] nums) {
    if (nums == null || nums.length == 0) {
      return -1;
    }

    int l = 0;
    int r = nums.length - 1;
    while (l + 2 <= r) {
      int m = l + ((r - l) >> 1);
      if (nums[m] < nums[l]) {
        r = m - 1;
      } else if (nums[m] < nums[m + 1]) {
        l = m + 1;
      } else {
        return m;
      }
    }

    if (l + 1 == r) {
      return nums[l] > nums[r] ? l : r;
    }

    return l;

    // examples:
    // *m < *l
    //   3 4 5 0 1 2
    //         m       go left
    //
    // *m > *l , *m < *m+1
    //   3 4 5 6 1 2
    //       m         go right
    //
    // *m > *l , *m > *m+1
    //   3 4 5 0 1 2
    //       m         ret m
    //
    // 2 element       ret *l vs *l+1
    //   0 1
    //
    // 1 element       ret *l
    //   0

  }
}
