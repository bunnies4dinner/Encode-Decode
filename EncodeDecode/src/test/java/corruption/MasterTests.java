package corruption;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MasterTests {
    private final String input = "nobody likes pineapple pizza";

    /* MASTER TESTS */
    @Test
    void SimpleEncodingAndCorruption_ShouldHaveSameOutputLength() {
        String encoded = Encode.simpleEncoder(input);
        String corrupted = Corrupt.simpleCorruption(encoded);

        assertEquals(encoded.length(), corrupted.length());
    }
    @Test
    void SimpleDecoder_DecodesCorruptedInputPerfectlyBack() {

        assertEquals(input, Decode.simpleDecoder(Corrupt.simpleCorruption(Encode.simpleEncoder(input))));
    }

    @Test
    void BitWiseEncodingAndCorruption_ShouldHaveSameOutputLength() {
        String encoded = Encode.simpleEncoder(input);
        String corruptedBytes = Corrupt.bitCorruptionBYTE(encoded);

        assertEquals(encoded.length(), corruptedBytes.length());
    }

    @Test
    void SimpleDecoder_DecodesCorruptedBytesInputPerfectlyBack() {

        assertEquals(input, Decode.simpleDecoder(Corrupt.bitCorruptionBYTE(Encode.simpleEncoder(input))));
    }
}
