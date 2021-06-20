package com.example;

import java.util.ArrayList;
import java.util.Arrays;

public class Helper {

    public static int binarySearch(int[] arr, int left, int right, int search){

        if (right >= left){
            int mid = left + ((right - left) / 2);

            if (arr[mid] == search){
                return mid;
            }

            if (arr[mid] > search){
                return binarySearch(arr, left, mid-1, search);
            }

            return binarySearch(arr, mid+1, right, search);
        }

        return -1;
    }

    public static int binarySearchLinear(int[] arr, int left, int right, int search){
        while (left <= right){
            int mid = left + ((right - left) / 2);

            if (arr[mid] == search){
                return mid;
            }

            if (arr[mid] > search){
                right = mid - 1;
            } else {
                left = mid +1;
            }
        }
        return -1;
    }

    public static int multiBase(int input){
        if (input == 0) return 0;
        if (input == 1) return 1;

        return multiBase(input - 1) + multiBase(input - 2);
    }

    public static void mergeSort(String[] arr, int n){
        if (n < 2) return;

        int mid = n / 2;
        String[] left = new String[mid];
        String[] right = new String[n-mid];

        System.arraycopy(arr, 0, left, 0, mid);

        for (int i = mid; i < n; i++){
            right[i - mid] = arr[i];
        }

        mergeSort(left, mid);
        mergeSort(right, n-mid);

        merge(arr, left, right, mid, n-mid);
    }

    public static void merge(String[] arr, String[] leftSeg, String[] rightSeg, int left, int right){

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < left && j < right){
            if (leftSeg[i].compareTo(rightSeg[j]) < 0){
                arr[k++] = leftSeg[i++];
            } else {
                arr[k++] = rightSeg[j++];
            }
        }

        while(i < left){
            arr[k++] = leftSeg[i++];
        }

        while(j < right){
            arr[k++] = rightSeg[j++];
        }
    }

    public static void mergeSort(ArrayList<String> arr, int n){
        if (n < 2) return;

        int mid = n / 2;
        ArrayList<String> left = new ArrayList<>();
        ArrayList<String> right = new ArrayList<>();

        for (int i = 0; i < mid; i++){
            left.add(arr.get(i));
        }

        for (int i = mid; i < n; i++){
            right.add(arr.get(i));
        }

        mergeSort(left, mid);
        mergeSort(right, n-mid);

        merge(arr, left, right, mid, n-mid);
    }

    public static void merge(ArrayList<String> arr, ArrayList<String> leftSeg, ArrayList<String> rightSeg, int left, int right){

        int i = 0;
        int j = 0;
        int k = 0;

        while (i < left && j < right){
            if (leftSeg.get(i).compareTo(rightSeg.get(j)) <= 0){
                arr.set(k++, leftSeg.get(i++));
            } else {
                arr.set(k++, rightSeg.get(j++));
            }
        }

        while(i < left){
            arr.set(k++, leftSeg.get(i++));
        }

        while(j < right){
            arr.set(k++, rightSeg.get(j++));
        }
    }

    public static void test(){
        int[] testNums = new int[]{8,9,6,5,7,4,5,8,5,10,15,20,55};
        ArrayList<String> testWords = new ArrayList<>();

        testWords.add("c");
        testWords.add("a");
        testWords.add("abc");
        testWords.add("b");

        String[] testWords2 = new String[]{ "c", "a", "abc", "b" };

        mergeSort(testWords, testWords.size());
        System.out.println(testWords);

        mergeSort(testWords2, testWords2.length);
        System.out.println(Arrays.toString(testWords2));

        System.out.println(binarySearch(testNums, 0, testNums.length, 20));
        System.out.println(binarySearchLinear(testNums, 0, testNums.length, 20));

    }
}
