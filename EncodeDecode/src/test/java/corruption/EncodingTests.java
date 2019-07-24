package corruption;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class EncodingTests {

    private final String input = "nobody likes pineapple pizza";

    /* TEST simpleEncoder */
    @Test
    void SimpleEncoder_ReturnsNonBinaryString() {
        assertFalse(Helper.isBinary(Encode.simpleEncoder("abc")));
    }

    @Test
    void SimpleEncoder_ReturnsOtherStringThanReceived() {
        assertNotEquals("abc", (Encode.simpleEncoder("abc")));
    }

    @Test
    void SimpleEncoder_ReturnsAllCharsTripledInString() {
        assertEquals(Encode.simpleEncoder("abc"), "aaabbbccc");
    }

    @Test
    void SimpleEncoder_OutputIsTripledTheLengthAsInput() {
        assertEquals(input.length() * 3, Encode.simpleEncoder(input).length());
    }

    /* TEST bitWiseEncoder */
    @Test
    void BitWiseEncoder_OutputIsLongerThanInput() {

        assertTrue(Encode.bitWiseEncoder(input).length() > input.length());
    }

    @Test
    void BitWiseEncoder_ReturnsBinaryString() {
        assertTrue(Helper.isBinary(Encode.bitWiseEncoder(input)));
    }

    @Test
    void BitWiseEncoder_OutputLengthDivisibleBy4() {
        assertTrue(Encode.bitWiseEncoder(input).length() % 4 == 0);
        assertTrue(Encode.bitWiseEncoder("abc").length() % 4 == 0);
        assertTrue(Encode.bitWiseEncoder("input-NOT_divisibleBy4!").length() % 4 == 0);
    }

    /* TEST hammingEncoder */
    @Test
    void HammingEncoder_ReturnsBinaryString() {
        assertTrue(Helper.isBinary(Encode.hammingEncoder(input)));
    }

    @Test
    void HammingEncoder_OutputIsLongerThanInput() {
        assertTrue(Encode.hammingEncoder(input).length() > input.length());
    }

    @Test
    void HammingEncoder_OutputLengthIsDivisibleBy8() {
        assertTrue(Encode.hammingEncoder(input).length() % 8 == 0);
    }

}
