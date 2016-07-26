package tomy.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;

import com.google.common.collect.TreeMultiset;


public class IntervalMerge {
  public static List<Interval> mergeIntervals(List<? extends Interval> list1, List<? extends Interval> list2) {
    Objects.requireNonNull(list1);
    Objects.requireNonNull(list2);

    if (list1.isEmpty() && list2.isEmpty()) {
      return new ArrayList<>();
    }

    Comparator<Interval> comparator = (Interval o1, Interval o2) -> {
        if (o1.begin < o2.begin) {
          return -1;
        } else if (o1.begin > o2.begin) {
          return 1;
        }
        if (o1.end < o2.end) {
          return -1;
        } else if (o1.end > o2.end) {
          return 1;
        }
        return 0;
    };

    Collections.sort(list1, comparator);
    Collections.sort(list2, comparator);

    int i1 = 0;
    int i2 = 0;
    List<Interval> merged = new ArrayList<>(Math.min(list1.size(), list2.size()));

    while (i1 < list1.size() && i2 < list2.size()) {
      Interval cur = null;
      //@@ double check if we need < or >
      if (comparator.compare(list1.get(i1), list2.get(i2)) <= 0) {
        cur = list1.get(i1);
        ++i1;
      } else {
        cur = list2.get(i2);
        ++i2;
      }

      mergeToList(merged, cur);
    }

    while (i1 < list1.size()) {
      mergeToList(merged, list1.get(i1));
      ++i1;
    }

    while (i2 < list2.size()) {
      mergeToList(merged, list2.get(i2));
      ++i2;
    }

    return merged;

    /*
      sort list1, list2

      i1 = 0, i2 = 0
      result = new list
      result.push({int_max, int_min})
      while i1 < list1.totalHeight && i2 < list2.totalHeight
        selected = null
        if list1[i1] < list2[i2]
          selected = i1
          ++i1
        else
          selected = i2
          ++i2

        if selected.begin - 1 > result.end
          result.push(selected)
        else
          merge result.last with selected

      while i1 < list1.totalHeight
        selected = i1
        ++i1
        if selected.begin - 1 > result.end
          result.push(selected)
        else
          merge result.last with selected

      same for list2


    */
  }

  private static void mergeToList(List<Interval> merged, Interval cur) {
    if (merged.isEmpty()) {
      merged.add(cur);
      return;
    }

    Interval last = merged.get(merged.size() - 1);
    if (cur.begin - 1 > last.end) {
      merged.add(new Interval(cur.begin, cur.end));
    } else {
      last.begin = Math.min(cur.begin, last.begin);
      last.end = Math.max(cur.end, last.end);
    }
  }

  public static List<Interval> mergeIntervalsWithPQ(List<? extends Interval>... intervalLists) {
    Objects.requireNonNull(intervalLists);

    class EndPoint implements Comparable<EndPoint> {
      int pos;
      boolean isBegin;

      public EndPoint(int pos, boolean isBegin) {
        this.pos = pos;
        this.isBegin = isBegin;
      }

      //@@ default PriorityQueue is min heap and expects natural order comparator
      @Override
      public int compareTo(EndPoint other) {
        if (pos < other.pos) {
          return -1;
        } else if (pos > other.pos) {
          return 1;
        }

        if (!isBegin && other.isBegin) {
          return -1;
        } else if (isBegin && !other.isBegin) {
          return 1;
        }
        return 0;
      }
    }

    PriorityQueue<EndPoint> endpointQue = new PriorityQueue<>();
    for (List<? extends Interval> intervalList : intervalLists) {
      for (Interval interval : intervalList) {
        endpointQue.add(new EndPoint(interval.begin, true));
        endpointQue.add(new EndPoint(interval.end, false));
      }
    }

    List<Interval> merged = new ArrayList<>();
    int numOverlapped = 0;
    int curBegin = 0;
    while (!endpointQue.isEmpty()) {
      //@@ PriorityQueue cannot use iterator to get correct order. Need to use poll()
      EndPoint endPoint = endpointQue.poll();
      if (endPoint.isBegin) {
        if (numOverlapped == 0) {
          curBegin = endPoint.pos;

          if (!merged.isEmpty()) {
            Interval last = merged.get(merged.size() - 1);
            if (last.end + 1 >= curBegin) {
              curBegin = last.begin;
              merged.remove(merged.size() - 1);
            }
          }
        }
        ++numOverlapped;
      } else {
        --numOverlapped;
        if (numOverlapped == 0) {
          merged.add(new Interval(curBegin, endPoint.pos));
        }
      }
    }

    return merged;
    /*
        for each interval in list1 and list2
          add begin/end points to endpointList

        new merged list
        numOverlapped = 0
        for each end point in endpointList
          if point is begin
            if numOverlapped == 0
              curBegin = pos
            ++numOverlapped
          else
            --numOverlapped
            if numOverlapped == 0
              merged.add({curBegin, pos})
     */
  }

