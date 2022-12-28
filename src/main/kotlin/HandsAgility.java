import java.util.HashMap;

public final class HandsAgility {
    private static final Integer PLAYERS = 2;
    private static final Integer ROUNDS = 9;

    public static void main(String[] args) {
        int k = 4;
        StringBuilder builder = new StringBuilder("1111" +
                "1111" +
                "1111" +
                "1111");
        char[] charArray = builder.toString().toCharArray();
        HashMap<Character, Integer> mapWithRepeatNumbers = getMapWithRepeatNumbers(charArray);

        int result = getResult(mapWithRepeatNumbers, k);

        System.out.println(result);
    }

    private static HashMap<Character, Integer> getMapWithRepeatNumbers(char[] charArray) {
        HashMap<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] != '.') {
                if (map.get(charArray[i]) != null) {
                    map.replace(charArray[i], map.get(charArray[i]) + 1);
                } else {
                    map.put(charArray[i], 1);
                }
            }
        }

        return map;
    }

    private static int getResult(HashMap<Character, Integer> mapWithRepeatNumbers, int k) {
        int result = 0;

        for (int t = 1; t <= ROUNDS; t++) {
            char charValue = (char) (t + '0');
            if (mapWithRepeatNumbers.get(charValue) != null && mapWithRepeatNumbers.get(charValue) <= k * PLAYERS) {
                result += 1;
            }
        }

        return result;
    }
}
