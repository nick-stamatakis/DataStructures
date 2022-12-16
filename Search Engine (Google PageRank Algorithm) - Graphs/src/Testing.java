import java.util.ArrayList;

public class Testing {
    public static void main(String[] args) {
//        Testing.descend(5);
//        System.out.println(Testing.pow(3,2, 3));
        int[] list = {2, 4, 6, 3, 5};
        for (int i = 0; i < list.length; i++){
            System.out.print(list[i] + " ");
        }
        System.out.println();
        list = bubbleSort(list);
        for (int i = 0; i < list.length; i++){
            System.out.print(list[i] + " ");
        }
    }
    public static void descend(int n){
        if (n == 0){
            return;
        }
        if (n > 0){
            System.out.println(n);
        }
        descend(n-1);
    }
    public static int pow(int x, int y, int total){
        if (y == 1) return total;
        return pow(x, y-1, total*= x);
    }

    public static int[] selectionSort(int[] list){
        // One by one move boundary of unsorted subarray
        for (int i = 0; i < list.length - 1; i++){
            //sets smallest value to begin loop
            int minIndex = i;
            // Find the minimum element in unsorted array
            for (int j = i + 1; j < list.length; j++){
                if (list[j] < list[minIndex]) {
                    minIndex = j;
                }
            }
            // Swap the found minimum element with the first element
            int temp = list[i];
            list[i] = list[minIndex];
            list[minIndex] = temp;
        }
        return list;
    }
    public static int[] insertionSort(int[] arr){
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
        return arr;
    }

    public static int[] bubbleSort(int[] arr){
        int n = arr.length;
        for (int i = 0; i < n - 1; i++)
            for (int j = 0; j < n - i - 1; j++)
                if (arr[j] > arr[j + 1]) {
                    // swap arr[j+1] and arr[j]
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
        return arr;
    }

}
