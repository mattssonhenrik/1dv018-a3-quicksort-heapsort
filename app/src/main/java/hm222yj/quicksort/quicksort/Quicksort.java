package hm222yj.quicksort.quicksort;

public class Quicksort {
    public int[] partion (int[] arrayToSort, int low, int high) {
        int i = low;
        int j = high;

        while (true) {
            i++;
            while (arrayToSort[i] < arrayToSort[low]) {
                if (i == high ) {
                    break;
                }
                i++;
            }

            j--;
            while (arrayToSort[low] < arrayToSort[j]) {
                if (j == low) {
                    break;
                }
                j--;
            }

            if (i >= j) {
                break;
            }

            int temp = arrayToSort[j];
            arrayToSort[j] = arrayToSort[i];
            arrayToSort[i] = temp;
        }
        int temp = arrayToSort[low];
        arrayToSort[low] = arrayToSort[j];
        arrayToSort[j] = temp;

        return arrayToSort;
    }

    public int[] sortArray() {

    }
}
