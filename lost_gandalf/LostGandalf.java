import java.util.Random;
import java.util.Scanner;
import java.util.Arrays;

class LostGandalf
{

    public static void main(String args[]) throws Exception
    {
        long startTime = System.nanoTime();

        Scanner in = new Scanner(System.in);
        int num_words = in.nextInt();

        String[] words = new String[num_words];

        for (int i = 0; i < num_words; ++i)
        {
            words[i] = in.next();
        }

        int num_queries = in.nextInt();
        String[] queries = new String[num_queries];

        for (int i = 0; i < num_queries; ++i)
        {
            queries[i] = in.next();
        }

        in = null;

        sort(words);

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < num_queries; ++i)
        {
            int position = rank(queries[i], words);

            if (position > -1)  sb.append((position+1) + "\n");
            else                sb.append(position + "\n");
        }

        System.out.print(sb.toString());

        System.out.printf("Time taken: %d ms%n", (System.nanoTime() - startTime) / 1000000);
    }


    private static int rank(String key, String[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            if      (key.compareTo(a[mid]) < 0) hi = mid - 1;
            else if (key.compareTo(a[mid]) > 0) lo = mid + 1;
            else                                return mid;
        }
        return -1;
    }


    private static final int CUTOFF =  15;   // cutoff to insertion sort

    // sort the array a[] of strings
    public static void sort(String[] a) {
        StdRandom.shuffle(a);
        sort(a, 0, a.length-1, 0);
        assert isSorted(a);
    }

    // return the dth character of s, -1 if d = length of s
    private static int charAt(String s, int d) { 
        assert d >= 0 && d <= s.length();
        if (d == s.length()) return -1;
        return s.charAt(d);
    }


        // 3-way string quicksort a[lo..hi] starting at dth character
        private static void sort(String[] a, int lo, int hi, int d) { 

            // cutoff to insertion sort for small subarrays
            if (hi <= lo + CUTOFF) {
                insertion(a, lo, hi, d);
                return;
            }

            int lt = lo, gt = hi;
            int v = charAt(a[lo], d);
            int i = lo + 1;
            while (i <= gt) {
                int t = charAt(a[i], d);
                if      (t < v) exch(a, lt++, i++);
                else if (t > v) exch(a, i, gt--);
                else              i++;
            }

            // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi]. 
            sort(a, lo, lt-1, d);
            if (v >= 0) sort(a, lt, gt, d+1);
            sort(a, gt+1, hi, d);
        }

        // sort from a[lo] to a[hi], starting at the dth character
        private static void insertion(String[] a, int lo, int hi, int d) {
            for (int i = lo; i <= hi; i++)
                for (int j = i; j > lo && less(a[j], a[j-1], d); j--)
                    exch(a, j, j-1);
        }

        // exchange a[i] and a[j]
        private static void exch(String[] a, int i, int j) {
            String temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }

        // is v less than w, starting at character d
        // DEPRECATED BECAUSE OF SLOW SUBSTRING EXTRACTION IN JAVA 7
        // private static boolean less(String v, String w, int d) {
        //    assert v.substring(0, d).equals(w.substring(0, d));
        //    return v.substring(d).compareTo(w.substring(d)) < 0; 
        // }

        // is v less than w, starting at character d
        private static boolean less(String v, String w, int d) {
            assert v.substring(0, d).equals(w.substring(0, d));
            for (int i = d; i < Math.min(v.length(), w.length()); i++) {
                if (v.charAt(i) < w.charAt(i)) return true;
                if (v.charAt(i) > w.charAt(i)) return false;
            }
            return v.length() < w.length();
        }

        // is the array sorted
        private static boolean isSorted(String[] a) {
            for (int i = 1; i < a.length; i++)
                if (a[i].compareTo(a[i-1]) < 0) return false;
            return true;
        }



    /**
     *  <i>Standard random</i>. This class provides methods for generating
     *  random number from various distributions.
     *  <p>
     *  For additional documentation, see <a href="http://introcs.cs.princeton.edu/22library">Section 2.2</a> of
     *  <i>Introduction to Programming in Java: An Interdisciplinary Approach</i> by Robert Sedgewick and Kevin Wayne.
     *
     *  @author Robert Sedgewick
     *  @author Kevin Wayne
     */
    static final class StdRandom 
    {

        private static Random random;    // pseudo-random number generator
        private static long seed;        // pseudo-random number generator seed

        // static initializer
        static {
            // this is how the seed was set in Java 1.4
            seed = System.currentTimeMillis();
            random = new Random(seed);
        }

        // don't instantiate
        private StdRandom() { }

        /**
         * Sets the seed of the psedurandom number generator.
         */
        public static void setSeed(long s) {
            seed   = s;
            random = new Random(seed);
        }

        /**
         * Returns the seed of the psedurandom number generator.
         */
        public static long getSeed() {
            return seed;
        }

        /**
         * Return real number uniformly in [0, 1).
         */
        public static double uniform() {
            return random.nextDouble();
        }

        /**
         * Returns an integer uniformly between 0 (inclusive) and N (exclusive).
         * @throws IllegalArgumentException if <tt>N <= 0</tt>
         */
        public static int uniform(int N) {
            if (N <= 0) throw new IllegalArgumentException("Parameter N must be positive");
            return random.nextInt(N);
        }

        ///////////////////////////////////////////////////////////////////////////
        //  STATIC METHODS BELOW RELY ON JAVA.UTIL.RANDOM ONLY INDIRECTLY VIA
        //  THE STATIC METHODS ABOVE.
        ///////////////////////////////////////////////////////////////////////////

        /**
         * Returns a real number uniformly in [0, 1).
         * @deprecated clearer to use {@link #uniform()}
         */
        public static double random() {
            return uniform();
        }

        /**
         * Returns an integer uniformly in [a, b).
         * @throws IllegalArgumentException if <tt>b <= a</tt>
         * @throws IllegalArgumentException if <tt>b - a >= Integer.MAX_VALUE</tt>
         */
        public static int uniform(int a, int b) {
            if (b <= a) throw new IllegalArgumentException("Invalid range");
            if ((long) b - a >= Integer.MAX_VALUE) throw new IllegalArgumentException("Invalid range");
            return a + uniform(b - a);
        }

        /**
         * Returns a real number uniformly in [a, b).
         * @throws IllegalArgumentException unless <tt>a < b</tt>
         */
        public static double uniform(double a, double b) {
            if (!(a < b)) throw new IllegalArgumentException("Invalid range");
            return a + uniform() * (b-a);
        }

        /**
         * Returns a boolean, which is true with probability p, and false otherwise.
         * @throws IllegalArgumentException unless <tt>p >= 0.0</tt> and <tt>p <= 1.0</tt>
         */
        public static boolean bernoulli(double p) {
            if (!(p >= 0.0 && p <= 1.0))
                throw new IllegalArgumentException("Probability must be between 0.0 and 1.0");
            return uniform() < p;
        }

        /**
         * Returns a boolean, which is true with probability .5, and false otherwise.
         */
        public static boolean bernoulli() {
            return bernoulli(0.5);
        }

        /**
         * Returns a real number with a standard Gaussian distribution.
         */
        public static double gaussian() {
            // use the polar form of the Box-Muller transform
            double r, x, y;
            do {
                x = uniform(-1.0, 1.0);
                y = uniform(-1.0, 1.0);
                r = x*x + y*y;
            } while (r >= 1 || r == 0);
            return x * Math.sqrt(-2 * Math.log(r) / r);

            // Remark:  y * Math.sqrt(-2 * Math.log(r) / r)
            // is an independent random gaussian
        }

        /**
         * Returns a real number from a gaussian distribution with given mean and stddev
         */
        public static double gaussian(double mean, double stddev) {
            return mean + stddev * gaussian();
        }

        /**
         * Returns an integer with a geometric distribution with mean 1/p.
         * @throws IllegalArgumentException unless <tt>p >= 0.0</tt> and <tt>p <= 1.0</tt>
         */
        public static int geometric(double p) {
            if (!(p >= 0.0 && p <= 1.0))
                throw new IllegalArgumentException("Probability must be between 0.0 and 1.0");
            // using algorithm given by Knuth
            return (int) Math.ceil(Math.log(uniform()) / Math.log(1.0 - p));
        }

        /**
         * Return an integer with a Poisson distribution with mean lambda.
         * @throws IllegalArgumentException unless <tt>lambda > 0.0</tt> and not infinite
         */
        public static int poisson(double lambda) {
            if (!(lambda > 0.0))
                throw new IllegalArgumentException("Parameter lambda must be positive");
            if (Double.isInfinite(lambda))
                throw new IllegalArgumentException("Parameter lambda must not be infinite");
            // using algorithm given by Knuth
            // see http://en.wikipedia.org/wiki/Poisson_distribution
            int k = 0;
            double p = 1.0;
            double L = Math.exp(-lambda);
            do {
                k++;
                p *= uniform();
            } while (p >= L);
            return k-1;
        }

        /**
         * Returns a real number with a Pareto distribution with parameter alpha.
         * @throws IllegalArgumentException unless <tt>alpha > 0.0</tt>
         */
        public static double pareto(double alpha) {
            if (!(alpha > 0.0))
                throw new IllegalArgumentException("Shape parameter alpha must be positive");
            return Math.pow(1 - uniform(), -1.0/alpha) - 1.0;
        }

        /**
         * Returns a real number with a Cauchy distribution.
         */
        public static double cauchy() {
            return Math.tan(Math.PI * (uniform() - 0.5));
        }

        /**
         * Returns a number from a discrete distribution: i with probability a[i].
         * throws IllegalArgumentException if sum of array entries is not (very nearly) equal to <tt>1.0</tt>
         * throws IllegalArgumentException unless <tt>a[i] >= 0.0</tt> for each index <tt>i</tt>
         */
        public static int discrete(double[] a) {
            double EPSILON = 1E-14;
            double sum = 0.0;
            for (int i = 0; i < a.length; i++) {
                if (!(a[i] >= 0.0)) throw new IllegalArgumentException("array entry " + i + " must be nonnegative: " + a[i]);
                sum = sum + a[i];
            }
            if (sum > 1.0 + EPSILON || sum < 1.0 - EPSILON)
                throw new IllegalArgumentException("sum of array entries does not approximately equal 1.0: " + sum);

            // the for loop may not return a value when both r is (nearly) 1.0 and when the
            // cumulative sum is less than 1.0 (as a result of floating-point roundoff error)
            while (true) {
                double r = uniform();
                sum = 0.0;
                for (int i = 0; i < a.length; i++) {
                    sum = sum + a[i];
                    if (sum > r) return i;
                }
            }
        }

        /**
         * Returns a real number from an exponential distribution with rate lambda.
         * @throws IllegalArgumentException unless <tt>lambda > 0.0</tt>
         */
        public static double exp(double lambda) {
            if (!(lambda > 0.0))
                throw new IllegalArgumentException("Rate lambda must be positive");
            return -Math.log(1 - uniform()) / lambda;
        }

        /**
         * Rearrange the elements of an array in random order.
         */
        public static void shuffle(Object[] a) {
            int N = a.length;
            for (int i = 0; i < N; i++) {
                int r = i + uniform(N-i);     // between i and N-1
                Object temp = a[i];
                a[i] = a[r];
                a[r] = temp;
            }
        }

        /**
         * Rearrange the elements of a double array in random order.
         */
        public static void shuffle(double[] a) {
            int N = a.length;
            for (int i = 0; i < N; i++) {
                int r = i + uniform(N-i);     // between i and N-1
                double temp = a[i];
                a[i] = a[r];
                a[r] = temp;
            }
        }

        /**
         * Rearrange the elements of an int array in random order.
         */
        public static void shuffle(int[] a) {
            int N = a.length;
            for (int i = 0; i < N; i++) {
                int r = i + uniform(N-i);     // between i and N-1
                int temp = a[i];
                a[i] = a[r];
                a[r] = temp;
            }
        }


        /**
         * Rearrange the elements of the subarray a[lo..hi] in random order.
         */
        public static void shuffle(Object[] a, int lo, int hi) {
            if (lo < 0 || lo > hi || hi >= a.length) {
                throw new IndexOutOfBoundsException("Illegal subarray range");
            }
            for (int i = lo; i <= hi; i++) {
                int r = i + uniform(hi-i+1);     // between i and hi
                Object temp = a[i];
                a[i] = a[r];
                a[r] = temp;
            }
        }

        /**
         * Rearrange the elements of the subarray a[lo..hi] in random order.
         */
        public static void shuffle(double[] a, int lo, int hi) {
            if (lo < 0 || lo > hi || hi >= a.length) {
                throw new IndexOutOfBoundsException("Illegal subarray range");
            }
            for (int i = lo; i <= hi; i++) {
                int r = i + uniform(hi-i+1);     // between i and hi
                double temp = a[i];
                a[i] = a[r];
                a[r] = temp;
            }
        }

        /**
         * Rearrange the elements of the subarray a[lo..hi] in random order.
         */
        public static void shuffle(int[] a, int lo, int hi) {
            if (lo < 0 || lo > hi || hi >= a.length) {
                throw new IndexOutOfBoundsException("Illegal subarray range");
            }
            for (int i = lo; i <= hi; i++) {
                int r = i + uniform(hi-i+1);     // between i and hi
                int temp = a[i];
                a[i] = a[r];
                a[r] = temp;
            }
        }

        /**
         * Unit test.
         */
        /*
        public static void main(String[] args) {
            int N = Integer.parseInt(args[0]);
            if (args.length == 2) StdRandom.setSeed(Long.parseLong(args[1]));
            double[] t = { .5, .3, .1, .1 };

            StdOut.println("seed = " + StdRandom.getSeed());
            for (int i = 0; i < N; i++) {
                StdOut.printf("%2d "  , uniform(100));
                StdOut.printf("%8.5f ", uniform(10.0, 99.0));
                StdOut.printf("%5b "  , bernoulli(.5));
                StdOut.printf("%7.5f ", gaussian(9.0, .2));
                StdOut.printf("%2d "  , discrete(t));
                StdOut.println();
            }

            String[] a = "A B C D E F G".split(" ");
            for (String s : a)
                StdOut.print(s + " ");
            StdOut.println();
        }
        */
    }


}

