package corruption;

import org.junit.jupiter.api.*;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class CorruptingTests {

    private final String input = "nobody likes pineapple pizza";


    /* TEST simpleCorruption */
    @Test
    void SimpleCorruption_ReturnsNonBinaryString() {
        assertFalse(Helper.isBinary(Corrupt.simpleCorruption("abc123ABC")));
    }

    @Test
    void SimpleCorruption_ReturnsString_WithOddNumberInput() {
        assertTrue(Corrupt.simpleCorruption("abc12") instanceof String);
    }

    @Test
    void SimpleCorruption_OutputHasSameLengthAsInput() {
        assertEquals(input.length(), Corrupt.simpleCorruption(input).length());
    }

    @Test
    void SimpleCorruption_ReturnsOtherStringThanReceived() {
        assertNotEquals("abc123ABC", (Corrupt.simpleCorruption("abc123ABC")));
    }

    /* TEST rngOneOfThree */
    @Test
    void RngOneOfThree_ReturnsNonBinaryString() {
        assertFalse(Helper.isBinary(Corrupt.rngOneOfThree("abc")));
    }

    @Test
    void RngOneOfThree_ReturnsOtherStringThanReceived() {
        assertNotEquals("abc", (Corrupt.rngOneOfThree("abc")));
    }

    @Test
    void RngOneOfThree_ReturnsOtherStringThanReceived_InputUnderTwoCharSize() {
        assertNotEquals("ab", (Corrupt.rngOneOfThree("ab")));
    }

    @Test
    void RngOneOfThree_OutputHasSameLengthAsInput() {
        assertTrue("abc".length() == Corrupt.rngOneOfThree("abc").length());
    }

    @Test
    void RngOneOfThree_ThrowsExceptionError_WhenInputGreaterThanThreeCharsLong() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Corrupt.rngOneOfThree("abcd");
        });

        assertEquals("String must be less than 4 chars long!", exception.getMessage());
    }

    /* TEST bitCorruption */
    private final String binIn = Helper.textToBinary(input);

    @Test
    void BitCorruption_ReturnsOtherStringThanReceived() {
        assertNotEquals(binIn, Corrupt.bitCorruption(binIn));
    }

    @Test
    void BitCorruption_StillWorks_IfInputIsNotBinary() {
        assertTrue(Pattern.matches("[01]+", Corrupt.bitCorruption("a")));
    }

    @Test
    void BitCorruption_ReturnsBinaryString() {
        assertTrue(Pattern.matches("[01]+", Corrupt.bitCorruption(binIn)));
    }

    @Test
    void BitCorruption_OutputHasSameLengthAsInput() {
        assertEquals(binIn.length(), Corrupt.bitCorruption(binIn).length());
    }

    /* TEST hamCorruption */
    private final String hamIn = Encode.hammingEncoder(input);

    @Test
    void HamCorruption_ReturnsOtherStringThanReceived() {
        assertNotEquals(hamIn, Corrupt.hamCorruption(hamIn));
    }

    @Test
    void HamCorruption_StillWorks_IfInputIsNotBinary() {
        assertTrue(Pattern.matches("[01]+", Corrupt.hamCorruption(Helper.binaryToText(hamIn))));
    }

    @Test
    void HamCorruption_ReturnsBinaryString() {
        assertTrue(Pattern.matches("[01]+", Corrupt.hamCorruption(hamIn)));
    }

    @Test
    void HamCorruption_OutputHasSameLengthAsInput() {
        assertEquals(hamIn.length(), Corrupt.hamCorruption(hamIn).length());
    }

    /* TEST rngBitWise */
    private final String binB = Helper.textToBinary("B");

    @Test
    void RngBitWise_ReturnsOtherStringThanReceived() {
        assertNotEquals(binB, Corrupt.rngBitWise(binB));
    }

    @Test
    void RngBitWise_StillWorks_IfInputIsGreaterThan8Bit() {
        assertNotEquals(binIn, Corrupt.rngBitWise(binIn));
        // but pls dont misuse it
    }

    @Test
    void RngBitWise_ReturnsBinaryString() {
        assertTrue(Pattern.matches("[01]+", Corrupt.rngBitWise(binB)));
    }

    @Test
    void RngBitWise_OutputHasSameLengthAsInput() {
        assertEquals(binB.length(), Corrupt.rngBitWise(binB).length());
    }

}
