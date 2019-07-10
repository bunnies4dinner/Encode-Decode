package corruption;

import java.util.*;

public class Corrupt {
    private static String base = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabsdefghijklmnopqrstuvwxyz !§$%&/()=?+-_€";
    private static Random rng = new Random();

/* TODO:
    SALT-method
    bit-wise corruption
    xor?
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

    /* make an error in one bit per byte */
    static char[] bitCorruptionCHAR(String input) {
        char[] cInput = input.toCharArray();
        char[] cOutput = new char[input.length()];
        String output = "";
        int i = 0;
        String sub;
        for (char c : cInput) {
            sub = Integer.toBinaryString(c);
            cOutput[i] = (rngBitWiseCHAR(sub));
            // output = output.concat(rngBitWise(sub));
            i++;
        }
        return cOutput;
    }

    static char rngBitWiseCHAR(String oneByteString) {
        int i = oneByteString.length();
        char[] switchy = oneByteString.toCharArray();
        int swappingBit = rng.nextInt(i);
        if (switchy[swappingBit] == '0') {
            switchy[swappingBit] = '1';
        } else switchy[swappingBit] = '0';
        return (char)(Integer.parseInt(new String(switchy)));
    }

    /* make an error in one bit per byte */
    static String bitCorruptionBYTE(String input) {
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

    static byte rngMyByte(byte oneByte) {
        if(oneByte==0) {
            throw new IllegalArgumentException("Did not receive byte");
        }
        byte[] swappy = base.getBytes();
        oneByte = swappy[rng.nextInt(base.length())];

        return (byte)(Integer.parseInt(String.valueOf(oneByte)));
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

