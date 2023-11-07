import java.util.NoSuchElementException;
import java.util.Iterator;
public class LinkedList<AnyType> implements Iterable<AnyType>{
    private Node<AnyType> head;
    
    public LinkedList() {
        head = null;
    }
    
    public void addFirst(AnyType item) {
        head = new Node<AnyType>(item, head);
    }

    public void addLast(AnyType item) {
        if (head == null) {
            addFirst(item);
            return;
        }
        Node<AnyType> tmp = head;
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        tmp.next = new Node<AnyType>(item, null);
    }

    public void insertAfter(AnyType item, AnyType key) {
        Node<AnyType> tmp = head;
        while (tmp!= null && !tmp.data.equals(key)) {
            tmp = tmp.next;
        }
        if (tmp.next != null) {
            tmp.next = new Node<AnyType>(item, tmp.next);
        }
    }

    public void insertBefore(AnyType item, AnyType key) {
        if (head == null) {
            return;
        }
        if (head.data.equals(key)) {
            addFirst(item);
            return;
        }
        Node<AnyType> pre = null; 
        Node<AnyType> cur = head;
        while (cur != null && !cur.data.equals(key)) {
            pre = cur;
            cur = cur.next;
        }
        if (cur != null) {
            pre.next = new Node<AnyType>(item, cur);
        }
    }
    
    public void remove(AnyType key) {
        if (head == null) {
            return;
        }
        
        if (head.data.equals(key)) {
            head = head.next;
        }
        Node<AnyType> pre = null;
        Node<AnyType> cur = head;
        while (cur != null && ! cur.data.equals(key)) {
            pre = cur;
            cur = cur.next;
        }
        if (cur != null) {
            pre.next = cur.next;
        }
    }

    @Override
    public Iterator<AnyType> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<AnyType> {
        private Node<AnyType> nextNode;
        LinkedListIterator() {
            nextNode = head;
        }
        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public AnyType next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            AnyType result = nextNode.data;
            nextNode = nextNode.next;
            return result;
        }
    }

    private static class Node<AnyType> {
        private AnyType data;
        private Node<AnyType> next;
        
        Node(AnyType d, Node<AnyType> n) {
            data = d;
            next = n;
        }
    }
    
    public static void main(String[] args) {
        LinkedList<Integer> numbers = new LinkedList<Integer>();
        numbers.addFirst(1);
        numbers.addLast(2);
        numbers.addLast(3);
        numbers.addLast(4);
        numbers.addLast(5);

        Iterator<Integer> itr = numbers.iterator();
        System.out.println("Iterating Through elements");
        while (itr.hasNext()) {
            System.out.println(itr.next());
        }
    }
}
