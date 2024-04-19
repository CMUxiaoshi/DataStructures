import java.util.NoSuchElementException;

/**
 * This class define a linked list that will be used to create a hash table.
 * The data would be a pair either (String, int) or (int, String), where the
 * first element is the key and the second is the value in LZE encoder and decoder.
 * @author Xiao Shi
 * @AndrewID xiaoshi
 * */
public class LinkedList<AnyType> {
    private Node<AnyType> head; // Define the root of the linked list

    public LinkedList() {
        head = null;
    }

    /**
     * Insert a new data into the linked list.
     * @param key the new key to insert.
     * @param value corresponding value.
     **/
    public void insert(AnyType key, AnyType value) {
        Node<AnyType> newNode = new Node(key, value,null);
        if (head == null) {
            head = newNode;
            return;
        }
        Node<AnyType> cur = head;
        while(cur.next != null) {
            cur = cur.next;
        }
        cur.next = newNode;
    }


    /**
     * Check if an element has already in the linked list.
     * This method is created for hashset checking.
     * @param item the key to check
     * @return a boolean indicator. If the item is already in the linked list, return true, false otherwise.
     * */
    public boolean contain(AnyType item) {
        Node<AnyType> cur = head;
        if (cur == null) {
            return false;
        }
        while(cur != null) {
            if (cur.key.equals(item)) {
                return true;
            }
            cur = cur.next;
        }
        return false;
    }


    /**
     * This method return the value given key.
     * @param key the key to search
     * @return value the corresponding key.
     * */
    public AnyType valueOf(AnyType key) {
        Node<AnyType> cur = head;
        if (cur == null) {
            throw new NoSuchElementException("The list is empty.");
        }
        while(cur != null) {
            if (cur.key.equals(key)) {
                return cur.value;
            }
            cur = cur.next;
        }
        throw new NoSuchElementException("Key not found in the list.");
    }


    public void print() {
        Node<AnyType> cur =head;
        while(cur != null) {
            System.out.println("("+ cur.key + "," +cur.value + ")");
            cur = cur.next;
        }
    }

    /**
     * A nested class defines the node to use.
     * data is a pair
     * next points to the next node
     * */
    private static class Node<AnyType> {
        private AnyType key;
        private AnyType value;
        private Node<AnyType> next;

        Node(AnyType k, AnyType v, Node<AnyType> n) {
            key = k;
            value = v;
            next = n;
        }
    }
}
