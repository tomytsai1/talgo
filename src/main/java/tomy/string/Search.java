package tomy.string;

import java.util.Objects;


public class Search {
  private static int[] buildLongestProperPrefix(String s) {
    Objects.requireNonNull(s);

    int[] lpp = new int[s.length()];
    if (lpp.length == 0) {
      return lpp;
    }
    lpp[0] = 0; //@@ critical, since for proper prefix we skip the first char, and need to set it to 0

    int i = 1;
    int prev = 0;
    while (i < s.length()) {
      if (s.charAt(i) == s.charAt(prev)) {
        //@@ matched at prev -> matched from 0..prev -> len of match = prev + 1
        lpp[i++] = ++prev;
      } else if (prev == 0) {
        lpp[i++] = 0;
      } else {
        prev = lpp[prev - 1];
      }
    }

    return lpp;
    /*
    special case
       s.len = 0

    general case
        lpp[0] = 0
        puzzleCurPos = 0
        while puzzleCurPos < s.len
          precondition:
            lpp[0 .. puzzleCurPos - 1] okay

          if s[puzzleCurPos] == s[k]
            lpp[puzzleCurPos++] = ++k
          else if k == 0
            lpp[puzzleCurPos++] = 0
          else
            k = lpp[k - 1]
     */
  }

  public static int searchKmp(String source, String target) {
    Objects.requireNonNull(source);
    Objects.requireNonNull(target);

    if (source.isEmpty() || target.isEmpty()) {
      return -1;
    }

    int[] lpp = buildLongestProperPrefix(target);
    int targetOffset = 0;
    nextPos: for (int pos = 0; pos < source.length(); ++pos) {
      char c = source.charAt(pos);
      while (c != target.charAt(targetOffset)) {
        if (targetOffset == 0) {
          continue nextPos;
        }
        targetOffset = lpp[targetOffset - 1];
      }

      if (targetOffset == target.length() - 1) {
        return pos - target.length() + 1;
      }

      ++targetOffset;
    }

    return -1;

    /*
        contract
          source != null
          target != null

          return -1 if not found
                 start position if found
        corner case
          source "" -> return -1
          target "" -> return -1
          source "a"
          target "a"

        general case
          build lpp of target
          targetOffset = 0
          for pos = 0 .. source.len - 1
            c = source[pos]
            while c != target[targetOffset] && targetOffset != 0
              targetOffset = lpp[targetOffset - 1]

            if targetOffset == target.len - 1
              return pos - (target.len - 1)
            else if targetOffset != 0
              ++targetOffset

          return -1
     */
  }
}
