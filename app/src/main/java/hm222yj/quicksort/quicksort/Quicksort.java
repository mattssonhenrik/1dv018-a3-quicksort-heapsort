package hm222yj.quicksort.quicksort;

public class Quicksort {

    // Sorting methods

    // quicksort
    public void sortArray(int[] arrayToSort, int low, int high, int depthSwitch) {
        if (high <= low) {
            return;
        }
        int j = partion(arrayToSort, low, high);
        sortArray(arrayToSort, low, j - 1, depthSwitch);
        // System.out.println("INVOKING THE RIGHT PART OF THE ARRAY SORTING ALGO");
        sortArray(arrayToSort, j + 1, high, depthSwitch);
    }

    private int partion(int[] arrayToSort, int low, int high) {
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

    // Heapsort
    public void heapSort(int[] arrayToSort) {
        int heaplength = arrayToSort.length;
        buildHeap(arrayToSort, heaplength);
        sortHeap(arrayToSort, heaplength);
    }

    private void buildHeap(int[] arrayToSort, int heapLength) {
        for (int i = heapLength / 2; i >= 1; i--) { // delar på två för att hitta sista föräldern innan bladen kommer
            sink(arrayToSort, i, heapLength);
        }
    }

    private void sortHeap(int[] arrayToSort, int heapLength) {
        for (int heapEnd = heapLength; heapEnd > 1; heapEnd--) {
            heapSwap(arrayToSort, 1, heapEnd);
            sink(arrayToSort, 1, heapEnd - 1);
        }
    }

    private void sink(int[] arrayToSort, int i, int heapSize) {
        while (true) {
            int left = 2 * i;
            int right = 2* i +1;
            int largestValue = i;
            
            if (left <= heapSize && getValue(arrayToSort, left) > getValue(arrayToSort, largestValue)) {
                largestValue = left;
            }
            if (right <= heapSize && getValue(arrayToSort, right) > getValue (arrayToSort, largestValue)) {
                largestValue = right;
            }
            if (largestValue == i) { // Vår exit-strategy
                break;
            }
            heapSwap(arrayToSort, i, largestValue);
            i = largestValue;
        }
    }

    // ------------------Helper methods------------------

    // Median of three pivot QS
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
        // System.out.println(number);
        // }
        swap(arrayToSort, low, medianIndex);
        return arrayToSort[low];
    }

    // Swap method QS
    private void swap(int[] arrayToSort, int i, int j) {
        int temp = arrayToSort[i];
        arrayToSort[i] = arrayToSort[j];
        arrayToSort[j] = temp;
    }

    // Swap method HS
    private void heapSwap(int[] arrayToSort, int i, int j) {
        swap(arrayToSort, i - 1, j - 1);
    }

    // Hämta indexvärdet HS
    private int getValue(int[] arrayToSort, int index) {
        return arrayToSort[index-1]; // Justerar för HS är 1-baserat, QS har ingen sådan justering utan 0-baserad.
    }
}
