package corruption;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


public class DecodingTests {

    private final String simpleCorruption = "znnoQobb€oomdd-yyM  ylltibiYkkOeesas n ppbiuinn§5eesaap0pppRlflFee 8 p7piWizzlEzzaa2";
    private final String bitWiseCorruption = "011111001000000010110011110100000000111011100011";

    /* simpleDecoder */
    @Test
    void SimpleDecoder_ReturnsString() {
        assertTrue(Decode.simpleDecoder(simpleCorruption) instanceof String);
    }

    @Test
    void SimpleDecoder_ReturnsOtherStringThanReceived() {
        assertNotEquals(simpleCorruption, (Decode.simpleDecoder(simpleCorruption)));
    }

    @Test
    void SimpleDecoder_ReturnsAThirdTheLengthAsInput() {
        assertEquals(simpleCorruption.length() / 3, Decode.simpleDecoder(simpleCorruption).length());
    }

    @Test
    void SimpleDecoder_ThrowsExceptionError_WhenInputCannotBeDividedByThree() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Decode.simpleDecoder("a4abb?Zc");
        });

        assertEquals("Input was not correctly encoded!", exception.getMessage());
    }

    /* bitWiseDecoder */
    @Test
    void BitWiseDecoder_ReturnsString() {
        assertTrue(Decode.bitWiseDecoder(bitWiseCorruption) instanceof String);
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
}
