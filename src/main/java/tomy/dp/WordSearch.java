package tomy.dp;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Queue;

import com.google.common.base.Preconditions;


public class WordSearch {
  /**
   * @return pos for each step; [[x1, y1], [x2, y2], etc]
   */
  public static int[][] wordSearchDfs(char[][] puzzle, String target) {
    Objects.requireNonNull(puzzle);
    if (puzzle.length == 0) {
      return new int[0][2];
    }

    char[] targetCharArray = target.toCharArray();

    LinkedHashSet<Integer> trace = new LinkedHashSet<>();
    outer: for (int i = 0; i < puzzle.length; ++i) {
      for (int j = 0; j < puzzle[0].length; ++j) {
        if (searchFromDfs(puzzle, i, j, targetCharArray, 0, trace)) {
          break outer;
        }
        trace.clear();
      }
    }

    if (trace.isEmpty()) {
      return null;
    }

    int[][] tracePos = new int[targetCharArray.length][2];
    Preconditions.checkState(trace.size() == tracePos.length);

    int pos = 0;
    for (int index : trace) {
      tracePos[pos][0] = index / puzzle[0].length;
      tracePos[pos][1] = index % puzzle[0].length;
      ++pos;
    }
    return tracePos;

    /*
    Special:
      puzzle == null
      puzzle.length == 0
    General:
        for each point in puzzle
          dfs from this point
              - pass trace
     */
  }

  private static boolean searchFromDfs(char[][] puzzle, int i, int j, char[] targetCharArray, int from,
      LinkedHashSet<Integer> trace) {
    int index = i * puzzle.length + j;
    if (i < 0 || i >= puzzle.length || j < 0 || j >= puzzle[0].length
        || trace.contains(i * puzzle.length + j) //@@ use trace as the "visited" flag array
        || puzzle[i][j] != targetCharArray[from]) {
      return false;
    }

    trace.add(index);

    if (from  == targetCharArray.length - 1) {
      return true;
    }

    if (searchFromDfs(puzzle, i + 1, j, targetCharArray, from + 1, trace) ||
        searchFromDfs(puzzle, i - 1, j, targetCharArray, from + 1, trace) ||
        searchFromDfs(puzzle, i, j + 1, targetCharArray, from + 1, trace) ||
        searchFromDfs(puzzle, i, j - 1, targetCharArray, from + 1, trace)) {
      return true;
    }

    //@@ when we do dfs search, need to take care of removing the unused history
    trace.remove(index);
    return false;
  }

  /**
   * @return pos for each step; [[x1, y1], [x2, y2], etc]
   */
  public static int[][] wordSearchBfs(char[][] puzzle, String target) {
    Objects.requireNonNull(puzzle);
    Objects.requireNonNull(target);

    if (puzzle[0].length == 0) {
      return null;
    }

    class SearchPos {
      public int puzzleCurPos;
      public int puzzlePrevPos;

      public SearchPos(int puzzleCurPos, int puzzlePrevPos) {
        this.puzzleCurPos = puzzleCurPos;
        this.puzzlePrevPos = puzzlePrevPos;
      }
    }

    Queue<SearchPos> searchQueue = new ArrayDeque<SearchPos>();

    for (int i = 0; i < puzzle.length; ++i) {
      for (int j = 0; j < puzzle[0].length; ++j) {
        searchQueue.add(new SearchPos(i * puzzle.length + j, -1));
      }
    }

    @SuppressWarnings("unchecked")
    HashMap<Integer, Integer>[][] trace = new HashMap[puzzle.length][puzzle[0].length];
    //@@ after we initialize the array, the values are still null, so we will need to new object for each element
    //@@ Also, it is still not sufficient for this data structure to prevent us from re-visisting the same element


    for (int matchedLen = 0; matchedLen < target.length(); ++matchedLen) {
      int queueSize = searchQueue.size();
      for (int queuePos = 0; queuePos < queueSize; ++queuePos) {
        SearchPos curSearchPos = searchQueue.poll();
        //@@ note that i = search pos / (puzzle width = puzzle[0].len) , not puzzle height (= puzzle.len)
        int i = curSearchPos.puzzleCurPos / puzzle[0].length;
        int j = curSearchPos.puzzleCurPos % puzzle[0].length;
        if (i < 0 || i >= puzzle.length || j < 0 || j > puzzle[0].length
            || puzzle[i][j] != target.charAt(matchedLen)
            || (trace[i][j] != null && trace[i][j].containsKey(matchedLen))) {
          continue;
        }

        if (trace[i][j] == null) {
          trace[i][j] = new HashMap<>();
        }
        trace[i][j].put(matchedLen, curSearchPos.puzzlePrevPos);

        if (matchedLen == target.length() - 1) {
          int[][] searchPath = new int[target.length()][2];
          int nextPos = curSearchPos.puzzleCurPos;
          for (; matchedLen >= 0; --matchedLen) {
            int curX = nextPos / puzzle[0].length;
            int curY = nextPos % puzzle[0].length;
            searchPath[matchedLen][0] = curX;
            searchPath[matchedLen][1] = curY;

            nextPos = trace[curX][curY].get(matchedLen);
          }

          Preconditions.checkState(nextPos == -1);
          return searchPath;
        }

        if (i < puzzle.length - 1) {
          searchQueue.add(new SearchPos((i + 1) * puzzle[0].length + j, curSearchPos.puzzleCurPos));
        }

        if (i > 0 ) {
          searchQueue.add(new SearchPos((i - 1) * puzzle[0].length + j, curSearchPos.puzzleCurPos));
        }

        if (j < puzzle[0].length - 1) {
          searchQueue.add(new SearchPos(i * puzzle[0].length + j + 1, curSearchPos.puzzleCurPos));
        }

        if (j > 1) {
          searchQueue.add(new SearchPos(i * puzzle[0].length + j - 1, curSearchPos.puzzleCurPos));
        }
      }
    }

    return null;

    /*
    Special
      puzzle == null
        return null
      puzzle.length == 0
        return null

    General:
      init trace for each point
      init queue
      for each pos in puzzle
        do bfs search:
        put {cur puzzle pos, target pos} into queue

      for each pos in puzzle
        do bfs search:
        for targetPos = 0 to target.len - 1
          for int puzzleCurPos = 0 to queue.len - 1
          pop queue head
          if puzzle pos is illegal or puzzle[head's puzzle pos] != target[head's target pos]
            continue
          if targetPos == target.len - 1
            assemble trace into final path
            return trace
          push all neighbors to queue
     */
  }
}
