package tomy.random;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Preconditions;
import tomy.interval.Interval;

import static tomy.random.RandomGen.RANDOM;


public class RandomInterval {
  public static List<Interval> genRandNonOverlappedInterval(int size, int minGap, int maxGap, int minInterval, int maxInterval) {
    Preconditions.checkArgument(size >= 0 && minGap <= maxGap);

    List<Interval> result = new ArrayList<>(size);
    int end = 0;
    for (int i = 0; i < size; ++i) {
      int begin = RANDOM.nextInt(maxGap - minGap + 1) + minGap + end;
      end = RANDOM.nextInt(maxInterval - minInterval + 1) + minInterval + begin;
      result.add(new Interval(begin, end));
    }
    return result;
  }
}
