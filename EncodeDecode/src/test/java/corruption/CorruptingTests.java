package corruption;

import org.junit.jupiter.api.*;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class CorruptingTests {

    private final String input = "nobody likes pineapple pizza";
    private final String binIn = Helper.textToBinary(input);
    private final String binB = Helper.textToBinary("B");

    /* simpleCorruption */
    @Test
    void SimpleCorruption_ReturnsString() {
        assertTrue(Corrupt.simpleCorruption("abc123ABC") instanceof String);
    }

    @Test
    void SimpleCorruption_ReturnsString_WithOddNumber() {
        assertTrue(Corrupt.simpleCorruption("abc12") instanceof String);
    }

    @Test
    void SimpleCorruption_ReturnsSameLengthAsInput() {
        assertEquals(input.length(), Corrupt.simpleCorruption(input).length());
    }

    @Test
    void SimpleCorruption_ReturnsOtherStringThanReceived() {
        assertNotEquals("abc123ABC", (Corrupt.simpleCorruption("abc123ABC")));
    }

    /* rngOneOfThree */
    @Test
    void RngOneOfThree_ReturnsString() {
        assertTrue(Corrupt.rngOneOfThree("abc") instanceof String);
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
    void RngOneOfThree_ThrowsExceptionError_WhenInputGreaterThanThreeCharsLong() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Corrupt.rngOneOfThree("abcd");
        });

        assertEquals("String must be less than 4 chars long!", exception.getMessage());
    }

    /* bitCorruption */
    @Test
    void BitCorruption_returnsOtherStringThanReceived() {
        assertNotEquals(binIn, Corrupt.bitCorruption(binIn));
    }

    @Test
    void BitCorruption_StillWorks_IfInputIsNotBinary() {
        assertTrue(Pattern.matches("[01]+", Corrupt.bitCorruption("a")));
    }

    @Test
    void BitCorruption_ReturnsOnlyBinary() {
        assertTrue(Pattern.matches("[01]+", Corrupt.bitCorruption(binIn)));
    }

    @Test
    void BitCorruption_ReturnsSameLengthAsReceived() {
        assertEquals(binIn.length(), Corrupt.rngBitWise(binIn).length());
    }

    /* rngBitWise */
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
    void RngBitWise_ReturnsOnlyBinary() {
        assertTrue(Pattern.matches("[01]+", Corrupt.rngBitWise(binB)));
    }

    @Test
    void RngBitWise_ReturnsSameLengthAsReceived() {
        assertEquals(binB.length(), Corrupt.rngBitWise(binB).length());
    }

    /* byteCorruption */
    @Test
    void ByteCorruption_ReturnsString() {
        assertTrue(Corrupt.byteCorruption("abc123ABC") instanceof String);
    }

    @Test
    void ByteCorruption_ReturnsOtherStringThanReceived() {
        assertNotEquals("abc123ABC", (Corrupt.byteCorruption("abc123ABC")));
    }

    @Test
    void ByteCorruption_ReturnsSameLengthAsInput() {
        assertEquals(input.length(), Corrupt.byteCorruption(input).length());
    }

    /* rngMyByte */
    @Test
    void RngMyByte_ReturnsOtherByteThanReceived() {
        assertNotEquals((byte) 49, Corrupt.rngMyByte((byte) 49));
    }

    @Test
    void RngMyByte_ThrowsExceptionError_WhenInputIsZero() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Corrupt.rngMyByte((byte) 0);
        });

        assertEquals("Did not receive byte", exception.getMessage());
    }

    /* bitCorruptionRET */
    @Test
    void BitCorruptionRET_ReturnsOtherValueThanReceived() {
        assertNotEquals(input, (Corrupt.bitCorruptionRET(input)));
    }

    @Test
    void BitCorruptionRET_ReturnsSameLengthAsInput() {
        assertEquals(input.length(), new String(Corrupt.bitCorruptionRET(input)).length());
    }

}
