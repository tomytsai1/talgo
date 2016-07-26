package tomy.array;

import java.util.Objects;
import java.util.Stack;


public class MaxArea {
  public static int[] findMaxRectArea(int[] heights) {
    Objects.requireNonNull(heights);

    int[] optResult = new int[] {Integer.MIN_VALUE, -1, -1};

    Stack<Integer> ascendingPos = new Stack<>();
    for (int i = 0; i < heights.length; ++i) {
      while (!ascendingPos.isEmpty() && heights[i] <= heights[ascendingPos.peek()]) {
        int prevHeight = heights[ascendingPos.pop()]; //@@ need to use heights[pos] to get the height
        int prevPos = ascendingPos.isEmpty() ? 0 : ascendingPos.peek() + 1;
        //@@ be careful about the height formula and the position
        int area = (i - prevPos) * prevHeight;
        if (area > optResult[0]) {
          optResult[0] = area;
          optResult[1] = prevPos;
          optResult[2] = i - 1;
        }
      }
      ascendingPos.push(i);
    }

    int lastPos = heights.length;
    while (!ascendingPos.isEmpty()) {
      int prevHeight = heights[ascendingPos.pop()];
      int prevPos = ascendingPos.isEmpty() ? 0 : ascendingPos.peek() + 1;
      int area = (lastPos - prevPos) * prevHeight;
      if (area > optResult[0]) {
        optResult[0] = area;
        optResult[1] = prevPos;
        optResult[2] = lastPos - 1;
      }
    }

    return optResult;

    /*
      example:
         2 3 2 5 4 6 7 3 4

        +2 s: 2
        +3 s: 2 3
        +2 compute 3 * (cur - peek pos (or 0 if empty)) , pop 3 , s 2
           compute 2 * (cur - peek pos (or 0 if empty)) , pop 2 , s
           push 2, s 2
        +5 s: 2 3 5

      init stack
      for pos in h[]
        if stack empty or h[pos] > h[stack top]
          push pos to stack
        else
          while stack not empty and h[stack top] <= h[pos]
            <(*) below>
            pop top
            area = h[top] * cur - 1 - (stack empty ? -1 : stack top)
            update opt solution

        while stack not empty
          (*) body
     */
  }
}
