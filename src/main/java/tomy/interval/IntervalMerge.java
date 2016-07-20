package tomy.interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;


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
      while i1 < list1.size && i2 < list2.size
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

      while i1 < list1.size
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
}
