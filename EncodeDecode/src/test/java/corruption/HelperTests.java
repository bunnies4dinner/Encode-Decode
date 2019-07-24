package corruption;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class HelperTests {

    private final String input = "nobody likes pineapple pizza";
    private final String binIn = "01101110";

    /* TEST binaryToText */
    @Test
    void BinaryToText_ConvertsCorrectly() {
        assertEquals("n", Helper.binaryToText(binIn));
    }

    @Test
    void BinaryToText_ReturnsNonBinaryString() {
        assertFalse(Helper.isBinary(Helper.binaryToText(binIn)));
    }

    @Test
    void BinaryToText_ThrowsException_IfInputWasNotBinary() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Helper.binaryToText("001101c");
        });

        assertEquals("Input was NOT (pure) binary!", exception.getMessage());
    }

    /* TEST textToBinary */
    @Test
    void TextToBinary_ReturnsBinaryString() {
        assertTrue(Pattern.matches("[01]+", Helper.textToBinary(input)));
    }

    @Test
    void ngs() {
        assertEquals(input, Helper.binaryToText(Helper.textToBinary(input)));
    }

    @Test
    void TextToBinary_OutputLengthIsDivisibleBy8() {
        assertTrue(Helper.textToBinary(input).length() % 8 == 0);
    }

    /* TEST toHex */
    @Test
    void ToHex_ReturnsHexadecimal_IfInputIsBinary() {
        assertEquals("34", Helper.toHex("00110100"));
    }

    @Test
    void ToHex_ReturnsHexadecimal_IfInputIsNonBinary() {
        assertEquals("61 62", Helper.toHex("ab"));
    }

    /* TEST isBinary */
    @Test
    void IsBinary_RecognizesBinary() {
        assertTrue(Helper.isBinary(binIn));
    }

    @Test
    void IsBinary_RecognizesNonBinary() {
        assertFalse(Helper.isBinary(input));
    }

    /* TEST stringifyArray */
    @Test
    void StringifyArray_ReturnsABinaryOnlyString_IfInputWasBinaryArray() {
        int[] binArr = {1,0,1,0,0,0,1,1};
        assertTrue(Helper.isBinary(Helper.stringifyArray(binArr)));
    }

}
