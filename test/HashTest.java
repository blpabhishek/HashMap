import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.assertEquals;

public class HashTest {
    Hash<String, Integer> hash;

    @Rule
    public ExpectedException thrown = ExpectedException.none();
    @Before
    public void setUp() throws Exception {
        hash = new Hash<>();
    }

    @Test
    public void testPut() throws Exception {
        assertEquals(new Integer(32),hash.put("Hello", 32));
    }

    @Test
    public void testPutIfKeyAlreadyExist() throws Exception {
        hash.put("Hello", 32);
        assertEquals(new Integer(32),hash.put("Hello", 322));
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
    public void testGetIfKeyDoesNotExist() throws Exception {
        thrown.expect(ValueNotFoundException.class);
        hash.get("UnknownKey");
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
        assertEquals(5,hash.size());
    }

    @Test
    public void testInCaseOfCollisionHashMapShouldGiveBackTheValue() throws Exception{
        hash.put("11",38);
        hash.put("b",32);
        assertEquals(new Integer(32),hash.get("b"));
    }

    @Test
    public void testHashMapShouldThrowErrorWhenKeyIsNotThere() throws Exception{
        hash.put("11",38);
        hash.put("b",32);
        thrown.expect(ValueNotFoundException.class);
        hash.get("PrankKey");
    }

    @Test
    public void testShouldAllowMeToInsertNullValue() throws Exception{
        hash.put(null,38);
        hash.put(null,null);
        assertEquals(null,hash.get(null));
    }

    @Test
    public void testRemoveKeyShouldRemoveTheSpecifiedKeyAndReturnBackTheValue() throws Exception{
        hash.put("Number", 38);
        Integer num = hash.remove("Number");
        assertEquals(new Integer(38),num);
    }
    @Test
    public void testRemoveKeyShouldThrowErrorIfTheSpecifiedKeyIsNotFound() throws Exception{
        hash.put("Key",398);
        hash.put("Name",38);
        hash.remove("Name");
        assertEquals(1,hash.size());
        thrown.expect(ValueNotFoundException.class);
        hash.get("Name");
    }
}