package corruption;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


public class DecodingTests {

    private final String input = "nobody likes pineapple pizza";


    /* TEST simpleDecoder */
    private final String simpleCorruption = "znnoQobb€oomdd-yyM  ylltibiYkkOeesas n ppbiuinn§5eesaap0pppRlflFee 8 p7piWizzlEzzaa2";

    @Test
    void SimpleDecoder_ReturnsNonBinaryString() {
        assertFalse(Helper.isBinary(Decode.simpleDecoder(simpleCorruption)));
    }

    @Test
    void SimpleDecoder_ReturnsOtherStringThanReceived() {
        assertNotEquals(simpleCorruption, (Decode.simpleDecoder(simpleCorruption)));
    }

    @Test
    void SimpleDecoder_OutputIsAThirdTheLengthAsInput() {
        assertEquals(simpleCorruption.length() / 3, Decode.simpleDecoder(simpleCorruption).length());
    }

    @Test
    void SimpleDecoder_ThrowsExceptionError_WhenInputCannotBeDividedByThree() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Decode.simpleDecoder("a4abb?Zc");
        });

        assertEquals("Input was not correctly encoded!", exception.getMessage());
    }

    /* TEST bitWiseDecoder */
    private final String bitWiseCorruption = "011111001000000010110011110100000000111011100011";

    @Test
    void BitWiseDecoder_ReturnsNonBinaryString() {
        assertFalse(Helper.isBinary(Decode.bitWiseDecoder(bitWiseCorruption)));
    }

    @Test
    void BitWiseDecoder_ReturnsOtherStringThanReceived() {
        assertNotEquals(bitWiseCorruption, Decode.bitWiseDecoder(bitWiseCorruption));
    }

    @Test
    void BitWiseDecoder_ReturnsReadableString() {
        assertEquals("ac", Decode.bitWiseDecoder(bitWiseCorruption));
    }

    @Test
    void BitWiseDecoder_StillWorks_IfInputIsNotBinary() {
        assertEquals("c", Decode.bitWiseDecoder("=@à"));
    }

    @Test
    void BitWiseDecoder_OutputIsShorterThanInput() {
        assertTrue(Decode.bitWiseDecoder(bitWiseCorruption).length() < bitWiseCorruption.length());
    }

    /* TEST hammingDecoder */
    private final String hamBitCorruption = "10101101110";

    @Test
    void HammingDecoder_ReturnsNonBinaryString() {
        assertFalse(Helper.isBinary(Decode.hammingDecoder(hamBitCorruption)));
    }

    @Test
    void HammingDecoder_ReturnsReadableString() {
        assertEquals("x", Decode.hammingDecoder("0110110000000100000000"));
    }

    @Test
    void HammingDecoder_StillWorks_IfInputIsNotBinary() {
        assertEquals("Y", Decode.hammingDecoder(Helper.binaryToText(hamBitCorruption)));
    }

    @Test
    void HammingDecoder_OutputIsShorterThanInput() {
        assertTrue(Decode.hammingDecoder(hamBitCorruption).length() < hamBitCorruption.length());
    }

}
