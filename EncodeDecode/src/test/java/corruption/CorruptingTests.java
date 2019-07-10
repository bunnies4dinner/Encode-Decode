package corruption;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class CorruptingTests {

    private final String input = "nobody likes pineapple pizza";

    /* simpleEncryption */
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

    /* rngCorruptMe */
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
    void BitCorruptionBYTE_ReturnsString() {
        assertTrue(Corrupt.bitCorruptionBYTE("abc123ABC") instanceof String);
    }

    @Test
    void BitCorruptionBYTE_ReturnsOtherStringThanReceived() {
        assertNotEquals("abc123ABC", (Corrupt.bitCorruptionBYTE("abc123ABC")));
    }

    @Test
    void BitCorruptionBYTE_ReturnsSameLengthAsInput() {
        assertEquals(input.length(), Corrupt.bitCorruptionBYTE(input).length());
    }

    @Test
    void BitCorruptionCHAR_ReturnsOtherStringThanReceived() {
        assertNotEquals(input, (Corrupt.bitCorruptionCHAR(input)));
    }


    /* rngMyByte */
    @Test
    void RngMyByte_ReturnsOtherByteThanReceived() {
        assertNotEquals((byte)49, Corrupt.rngMyByte((byte)49));
    }

    @Test
    void RngMyByte_ThrowsExceptionError_WhenInputIsZero() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            Corrupt.rngMyByte((byte)0);
        });

        assertEquals("Did not receive byte", exception.getMessage());
    }

}
