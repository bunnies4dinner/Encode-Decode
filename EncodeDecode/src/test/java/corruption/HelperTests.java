package corruption;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class HelperTests {

    private final String input = "nobody likes pineapple pizza";

    /* binaryToText */
    @Test
    void BinaryToText_ConvertsCorrectly() {
        assertEquals(input, Helper.binaryToText(Helper.textToBinary(input)));
    }

    @Test
    void BinaryToText_ThrowsException_IfInputWasNotBinary() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Helper.binaryToText("001101c");
        });

        assertEquals("Input was NOT (pure) binary!", exception.getMessage());
    }

    /* testToBinary */
    @Test
    void TextToBinary_ReturnsOnlyBinary() {
        assertTrue(Pattern.matches("[01]+", Helper.textToBinary(input)));
    }

    @Test
    void TextToBinary_ReturnsLengthDivisibleBy8() {
        assertTrue(Helper.textToBinary(input).length()%8==0);
    }

    /* is Binary */

    @Test
    void IsBinary_RecognizesBinary() {
        assertTrue(Helper.isBinary(Helper.textToBinary(input)));
    }

    @Test
    void IsBinary_RecognizesNonBinary() {
        assertFalse(Helper.isBinary(input));
    }

}
