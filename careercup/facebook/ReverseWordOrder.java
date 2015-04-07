/**
 * http://www.careercup.com/question?id=5717567253512192
 *
 * Inplace reverse a sentence 
 *
 * You given a sentence of english words and spaces between them. 
 * Nothing crazy: 
 * 1) no double spaces 
 * 2) no empty words 
 * 3) no spaces at the ends of a sentence

 * void inplace_reverse(char* arr, int length);
 
 * Example: 
 * input "I wish you a merry Christmas" 
 * output "Christmas merry a you wish I" 

 * Constrains: O(1) additional memory
 */

public class ReverseWordOrder {
    public static void inplaceReverse(char[] a) {
        swapOrders(a, 0, a.length-1);
        reverseWords(a);
    }

    private static void swapOrders(char[] a, int start, int end) {
        final int N = end - start + 1;

        if (N == 1) {
            return;
        }

        for (int i = 0; i < N/2; ++i) {
            char   tmp = a[start+i];
            a[start+i] = a[end-i];
            a[end-i]   = tmp;
        }
    }

    private static void reverseWords(char[] a) {
        int  leftPtr = 0;
        int rightPtr = 1;

        while (rightPtr < a.length) {
            if (a[rightPtr] == ' ') {
                swapOrders(a, leftPtr, rightPtr-1);
                leftPtr = rightPtr+1;
            }
            ++rightPtr;
        }
        // the final word
        swapOrders(a, leftPtr, rightPtr-1);
    }

    public static void main(String[] args) {
        String s = "I wish you a merry christmas";
        char[] r = s.toCharArray();
        inplaceReverse(r);
        System.out.println(new String(r));
    }
}
