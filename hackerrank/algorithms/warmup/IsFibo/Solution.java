/**
 * https://www.hackerrank.com/challenges/is-fibo
 */

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    
    private static long lastFibNum, secondLastFibNum, lastFibTerm;    
    private static HashMap<Long, Long> fibTable;
    
    static {
        // we ignore the Fib(1) because if the input query is 1, it will be covered by Fib(2).
        secondLastFibNum = 1;
        lastFibNum = 2;
        lastFibTerm = 1;    // initialise it to 1
        
        fibTable = new HashMap<Long, Long>();
        
        fibTable.put(secondLastFibNum, ++lastFibTerm);
        fibTable.put(lastFibNum, ++lastFibTerm);
    }
    
    public static boolean isFibo(long num) {
        // base case or it was previously calculated and cached
        if (fibTable.containsKey(num)) return true;
        
        // not in the already-calculated range
        if (num < lastFibNum)          return false;
        
        while (lastFibNum < num) {
            final long fibNum = secondLastFibNum + lastFibNum;
            
            fibTable.put(fibNum, ++lastFibTerm);
            
            secondLastFibNum = lastFibNum;
            lastFibNum = fibNum;
            
            if (lastFibNum == num)      return true;
        }
        
        return false;
    }
    
    private static void printFibo(boolean fibo) {
        if (fibo)   System.out.println("IsFibo");
        else        System.out.println("IsNotFibo");
    }

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner = new Scanner(System.in);
        
        final long numTestCases = scanner.nextInt();
        
        for (long i = 0; i < numTestCases; ++i) {
            final long num = scanner.nextLong();
            printFibo(isFibo(num));
        }
    }
}
