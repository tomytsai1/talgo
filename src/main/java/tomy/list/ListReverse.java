package tomy.list;

import com.google.common.base.Preconditions;


public class ListReverse {
  public static <T> SList<T> reverseSList(SList<T> cur) {
    // prev = null
    // while cur != null
      // next = cur.n
      // cur.n = prev
      // prev = cur
      // cur = next

    //!!! remember to check if we set the very first node.n to null (which will become the new tail
    SList<T> prev = null;
    while (cur != null) {
      SList<T> next = cur.n;
      cur.n = prev;
      prev = cur;
      cur = next;
    }

    return prev;
  }

  public static <T> SList<T> reverseSListEveryK(SList<T> cur, int k) {
    Preconditions.checkArgument(k > 0);
    // example:
    // 1 2 3 4 , k = 2
    // 2 1 4 3

    SList<T> head = null;
    SList<T> prevTail = null;
    while (cur != null) {
      int revK = k;
      SList<T> curTail = cur;
      SList<T> prev = null;
      //!!! alt: we can combine the inner loop and outer loop into one
      // then we use if (revK == k) { do init setup }
      // and if (revK == 0) { do group-of-k chaining }
      do {
        SList<T> next = cur.n;
        cur.n = prev;
        prev = cur;
        cur = next;
      } while (cur != null && --revK > 0);

      if (head == null) {
        head = prev;
      } else {
        prevTail.n = prev;
      }

      prevTail = curTail;
    }

    return head;
  }

  public static <T> SList<T> reverseSListEveryAltK(SList<T> cur, int k) {
    Preconditions.checkArgument(k > 0);
    // example:
    // 1 2 3 4 5 6 7 8, k = 2
    // 2 1 3 4 6 5 7 8

    SList<T> head = null;
    SList<T> prevTail = null;
    while (cur != null) {
      int revK = k;

      SList<T> curTail = cur;
      SList<T> prev = null;
      do {
        SList<T> next = cur.n;
        cur.n = prev;
        prev = cur;
        cur = next;
      } while (cur != null && --revK > 0);

      if (head == null) {
        head = prev;
      } else {
        prevTail.n = prev;
      }

      prevTail = curTail;
      prevTail.n = cur;

      //!!! note that "while (--revK > 0)" will run the loop for (init k - 1) times
      // In comparison, if we use "do {} while (--revK > 0)", if will run the loop for k times, but need to be careful
      // about init condition (cur != null)
      revK = k;
      while (cur != null && --revK > 0) {
        cur = cur.n;
      }

      if (cur == null) {
        break;
      }

      prevTail = cur;
      cur = cur.n;
    }

    return head;
  }

  public static <T> DList<T> reverseDList(DList<T> cur) {
    // prev = null
    // while cur != null
    // next = cur.n
    // cur.n = prev
    // prev = cur
    // cur = next

    DList<T> prev = null;
    while (cur != null) {
      DList<T> next = cur.n;
      cur.n = cur.p;
      cur.p = next;

      prev = cur;
      cur = next;
    }

    return prev;
  }

  public static <T> DList<T> reverseCList(DList<T> cur) {
    // prev = null
    // while cur != null
    // next = cur.n
    // cur.n = prev
    // prev = cur
    // cur = next
    if (cur == null) {
      return null;
    }


    DList<T> head = cur;
    do {
      DList<T> next = cur.n;
      cur.n = cur.p;
      cur.p = next;

      cur = next;
    } while (cur != head);

    return head;
  }

}
