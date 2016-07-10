package tomy.tree;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import com.google.common.base.Strings;
import org.testng.log4testng.Logger;
import tomy.array.ArrayReverse;

import static com.google.common.primitives.Ints.max;
import static com.google.common.primitives.Ints.min;


public class TreePrint {
  private static final Logger LOGGER = Logger.getLogger(TreePrint.class);
  public static final int VALUE_SIZE = 2;
  public static final String VALUE_FORMAT = "%" + VALUE_SIZE + "d";

  public static void printPre(Tree tree) {
    LOGGER.debug(toPreString(tree));
  }

  public static void printPre(TreeN tree) {
    LOGGER.debug(toPreString(tree));
  }

  public static String toPreString(Tree tree) {
    String result = "";
    if (tree == null) {
      return "";
    }

    Stack<Tree> stack = new Stack<>();
    stack.push(tree);

    while (!stack.empty()) {
      Tree cur = stack.pop();

      result += "\n" + cur.toString();

      if (cur.r != null) {
        stack.push(cur.r);
      }

      if (cur.l != null) {
        stack.push(cur.l);
      }
    }

    return result;
  }

  public static String toPreString(TreeN tree) {
    if (tree == null) {
      return "";
    }

    String result = tree.toString();

    if (tree.l != null) {
      result += toPreString(tree.l);
    }

    if (tree.r != null) {
      result += toPreString(tree.r);
    }

    return result;
  }

  public static void printBfs(Tree tree) {
    LOGGER.debug(toBfsString(tree));
  }

  public static void printBfs(TreeN tree) {
    LOGGER.debug(toBfsString(tree));
  }

  public static String toBfsString(Tree tree) {
    String result = "";
    if (tree == null) {
      return "";
    }

    Queue<Tree> queue = new ArrayDeque<>();
    queue.add(tree);
    int level = 0;
    int numNodesInLevel = 1;
    int numNodesInNextLevel = 0;

    while (!queue.isEmpty()) {
      Tree cur = queue.remove();

      result += String.format("\n[%2d] ", level) + cur.toString();

      if (cur.l != null) {
        ++numNodesInNextLevel;
        queue.add(cur.l);
      }

      if (cur.r != null) {
        ++numNodesInNextLevel;
        queue.add(cur.r);
      }

      if (--numNodesInLevel == 0) {
        ++level;
        numNodesInLevel = numNodesInNextLevel;
        numNodesInNextLevel = 0;
      }
    }

    return result;
  }

  public static String toBfsString(TreeN tree) {
    String result = "";
    if (tree == null) {
      return "";
    }

    Queue<TreeN> queue = new ArrayDeque<>();
    queue.add(tree);
    int level = 0;
    int numNodesInLevel = 1;
    int numNodesInNextLevel = 0;

    while (!queue.isEmpty()) {
      TreeN cur = queue.remove();

      result += String.format("\n[%2d] ", level) + cur.toString();

      if (cur.l != null) {
        ++numNodesInNextLevel;
        queue.add(cur.l);
      }

      if (cur.r != null) {
        ++numNodesInNextLevel;
        queue.add(cur.r);
      }

      if (--numNodesInLevel == 0) {
        ++level;
        numNodesInLevel = numNodesInNextLevel;
        numNodesInNextLevel = 0;
      }
    }

    return result;
  }

  public static void print(Tree tree) {
    LOGGER.debug(toGraphString(tree));
  }

  public static String toGraphString(Tree tree) {
    TreeN treeN = TreeN.convertTreeToTreeN(tree);

    return toGraphString(treeN);
  }

  public static void print(TreeN treeN) {
    LOGGER.debug(toGraphString(treeN));
  }

  public static String toGraphString(TreeN treeN) {
    List<TreeN> sortedTreeNodes = topologicalSort(treeN);

    assignPosFromTopo(sortedTreeNodes);

    fineTunePos(treeN);

    return toGraphStringFromPos(treeN);
  }

