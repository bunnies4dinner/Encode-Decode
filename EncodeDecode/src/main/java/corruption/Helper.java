package corruption;

import java.util.regex.Pattern;

public class Helper {

    /**
     * @param textIn input String as readable text
     * @return String in binary
     */
    static String textToBinary(String textIn) {
        String binaryOut = "";
        char[] textAsChar = textIn.toCharArray();
        for (char c : textAsChar) {
            String binarySingleChar = Integer.toBinaryString(c);
            while (binarySingleChar.length() < 8) {
                // make every Byte 8 digits long
                binarySingleChar = "0" + binarySingleChar;
            }
            binaryOut = binaryOut.concat(binarySingleChar);
        }
        return binaryOut;
    }

    /**
     * @param binaryIn input String in binary
     * @return String in readable text
     */
    static String binaryToText(String binaryIn) {
        if (!isBinary(binaryIn)) {
            throw new IllegalArgumentException("Input was NOT (pure) binary!");
        }
        String textOut = "";
        for (int i = 0; i < binaryIn.length(); i += 8) {
            String oneByte = binaryIn.substring(i, i + 8);
            char c = (char) Integer.parseInt(oneByte, 2);
            textOut = textOut.concat(Character.toString(c));
        }
        return textOut;
    }

    static int charToInt(char charIn) {
        String oneBit = Character.toString(charIn);
        return Integer.parseInt(oneBit);
    }

    /**
     * @param myString the String you want to check
     * @return true, if your String IS binary
     */
    static boolean isBinary(String myString) {
        return Pattern.matches("[01]+", myString);
    }

}
