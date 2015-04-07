/**
  *
  * http://www.careercup.com/question?id=5092486548553728
  *
  * Given two strings, return boolean True/False, if they are only one edit apart.
  * Edit can be insert/delete/update of only one character in the string. Eg: 
  * 
  * -True 
  * xyz,xz 
  * xyz, xyk 
  * xy, xyz 
  *
  * -False 
  * xyz, xyz 
  * xyz,xzy 
  * x, xyz
  */

import java.util.LinkedList;

public final class DiffByOneChar {
    public static boolean diffByOne(String s1, String s2) {
        if (s1 == null || s2 == null) {
            return false;
        }

        if (s1.equals(s2)) {
            return false;
        }

        if (Math.abs(s1.length() - s2.length()) > 1) {
            return false;
        }

        String shorter, longer;

        if (s1.length() <= s2.length()) {
            shorter = s1;
            longer  = s2;
        } else {
            shorter = s2;
            longer  = s1;
        }

        final int minLength = shorter.length();

        int index = 0;

        while (index < minLength) {
            if (shorter.charAt(index) != longer.charAt(index) &&
                index + 1 < minLength) { // last char different
                return longer.substring(index+1).equals(shorter.substring(index));
            }
            ++index;
        }
        return true;
    }

    private static void runTest(String testcase) {
        String[] split = testcase.split(",");
        String  s1 = split[0], s2 = split[1];

        System.out.println(s1);
        System.out.println(s2);
        System.out.println("-> " + diffByOne(s1, s2));
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedList<String> testcases = new LinkedList<String>();

        String testcase01 = "xyz,xz";
        String testcase02 = "xyz,xyk";
        String testcase03 = "xy,xyz";
        String testcase04 = "xyz,xyz";
        String testcase05 = "xyz,xzy";
        String testcase06 = "x,xyz";
        String testcase11 = "abcd,abc";
        String testcase12 = "xyzkqow,xyzbmqow";

        testcases.add(testcase01);
        testcases.add(testcase02);
        testcases.add(testcase03);
        testcases.add(testcase04);
        testcases.add(testcase05);
        testcases.add(testcase06);

        testcases.add(testcase11);
        testcases.add(testcase12);

        for (String s: testcases) {
            runTest(s);
        }

    }
}
