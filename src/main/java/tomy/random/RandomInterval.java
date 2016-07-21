package tomy.random;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Preconditions;
import tomy.interval.Interval;
import tomy.interval.IntervalWithHeight;


public class RandomInterval {
  public static List<Interval> genRandNonOverlappedInterval(int size, int minGap, int maxGap, int minInterval, int maxInterval) {
    Preconditions.checkArgument(size >= 0 && minGap <= maxGap && minInterval <= maxInterval);

    List<Interval> result = new ArrayList<>(size);
    int end = 0;
    for (int i = 0; i < size; ++i) {
      int begin = RandomGen.getRandom().nextInt(maxGap - minGap + 1) + minGap + end;
      end = RandomGen.getRandom().nextInt(maxInterval - minInterval + 1) + minInterval + begin;
      result.add(new Interval(begin, end));
    }
    return result;
  }

  public static List<IntervalWithHeight> genRandNonOverlappedIntervalWithHeight(int size, int minGap, int maxGap,
      int minInterval, int maxInterval, int minHeight, int maxHeight) {
    Preconditions.checkArgument(size >= 0 && minGap <= maxGap && minInterval <= maxInterval && minHeight <= maxHeight);

    List<IntervalWithHeight> result = new ArrayList<>(size);
    int end = 0;
    for (int i = 0; i < size; ++i) {
      int begin = RandomGen.getRandom().nextInt(maxGap - minGap + 1) + minGap + end;
      end = RandomGen.getRandom().nextInt(maxInterval - minInterval + 1) + minInterval + begin;
      int height = RandomGen.getRandom().nextInt(maxHeight - minHeight + 1) + minHeight;
      result.add(new IntervalWithHeight(begin, end, height));
    }
    return result;
  }

}
