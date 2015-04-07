/**
 * Given an array of ages (integers) sorted lowest to highest,
 * output the number of occurrences for each age. 
 * For instance: 
 * [8,8,8,9,9,11,15,16,16,16] 
 * should output something like: 
 * 8: 3 
 * 9: 2 
 * 11: 1 
 * 15: 1 
 * 16: 3 

 * This should be done in less than O(n).
 *
 * http://www.geeksforgeeks.org/count-number-of-occurrences-in-a-sorted-array/
 */

 public class ModifiedBinarySearch {
     private static int findFirst(int[] a, int key, int lo, int hi) {
         while (lo <= hi) {
             int mid = lo + (hi - lo) / 2;

             if (a[mid] == key) {
                 if (mid == 0 || a[mid-1] < a[mid]) {
                     return mid;
                 }
                 hi = mid - 1;
             } else if (a[mid] < key) {
                 lo = mid + 1;
             } else {
                 hi = mid - 1;
             }
         }
         return -1;
     }

     private static int findLast(int[] a, int key, int lo, int hi) {
         while (lo <= hi) {
             int mid = lo + (hi - lo) / 2;

             if (a[mid] == key) {
                 if (mid == a.length - 1 || a[mid+1] > a[mid]) {
                    return mid;
                 }
                 lo = mid + 1;
             } else if (a[mid] < key) {
                 lo = mid + 1;
             } else {
                 hi = mid - 1;
             }
         }
         return -1;
     }

     private static int count(int[] a, int key) {
         int firstPos = findFirst(a, key, 0, a.length-1);

         if (firstPos == -1) {
             return 0;
         }

         int lastPos = findLast(a, key, firstPos, a.length-1);

         return lastPos - firstPos + 1;
     }

    public static void printFrequencies(int[] a) {
        int   i = 0;

        while (i < a.length) {
            int k = a[i];
            int frequency = count(a, k);
            System.out.println(k + ": " + frequency);
            i = i+frequency;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] a1 = {1, 2, 2, 3, 3, 3, 3};
        printFrequencies(a1);

        int[] a2 = {1, 1, 2, 3, 3, 3, 3, 4, 4, 5, 6, 6};
        printFrequencies(a2);

        int[] a3 = {1, 2, 3, 4, 5, 6};
        printFrequencies(a3);

        int[] a4 = {8,8,8,9,9,11,15,16,16,16};
        printFrequencies(a4);
    }
 }
