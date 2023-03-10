

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertNull;

class MyHashTableTest {

    @Test
    void testPutAndGet() {
        MyHashTable hashTable = new MyHashTable();
        hashTable.put("key1", "value1");
        hashTable.put("key2", "value2");
        hashTable.put("key3", "value3");

        assertEquals("value1", hashTable.get("key1"));
        assertEquals("value2", hashTable.get("key2"));
        assertEquals("value3", hashTable.get("key3"));
    }

    @Test
    void testPutAndUpdate() {
        MyHashTable hashTable = new MyHashTable();
        hashTable.put("key1", "value1");
        hashTable.put("key1", "value2");

        assertEquals("value2", hashTable.get("key1"));
    }

    @Test
    void testRemove() {
        MyHashTable hashTable = new MyHashTable();
        hashTable.put("key1", "value1");
        hashTable.put("key2", "value2");
        hashTable.put("key3", "value3");

        hashTable.remove("key2");

        assertEquals("value1", hashTable.get("key1"));
        assertEquals("value3", hashTable.get("key3"));
    }
}
