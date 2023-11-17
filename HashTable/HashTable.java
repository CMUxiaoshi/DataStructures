/**
 * A very basic hashtable use linear probing as its collision resolution method.
 * This hash table only presents basic concepts of hash table,
 * many places require further improvement.
 * @Author Xiao Shi
*/
public class HashTable implements HashTableInterface{
    private static final DataItem DELETE = new DataItem(-1);
    private DataItem[] hashArray;

    /**
     * Private helper method for hashing a key value.
     * @param key the key need to transform
     * @return the hash value of the key
    */
    private int hashFunc(int key) {
        return key % hashArray.length;
    }

    /**
     * Search for the key in hashtable.
     * @param key the key need to search
     * @return true if found, false otherwise
    */
    @Override
    public boolean search(int key) {
        int hashVal = hashFunc(key);
        while (hashArray[hashVal] != null) { //linear check from index = hashVal
            if (hashArray[hashVal].key == key) {
                return true;
            }
            hashVal++;
            // wrap around to avoid outbound
            hashVal = hashVal % hashArray.length;
        }
        // if not found
        return false;
    }

    /**
     * Delete and return the key.
     * @param key the key need to be deleted
     * @return the key we delete. This is for check use, if not found, return -1.
    */
    @Override
    public int delete(int key) {
        int hashVal = hashFunc(key);
        while (hashArray[hashVal] != null) {
            if (hashArray[hashVal].key == key){
                int result = hashArray[hashVal].key;
                //lasy deletion
                hashArray[hashVal] = DELETE;
                return result;
            }
            hashVal++;
            hashVal = hashVal % hashArray.length;
        }
        return -1;
    }

    /**
     * Insert the key
     * @param key the key we want to insert
    */
    @Override
    public void insert(int key){
        int hashVal = hashFunc(key);
        //we can use "==" here because we only need to check identify as delete is a final static variable.
        while (hashArray[hashVal] != null && hashArray[hashVal] != DELETE) { 
            hashVal++;
            hashVal = hashVal % hashArray.length;
        }
        DataItem item = new DataItem(key);
        hashArray[hashVal] = item;
    }
    /**
     * Private class that store key
    */
    private static class DataItem {
        private int key;

        DataItem (int k) {
            key = k;
        }
    }
}