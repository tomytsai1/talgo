package tomy.array;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeSet;


public class ArrayMerge {
  public static List<Integer> arrayMerge(List<List<Integer>> sources) {
    List<Integer> mergedList = new ArrayList<>();

    class ValueRef implements Comparable<ValueRef> {
      public int value;
      public int index;
      public int offset;

      public ValueRef(int value, int index, int offset) {
        this.value = value;
        this.index = index;
        this.offset = offset;
      }

      //@@ when we have a priority queue or treeset/map, remember to define a comparable or comparator
      @Override
      public int compareTo(ValueRef other) {
        return value - other.value;
      }
     }

    PriorityQueue<ValueRef> valueQueue = new PriorityQueue<ValueRef>();
    int sourceIndex = 0;
    for (List<Integer> source : sources) {
      if (!source.isEmpty()) {
        valueQueue.add(new ValueRef(source.get(0), sourceIndex, 0));
        ++sourceIndex;
      }
    }

    while (!valueQueue.isEmpty()) {
      ValueRef valueRef = valueQueue.poll();
      mergedList.add(valueRef.value);

      if (sources.get(valueRef.index).size() > valueRef.offset + 1) {
        valueQueue.add(new ValueRef(
            sources.get(valueRef.index).get(valueRef.offset + 1), valueRef.index, valueRef.offset + 1));
      }
    }

    return mergedList;

    /*
        init result list
        init priority queue {value, source index, source offset}
        for each source array
          add the first item into queue

        while queue not empty
          pop the queue head {value, source index, offset}
          add head value into result
          if sources[index].len > offset + 1
            add {source[index][offset + 1], index, offset + 1} into queue
     */
  }

  public static List<Integer> arrayMerge2(List<List<Integer>> sources) {
    List<Integer> mergedList = new ArrayList<>();

    class ValueRef implements Comparable<ValueRef> {
      public int value;
      public int index;
      public int offset;

      public ValueRef(int value, int index, int offset) {
        this.value = value;
        this.index = index;
        this.offset = offset;
      }

      //@@ when we have a priority queue or treeset/map, remember to define a comparable or comparator
      @Override
      public int compareTo(ValueRef other) {
        return value != other.value ? value - other.value : index - other.index;
      }
    }

    TreeSet<ValueRef> valueSet = new TreeSet<ValueRef>();
    int sourceIndex = 0;
    for (List<Integer> source : sources) {
      if (!source.isEmpty()) {
        valueSet.add(new ValueRef(source.get(0), sourceIndex, 0));
        ++sourceIndex;
      }
    }

    while (!valueSet.isEmpty()) {
      ValueRef valueRef = valueSet.pollFirst();
      mergedList.add(valueRef.value);

      if (sources.get(valueRef.index).size() > valueRef.offset + 1) {
        valueSet.add(new ValueRef(
            sources.get(valueRef.index).get(valueRef.offset + 1), valueRef.index, valueRef.offset + 1));
      }
    }

    return mergedList;

    /*
        init result list
        init priority queue {value, source index, source offset}
        for each source array
          add the first item into queue

        while queue not empty
          pop the queue head {value, source index, offset}
          add head value into result
          if sources[index].len > offset + 1
            add {source[index][offset + 1], index, offset + 1} into queue
     */
  }

}
