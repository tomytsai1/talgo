package tomy.trie;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;

import org.testng.log4testng.Logger;
import tomy.list.DList;
import tomy.tree.Tree;
import tomy.tree.TreeN;


public class TriePrint {
  private static final Logger LOGGER = Logger.getLogger(TriePrint.class);
  public static void printBfs(Trie trie) {
    LOGGER.debug(toBfsString(trie));
  }

  private static String toBfsString(Trie trie) {
    String result = "";
    if (trie == null) {
      return "";
    }

    Queue<Trie> queue = new ArrayDeque<>();
    queue.add(trie);
    int level = 0;
    int numNodesInLevel = 1;
    int numNodesInNextLevel = 0;

    while (!queue.isEmpty()) {
      Trie cur = queue.remove();

      result += String.format("\n[%2d] ", level) + cur.toString();

      for (Map.Entry<Character, Trie> nextEntry : cur.next.entrySet()) {
        queue.add(nextEntry.getValue());
        ++numNodesInNextLevel;
      }

      if (--numNodesInLevel == 0) {
        ++level;
        numNodesInLevel = numNodesInNextLevel;
        numNodesInNextLevel = 0;
      }
    }

    return result;
  }
}
