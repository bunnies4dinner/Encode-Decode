package corruption;

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


}
