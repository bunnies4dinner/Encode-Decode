package corruption;

public class Decode {


    /**
     * @param decodeMe any String you feel needs to be decoded
     * @return a decoded, readable String
     * recommended input are simpleEncoded, then simpleCorrupted Strings
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
     * recommended input are bitWiseEncoded, then bitWiseCorrupted Strings
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
            String leftsideByte = "";
            String rightsideByte = "";
            // go through whole byte w/o parity & safe left & right sides
            for (int j = 0; j < 6; j += 2) {
                leftsideByte = leftsideByte.concat(Character.toString(oneByte.charAt(j)));
                rightsideByte = rightsideByte.concat(Character.toString(oneByte.charAt(j + 1)));
            }
            // if parity is not corrupted, check rest
            if (oneByte.charAt(6) == oneByte.charAt(7)) {
                int one = Integer.parseInt(Character.toString(leftsideByte.charAt(0)));
                int two = Integer.parseInt(Character.toString(leftsideByte.charAt(1)));
                int three = Integer.parseInt(Character.toString(leftsideByte.charAt(2)));
                int parity = Integer.parseInt(Character.toString(oneByte.charAt(6)));
                if ((one ^ two ^ three) == parity) {
                    decoded = decoded.concat(leftsideByte);
                } else {
                    decoded = decoded.concat(rightsideByte);
                }
            } else {
                decoded = decoded.concat(leftsideByte);
            }
        }
        // remove what we added for fun
        while (decoded.length() % 8 != 0) {
            decoded = decoded.substring(0, decoded.length() - 1);
        }

        return Helper.binaryToText(decoded);
    }
}