  private static String toGraphStringFromPos(TreeN treeN) {
    if (treeN == null) {
      return "";
    }

    int numInLevel = 1;
    int numInNextLevel = 0;
    Queue<TreeN> queue = new ArrayDeque<>();
    queue.add(treeN);

    StringBuilder connectorStr = new StringBuilder();
    StringBuilder levelStr = new StringBuilder();
    StringBuilder result = new StringBuilder();

    while (!queue.isEmpty()) {
      TreeN cur = queue.remove();
      levelStr.append(Strings.repeat(" ", cur.pos - levelStr.length()));
      levelStr.append(cur.v);

      if (cur.l != null) {
        int flatStartPos =  cur.l.pos + 1;
        int connectPos = cur.pos - 1;
        int flatEndPos =  connectPos;
        connectorStr.append(Strings.repeat(" ", flatStartPos - connectorStr.length()));
        connectorStr.append(Strings.repeat("_", flatEndPos - flatStartPos));
        connectorStr.append('/');

        queue.add(cur.l);
        ++numInNextLevel;
      }

      if (cur.r != null) {
        int connectPos = cur.pos + 1;
        int flatStartPos =  connectPos + 1;
        int flatEndPos =  cur.r.pos;
        connectorStr.append(Strings.repeat(" ", connectPos - connectorStr.length()));
        connectorStr.append('\\');
        connectorStr.append(Strings.repeat("_", flatEndPos - flatStartPos));

        queue.add(cur.r);
        ++numInNextLevel;
      }

      if (--numInLevel == 0) {
        numInLevel = numInNextLevel;
        numInNextLevel = 0;

        result.append("\n");
        result.append(levelStr);
        result.append("\n");
        result.append(connectorStr);

        levelStr.setLength(0);
        connectorStr.setLength(0);
      }
    }

    return result.toString();
  }

  private static void fineTunePos(TreeN treeN) {
    if (treeN == null) {
      return;
    }

    fineTunePos(treeN.r);
    fineTunePos(treeN.l);

    int lb = treeN.pos;
    int rb = Integer.MAX_VALUE;
    List<Integer> listPos = new ArrayList<>();

    if (treeN.l != null) {
      if (treeN.r != null) {
        rb = min(rb, treeN.r.pos - 2);
        listPos.add((treeN.l.pos + treeN.r.pos)/ 2 );
      } else {
        listPos.add(treeN.l.pos + 2);
      }
    } else if (treeN.r != null) {
      rb = min(rb, treeN.r.pos - 2);
      listPos.add(treeN.r.pos - 2);
    }

    if (treeN.n != null) {
      rb = min(rb, treeN.n.pos - String.valueOf(treeN.v).length() - 1);
    }

    if(treeN.p != null) {
      if (treeN == treeN.p.l) {
        rb = min(rb, treeN.p.pos - 2);
        listPos.add(treeN.p.pos - 2);
      } else {
        listPos.add(treeN.p.pos + 2);
      }
    }

    int sum = 0;
    int num = 0;
    for (int pos: listPos) {
      if (pos >= lb && pos <= rb) {
        sum += pos;
        ++num;
      }
    }

    if (num > 0) {
      treeN.pos = sum / num;
    }
  }

  private static void assignPosFromTopo(List<TreeN> sortedTreeNodes) {
    for (TreeN cur: sortedTreeNodes) {
      if (cur.n != null) {
        int nextOffset = cur.pos + String.valueOf(cur.v).length() + 1;
        cur.n.pos = max(cur.n.pos, nextOffset);
      }

      int sideNextOffset = cur.pos + 2;
      if (cur.p != null && cur.p.l == cur) {
        cur.p.pos = max(cur.p.pos, sideNextOffset);
      }

      if (cur.r != null) {
        cur.r.pos = max(cur.r.pos, sideNextOffset);
      }
    }
  }

  private static List<TreeN> topologicalSort(TreeN treeN) {
    List<TreeN> list = new ArrayList<>();

    topologicalSortDfs(treeN, list);
    ArrayReverse.reverse(list);
    return list;
  }

  private static void topologicalSortDfs(TreeN treeN, List<TreeN> list) {
    if (treeN == null || treeN.visited) {
      return;
    }

    treeN.visited = true;
    topologicalSortDfs(treeN.n, list);
    if (treeN.p != null && treeN.p.l == treeN) {
      topologicalSortDfs(treeN.p, list);
    }
    topologicalSortDfs(treeN.r, list);
    list.add(treeN);

    topologicalSortDfs(treeN.l, list);
  }
}
