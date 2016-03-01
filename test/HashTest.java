import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HashTest {
    Hash<String, Integer> hash;
    @Before
    public void setUp() throws Exception {
        hash = new Hash<>(10);
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
        hash.put("Something",32);
        hash.put("SomeOtherThing",45);
        assertEquals("{Something=32,SomeOtherThing=45}",hash.toString());
    }

    @Test
    public void testIfCollision() throws Exception{
        hash.put("Something",32);
        hash.put("SomeOtherThing",45);
        hash.put("Some",32);
        hash.put("OtherThing",45);
        hash.put("thing",32);
        hash.put("Thing",45);
        hash.put("SomeThing",45);
        System.out.println("hash = " + hash);
        System.out.println("hash.size() = " + hash.size());
    }

    @Test
    public void testSize() throws Exception {
        hash.put("Something",32);
        hash.put("SomeOtherThing",45);
        assertEquals(2,hash.size());
    }
}