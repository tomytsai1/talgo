package tomy.trie;

import java.util.ArrayList;
import java.util.List;

import tomy.array.ArrayReverse;


public class TrieOp {
  public static boolean insert(Trie trie, String s) {
    Trie cur = trie;
    for (int i = 0; i < s.length(); ++i) {
      char c = s.charAt(i);
      if (cur.next.containsKey(c)) {
        cur = cur.next.get(c);
      } else {
        Trie next = new Trie(c);
        cur.next.put(c, next);
        cur = next;
      }
    }

    if (cur.eos) {
      return false;
    }

    cur.eos = true;
    return true;
  }

  public static boolean search(Trie trie, String s) {
    Trie cur = trie;
    for (int i = 0; i < s.length(); ++i) {
      char c = s.charAt(i);
      if (cur.next.containsKey(c)) {
        cur = cur.next.get(c);
      } else {
        return false;
      }
    }

    return cur.eos;
  }

  public static boolean remove(Trie trie, String s) {
    Trie cur = trie;

    List<Trie> path = new ArrayList<>();
    for (int i = 0; i < s.length(); ++i) {
      char c = s.charAt(i);
      if (cur.next.containsKey(c)) {
        path.add(cur);
        cur = cur.next.get(c);
      } else {
        return false;
      }
    }

    if (!cur.eos) {
      return false;
    }
    cur.eos = false;

    if (cur.next.size() > 0) {
      return true;
    }

    char prevChar = cur.c;
    ArrayReverse.reverse(path);
    for (Trie trieToRemove: path) {
      trieToRemove.next.remove(prevChar);
      if (trieToRemove.next.size() > 0) {
        return true;
      }

      prevChar = trieToRemove.c;
    }
    return true;
  }

}
