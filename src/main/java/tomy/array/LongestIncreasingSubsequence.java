package tomy.array;

import java.util.Objects;
import java.util.TreeSet;

import com.google.common.base.Preconditions;
import com.google.common.collect.BoundType;
import com.google.common.collect.TreeMultiset;


public class LongestIncreasingSubsequence {
  public static int[] findLongestIncreasingSubsequence(int[] nums) {
    TreeSet<Integer> setLIS = new TreeSet<>((i, j) -> nums[i] - nums[j]);
    int[] prevPos = new int[nums.length];

    int optLen = 0;
    int optLastPos = -1;
    for (int i = 0; i < nums.length; ++i) {
      //@@ note: here we use treeset that only supports non-dup keys
      Integer ceiling = setLIS.ceiling(i);
      Integer lower = setLIS.lower(i);

      if (ceiling != null) {
        setLIS.remove(ceiling);
      } else if (setLIS.size() + 1 > optLen) {
        optLen = setLIS.size() + 1;
        optLastPos = i;
      }

      //@@ if ceiling value == ith value in nums, we need to remove the ceiling first,
      // otherwise, set.add(...) will fail
      setLIS.add(i);

      prevPos[i] = lower == null ? -1 : lower;
    }

    int[] selected = new int[optLen];
    int index = optLen - 1;
    while (optLastPos != -1) {
      selected[index] = nums[optLastPos];

      optLastPos = prevPos[optLastPos];
      --index;
    }
    Preconditions.checkState(index == -1);

    return selected;
  }

  public static int[] findLongestIncreasingSubsequenceBruteForce(int[] nums, boolean strictlyIncreasing) {
    Objects.requireNonNull(nums);

    int optLen = -1;
    int[] optLIS = new int[0];
    if (nums.length == 0) {
      return optLIS;
    }

    boolean[] select = new boolean[nums.length];
    while (true) {
      int i = 0;
      for (; i < select.length && select[i]; ++i) {
        select[i] = false;
      }
      if (i == select.length) {
        break;
      }
      select[i] = true;

      int curLen = 1;
      int prevValue = nums[i];
      for (++i; i < select.length; ++i) {
        if (select[i]) {
          if (nums[i] < prevValue || (strictlyIncreasing && nums[i] == prevValue)) {
            break;
          }
          prevValue = nums[i];
          ++curLen;
        }
      }

      if (i < select.length) {
        continue;
      }
      if (curLen >= optLen) {
        optLen = curLen;
        optLIS = new int[optLen];
        int outIndex = 0;
        for (i = 0; i < select.length; ++i) {
          if (select[i]) {
            optLIS[outIndex] = nums[i];
            ++outIndex;
          }
        }
        Preconditions.checkState(outIndex == optLen);
      }
    }

    return optLIS;
  }

  public static int[] findLongestNondcreasingSubsequence(int[] nums) {
    TreeMultiset<Integer> setLIS = TreeMultiset.create((i, j) -> nums[i] - nums[j]);
    int[] prevPos = new int[nums.length];

    int optLen = 0;
    int optLastPos = -1;
    for (int i = 0; i < nums.length; ++i) {
      //@@ tricky: get the upper bound in a mutliset
      Integer higher = setLIS.tailMultiset(i, BoundType.OPEN).isEmpty() ? null
          : setLIS.tailMultiset(i, BoundType.OPEN).firstEntry().getElement();
      //@@ tricky: get the (upper bound - 1)in a mutliset
      Integer floor = setLIS.headMultiset(i, BoundType.CLOSED).isEmpty() ? null
          : setLIS.headMultiset(i, BoundType.CLOSED).lastEntry().getElement();
      //@@ check if floor is pointing to the same num. notice the use of nums[floor] != nums[puzzleCurPos]
      if (floor == null || nums[floor] != nums[i]) {
        setLIS.add(i);
      } else {
        //@@ tricky: need to remove the original key
        int count = setLIS.count(floor);
        setLIS.remove(floor, count);
        setLIS.add(i, count + 1);
      }

      if (higher != null) {
        setLIS.remove(higher);
      } else if (setLIS.size() > optLen) {
        optLen = setLIS.size();
        optLastPos = i;
      }

      prevPos[i] = floor == null ? -1 : floor;
    }

    int[] selected = new int[optLen];
    int index = optLen - 1;
    while (optLastPos != -1) {
      selected[index] = nums[optLastPos];

      optLastPos = prevPos[optLastPos];
      --index;
    }
    Preconditions.checkState(index == -1);

    return selected;
  }

}
