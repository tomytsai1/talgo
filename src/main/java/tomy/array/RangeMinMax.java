package tomy.array;

import java.util.ArrayDeque;
import java.util.Deque;


public class RangeMinMax {
  /**
   * contract: all numbers in nums > 0
   *
   * @param nums
   * @return
   */
  public static int[] findLongestRangeMin2LargerThanMax(int[] nums) {
    if (nums == null || nums.length == 0) {
      return new int[] {-1, -1};
    }

    int optLen = 1;
    int optStart = 0;
    int begin = 0;
    int end = 0;
    Deque<Integer> minQue = new ArrayDeque<>();
    Deque<Integer> maxQue = new ArrayDeque<>();
    minQue.add(nums[0]);
    maxQue.add(nums[0]);

    while (true) {
      int curMin = minQue.peekFirst();
      int curMax = maxQue.peekFirst();
      if (curMin * 2 > curMax) {
        if (end - begin + 1 > optLen) {
          optLen = end - begin + 1;
          optStart = begin;
        }

        ++end;
        if (end >= nums.length) {
          break;
        }

        //@@ remove -> need to check if empty
        while (!maxQue.isEmpty() && maxQue.peekLast() < nums[end]) {
          maxQue.removeLast();
        }
        maxQue.addLast(nums[end]);

        while (!minQue.isEmpty() && minQue.peekLast() > nums[end]) {
          minQue.removeLast();
        }
        minQue.addLast(nums[end]);
      } else {
        ++begin;
        if (begin + optLen >= nums.length) {
          break;
        }

        //@@ special case: previous end can be equal to or less than the current begin
        // in this function, one element always satisfies the condition, so we will always shift end at least once
        // hence, it is impossible to see begin > end
        if (begin == end) {
          maxQue.clear();
          maxQue.add(nums[begin]);

          minQue.clear();
          minQue.add(nums[begin]);
        } else {
          if (maxQue.peekFirst() == nums[begin - 1]) {
            maxQue.removeFirst();
          }
          if (minQue.peekFirst() == nums[begin - 1]) {
            minQue.removeFirst();
          }
        }
      }
    }

    return new int[] {optStart, optLen};

    // check nums == null , nums.len == 0

    // optLen = 1, optStart = 0
    // end = min = max = nums[0]
    // while num.len - begin > optLen
      // if min * 2 > max
        // update optLen

        // if end == nums.len - 1
          // break

        // ++end
        // update min, max
      // else
        // if begin == nums.len - 1
          // break

        // ++begin
        // if begin > end
          // reset min = max = nums[begin]
        // else
          // update min, max

    // example:
    // 1 4 3 5 7 6
    // 1
    //   4 3 5 7
    //     3 5 7
    //       5 7
  }
}
