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

    return "[v" + String.format(ListPrint.VALUE_FORMAT, v) + nString + "]";
  }
}
