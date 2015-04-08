/**
 * http://www.careercup.com/question?id=5186461135536128
 * Given an unsorted array of natural numbers. Where numbers repeat in array. Out put numbers in the order of frequency. Where number of out put is passed as parameter. 
 * For Ex: 
 * Array -> [0, 0, 100, 3, 5, 4, 6, 4, 2, 100, 2, 100] 
 * n -> 2 
 * Out put -> [100, 0] or [100, 2] 
 * Since 100 is repeated 3 times and 0, 2 is repeated 2 times, out put up to 2 most frequent elements, program should out put either 100, 0 or 100, 2
 */

import java.util.HashMap;

public class FrequencySorter {

    private static NumFreq[] orderByFrequency(int[] a) {
        HashMap<Integer, Integer> frequencies = new HashMap<Integer, Integer>();

        int maxFrequency = Integer.MIN_VALUE;
        for (int i = 0; i < a.length; ++i) {
            int freq = 1;

            if (frequencies.containsKey(a[i])) {
                freq += frequencies.get(a[i]);
            }

            frequencies.put(a[i], freq);

            if (freq > maxFrequency) {
                maxFrequency = freq;
            }
        }

        NumFreq[] numFreq = new NumFreq[frequencies.size()];
        int position = 0;
        for (Integer num: frequencies.keySet()) {
            NumFreq nf = new NumFreq(num, frequencies.get(num));
            System.out.println(nf.number + ", " + nf.frequency);
            numFreq[position++] = nf;
        }

        sort(numFreq, maxFrequency);

        return numFreq;
    }

    private static void sort(NumFreq[] a, int R) {
        final int N = a.length;
        final NumFreq[] aux = new NumFreq[N];
        final int[] count = new int[R+1];

        // count frequencies of the given pair
        for (int i = 0; i < N; ++i) {
            count[a[i].frequency]++;
        }

        // compute cumulates
        for (int r = 0; r < R; ++r) {
            count[r+1] += count[r];
        }

        // move data
        for (int i = 0; i < N; ++i) {
            aux[count[a[i].frequency-1]++] = a[i];
        }

        // copy back
        for (int i = 0; i < N; ++i) {
            a[i] = aux[i];
        }
    }

    private static class NumFreq {
        int number, frequency;

        private NumFreq(int number, int frequency) {
            this.number = number;
            this.frequency = frequency;
        }
    }

    public static void main(String[] args) {
        NumFreq[] numFreq = orderByFrequency(new int[]{0, 0, 100, 3, 5, 4, 6, 4, 2, 100, 2, 100});
        final int numOutput = 4;

        System.out.print("[");
        for (int i = 0; i < numOutput; ++i) {
            NumFreq nf = numFreq[numFreq.length-1-i];
            System.out.print(nf.number + ", ");
        }
        System.out.println("]");
    }
}
