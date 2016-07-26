package tomy.array;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;


public class SubarraySum {
  /**
   * Assumption: nums[i] >= 0 for all i
   *
   * @param nums
   * @param k
   * @return
   */
  public static int[] findShortestSubarrayWithSumLargerThanK(int[] nums, int k) {
    int[] resultRange = new int[] {-1, -1};
    if (nums == null || nums.length == 0) {
      return resultRange;
    }

    int begin = 0;
    int end = 0;
    int sum = nums[0];
    int optPos = -1;
    int optLen = nums.length + 1;
    while (true) {
      if (sum >= k) {
        if (end - begin + 1 < optLen) {
          optLen = end - begin + 1;
          optPos = begin;
        }

        if (begin == nums.length - 1) {
          break;
        }

        if (begin < end) {
          sum -= nums[begin];
          ++begin;
        } else {
          ++begin;
          end = begin;
          sum = nums[begin];
        }
      } else {
        ++end;
        if (end >= nums.length) {
          break;
        }
        sum += nums[end];
      }
    }

    if (optPos != -1) {
      resultRange[0] = optPos;
      resultRange[1] = optPos + optLen - 1;
    }
    return resultRange;

    /*
    Corner
      nums == null
      nums.len == 0
        return -1, -1
      nums.len == 1
        general
      nums.len == 2
        general

    General
      begin = 0
      end = 0
      sum = nums[0]
      optPos = -1
      optLen = -1
      while true
        if sum >= k
          update opt pos and len

          if begin == nums.len - 1
            break

          if begin < end
            sum -= nums[begin]
            ++begin
          else
            ++begin
            end = begin
            sum = nums[begin]
        else
          ++end
          if end >= nums.len
            break
          sum += nums[end]

      return [optPos, optPos + optLen - 1]

     */
  }

  public static int[] findShortestSubarrayWithSumLargerThanKBruteForce(int[] nums, int k) {
    int[] resultRange = new int[]{-1, -1};
    if (nums == null || nums.length == 0) {
      return resultRange;
    }

    int optPos = -1;
    int optLen = nums.length + 1;
    for (int begin = 0; begin < nums.length; ++begin) {
      for (int end = begin; end < nums.length; ++end) {
        int sum = Arrays.stream(nums, begin, end + 1).sum();
        if (sum >= k) {
          if (end - begin + 1 < optLen) {
            optPos = begin;
            optLen = end - begin + 1;
          }
        }
      }
    }

    if (optPos != -1) {
      resultRange[0] = optPos;
      resultRange[1] = optPos + optLen - 1;
    }
    return resultRange;
  }

  /**
   * Assumption: nums[i] can be <=> 0 for all i
   *
   * @param nums
   * @param k
   * @return
   */
  public static int[] findShortestSubarrayWithNegValuesAndSumLargerThanK(int[] nums, int k) {
    int[] resultRange = new int[] {-1, -1};
    if (nums == null || nums.length == 0) {
      return resultRange;
    }

    int[] psum = new int[nums.length + 1];
    for (int i = 1; i <= nums.length; ++i) {
      psum[i] = nums[i - 1] + psum[i - 1];
    }

    int optLen = nums.length + 1;
    int optPos = -1;
    Deque<Integer> posDeque = new ArrayDeque<>();
    posDeque.add(0);

    for (int i = 1; i < psum.length; ++i) {
      int curVal = psum[i];
      while (!posDeque.isEmpty() && curVal >= psum[posDeque.getFirst()] + k) {
        if (i - posDeque.getFirst() < optLen) {
          optLen = i - posDeque.getFirst();
          optPos = posDeque.getFirst();
        }

        posDeque.removeFirst();
      }

      while (!posDeque.isEmpty() && psum[posDeque.getLast()] >= curVal) {
        posDeque.removeLast();
      }
      posDeque.addLast(i);
    }

    if (optPos != -1) {
      resultRange[0] = optPos;
      resultRange[1] = optPos + optLen - 1;
    }

    return resultRange;

    /*
    Corner
      nums == null
      nums.len == 0
        return -1, -1
      nums.len == 1
        general
      nums.len == 2
        general

    General
      generate partial sum array
        - find shortest |j - i| s.t. s[j] - s[i] >= k
        org
          3 1 1 1
        deque
          3 4 5 6

      optLen = -1
      optPos = -1
      init deque {pos}
      deque.addLast(-1) -> special value, -1 means no previous

      for i = 0 to psum.len - 1
        curVal = nums[i]
        while deque.size > 1
          if curVal < nums[deque.first] + k (deque.first == -1 -> val = 0)
            break
          update optLen / optPos
            - optLen = i - deque.first
            - optPos = deque.first + 1
          deque.removeFirst

        while deque is not empty and psum[deque.last] > curVal
          deque.removeLast

        deque.addLast(i)

      return [optPos, optPos + optLen - 1]
     */
  }


  /**
   * Assumption: nums[i] >= 0 for all i
   *
   * @param nums
   * @param k
   * @return
   */
  public static int[] findLongestSubarrayWithSumSmallerThanK(int[] nums, int k) {
    int[] resultRange = new int[] {-1, -1};
    if (nums == null || nums.length == 0) {
      return resultRange;
    }

    int begin = 0;
    int end = 0;
    int sum = nums[0];
    int optPos = -1;
    int optLen = -1;
    while (true) {
      if (sum > k) {
        if (begin == nums.length - 1) {
          break;
        }

        if (begin < end) {
          sum -= nums[begin];
          ++begin;
        } else {
          ++begin;
          end = begin;
          sum = nums[begin];
        }
      } else {
        if (end - begin + 1 > optLen) {
          optLen = end - begin + 1;
          optPos = begin;
        }

        ++end;
        if (end >= nums.length) {
          break;
        }
        sum += nums[end];
      }
    }

    if (optPos != -1) {
      resultRange[0] = optPos;
      resultRange[1] = optPos + optLen - 1;
    }
    return resultRange;
  }

  public static int[] findLongestSubarrayWithSumSmallerThanKBruteForce(int[] nums, int k) {
    int[] resultRange = new int[]{-1, -1};
    if (nums == null || nums.length == 0) {
      return resultRange;
    }

    int optPos = -1;
    int optLen = -1;
    for (int begin = 0; begin < nums.length; ++begin) {
      for (int end = begin; end < nums.length; ++end) {
        int sum = Arrays.stream(nums, begin, end + 1).sum();
        if (sum <= k) {
          if (end - begin + 1 > optLen) {
            optPos = begin;
            optLen = end - begin + 1;
          }
        }
      }
    }

    if (optPos != -1) {
      resultRange[0] = optPos;
      resultRange[1] = optPos + optLen - 1;
    }
    return resultRange;
  }

}
