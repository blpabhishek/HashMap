import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HashTest {
    Hash<String, Integer> hash;
    @Before
    public void setUp() throws Exception {
        hash = new Hash<>(10,2);
    }


    @Test
    public void testGet() throws Exception {
        hash.put("Hello", 32);
        hash.put("Something",23);
        assertEquals(new Integer(32), hash.get("Hello"));
        Integer value = hash.get("Something");
        assertEquals(new Integer(23),value);
    }

    @Test
    public void testToString() throws Exception {
        hash.put("Something",32);
        hash.put("SomeOtherThing",45);
        assertEquals("{Something=32,SomeOtherThing=45}",hash.toString());
    }

    @Test
    public void testSize() throws Exception {
        hash.put("Something",32);
        hash.put("SomeOtherThing",45);
        assertEquals(2,hash.size());
    }

    @Test
    public void testItCanOverrideLastValueAndSizeShouldRemainSame() throws Exception{
        hash.put("1",32);
        hash.put("2",45);
        hash.put("3",32);
        hash.put("3",67);
        hash.put("4",45);
        hash.put("5",32);
        hash.put("6",45);
        hash.put("7",45);
        assertEquals(7,hash.size());
    }

    @Test
    public void testDealingWithCollision() throws Exception{
        hash.put("1",32);
        hash.put("2",45);
        hash.put("7",45);
        hash.put("11",32);
        hash.put("b",32);
        System.out.println("hash = " + hash);
        assertEquals(5,hash.size());
    }
}