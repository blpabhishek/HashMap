import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HashTest {
    Hash<String, Integer> hash;
    @Before
    public void setUp() throws Exception {
        hash = new Hash<>();
    }

    @Test
    public void testHashFunction() throws Exception {
        int key = hash.hashFunction("Key");
        assertTrue(key<=100);
    }

    @Test
    public void testPut() throws Exception {
        assertTrue(hash.put("Key",32));
    }

    @Test
    public void testGet() throws Exception {
        boolean key = hash.put("Hello", 32);
        assertEquals(new Integer(32), hash.get("Hello"));
    }

    @Test
    public void testToString() throws Exception {
        hash.put("SomeThing",32);
        assertEquals(hash.toString(),"{32}");
    }
}