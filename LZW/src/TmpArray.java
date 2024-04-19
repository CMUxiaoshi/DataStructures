/**
 * Store information every two strings
 *
 * */
public class TmpArray {
    private int size;
    private int[] tmp;

    private static final int maxSize = 2;
    public TmpArray() {
        tmp = new int[maxSize];
        size = 0;
    }

    public boolean isFull() {
        return size == maxSize;
    }

    public int getSize() {
        return size;
    }

    public void insert(int value) {
        tmp[size] = value;
        size = size + 1;
    }

    public int getElement(int index) {
        return tmp[index];
    }
}