  public static List<Interval> mergeIntervalsWithPQ2(List<? extends Interval>... intervalLists) {
    Objects.requireNonNull(intervalLists);

    class IntervalMark {
      public int listPos;
      public int pos;

      public IntervalMark(int listPos, int pos) {
        this.listPos = listPos;
        this.pos = pos;
      }
    }

    PriorityQueue<IntervalMark> intervalQue = new PriorityQueue<>((mark1, mark2) -> {
          Interval interval1 = intervalLists[mark1.listPos].get(mark1.pos);
          Interval interval2 = intervalLists[mark2.listPos].get(mark2.pos);

          if (interval1.begin < interval2.begin) {
            return -1;
          } else if (interval1.begin > interval2.begin) {
            return 1;
          }

          if (interval1.end < interval2.end) {
            return -1;
          } else if (interval1.end > interval2.end) {
            return 1;
          }
          return 0;
        });

    int listPos = 0;
    for (List<? extends Interval> intervalList : intervalLists) {
      if (!intervalList.isEmpty()) {
        intervalQue.add(new IntervalMark(listPos, 0));
      }
      ++listPos;
    }

    List<Interval> merged = new ArrayList<>();
    while (!intervalQue.isEmpty()) {
      IntervalMark intervalMark = intervalQue.poll();
      Interval interval = intervalLists[intervalMark.listPos].get(intervalMark.pos);
      mergeToList(merged, interval);

      if (intervalMark.pos < intervalLists[intervalMark.listPos].size() - 1) {
        intervalQue.add(new IntervalMark(intervalMark.listPos, intervalMark.pos + 1));
      }
    }

    return merged;
  }

  public static List<IntervalWithHeight> mergeIntervalsWithHeight(List<? extends IntervalWithHeight>... intervalLists) {
    Objects.requireNonNull(intervalLists);

    class IntervalMark {
      public int listPos;
      public int pos;
      public IntervalWithHeight interval;

      public IntervalMark(int listPos, int pos, IntervalWithHeight interval) {
        this.listPos = listPos;
        this.pos = pos;
        this.interval = interval;
      }
    }

    PriorityQueue<IntervalMark> intervalQue = new PriorityQueue<>((mark1, mark2) -> {
      IntervalWithHeight interval1 = mark1.interval;
      IntervalWithHeight interval2 = mark2.interval;

      if (interval1.begin != interval2.begin) {
        return interval1.begin - interval2.begin;
      }

      //@@ it is critical that we sort on begin, height, (end doesn't matter) order
      // such we don't need to deal with out-of-order heights for the inputs
      if (interval1.height != interval2.height) {
        return interval2.height - interval1.height;
      }

      return interval1.end - interval2.end;
    });

    int listPos = 0;
    for (List<? extends IntervalWithHeight> intervalList : intervalLists) {
      if (!intervalList.isEmpty()) {
        intervalQue.add(new IntervalMark(listPos, 0, intervalLists[listPos].get(0)));
      }
      ++listPos;
    }

    List<IntervalWithHeight> merged = new ArrayList<>();
    while (!intervalQue.isEmpty()) {
      IntervalMark intervalMark = intervalQue.poll();
      IntervalWithHeight next = intervalMark.interval;
      if (merged.isEmpty()) {
        //@@ note: we cannot do merged.add(next), because later we may edit the last, and change the input value
        merged.add(new IntervalWithHeight(next.begin, next.end, next.height));
      } else {
        IntervalWithHeight cur = merged.get(merged.size() - 1);
        if (cur.end < next.begin) {
          if (cur.end + 1 == next.begin && cur.height == next.height) {
            cur.end = next.end;
          } else {
            merged.add(new IntervalWithHeight(next.begin, next.end, next.height));
          }
        } else if (cur.height >= next.height) {
          if (next.end > cur.end) {
            intervalQue.add(new IntervalMark(-1, 0,
                new IntervalWithHeight(cur.end + 1, next.end, next.height)));
          }
        } else { // cur.height < next.height
          //@@ note here we don't worry about next.begin == cur.begin, because we set the comparator s.t. this doesn't happen

          //@@ we will modify cur.end, so need to do condition check before modifying it
          if (cur.end > next.end) {
            intervalQue.add(new IntervalMark(-1, 0, new IntervalWithHeight(next.end + 1, cur.end, cur.height)));
          }

          cur.end = next.begin - 1;
          merged.add(new IntervalWithHeight(next.begin, next.end, next.height));
        }
      }

      if (intervalMark.listPos >= 0
          && intervalMark.pos < intervalLists[intervalMark.listPos].size() - 1) {
        intervalQue.add(new IntervalMark(intervalMark.listPos, intervalMark.pos + 1,
            intervalLists[intervalMark.listPos].get(intervalMark.pos + 1)));
      }
    }

    return merged;
  }

  static class EndPoint implements Comparable<EndPoint> {
    int x;
    int height;
    boolean isEnd;

    EndPoint(int x, int height, boolean isEnd) {
      this.x = x;
      this.height = height;
      this.isEnd = isEnd;
    }

