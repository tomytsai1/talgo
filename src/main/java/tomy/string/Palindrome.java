package tomy.string;

import java.util.Objects;


public class Palindrome {
  private static int longestPalindromeFrom(char[] charArray, int left, int right) {
    int len = 0;
    while (left >= 0 && right < charArray.length && charArray[left] == charArray[right]) {
      --left;
      ++right;
    }

    return right - left - 1;
  }

  public static String longestPalindromSubstringBasic(String str) {
    Objects.requireNonNull(str);

    int optStart = 0;
    int optLen = 0;
    char[] charArray = str.toCharArray();

    for (int i = 0; i < str.length(); ++i) {
      int palindromeLen = longestPalindromeFrom(charArray, i, i);
      if (palindromeLen > optLen) {
        optLen = palindromeLen;
        optStart = i - palindromeLen / 2;
      }

      if (i != 0) {
        palindromeLen = longestPalindromeFrom(charArray, i, i - 1);
        if (palindromeLen > optLen) {
          optLen = palindromeLen;
          optStart = i - palindromeLen / 2;
        }
      }
    }

    return str.substring(optStart, optStart + optLen);
  }

  public static String longestPalindromSubstringDp(String str) {
    Objects.requireNonNull(str);

    // end 0
    // end 5 cur[3] = char[3] == char[5] && prev[4]
    //       cur[3] = char[3] == char[5] && prev[4]
    // cur[end] = true
    // cur[end - 1] = char[y] == char[end] //@@ this formula has 2 special cases (corner and corner - 1)
    // cur[y (0 <= y < end)] = char[y] == char[end] && prev[end + 1]

    boolean[] prev = new boolean[str.length()]; // for puzzleCurPos == 0..end, if char[puzzleCurPos .. end] is palindrome
    boolean[] cur = new boolean[str.length()];

    if (str.isEmpty()) {
      return "";
    }

    int optLen = 1; //@@ * special case (see below)
    int optStart = 0;
    char[] charArray = str.toCharArray();

    for (int end = 0; end < str.length(); ++end) {
      for (int i = 0; i < end; ++i) {
        if (charArray[i] == charArray[end] && (i >= end - 1 || prev[i + 1])) {
          cur[i] = true;
          if (end - i + 1 > optLen) {
            optLen = end - i + 1;
            optStart = i;
          }
        } else {
          cur[i] = false; //@@ need to set false, because the value was not clean (swapped from prev round)
        }
      }
      cur[end] = true; //@@ * we don't need to check if we have optLen of 1 here. It was taken care in * above

      boolean[] temp = prev;
      prev = cur;
      cur = temp;
    }

    return str.substring(optStart, optStart + optLen);
  }

}
