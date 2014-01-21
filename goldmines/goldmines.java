/* IMPORTANT: class must not be public. */


import java.io.BufferedReader;
import java.io.InputStreamReader;


class GoldMines {
    public static void main(String args[] ) throws Exception {

        mine();

    }

    private static void mine() throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        long[][] sum_grid;

        {
            int[] dim = convertStringToInt(line, 0);
            sum_grid = new long[dim[0]][dim[1]];


            for (int i = 0; i < dim[0]; i++) {
                line = br.readLine();
                int[] row_gold = convertStringToInt(line, 0);

                long sum = 0;

                for (int j = 0; j < dim[1]; j++) {
                    sum += row_gold[j];

                    if (i == 0) {
                        sum_grid[i][j] = sum;
                    } else {
                        sum_grid[i][j] = sum_grid[i-1][j] + sum;
                    }
                }
                
            }
        }

        // read the number of queries
        line = br.readLine();
        int num_queries = Integer.parseInt(line);
        long[] results = new long[num_queries];

        for (int i = 0; i < num_queries; i++) {
            line = br.readLine();
            int[] query = convertStringToInt(line, -1);

            long all_sum = sum_grid[query[2]][query[3]];
            if (query[0] != 0 && query[2] != 0)
                all_sum -= sum_grid[query[0]-1][query[3]];

            if (query[1] != 0)
                all_sum -= sum_grid[query[2]][query[1]-1];

            if (query[0] != 0 && query[1] != 0)
              all_sum += sum_grid[query[0]-1][query[1]-1];

            results[i] = all_sum;
        }


        int size = results.length;
        for (int i = 0; i < size; i++) {
            System.out.print(results[i]);
            System.out.print("\n");
        }
    }

    private static int[] convertStringToInt(String str, int offset) {
        String strarray[] = str.split(" ");
        int[] intarray = new int[strarray.length];

        int size = strarray.length;

        for (int i = 0; i < size; i++) {
            intarray[i] = Integer.parseInt(strarray[i])  + offset ;
        }
        return intarray;
    }


}

// Flags: -Xmx1024M -Xms128M