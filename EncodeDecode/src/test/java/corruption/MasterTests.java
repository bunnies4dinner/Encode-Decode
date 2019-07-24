package corruption;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MasterTests {

    private final String input = "nobody likes pineapple pizza";

    /* TEST simple */
    @Test
    void SimpleDecoder_DecodesNonCorruptedInputPerfectlyBack() {
        assertEquals(input, Decode.simpleDecoder(Encode.simpleEncoder(input)));
    }

    @Test
    void SimpleEncodingAndCorruption_ShouldHaveSameOutputLength() {
        String encoded = Encode.simpleEncoder(input);
        String corrupted = Corrupt.simpleCorruption(encoded);

        assertEquals(encoded.length(), corrupted.length());
    }

    @Test
    void SimpleEncodingAndCorruption_ShouldNotEqual() {
        String encoded = Encode.simpleEncoder(input);
        String corrupted = Corrupt.simpleCorruption(encoded);

        assertNotEquals(encoded, corrupted);
    }

    @Test
    void SimpleDecoder_DecodesCorruptedInputPerfectlyBack() {

        assertEquals(input, Decode.simpleDecoder(Corrupt.simpleCorruption(Encode.simpleEncoder(input))));
    }

    /* TEST bit-wise */
    @Test
    void BitWiseDecoder_DecodesNonCorruptedInputPerfectlyBack() {
        assertEquals(input, Decode.bitWiseDecoder(Encode.bitWiseEncoder(input)));
    }

    @Test
    void BitWiseEncodingAndCorruption_ShouldHaveSameOutputLength() {
        String encoded = Encode.bitWiseEncoder(input);
        String bitCorrupted = Corrupt.bitCorruption(encoded);

        assertEquals(encoded.length(), bitCorrupted.length());
    }

    @Test
    void BitWiseEncodingAndCorruption_ShouldNotEqual() {
        String encoded = Encode.bitWiseEncoder(input);
        String bitCorrupted = Corrupt.bitCorruption(encoded);

        assertNotEquals(encoded, bitCorrupted);
    }

    @Test
    void BitWiseDecoder_DecodesCorruptedBytesInputPerfectlyBack() {

        assertEquals(input, Decode.bitWiseDecoder(Corrupt.bitCorruption(Encode.bitWiseEncoder(input))));
    }

    /* TEST hamming code */
    @Test
    void HammingDecoder_DecodesNonCorruptedInputPerfectlyBack() {
        assertEquals(input, Decode.hammingDecoder(Encode.hammingEncoder(input)));
    }

    @Test
    void HammingEncodingAndBitWiseCorruption_ShouldHaveSameOutputLength() {
        String encoded = Encode.hammingEncoder(input);
        String bitCorrupted = Corrupt.bitCorruption(encoded);

        assertEquals(encoded.length(), bitCorrupted.length());
    }

    @Test
    void HammingEncodingAndBitWiseCorruption_ShouldNotEqual() {
        String encoded = Encode.hammingEncoder("x");
        String bitCorrupted = Corrupt.hamCorruption(encoded);

        assertNotEquals(encoded, bitCorrupted);
    }

    @Test
    void HammingDecoder_DecodesCorruptedInputPerfectlyBack_ShortInput() {
        assertEquals("x", Decode.hammingDecoder(Corrupt.hamCorruption(Encode.hammingEncoder("x"))));
    }

    @Test
    void HammingDecoder_DecodesCorruptedInputPerfectlyBack_ShortInput_Binary() {
        assertEquals(Helper.textToBinary("x"), Helper.textToBinary(Decode.hammingDecoder(Corrupt.hamCorruption(Encode.hammingEncoder("x")))));
    }

    @Test
    void HammingDecoder_DecodesCorruptedInputPerfectlyBack_With11bitCorruption() {
        assertEquals(input, Decode.hammingDecoder(Corrupt.hamCorruption(Encode.hammingEncoder(input))));
    }


}
