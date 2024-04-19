import java.util.NoSuchElementException;

/**
 * A class defines hashset.
 * Array-Linked list structure is using here.
 * A hash set shall not contain data already in the set.
 * @author Xiao Shi
 * @AndrewID xiaoshi
 * */
public class HashSet<AnyType> {
    private LinkedList<AnyType>[] base;
    private static final int size = 127;

    private int curSize;
    public HashSet() {
        base = new LinkedList[size];
        curSize = 0;
    }

    /**
     * Insert key, value pair into the hashset.
     * If the linked list at base[i] is not initialized, then initialize first.
     * This is a hash set so repeating key,value should not be inserted into the table
     * @param key The key to insert.
     * @param value The value to insert
     * */
    public void insert(AnyType key, AnyType value) {
        int hashValue = Math.abs(key.hashCode());
        int index = hashValue % size;
        if (base[index] == null) {
            base[index] = new LinkedList();
        }
        if (! contain(key)) {
            base[index].insert(key, value);
            curSize = curSize + 1;
        }
    }

    /**
     * Check if the key is in the hash table.
     * @param key the key to check
     * @return if the key is in the hash table, return true. Return false otherwise.
     * */
    public boolean contain(AnyType key) {
        int hashValue = Math.abs(key.hashCode());
        int index = hashValue % size;
        return base[index] != null && base[index].contain(key);
    }

    /**
     * Return the value given a key.
     * @param key the key to search
     * @throws NoSuchElementException if the key is not found
     * */
    public AnyType valueOf(AnyType key) {
        int hashValue = Math.abs(key.hashCode());
        int index = hashValue % size;
        if (contain(key)) {
            return base[index].valueOf(key);
        }
        throw new NoSuchElementException();
    }

    /**
     * Return the current size of the table to make sure it's a 12 bit LZW compression
     * @return the current size.
     * */
    public int getCurSize() {
        return curSize;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            if (base[i] == null) {
                continue;
            } else {
                base[i].print();
            }
        }
    }

    public static void main(String[] args) {
        HashSet<String> hash = new HashSet();
        hash.insert("key", "value");
    }


}
