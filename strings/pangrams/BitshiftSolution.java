import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
        private static final int R = 26;
            
                public static boolean isPangram(String s) {
                            if (s.length() < 1 || s.length() > 1000) {
                                            throw new IllegalArgumentException();
                                                    }
                                                            
                                                                    int indicators = 0;
                                                                            
                                                                                    for (int i = 0; i < s.length(); ++i) {
                                                                                                    int asciiValue = (int) s.charAt(i);
                                                                                                                
                                                                                                                            if (asciiValue >= 65 && asciiValue <= 90) {
                                                                                                                                                int offset = asciiValue - 65;
                                                                                                                                                                indicators |= (1 << offset);
                                                                                                                                                                            } else if (asciiValue >= 97 && asciiValue <= 122) {
                                                                                                                                                                                                int offset = asciiValue - 97;
                                                                                                                                                                                                                indicators |= (1 << offset);
                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                            
                                                                                                                                                                                                                                                    for (int i = 0; i < R; ++i) {
                                                                                                                                                                                                                                                                    if ((indicators & (1 << i)) == 0) {
                                                                                                                                                                                                                                                                                        return false;
                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                    
                                                                                                                                                                                                                                                                                                                            return true;
                                                                                                                                                                                                                                                                                                                                }

                                                                                                                                                                                                                                                                                                                                    public static void main(String[] args) {
                                                                                                                                                                                                                                                                                                                                                /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
                                                                                                                                                                                                                                                                                                                                                        Scanner scanner = new Scanner(System.in);
                                                                                                                                                                                                                                                                                                                                                                String  input   = scanner.nextLine();
                                                                                                                                                                                                                                                                                                                                                                        if (isPangram(input)) {
                                                                                                                                                                                                                                                                                                                                                                                        System.out.println("pangram");
                                                                                                                                                                                                                                                                                                                                                                                                } else {
                                                                                                                                                                                                                                                                                                                                                                                                                System.out.println("not pangram");
                                                                                                                                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                                                                                                                            }
}
