package corruption;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;


public class DecodingTests {

    private final String input = "znnoQobb€oomdd-yyM  ylltibiYkkOeesas n ppbiuinn§5eesaap0pppRlflFee 8 p7piWizzlEzzaa2";

    /* simpleDecoder */
    @Test
    void SimpleDecoder_ReturnsString() {
        assertTrue(Decode.simpleDecoder("a4abb?Zcc") instanceof String);
    }

    @Test
    void SimpleDecoder_ReturnsOtherStringThanReceived() {
        assertNotEquals("a4abb?Zcc", (Decode.simpleDecoder("a4abb?Zcc")));
    }

    @Test
    void SimpleDecoder_ReturnsAThirdTheLengthAsInput() {
        assertEquals(input.length()/3, Decode.simpleDecoder(input).length());
    }

    @Test
    void SimpleDecoder_ThrowsExceptionError_WhenInputCannotBeDividedByThree() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Decode.simpleDecoder("a4abb?Zc");
        });

        assertEquals("Input was not correctly encoded!", exception.getMessage());
    }
}
