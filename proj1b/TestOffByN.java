import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    static OffByN offByN = new OffByN(5);

    @Test
    public void TestOffByN() {
        assertFalse(offByN.equalChars('a', 'b'));
        assertFalse(offByN.equalChars('a', 'e'));
        assertTrue(offByN.equalChars('a', 'f'));
        assertTrue(offByN.equalChars('f', 'a'));
    }

}
