package tomy.matrix;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.stream.Collectors;

import com.google.common.primitives.Ints;


public class ConnectedComponents {
  public static int[] getComponentSizesDfs(int[][] areaOrg) {
    /*
      corner
        area == null
          -> ex
        area.len == 0
          -> int[0]
        area[0].len == 0
          -> g
        area[1][1]
          -> g
    */

    Objects.requireNonNull(areaOrg);
    if (areaOrg.length == 0) {
      return new int[0];
    }

    int[][] area = MatrixUtil.matrixCopy(areaOrg);

    List<Integer> areaCountList = new ArrayList<>();

    final int areaHeight = area.length;
    final int areaWidth = area[0].length;
    for (int i = 0; i < areaHeight; ++i) {
      for (int j = 0; j < areaWidth; ++j) {
        int count = dfsCountArea(area, i, j);
        if (count > 0) {
          areaCountList.add(count);
        }
      }
    }

    int[] areaCountArray = Ints.toArray(areaCountList);
    Arrays.sort(areaCountArray);
    return areaCountArray;

    /*
      general
        init result list
        for each point in area
          count = dfsCount
          if count != 0
            add new count to result

        convert result to array & return
     */
  }

  private static int dfsCountArea(int[][] area, int i, int j) {
    if (i < 0 || i >= area.length || j < 0 || j >= area[0].length || area[i][j] == 0) {
      return 0;
    }

    area[i][j] = 0;

    return 1 + dfsCountArea(area, i + 1, j) + dfsCountArea(area, i - 1, j)
        + dfsCountArea(area, i, j + 1) + dfsCountArea(area, i, j - 1);
  }

  public static int[] getComponentSizesBfs(int[][] areaOrg) {
    /*
      corner
        area == null
          -> ex
        area.len == 0
          -> int[0]
        area[0].len == 0
          -> g
        area[1][1]
          -> g
    */

    Objects.requireNonNull(areaOrg);
    if (areaOrg.length == 0) {
      return new int[0];
    }

    int[][] area = MatrixUtil.matrixCopy(areaOrg);

    List<Integer> areaCountList = new ArrayList<>();

    final int areaHeight = area.length;
    final int areaWidth = area[0].length;
    for (int i = 0; i < areaHeight; ++i) {
      for (int j = 0; j < areaWidth; ++j) {
        int count = bfsCountArea(area, i, j);
        if (count > 0) {
          areaCountList.add(count);
        }
      }
    }

    int[] areaCountArray = Ints.toArray(areaCountList);
    Arrays.sort(areaCountArray);
    return areaCountArray;

    /*
      general
        init result list
        for each point in area
          count = dfsCount
          if count != 0
            add new count to result

        convert result to array & return
     */
  }

  private static int bfsCountArea(int[][] area, int i, int j) {
    int count = 0;

    Queue<Integer> areaCountQueue = new ArrayDeque<>();
    areaCountQueue.add(i * area[0].length + j);

    while (!areaCountQueue.isEmpty()) {
      int index = areaCountQueue.poll();
      int y = index / area[0].length;
      int x = index % area[0].length;
      if (y < 0 || y >= area.length || x < 0 || x >= area[0].length || area[y][x] == 0) {
        continue;
      }

      area[y][x] = 0;
      ++count;

      //@@ need to check the boundary before add the merged index value to queu
      if (y < area.length - 1) {
        areaCountQueue.add((y + 1) * area[0].length + x);
      }

      if (y > 0) {
        areaCountQueue.add((y - 1) * area[0].length + x);
      }

      if (x < area[0].length - 1) {
        areaCountQueue.add(y * area[0].length + x + 1);
      }

      if (x > 0) {
        areaCountQueue.add(y * area[0].length + x - 1);
      }
    }

    return count;
  }

  //@@ this class need to be static, since it doesn't need any outer context
  private static class AreaLabelUnion {
    private class AreaWithLabel {
      public int label;
      public int size;

      public AreaWithLabel(int label, int size) {
        this.label = label;
        this.size = size;
      }
    }

    private AreaWithLabel[] labels;

    public AreaLabelUnion(int totalNum) {
      labels = new AreaWithLabel[totalNum];
      //@@ initialize all elements with its label and size 0
      // otherwise, it is not easy to dynamically assign a label (may need another hashmap to track it)
      for (int i = 0; i < totalNum; ++i) {
        labels[i] = new AreaWithLabel(i, 0);
      }
    }

    public int initLabelWithOneElement(int label) {
      labels[label].size = 1;
      return label;
    }

    public int findRootLabel(int pos) {
      int label = pos;
      while (labels[label].label != label) {
        label = labels[label].label;
      }

      while (labels[pos].label != label) {
        int newPos = labels[pos].label;
        labels[pos].label = label;
        pos = newPos;
      }
      return label;
    }

    public int unionLabel(int label1, int label2) {
      //@@ !! corner case: label1 == label2
      if (label1 == label2) {
        return label1;
      }

      if (labels[label1].size >= labels[label2].size) {
        labels[label1].size += labels[label2].size;
        labels[label2].label = label1;
        labels[label2].size = 0;
        return label1;
      } else {
        labels[label2].size += labels[label1].size;
        labels[label1].label = label2;
        labels[label1].size = 0;
        return label2;
      }
    }

    public int[] getNonZeroAreaCounts() {
      List<Integer> areaCounts = Arrays.stream(labels)
          .filter(label -> label.size != 0)
          .map(label -> label.size)
          .collect(Collectors.toList());
      return Ints.toArray(areaCounts);
    }
  }

  public static int[] getComponentSizesUnionFind(int[][] areaOrg) {
    /*
      corner
        area == null
          -> ex
        area.len == 0
          -> int[0]
        area[0].len == 0
          -> g
        area[1][1]
          -> g
    */

    Objects.requireNonNull(areaOrg);
    if (areaOrg.length == 0) {
      return new int[0];
    }

    int[][] area = MatrixUtil.matrixCopy(areaOrg);

    final int areaHeight = area.length;
    final int areaWidth = area[0].length;
    AreaLabelUnion areaLabelUnion = new AreaLabelUnion(areaHeight * areaWidth);

    for (int i = 0; i < areaHeight; ++i) {
      for (int j = 0; j < areaWidth; ++j) {
        //@@ union find strucutre doesn't know about if area[i][j] is 0 or 1, we need to handle the logic
        if (area[i][j] == 0) {
          continue;
        }
        //@@ we are sure this element was not visited before (since we only union with previous row or col)
        // hence we need to initialize the label with size 1
        int curLabel = areaLabelUnion.initLabelWithOneElement(i * areaWidth + j);

        if (j > 0 && area[i][j - 1] != 0) {
          int leftLabel = areaLabelUnion.findRootLabel(i * areaWidth + j - 1);
          //@@ need to update the curLabel, because after union, it may have been assigned to another group
          curLabel = areaLabelUnion.unionLabel(curLabel, leftLabel);
        }

        if (i > 0 && area[i - 1][j] != 0) {
          int topLabel = areaLabelUnion.findRootLabel((i - 1) * areaWidth + j);
          areaLabelUnion.unionLabel(curLabel, topLabel);
        }
      }
    }

    int[] areaCountArray = areaLabelUnion.getNonZeroAreaCounts();
    Arrays.sort(areaCountArray);
    return areaCountArray;
  }


}
