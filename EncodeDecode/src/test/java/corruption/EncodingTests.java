package corruption;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class EncodingTests {

    private final String input = "nobody likes pineapple pizza";

    /* simpleEncoder */
    @Test
    void SimpleEncoder_ReturnsString() {
        assertTrue(Encode.simpleEncoder("abc") instanceof String);
    }

    @Test
    void SimpleEncoder_ReturnsOtherStringThanReceived() {
        assertNotEquals("abc", (Encode.simpleEncoder("abc")));
    }

    @Test
    void SimpleEncoder_ReturnsAllCharsTripledInString() {
        assertEquals(Encode.simpleEncoder("abc"),"aaabbbccc");
    }

    @Test
    void SimpleEncoder_ReturnsTripledTheLengthAsInput() {
        assertEquals(input.length()*3, Encode.simpleEncoder(input).length());
    }

}
