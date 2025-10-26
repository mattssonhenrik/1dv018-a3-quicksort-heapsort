package hm222yj.quicksort.quicksort;

import java.util.Arrays;

public class Quicksort {
    public void sortArray(int[] arrayToSort, int low, int high) {
        if (high <= low) {
            return;
        }
        int j = partion(arrayToSort, low, high);
        sortArray(arrayToSort, low, j - 1);
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
        int temp = arrayToSort[low];
        arrayToSort[low] = arrayToSort[j];
        arrayToSort[j] = temp;

        return j;
    }

    public int medianOfThree(int[] arrayToSort, int low, int high) {
        int medianIndex = (low + high) / 2;
        int[] medianArray = { arrayToSort[low], arrayToSort[medianIndex], arrayToSort[high] };
        Arrays.sort(medianArray);
        // System.out.println("This is the pivot " + medianArray[1]);
        // System.out.println("In the array: ");
        // for (int number : medianArray) {
        // System.out.println(number);
        // }
        return medianArray[1];
    }
}
