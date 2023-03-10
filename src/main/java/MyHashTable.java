import java.util.Arrays;
import java.util.Hashtable;
import java.util.Map;
public class MyHashTable {
    private static final int INITIAL_CAPACITY = 2;
    private static final float LOAD_FACTOR = 0.75f;
    private Entry[] table;
    private int size;

    /*Initializes the hash table with a fixed initial capacity and size of 0.*/
    public MyHashTable() {
        table = new Entry[INITIAL_CAPACITY];
        size = 0;
    }


    /*
    A helper method that calculates the hash code for a given key.
    This method determine the index where a key-value pair will be stored in the hash table.
    */
    private int hash(String key) {
        int hash = 0;
        for (int i = 0; i < key.length(); i++) {
            hash = 31 * hash + key.charAt(i);
        }
        return hash;
    }

    /*
    This method retrieves the value associated with a given key.
    It calculates the index based on the hash code of the key and iterates through a linked list to find the key-value pair.
    */
    public String get(String key) {
        int index = hash(key) % table.length;
        for (Entry entry = table[index]; entry != null; entry = entry.next) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }
        return null;
    }

    /*
        This method inserts a key-value pair into the hash table.
        The method calculates the index based on the hash code of the key and iterates through
        a linked list to find if the key already exists.
        If it does, the method updates the value associated with the key.
        If it doesn't, the method creates a new entry and adds it to the linked list.
        The method also checks if the size of the hash table has exceeded a load factor, and if it has,
        it resizes the hash table.
    */
    public void put(String key, String value) {
        int index = hash(key) % table.length;
        for (Entry entry = table[index]; entry != null; entry = entry.next) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }
        //if key doesn't exit create a new entry and add it to the linked list
        Entry newEntry = new Entry(key, value);
        newEntry.next = table[index];
        table[index] = newEntry;
        size++;

        // Check if resize is needed
        if (size > LOAD_FACTOR * table.length) {
            resize();
        }
    }

    /*
    This method removes a key-value pair from the hash table.
    The method calculates the index based on the hash code of the key and iterates through a
    linked list to find the key-value pair. If found, the method removes the pair and updates the size of the hash table.
    */
    public void remove(String key) {
        int index = hash(key) % table.length;
        Entry prev = null;
        for (Entry entry = table[index]; entry != null; entry = entry.next) {
            if (entry.key.equals(key)) {
                if (prev == null) {
                    table[index] = entry.next;
                } else {
                    prev.next = entry.next;
                }
                size--;
                return;
            }
            prev = entry;
        }
    }


    /*A helper method that doubles the size of the hash table and rehashes all the key-value pairs.*/
    private void resize() {
        Entry[] newTable = new Entry[table.length * 2];
        for (Entry entry : table) {
            while (entry != null) {
                Entry next = entry.next;
                int index = hash(entry.key) % newTable.length;
                entry.next = newTable[index];
                newTable[index] = entry;
                entry = next;
            }
        }
        table = newTable;
    }

    /*
    An inner class that represents a key-value pair in the hash table.
    Each entry has a key, value, and a reference to the next entry in the linked list.
     */
    private static class Entry {
        String key;
        String value;
        Entry next;

        Entry(String key, String value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Entry{" +
                    "key='" + key + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        MyHashTable hashTable = new MyHashTable();

        hashTable.put("key1", "value1");
        hashTable.put("key1", "value2");
        hashTable.put("key3", "value3");
        System.out.println(hashTable.size);
        for (MyHashTable.Entry m : hashTable.table) {
            System.out.println(m);
        }

        Hashtable<String, String> hasa = new Hashtable<>(2);
        hasa.put("key1", "value1");
        hasa.put("key1", "value2");
        hasa.put("key3", "value3");
        System.out.println(hasa.size());
        System.out.println(hasa);


    }
}
