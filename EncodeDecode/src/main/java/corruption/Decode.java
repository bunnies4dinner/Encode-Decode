package corruption;

import java.util.Arrays;

public class Decode {


    /**
     * @param decodeMe any String you feel needs to be decoded
     * @return a decoded, readable String
     * DOC: recommended input are simpleEncoded, then simpleCorrupted Strings
     */
    static String simpleDecoder(String decodeMe) {
        String output = "";
        String sub;
        if (decodeMe.length() % 3 != 0) {
            throw new IllegalArgumentException("Input was not correctly encoded!");
        }
        for (int i = 0; i < decodeMe.length(); i += 3) {
            sub = decodeMe.substring(i, i + 3).concat(Character.toString(decodeMe.charAt(i)));

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

    /**
     * @param decodeMe any String you feel needs to be decoded (binary recommended)
     * @return a decoded, readable String
     * DOC: recommended input are bitWiseEncoded, then bitWiseCorrupted Strings
     * splits Bytes into bits, comparing neighboured pairs
     * because of encoding, pairs should be equal, otherwise corrupted
     * if corruption is found, compare singles with parity pair, reconstruct, then remove parity & doubles
     * corruption in parity pair is not to be taken care of
     * then remove last unnecessary bits, bc we added them for fun
     */
    static String bitWiseDecoder(String decodeMe) {
        String decoded = "";

        if (!Helper.isBinary(decodeMe)) {
            decodeMe = Helper.textToBinary(decodeMe);
        }

        for (int i = 0; i < decodeMe.length(); i += 8) {
            // take one byte at a time
            String oneByte = decodeMe.substring(i, i + 8);
            int[] leftArray = new int[3];
            int[] rightArray = new int[3];
            int m = 0;
            // go through whole byte w/o parity & safe left & right sides
            for (int j = 0; j < 6; j += 2) {
                leftArray[m] = Integer.parseInt(Character.toString(oneByte.charAt(j)));
                rightArray[m] = Integer.parseInt(Character.toString(oneByte.charAt(j + 1)));
                m++;
            }
            // if parity is not corrupted, check rest
            if (oneByte.charAt(6) == oneByte.charAt(7)) {
                int parity = Integer.parseInt(Character.toString(oneByte.charAt(6)));
                if ((leftArray[0] ^ leftArray[1] ^ leftArray[2]) == parity) {
                    decoded = decoded.concat(Helper.stringifyArray(leftArray));
                } else {
                    decoded = decoded.concat(Helper.stringifyArray(rightArray));
                }
            } else {
                decoded = decoded.concat(Helper.stringifyArray(leftArray));
            }
        }
        // remove what we added for fun
        while (decoded.length() % 8 != 0) {
            decoded = decoded.substring(0, decoded.length() - 1);
        }

        return Helper.binaryToText(decoded);
    }

    /**
     * @param decodeMe any String you feel needs to be decoded
     * @return a decoded, readable String
     * DOC: recommended input are hammingEncoded, then bitWiseCorrupted Strings
     * split input into bytes, those into bits
     * drop last bit out bc it had fun enough
     * compare bits with parities from encoding, fix what's broken
     * drop parities out & send the reconstructed bits on its way
     * then drop every extra-bit we added in the beginning out as well, until string is divisible by 8
     */
    static String hammingDecoder(String decodeMe) {
        String decoded = "";
        int[] hamArray = new int[11];
        int[] parity = new int[4];

        if (!Helper.isBinary(decodeMe)) {
            decodeMe = Helper.textToBinary(decodeMe);
        }

        for (int i = 0; i < decodeMe.length(); i += 11) {
            /* take one byte at a time */
            String oneByte = decodeMe.substring(i, i + 11);
            for (int j = 0; j < 11; j++) {
                hamArray[j] = Integer.parseInt(Character.toString(oneByte.charAt(j)));
            }
            /* DOC: parity bit X checks all the bits positions whose binary representation
               DOC: includes a 1 in the Xth least significant position (XOR) */
            /* check parity 1 for positions 1, 3, 5, 7, 9, 11 */
            parity[3] = (hamArray[10] ^ hamArray[8] ^ hamArray[6] ^ hamArray[4] ^ hamArray[2] ^ hamArray[0]);
            /* check parity 2 for positions 2, 3, 6, 7, 10, 11 */
            parity[2] = (hamArray[9] ^ hamArray[8] ^ hamArray[5] ^ hamArray[4] ^ hamArray[1] ^ hamArray[0]);
            /* check parity 4 for positions 4-7 */
            parity[1] = (hamArray[7] ^ hamArray[6] ^ hamArray[5] ^ hamArray[4]);
            /* check parity 8 for position 8-11 */
            parity[0] = (hamArray[3] ^ hamArray[2] ^ hamArray[1] ^ hamArray[0]);

            /* DOC: determine the corrupted bit & index */
            int corruptedBit = Integer.parseInt(Helper.stringifyArray(parity), 2);
            int corruptedIndex = 11 - corruptedBit;
            /* DOC: flip out the weak */
            if (corruptedIndex != 11) { // TODO
                hamArray[corruptedIndex] = hamArray[corruptedIndex] == 1 ? 0 : 1;
            }


            /* DOC: only pass on the desired, healthy bits */
            String decodedSection = Helper.stringifyArray(hamArray);
            String desiredBits = decodedSection.substring(0, 3).concat(decodedSection.substring(4, 7)).concat(decodedSection.substring(8, 9));
            decoded = decoded.concat(desiredBits);
        }
        /* remove what we added for "fun" */
        while (decoded.length() != 7 && decoded.length() % 8 != 0) {
            decoded = decoded.substring(0, decoded.length() - 1);
        }

        return Helper.binaryToText(decoded);
    }
}
