/**
 * Sort an integer array through the bubble sort method.
 * @Author Xiao Shi
 * email: xiaoshi@andrew.cmu.edu
*/
public class Sort {

    /**
     * Swap elements.
     * @param data array we want to swap elements
     * @param one the index of the first elements
     * @param two the index of the second elements
    */
    private static void swap(int[] data, int one, int two) {
        int tmp = data[one];
        data[one] = data[two];
        data[two] = tmp;
    }

    /**
     * Bubble Sort: Compare elements from left two right. If elements on the left is bigger, then swap.
     * After every iteration, the biggest elements should on the right hand side.
     * Iterate until all elements are sorted. 
     * Time complexity: O(n^2)
     * @param data the array we want to sort
    */
    public static void bubbleSort(int[] data) {
        for (int out = data.length - 1; out > 0; out--){
            for (int in = 0; in < out; in++) { //No need to compare with the last elements as it's the biggest.
                if (data[in] > data[in + 1]) {
                    swap(data, in, in + 1);
                }
            }
        }
    }
    /**
     * Selection sort: put the smallest number on the left hand side each iteration.
     * update minimum if number on the right is smaller than the current minimum.
     * The next round start from the index that is 1 bigger than the former iteration start.
     * Time complexity: O(n^2).
     * One swap at most every iteration.
     * @param data the array we want to sort
    */
    public static void selectionSort(int[] data) {
        for (int out = 0; out < data.length; out++) {
            int min = out; // initial the minimum
            for (int in = out + 1; in < data.length; in++) {
                if (data[in] < data[min]) {
                    min = in;// reset the new min index
                }
            }
            swap(data, out, min);
        }
    }

    /**
     * Insertation sort: insert a number into the left handside each iteration.
     * Compare the new input with number that already inserted.
     * Insert the number in the right position. 
     * There is no swap operation in this method.
     * @param data the array we want to sort.
    */
    public static void insertionSort(int[] data) {
        for (int out = 1; out < data.length; out++) {
            int tmp = data[out];
            int in = out;
            while (in > 0 && data[in - 1] > tmp) {
                data[in] = data[in -  1]; //shift
                in--;
            }
            data[in] = tmp; //insert
        }
    }
    /**
     * Print the array.
     * @param data the array we want to print
    */
    public static void print(int[] data) {
        System.out.print("[");
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i]+ " ");
        }
        System.out.print("]");
    }

    public static void main(String[] args) {
        int[] dataa = { 4, 7, 2, 5, 3 };
        int[] datab = { 4, 7, 2, 5, 3 };
        int[] datac = { 4, 7, 2, 5, 3 };
        Sort.bubbleSort(dataa);
        Sort.selectionSort(datab);
        Sort.insertionSort(datac);
        print(dataa);
        print(datab);
        print(datac);
    }
}
