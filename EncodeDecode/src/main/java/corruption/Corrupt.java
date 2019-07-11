package corruption;

import java.util.*;

public class Corrupt {
    // base has every char a String can use (and only the cool ones from that pool)
    private static String base = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz !§$%&/()=?+-_€~#.";
    private static Random rng = new Random();

    /**
     * @param input any String you'd like
     * @return a corrupted string
     * in every String's triplet one char is being swapped with another randomly char
     */
    static String simpleCorruption(String input) {
        String output = "";
        String sub;
        for (int i = 0; i < input.length(); i += 3) {
            int j = i + 3;
            while (j > input.length()) --j;
            sub = input.substring(i, j);
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
     * @param inputBinary input String in binary
     * @return corrupted String in binary
     * make an error in one bit per byte
     */
    static String bitCorruption(String inputBinary) {
        if(!Helper.isBinary(inputBinary)) {
            inputBinary = Helper.textToBinary(inputBinary);
        }
        String output = "";
        for (int k = 0; k < inputBinary.length(); k += 8) {
            output = output.concat(rngBitWise(inputBinary.substring(k, k + 8)));
        }
        return output;
    }

    /**
     * @param oneByteString input String in binary, length shall be 8 bit
     * @return corrupted String in binary
     */
    static String rngBitWise(String oneByteString) {
        char[] switchy = oneByteString.toCharArray();
        int swappingBit = rng.nextInt(oneByteString.length());
        if (switchy[swappingBit] == '0') {
            switchy[swappingBit] = '1';
        } else switchy[swappingBit] = '0';
        return new String(switchy);
    }

    /**
     * R E T I R E D
     *
     * @param input the desired String to be corrupted
     * @return char Array of corrupted chars
     * make an error in one bit per byte
     */
    static char[] bitCorruptionRET(String input) {
        char[] cInput = input.toCharArray();
        char[] cOutput = new char[input.length()];
        int i = 0;
        String sub;
        for (char c : cInput) {
            sub = Integer.toBinaryString(c);
            cOutput[i] = (rngBitWiseRET(sub));
            i++;
        }
        return cOutput;
    }

    /**
     * R E T I R E D
     *
     * @param oneByteString input String in binary, length shall be 8 bit
     * @return single corrupted char
     */
    static char rngBitWiseRET(String oneByteString) {
        int i = oneByteString.length();
        char[] switchy = oneByteString.toCharArray();
        int swappingBit = rng.nextInt(i);
        if (switchy[swappingBit] == '0') {
            switchy[swappingBit] = '1';
        } else switchy[swappingBit] = '0';
        return (char) (Integer.parseInt(new String(switchy), 2));
    }

    /**
     * R E T I R E D
     *
     * @param input the desired String to be corrupted
     * @return String of converted corrupted Bytes
     * corrupts every Byte as Byte and not in binary
     */
    static String byteCorruption(String input) {
        byte[] cInput = input.getBytes();
        byte[] cOutput = new byte[input.length()];
        String output = "";
        int i = 0;
        byte sub;
        for (byte c : cInput) {
            sub = c;
            cOutput[i] = (rngMyByte(sub));
            // output = output.concat(rngBitWise(sub));
            i++;
        }
        return new String(cOutput);
    }

    /**
     * R E T I R E D
     *
     * @param oneByte input single Byte only
     * @return corrupted Byte
     */
    static byte rngMyByte(byte oneByte) {
        if (oneByte == 0) {
            throw new IllegalArgumentException("Did not receive byte");
        }
        byte[] swappy = base.getBytes();
        oneByte = swappy[rng.nextInt(base.length())];

        return (byte) (Integer.parseInt(String.valueOf(oneByte)));
    }


    /* not asked for, but fun! */
    static char[] xorCorruption(String input) {
        int key = 7;
        char[] cOutput = input.toCharArray();
        for (int i = 0; i < input.length(); i++) {
            cOutput[i] = (char) (cOutput[i] ^ key);
        }
        return cOutput;
    }

}

