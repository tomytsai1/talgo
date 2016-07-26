package tomy.dp;

import java.util.Arrays;


public class BoxStack {
  //@@ this class is static, because it is used to be returned from a static function
  public static class BoxStackInfo {
    public int totalHeight;
    public Box[] selectedBoxes;

    public BoxStackInfo(int totalHeight, Box[] selectedBoxes) {
      this.totalHeight = totalHeight;
      this.selectedBoxes = selectedBoxes;
    }
  }

  public static BoxStackInfo computeMaxBoxStackHeight(Box[] boxes) {
    class Trace {
      public int totalHeight;
      public int prevIndex;

      public Trace(int totalHeight, int prevIndex) {
        this.totalHeight = totalHeight;
        this.prevIndex = prevIndex;
      }
    }

    Arrays.sort(boxes, (box1, box2) ->
        box1.width != box2.width ? box1.width - box2.width : box1.length - box2.length);
    Trace[] traces = new Trace[boxes.length];

    int optLast = -1;
    int optHeight = -1;
    for (int cur = 0; cur < boxes.length; ++cur) {
      int curOptPrev = -1;
      int curOptHeight = -1;
      for (int prev = 0; prev < cur; ++prev) {
        if (boxes[prev].length <= boxes[cur].length) {
          if (traces[prev].totalHeight + boxes[cur].height > curOptHeight) {
            curOptPrev = prev;
            curOptHeight = traces[prev].totalHeight + boxes[cur].height;
          }
        }
      }

      traces[cur] = new Trace(curOptHeight == -1 ? boxes[cur].height : curOptHeight, curOptPrev);
      if (curOptHeight > optHeight) {
        optHeight = curOptHeight;
        optLast = cur;
      }
    }

    int boxStackSize = 0;
    int target = optLast;
    while (target != -1) {
      ++boxStackSize;
      target = traces[target].prevIndex;
    }

    Box[] selectedBoxes = new Box[boxStackSize];
    for (target = optLast, --boxStackSize; target != -1; --boxStackSize) {
      selectedBoxes[boxStackSize] = boxes[target];
      target = traces[target].prevIndex;
    }

    return new BoxStackInfo(optHeight, selectedBoxes);
  }
}
