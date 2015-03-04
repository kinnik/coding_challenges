/* http://www.careercup.com/question?id=6305076727513088
 * Question: Given a sequence of positive integers A and an integer T,
 * return whether there is a continuous sequence of A that sums up to exactly T 
 * Example 
    [23, 5, 4, 7, 2, 11], 20. Return True because 7 + 2 + 11 = 20 
    [1, 3, 5, 23, 2], 8. Return True because 3 + 5 = 8 
    [1, 3, 5, 23, 2], 7 Return False because no sequence in this array adds up to 7
 */


public class ContinuousSequenceSum {
    public static boolean sumsTo(int[] a, int T) {
        int wL = 0, wR = 0, sum = a[0];

        while (wR < a.length) {
            if (sum < T) {
                if (wR < a.length-1) ++wR;
                else return false;
                sum += a[wR];
            }

            if (sum==T) return true;

            if (sum > T) {
                sum -= a[wL];
                ++wL;
            }
        }
        return false;
    }

public static void main(String[] args) {
    int[] a1 = {23, 5, 4, 7, 2, 11};
    int   t1 = 20;
    System.out.println(sumsTo(a1, t1)); // true

    int[] a2 = {1, 3, 5, 23, 2};
    int   t2 = 8;
    System.out.println(sumsTo(a2, t2)); // true

    int[] a3 = {1, 3, 5, 23, 2};
    int   t3 = 7;
    System.out.println(sumsTo(a3, t3)); // false

    int[] b1 = {23, 5, 12, 4, 7, 9};
    int   u1 = 20;
    System.out.println(sumsTo(b1, u1)); // true

    int[] b2 = {1, 2, 23, 24, 20, 4, 7, 9};
    int   u2 = 20;
    System.out.println(sumsTo(b2, u2)); // true

    int[] b3 = {25, 23, 24, 20, 4, 7, 9};
    int   u3 = 20;
    System.out.println(sumsTo(b3, u3)); // true

    int[] b4 = {1, 2, 3, 4, 20, 21, 5, 9, 6};
    int   u4 = 20;
    System.out.println(sumsTo(b4, u4)); // true
}
}
