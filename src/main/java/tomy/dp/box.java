package tomy.dp;

public class Box {
  public int length;
  public int width;
  public int height;

  public Box(int length, int width, int height) {
    this.length = length;
    this.width = width;
    this.height = height;
  }

  @Override
  public String toString() {
    return String.format("[L%d W%d H%d]", length, width, height);
  }
}
