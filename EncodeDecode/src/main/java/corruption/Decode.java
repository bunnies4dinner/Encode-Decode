package corruption;

public class Decode {

    static String simpleDecoder(String input) {
        String output = "";
        String sub;
        if (input.length() % 3 != 0) {
            throw new IllegalArgumentException("Input was not correctly encoded!");
        }
        for (int i = 0; i < input.length(); i += 3) {
            sub = input.substring(i, i + 3).concat(Character.toString(input.charAt(i)));

            char[] triplets = sub.toCharArray();

            for (int k = 0; k < 3; k++) {
                if (triplets[k] == triplets[k + 1]) {
                    output = output.concat(Character.toString(triplets[k]));
                    break;
                }
            }
        }
        return output;
    }

    static String bitWiseDecoder(String input)
}
