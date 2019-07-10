package corruption;

public class Encode {

    static String simpleEncoder(String input) {
        String output = "";
        for (int i = 0; i < input.length(); i++) {
            for (int j = 0; j < 3; j++) {
                output = output.concat(Character.toString(input.charAt(i)));
            }
        }
        return output;
    }
}
