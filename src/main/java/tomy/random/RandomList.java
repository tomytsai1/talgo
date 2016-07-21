package tomy.random;

import java.util.HashMap;
import java.util.Map;

import tomy.list.DList;
import tomy.list.SList;


public class RandomList {
  public static SList<Integer> genRandSList(int size) {
    if (size == 0) {
      return null;
    }

    SList<Integer> head = null;
    SList<Integer> prev = null;
    Map<Integer, Integer> exchangedValues = new HashMap<>();
    for (int i = 0; i < size; ++i) {
      int newIndex = i + RandomGen.getRandom().nextInt(size - i);
      SList<Integer> cur = new SList<>(exchangedValues.getOrDefault(newIndex, newIndex));

      if (head == null) {
        head = cur;
      }

      if (prev != null) {
        prev.n = cur;
      }
      prev = cur;

      exchangedValues.put(newIndex, exchangedValues.getOrDefault(i, i));
    }

    return head;
  }

  public static DList<Integer> genRandDList(int size) {
    if (size == 0) {
      return null;
    }

    DList<Integer> head = null;
    DList<Integer> prev = null;
    Map<Integer, Integer> exchangedValues = new HashMap<>();
    for (int i = 0; i < size; ++i) {
      int newIndex = i + RandomGen.getRandom().nextInt(size - i);
      DList<Integer> cur = new DList<>(exchangedValues.getOrDefault(newIndex, newIndex));

      if (head == null) {
        head = cur;
      }

      if (prev != null) {
        prev.n = cur;
        cur.p = prev;
      }
      prev = cur;

      exchangedValues.put(newIndex, exchangedValues.getOrDefault(i, i));
    }

    return head;
  }

  public static DList<Integer> genRandCList(int size) {
    if (size == 0) {
      return null;
    }
    DList<Integer> head = genRandDList(size);
    DList<Integer> cur = head;
    while (cur.n != null) {
      cur = cur.n;
    }
    cur.n = head;
    head.p = cur;

    return head;
  }
}
