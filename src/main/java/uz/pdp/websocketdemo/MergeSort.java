package uz.pdp.websocketdemo;

/* Java program for Merge Sort */
class MergeSort {
    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]

    static void mergeSort(int[] arr) {
        int inputLength = arr.length;
        if(inputLength<2) return;

        int midIndex = inputLength/2;

        int[] leftHalf = new int[midIndex];
        int[] rightHalf = new int[inputLength-midIndex];

        for (int i = 0; i < midIndex; i++) {
            leftHalf[i] = arr[i];
        }
        for (int i = midIndex; i < inputLength; i++) {
            rightHalf[i-midIndex] = arr[i];
        }

        mergeSort(leftHalf);
        mergeSort(rightHalf);

        merge(arr, leftHalf, rightHalf);
    }


    private static void merge(int[] inputArray, int[] leftHalf, int[] rightHalf){
        int leftSize = leftHalf.length;
        int rightSize = rightHalf.length;
        int i=0,j=0,k=0;

        while (i<leftSize && j<rightSize){
            inputArray[k++]=(leftHalf[i]<=rightHalf[j])?leftHalf[i++]:rightHalf[j++];
        }
        while (i<leftSize){
            inputArray[k++] = leftHalf[i++];
        }
        while (j<rightSize){
            inputArray[k++] = rightHalf[j++];
        }
    }



    /* A utility function to print array of size n */
    static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    // Driver code
    public static void main(String args[]) {
        int arr[] = {12, 11, 13, 5, 6, 7};

        System.out.println("Given Array");
        printArray(arr);

        mergeSort(arr);


        System.out.println("\nSorted array");
        printArray(arr);
    }
}
/* This code is contributed by Rajat Mishra */
