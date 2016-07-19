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
    String pString = refToValueFunc.apply(p, 'p');
    String nString = refToValueFunc.apply(n, 'n');

    return "[" + String.format(ListPrint.VALUE_FORMAT, v) + pString + nString + "]";
  }

  public static int size(DList<?> dlist) {
    int size = 0;
    DList<?> cur = dlist;
    while (cur != null) {
      cur = cur.n;
      ++size;
      if (cur == dlist) {
        break;
      }
    }

    return size;
  }
}
