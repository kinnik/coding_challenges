/*
 * http://www.careercup.com/question?id=6009238532915200
 *
 * write an algorithm to decide weather a string is a palindrome. 
 * Ignore any non-letter characters in the the string. 
 * Ignore capital/lower case. 
 * Space complexity O(1) 

 * for example, the following should return true: 
     A man, a plan, a canal -- Panama!
 */


public class Palindrome {
    public static boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return false;
        }

        int i = 0, j = s.length() - 1;

        while (i < j) {
            while(!isAlphabet(s.charAt(i))) {
                ++i;
                if (i >= j) {
                    break;
                }
            }

            while (!isAlphabet(s.charAt(j))) {
                --j;
                if (j <= i) {
                    break;
                }
            }

            if (isSameLetter(s.charAt(i), s.charAt(j))) {
                ++i;
                --j;
            } else {
                return false;
            }
        }

        return true;
    }

    private static boolean isAlphabet(char c) {
        return (c >= 'a' && c <= 'z') ||
               (c >= 'A' && c <= 'Z');
    }

    private static boolean isSameLetter(char c1, char c2) {
        char c1l, c2l;

        if (Character.isUpperCase(c1)) {
            c1l = Character.toLowerCase(c1);
        } else {
            c1l = c1;
        }

        if (Character.isUpperCase(c2)) {
            c2l = Character.toLowerCase(c2);
        } else {
            c2l = c2;
        }

        return c1l == c2l;
    }

    private static void runTests(String s, boolean expectedResult) {
        System.out.println(s);
        System.out.println("-> " + isPalindrome(s));
        System.out.println();
        assert(isPalindrome(s) == expectedResult);
    }

    public static void main(String[] args) {
        String  testcase;
        boolean expectedResult;

        testcase = "A man, a plan, a canal -- Panama!";
        expectedResult = true;

        runTests(testcase, expectedResult);
    }
}
