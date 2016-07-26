package tomy.interval;

import java.util.List;


public class IntervalSum {
  public static class IntervalSumInfo {
    int maxTotalHeight;
    List<IntervalWithHeight> selectedIntervals;

    public IntervalSumInfo(int maxTotalHeight, List<IntervalWithHeight> selectedIntervals) {
      this.maxTotalHeight = maxTotalHeight;
      this.selectedIntervals = selectedIntervals;
    }
  }

  public static IntervalSumInfo computeMaxSumOfHeightsFromNonOvelappedIntervals(List<? extends IntervalWithHeight> intervals) {
    return null;
    /*
      NOTE: complex algorithm, implemented later
      NOTE: inspired by greedy algorithm: max number of non-overlapped intervals (sort by end time and do greedy select)

      create interval traces
        - interval
        - prev pos
        - optimal pos
        - optimal height
      sort traces based on end time

      for cur = 0 .. traces.len - 1
        curInterval = traces[cur].interval
        curOptPrev = -1
        prevOptHeight = cur == 0 ? 0 : traces[cur - 1].optHeight

        use curInterval.begin to find lower bound -> index is low
        if low == -1
        newHeight = curInterval.height + trace[trace[low].optPos].optHeight
        if newHeight > prevOptHeight
          curTrace.optPos = cur
          curTrace.optHeight = newHeight
          curTrace.prevPos = trace[low].optPos
        else
          curTrace.optPos = trace[cur - 1].optPos
          curTrace.optHeight = curOptHeight
          curTrace.prevPos = low
     */
  }
}
