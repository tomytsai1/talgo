package tomy.trie;

import java.util.HashMap;
import java.util.Map;


public class Trie {
  public char c;
  public Map<Character, Trie> next = new HashMap<>();
  public boolean eos = false;

  public Trie(char c) {
    this.c = c;
  }

  @Override
  public String toString() {
    StringBuilder result = new StringBuilder("[" + c);

    if (eos) {
      result.append('*');
    }

    boolean first = true;
    for (Character c : next.keySet()) {
      if (first) {
        result.append(" (");
        first = false;
      }
      result.append(c);
    }

    if (!next.isEmpty()) {
      result.append(')');
    }

    result.append("]");
    return result.toString();
  }
}