    @Override
    public int compareTo(EndPoint other) {
      if (this.x != other.x) {
        return this.x - other.x;
      }

      //@@ tricky: start point always come before end point, so we can stitch two continuous blocks
      if (this.isEnd != other.isEnd) {
        return Boolean.compare(this.isEnd, other.isEnd);
      }

      //@@ tricky: the height order are designed to keep the max height in queue as long as possible
      return this.isEnd ? this.height - other.height : other.height - this.height;
    }
  }

  public static List<IntervalWithHeight> mergeIntervalsWithHeight2(List<? extends IntervalWithHeight>... intervalLists) {
    Objects.requireNonNull(intervalLists);

    List<EndPoint> allEndPoints = new ArrayList<>();
    Arrays.stream(intervalLists).forEach(intervalList ->
        intervalList.forEach(interval -> {
          allEndPoints.add(new EndPoint(interval.begin, interval.height, false));
          allEndPoints.add(new EndPoint(interval.end, interval.height, true));
        }));
    Collections.sort(allEndPoints);

    PriorityQueue<Integer> heights = new PriorityQueue<>(Collections.reverseOrder());
    List<EndPoint> merged = new ArrayList<>();
    for (EndPoint endPoint : allEndPoints) {
      if (!endPoint.isEnd) {
        if (heights.isEmpty() || endPoint.height > heights.peek()) {
          merged.add(new EndPoint(endPoint.x, endPoint.height, false));
        }
        heights.add(endPoint.height);
      } else {
        heights.remove(endPoint.height);

        if (heights.isEmpty()) {
          merged.add(new EndPoint(endPoint.x + 1, 0, false));
        } else if (heights.peek() < endPoint.height) {
          merged.add(new EndPoint(endPoint.x + 1, heights.peek(), false));
        }
      }
    }

    int prevX = 0;
    int prevH = 0;
    List<IntervalWithHeight> result = new ArrayList<>();
    for (EndPoint endPoint : merged) {
      if (prevH != 0) {
        //@@ we may have a block with 0 height, and such block will have negative len
        // we can avoid this logic by re-defining the range as [x, y) , instead of [x,y], see next function
        if (endPoint.x > prevX) {
          if (result.isEmpty()) {
            result.add(new IntervalWithHeight(prevX, endPoint.x - 1, prevH));
          } else {
            IntervalWithHeight lastInterval = result.get(result.size() - 1);
            //@@ here we have very complex logic to concatenate two continuos blocks
            // we can avoid this logic by re-defining the range as [x, y) , instead of [x,y], see next function
            if (lastInterval.end + 1 < prevX || lastInterval.height != prevH) {
              result.add(new IntervalWithHeight(prevX, endPoint.x - 1, prevH));
            } else {
              lastInterval.end = endPoint.x - 1;
            }
          }
        }
      }
      prevX = endPoint.x;
      prevH = endPoint.height;
    }

    return result;
  }

  public static List<IntervalWithHeight> mergeIntervalsWithHeight3(List<? extends IntervalWithHeight>... intervalLists) {
    Objects.requireNonNull(intervalLists);

    List<EndPoint> allEndPoints = new ArrayList<>();
    Arrays.stream(intervalLists).forEach(intervalList ->
        intervalList.forEach(interval -> {
          allEndPoints.add(new EndPoint(interval.begin, interval.height, false));
          allEndPoints.add(new EndPoint(interval.end + 1, interval.height, true));
        }));
    Collections.sort(allEndPoints);

    //@@ we will need to add and remove the height randomly, and heights may have dups, so we need TreeMultiset
    TreeMultiset<Integer> heights = TreeMultiset.create();
    List<EndPoint> merged = new ArrayList<>();
    for (EndPoint endPoint : allEndPoints) {
      if (!endPoint.isEnd) {
        if (heights.isEmpty() || endPoint.height > heights.lastEntry().getElement()) {
          merged.add(new EndPoint(endPoint.x, endPoint.height, false));
        }
        heights.add(endPoint.height);
      } else {
        heights.remove(endPoint.height);

        if (heights.isEmpty()) {
          merged.add(new EndPoint(endPoint.x, 0, false));
        } else if (heights.lastEntry().getElement() < endPoint.height) {
          merged.add(new EndPoint(endPoint.x, heights.lastEntry().getElement(), false));
        }
      }
    }

    int prevX = 0;
    int prevH = 0;
    List<IntervalWithHeight> result = new ArrayList<>();
    for (EndPoint endPoint : merged) {
      if (prevH != 0) {
        //@@ we don't need to worry if we will have a block with 0 height, because we designed the endpoint comparator
        // carefully above to avoid this case
        if (result.isEmpty()) {
          result.add(new IntervalWithHeight(prevX, endPoint.x - 1, prevH));
        } else {
          //@@ we don't need to worry if we will have two continuous blocks, because we designed the endpoint comparator
          // carefully above to avoid this case
          result.add(new IntervalWithHeight(prevX, endPoint.x - 1, prevH));
        }
      }
      prevX = endPoint.x;
      prevH = endPoint.height;
    }

    return result;
  }

}
