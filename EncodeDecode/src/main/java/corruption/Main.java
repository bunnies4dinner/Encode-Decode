package corruption;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String input = "nobody likes pineapple pizza";

        for (int k = 0; k < 100; k++) {
            System.out.println("\nEncoded: "+Encode.simpleEncoder(input));
            System.out.println("Corrupted: "+Corrupt.simpleCorruption(Encode.simpleEncoder(input)));
            System.out.println("Decoded: "+Decode.simpleDecoder(Corrupt.simpleCorruption(Encode.simpleEncoder(input))));

            System.out.println("\nBit-Encoded: "+Encode.simpleEncoder(input));
            System.out.println("Bit-Corrupted: "+Corrupt.bitCorruptionBYTE(Encode.simpleEncoder(input)));
            System.out.println("Bit-Decoded: "+Decode.simpleDecoder(Corrupt.bitCorruptionBYTE(Encode.simpleEncoder(input))));

            // "suddenly chinese effect"
            char[] corruptedArray = Corrupt.bitCorruptionCHAR(input);
            System.out.println("Bit-Corrupted (chinese effect): ");
            for (char c : corruptedArray) {
                System.out.print(c);
            }



        }

    }
}
