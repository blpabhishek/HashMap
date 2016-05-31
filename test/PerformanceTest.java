import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class PerformanceTest {
    Hash<String, Integer> hash;
    @Before
    public void setUp() throws Exception {
        hash = new Hash<>(10000,200);
    }

    @Test
    public void testAddingLargeValues() throws Exception {
        int value = 500;
        for (int i = 0; i < value; i++)
            hash.put(i+"", i);
        assertEquals(hash.size(),500);
    }
}
