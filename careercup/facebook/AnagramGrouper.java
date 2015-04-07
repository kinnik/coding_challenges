/**
 * http://www.careercup.com/question?id=5723872416497664
 * 
 * Given a list of strings, return a list of lists of strings that groups all anagrams. 
 *
 * Ex. given {trees, bike, cars, steer, arcs} 
 * return { {cars, arcs}, {bike}, {trees, steer} } 

 * m = # of words 
 * n = length of longest word 
 */

import java.util.*;

public class AnagramGrouper {

    public static String[][] group(String[] words) {
        HashMap<String, LinkedList<String>> anagrams = new HashMap<String, LinkedList<String>>();

        for (String s: words) {
            String key = sortString(s);
            if (anagrams.containsKey(key)) {
                anagrams.get(key).add(s);
            } else {
                LinkedList<String> list = new LinkedList<String>();
                list.add(s);
                anagrams.put(key, list);
            }
        }

        String[][] grouped = new String[anagrams.keySet().size()][];
        int groupIndex   = 0;

        for (String key: anagrams.keySet()) {
            int size = anagrams.get(key).size();
            String[] onegroup = anagrams.get(key).toArray(new String[size]);
            grouped[groupIndex++] = onegroup;
        }

        return grouped;
    }

    private static String sortString(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public static void main(String[] args) {
        String[] input = {"trees", "bike", "cars", "steer", "arcs"};
        String[][] output = group(input);

        System.out.print("{ ");
        for (String[] group: output) {
            System.out.print("{ ");
            for (String word: group) {
                System.out.print(word + " ");
            }
            System.out.print(" }");
        }
        System.out.println(" }");
    }


}
