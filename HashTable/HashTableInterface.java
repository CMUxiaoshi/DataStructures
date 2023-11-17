/**
 * HashTable interface that takes only positive integers
 * No mapping. Just keys
 * HashTable is good at searching, deleting and insertion
*/
public interface HashTableInterface{
    /**
     * Returns true when the key is found.
     * @param key int key to search
     * @return boolean true if found, false not found
    */
    boolean search(int key);

    /**
     * Deletes and returns an int key from the table.
     * @param key int key to delete
     * @return the deleted int key(if not found, -1)
    */
    int delete(int key);

    /**
     * Inserts an int key to the table.
     * @param key int key to insert.
    */
    void insert(int key);
}
