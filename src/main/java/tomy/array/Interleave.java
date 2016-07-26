package tomy.array;

import java.util.Arrays;
import java.util.Objects;


public class Interleave {
  public static void interleavePosNeg(int[] nums) {
    Objects.requireNonNull(nums);
    if (nums.length == 0) {
      return;
    }

    long posCount = Arrays.stream(nums).filter(x -> x >= 0).count();
    long negCount = Arrays.stream(nums).filter(x -> x < 0).count();

    //@@ note nums[0] requires num.length >= 1, so we need to handle special case (len == 0) before
    boolean posFirst = posCount > negCount || (posCount == negCount && nums[0] >= 0);
    int interleaveLen = 2 * (int) Math.min(posCount, negCount);

    int i = 0;
    while (i < nums.length) {
      int numSlotFound = 0;
      int posSlot = -1;
      int negSlot = -1;

      while (i < interleaveLen && numSlotFound != 2) {
        if (((i & 1) == 0) == posFirst) {
          if (nums[i] < 0 && posSlot < 0) {
            posSlot = i;
            ++numSlotFound;
          }
        } else {
          if (nums[i] >= 0 && negSlot < 0) {
            negSlot = i;
            ++numSlotFound;
          }
        }
        ++i;
      }

      while (i < nums.length && numSlotFound != 2) {
        if (posFirst == (nums[i] < 0)) {
          if (nums[i] < 0) {
            posSlot = i;
            ++numSlotFound;
          } else {
            negSlot = i;
            ++numSlotFound;
          }
        }
        ++i;
      }

      if (numSlotFound < 2) {
        break;
      } else {
        int temp = nums[posSlot];
        nums[posSlot] = nums[negSlot];
        nums[negSlot] = temp;

        i = Math.min(posSlot, negSlot) + 1;
      }
    }

    /*
        assume posFirst == true
        while puzzleCurPos < len
          numSlotFound = 0
          posSlot = -1
          negSlot = -1
          while puzzleCurPos < len
            if ((puzzleCurPos & 1) == 0) == posFirst
              if n[puzzleCurPos] < 0
                if posSlot < 0
                  posSlot = puzzleCurPos
                  if ++numSlotFound == 2
                    break
            else
              if n[puzzleCurPos] >= 0
                if negSlot < 0
                  negSlot = puzzleCurPos
                  if ++numSlotFound == 2
                    break
            ++puzzleCurPos

          if numSlotFound < 2
            break
          else
            swap(n[posSlot], n[negSlot])
            puzzleCurPos = min(posSlot, negSlot) + 1
     */
  }
}
