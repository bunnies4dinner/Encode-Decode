package corruption;

import java.util.*;

public class Corrupt {
    // base has every char a String can use (and only the cool ones from that pool)
    private static String base = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz !§$%&/()=?+-_€~#.";
    private static Random rng = new Random();

    /**
     * @param corruptMe any String you'd like
     * @return a corrupted string
     * in every String's triplet one char is being swapped with another randomly char
     */
    static String simpleCorruption(String corruptMe) {
        String output = "";
        String sub;
        for (int i = 0; i < corruptMe.length(); i += 3) {
            int j = i + 3;
            while (j > corruptMe.length()) --j;
            sub = corruptMe.substring(i, j);
            output = output.concat(rngOneOfThree(sub));
        }
        return output;
    }

    /**
     * @param threeCharString a String of three chars, aka triplet
     * @return a corrupted triplet
     * one char is being swapped with another randomly char
     */
    static String rngOneOfThree(String threeCharString) {
        int i = 3;
        if (threeCharString.length() > 3) {
            throw new IllegalArgumentException("String must be less than 4 chars long!");
        }
        // if string.length is less than desired length, method will adapt
        while (threeCharString.length() < i) {
            --i;
        }
        char[] switchy = threeCharString.toCharArray();
        switchy[rng.nextInt(i)] = base.charAt(rng.nextInt(base.length()));
        return new String(switchy);
    }

    /**
     * @param corruptMe input String to corrupt
     * @return corrupted String in binary
     * make an error in one bit per byte
     */
    static String bitCorruption(String corruptMe) {
        if (!Helper.isBinary(corruptMe)) {
            corruptMe = Helper.textToBinary(corruptMe);
        }
        String output = "";
        for (int k = 0; k < corruptMe.length(); k += 8) {
            output = output.concat(rngBitWise(corruptMe.substring(k, k + 8)));
        }
        return output;
    }

    /**
     * @param corruptMe input String to corrupt
     * @return corrupted String in binary
     *  DOC: Hamming Code
     * make an error in one bit every 11 bits
     */
    static String hamCorruption(String corruptMe) {
        if (!Helper.isBinary(corruptMe)) {
            corruptMe = Helper.textToBinary(corruptMe);
        }
        String output = "";
        for (int k = 0; k < corruptMe.length(); k += 11) {
            output = output.concat(rngBitWise(corruptMe.substring(k, k + 11)));
        }
        return output;
    }

    /**
     * @param sectionString input section-String in binary
     * @return corrupted String in binary
     */
    static String rngBitWise(String sectionString) {
        char[] switchy = sectionString.toCharArray();
        int swappingBit = rng.nextInt(sectionString.length());

        switchy[swappingBit] = switchy[swappingBit] == '0' ? '1' : '0';

        return new String(switchy);
    }

}

