package corruption;

public class Main {
    public static void main(String[] args) {
        String input = "nobody likes pineapple pizza";

        for (int k = 0; k < 1; k++) {
            System.out.println(input);
            System.out.println("\nEncoded: " + Encode.simpleEncoder(input));
            System.out.println("Corrupted: " + Corrupt.simpleCorruption(Encode.simpleEncoder(input)));
            System.out.println("Decoded: " + Decode.simpleDecoder(Corrupt.simpleCorruption(Encode.simpleEncoder(input))));
            System.out.println("- - -");

            System.out.println(Helper.binaryToText(Helper.textToBinary(input)));
            System.out.println("Bit-Encoded: " + Encode.bitWiseEncoder(input));
            System.out.println("Bit-Corrupted: "+ Corrupt.bitCorruption(Encode.bitWiseEncoder(input)));
            System.out.println(Helper.binaryToText(Corrupt.bitCorruption(Encode.bitWiseEncoder("c"))));
          System.out.println("Bit-Decoded: "+Decode.bitWiseDecoder(Corrupt.bitCorruption(Encode.bitWiseEncoder(input))));
            System.out.println("- - -");


        }

    }
}
