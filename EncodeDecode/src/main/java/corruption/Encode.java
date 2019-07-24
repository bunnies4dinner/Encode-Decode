package corruption;

import java.util.Arrays;

public class Encode {

    /**
     * @param encodeMe any String you'd like
     * @return a String three times the size of the input
     * triples every char in the String
     */
    static String simpleEncoder(String encodeMe) {
        String output = "";
        for (int i = 0; i < encodeMe.length(); i++) {
            for (int j = 0; j < 3; j++) {
                output = output.concat(Character.toString(encodeMe.charAt(i)));
            }
        }
        return output;
    }

    /**
     * @param encodeMe any String you'd like, binary is an option too
     * @return an encoded String in binary
     * takes 3 bit at a time, doubling them and
     * saving a xor-parity of it in the output-String
     * if the binary length cannot be divided by 3, bc of reasons we add zeros to the tail
     */
    static String bitWiseEncoder(String encodeMe) {
        String encodedBinary = "";

        if (!Helper.isBinary(encodeMe)) {
            encodeMe = Helper.textToBinary(encodeMe);
        }

        while (encodeMe.length() % 3 != 0) {
            // String must be divisible by 3, adding non-relevant zeros
            encodeMe = encodeMe + "0";
        }
        for (int k = 0; k < encodeMe.length(); k += 3) {
            // taking 3 bits at a time to store, doubled
            String bitOne = Character.toString(encodeMe.charAt(k));
            String bitTwo = Character.toString(encodeMe.charAt(k + 1));
            String bitThree = Character.toString(encodeMe.charAt(k + 2));

            // parity is required to reconstruct a corrupted input
            int parity = Integer.parseInt(bitOne) ^ Integer.parseInt(bitTwo) ^ Integer.parseInt(bitThree);
            encodedBinary = encodedBinary.concat(bitOne + bitOne + bitTwo + bitTwo + bitThree + bitThree + "" + parity + "" + parity);
        }
        return encodedBinary;
    }

    /**
     * @param encodeMe any String you want do encode
     * @return an hamming-encoded String in binary
     * DOC: Hamming Code
     * 2^r >= m + r + 1, where r = redundant, m = data bit
     * take 7 bits, so redundant (parity) bits are 4
     * all bit positions that are a power of 2 are marked as parity bits (1, 2, 4, 8, ...)
     * parity bits represent their own cluster of bits (even parity -> XOR)
     * add a useless zero at the end for fun and accuracy
     * further explanation: https://www.geeksforgeeks.org/computer-network-hamming-code/
     */
    static String hammingEncoder(String encodeMe) {
        String oneByte;
        String binaryOut = "";
        int[] hamArray = new int[11];

        if (!Helper.isBinary(encodeMe)) {
            encodeMe = Helper.textToBinary(encodeMe);
        }

        while (encodeMe.length() % 7 != 0) {
            /* String must be divisible by 7, adding non-relevant zeros*/
            encodeMe = encodeMe + "0";
        }

        for (int k = 0; k < encodeMe.length(); k += 7) {
            /* taking 7 bits at a time to store*/
            int m = 0;
            oneByte = encodeMe.substring(k, k + 7);
            for (int i = 0; i < 9; i++) {
                /* making an array of the relevant bits, storing in right position*/
                hamArray[i] = Integer.parseInt(Character.toString(oneByte.charAt(m)));
                m++;
                if (i == 2 || i == 6) {
                    /* skip to keep space for parity */
                    i++;
                }
            }
            /* DOC: parity bit X coverts all the bits positions whose binary representation
               DOC: includes a 1 in the Xth least significant position (XOR) */
            /* parity 1 for positions 1, 3, 5, 7, 9, 11 */
            hamArray[10] = (hamArray[8] ^ hamArray[6] ^ hamArray[4] ^ hamArray[2] ^ hamArray[0]);
            /* parity 2 for positions 2, 3, 6, 7, 10, 11 */
            hamArray[9] = (hamArray[8] ^ hamArray[5] ^ hamArray[4] ^ hamArray[1] ^ hamArray[0]);
            /* parity 4 for positions 4-7 */
            hamArray[7] = (hamArray[6] ^ hamArray[5] ^ hamArray[4]);
            /* parity 8 for position 8-11 */
            hamArray[3] = (hamArray[2] ^ hamArray[1] ^ hamArray[0]);

            binaryOut = binaryOut.concat(Helper.stringifyArray(hamArray));
        }
        return binaryOut;
    }

}
