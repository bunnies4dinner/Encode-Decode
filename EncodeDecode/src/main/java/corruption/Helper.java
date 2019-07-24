package corruption;

import java.util.Arrays;
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
                // DOC: make every Byte 8 digits long
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
        int bL = binaryIn.length();
        // DOC: there is no edge-case where length is possibly anything else than 7, 8 or 11
        int bitRange = bL % 8 == 0 ? 8 : bL % 7 == 0 ? 7 : 11;
        for (int i = 0; i < binaryIn.length(); i += bitRange) {
            String oneByte = binaryIn.substring(i, i + bitRange);
            char c = (char) Integer.parseInt(oneByte, 2);
            textOut = textOut.concat(Character.toString(c));
        }
        return textOut;
    }

    /**
     * @param textIn input String
     * @return String in hexadecimal
     * input can be binary or non-binary
     */
    static String toHex(String textIn) {
        if (!isBinary(textIn)) {
            textIn = textToBinary(textIn);
        }
        String hexOut = "";
        // iterate byte-sized or else binary strings are too much
        for (int i = 0; i < textIn.length(); i += 8) {
            String oneByte = textIn.substring(i, i + 8);
            int dec = Integer.parseInt(oneByte, 2);
            hexOut = hexOut.concat(Integer.toString(dec, 16));
        }

        hexOut = hexOut.replaceAll("..(?!$)", "$0 ");

        return hexOut;
    }

    /**
     * @param myString the String you want to check
     * @return true, if your String IS binary
     */
    static boolean isBinary(String myString) {
        return Pattern.matches("[01]+", myString);
    }

    /**
     * @param myArray the Array you want to convert
     * @return a string from your Array
     * turns an int Array into a readable String w/o braces and stuff
     */
    static String stringifyArray(int[] myArray) {
        String stringify = Arrays.toString(myArray);
        return stringify.replaceAll(", ", "").replaceAll("\\[", "").replaceAll("\\]", "");
    }

}
