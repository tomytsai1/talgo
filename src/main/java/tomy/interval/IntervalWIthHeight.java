package tomy.interval;

public class IntervalWithHeight {
  public int begin;
  public int end;
  public int height;

  public IntervalWithHeight(int begin, int end, int height) {
    this.begin = begin;
    this.end = end;
    this.height = height;
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof IntervalWithHeight)) {
      return  false;
    }
    IntervalWithHeight otherInterval = (IntervalWithHeight) other;

    return begin == otherInterval.begin && end == otherInterval.end && height == otherInterval.height;
  }

  @Override
  public String toString() {
    return "[" + begin + ", " + end + " H " + height + "]";
  }
}
