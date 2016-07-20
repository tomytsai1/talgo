package tomy.interval;

public class Interval {
  public int begin;
  public int end;

  public Interval(int begin, int end) {
    this.begin = begin;
    this.end = end;
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof  Interval)) {
      return  false;
    }
    Interval otherInterval = (Interval) other;

    return begin == otherInterval.begin && end == otherInterval.end;
  }

  @Override
  public String toString() {
    return "[" + begin + ", " + end + "]";
  }
}
