package tomy.list;

public class SList<T> {
  public T v;
  public SList<T> n;

  public SList(T v) {
    this.v = v;
  }

  @Override
  public String toString() {
    String nString = n == null ? "" : " n" + String.format(ListPrint.VALUE_FORMAT, n.v);

    return "[" + String.format(ListPrint.VALUE_FORMAT, v) + nString + "]";
  }

  public static int size(SList<?> slist) {
    int size = 0;
    while (slist != null) {
      slist = slist.n;
      ++size;
    }

    return size;
  }
 }
