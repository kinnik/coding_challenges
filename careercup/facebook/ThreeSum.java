/** http://www.careercup.com/question?id=5736292639834112
  * Given an array of integers, return true if there're 3 numbers 
  * adding up to zero (repetitions are allowed) 
  * {10, -2, -1, 3} -> true 
  * {10, -2, 1} -> true -2 + 1 +1 =0
  */

import java.util.*;

public class ThreeSum {
    public static boolean threeSumExists(int[] a) {
        HashSet<Integer> set = new HashSet<Integer>();

        for (int i: a) {
            set.add(i);
        }

        for (int i = 0; i < a.length-1; ++i) {
            for (int j = 0; j < a.length; ++j) {
                int pairSum = a[i] + a[j];
                if (set.contains(-pairSum)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] a1 = {10, -2, -1, 3};
        int[] a2 = {10, -2, 1};
        int[] a3 = {10, -2, 2};

        System.out.println("a1: " + threeSumExists(a1));
        System.out.println("a2: " + threeSumExists(a2));
        System.out.println("a3: " + threeSumExists(a3));
    }
}
