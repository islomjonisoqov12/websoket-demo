package uz.pdp.websocketdemo;

import us.dustinj.timezonemap.TimeZoneMap;

import java.util.List;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

/* Java program for Merge Sort */
class MergeSort2 {
    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]

    static void mergeSort(int[] arr) {
        int inputLength = arr.length;
        if(inputLength<2) return;

        int midIndex = inputLength / 2;

        int[] leftArr = new int[midIndex];
        int[] rightArr = new int[inputLength-midIndex];

        for (int i=0; i<midIndex; i++) leftArr[i]=arr[i];
        for (int i=midIndex; i<inputLength; i++) rightArr[i - midIndex] = arr[i];


        mergeSort(leftArr);
        mergeSort(rightArr);


        merge(arr, leftArr, rightArr);
    }


    private static void merge(int[] inputArray, int[] leftHalf, int[] rightHalf){
        int leftLength = leftHalf.length;
        int rightLength = rightHalf.length;

        int l=0, r=0, i =0;

        while (l<leftLength && r<rightLength){
            inputArray[i++]=(leftHalf[l]<=rightHalf[r])?leftHalf[l++]:rightHalf[r++];
        }

        if(l+1<leftLength) inputArray[i++]=leftHalf[l++];
        else inputArray[i++] = rightHalf[r++];

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
