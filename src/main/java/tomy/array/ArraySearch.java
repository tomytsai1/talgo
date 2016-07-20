package tomy.array;

public class ArraySearch {
  //@@@ basic search loop
  // A. for finding x in (start, end]
  //    1. while (l < r) { m = (l+r)/2; ... }  => assume [l, r] in legal range, and final index is l (== r)
  //    2. while (l < r - 1) { m = (l+r+1)/2; ... }  => assume [l, r) in legal range, and final index is l (== r - 1)
  //    3. while (len > 0) { step = len >> 1; ... }  => assume [l, l+len] in legal range, and final index is l (== r)
  //
  // B. for finding x in (start, end]
  //    1. while (l < r) { m = (l+r+1)/2; ... }  => assume [l, r] in legal range, and final index is l (== r)
  //    2. while (l < r - 1) { m = (l+r)/2; ... }  => assume [l, r) in legal range, and final index is l (== r - 1)
  //    3. while (len > 1) { step = len >> 1; ... }  => assume [l, l+len] in legal range, and final index is l (== r - 1)
}
