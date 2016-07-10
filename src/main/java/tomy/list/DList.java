package tomy.list;

import java.util.function.BiFunction;


public class DList<T> {
  public T v;
  public DList<T> n;
  public DList<T> p;

  public DList(T v) {
    this.v = v;
  }

  @Override
  public String toString() {
    BiFunction<DList<T>, Character, String>
        refToValueFunc = (ref, prefix) -> ref == null ? "" : " " + prefix + String.format(ListPrint.VALUE_FORMAT, ref.v);
    String nString = refToValueFunc.apply(n, 'n');
    String pString = refToValueFunc.apply(p, 'p');

    return "[v" + String.format(ListPrint.VALUE_FORMAT, v) + nString + pString + "]";
  }
}
