package tomy.random;

public class RandomString {
  public static String genRandStr(int len, char minChar, char maxChar) {
    StringBuilder result = new StringBuilder(len);
    for (int i = 0; i < len ; ++i) {
      result.append((char)(minChar + RandomGen.getRandom().nextInt(maxChar - minChar + 1)));
    }
    return result.toString();
  }
}
