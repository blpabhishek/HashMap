import org.junit.Before;
import org.junit.Test;


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
        System.out.println("hash = " + hash);
        System.out.println("size = " + hash.size());
    }
}
