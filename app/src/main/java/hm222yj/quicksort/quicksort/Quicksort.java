package hm222yj.quicksort.quicksort;

public class Quicksort {
    public void sortArray(int[] arrayToSort, int low, int high) {
        if (high <= low) {
            return;
        }
        int j = partion(arrayToSort, low, high);
        sortArray(arrayToSort, low, j - 1);
        // System.out.println("INVOKING THE RIGHT PART OF THE ARRAY SORTING ALGO");
        sortArray(arrayToSort, j + 1, high);
    }

    public int partion(int[] arrayToSort, int low, int high) {
        int i = low;
        int j = high + 1;
        int pivot = medianOfThree(arrayToSort, low, high);

        while (true) {
            i++;
            while (arrayToSort[i] < pivot) {
                if (i == high) {
                    break;
                }
                i++;
            }
            j--;
            while (pivot < arrayToSort[j]) {
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
        swap(arrayToSort, low, j);

        return j;
    }

    public int medianOfThree(int[] arrayToSort, int low, int high) {
        int medianIndex = low + ((high - low) / 2);
        if (arrayToSort[medianIndex] < arrayToSort[low]) {
            swap(arrayToSort, low, medianIndex);
        }
        if (arrayToSort[high] < arrayToSort[low]) {
            swap(arrayToSort, low, high);
        }
        if (arrayToSort[high] < arrayToSort[medianIndex]) {
            swap(arrayToSort, medianIndex, high);
        }
        // System.out.println("This is high: " + high);
        // System.out.println("This is low: " + low);
        // System.out.println("This is the pivot " + arrayToSort[medianIndex]);
        // System.out.println("This is the median index " + medianIndex);
        // System.out.println("In the array: ");
        // for (int number : arrayToSort) {
        //     System.out.println(number);
        // }
        swap(arrayToSort, low, medianIndex);
        return arrayToSort[low];
    }

    // ------------------Helper methods------------------
    // Swap method
    private void swap(int[] arrayToSort, int i, int j) {
        int t = arrayToSort[i];
        arrayToSort[i] = arrayToSort[j];
        arrayToSort[j] = t;
    }
}
