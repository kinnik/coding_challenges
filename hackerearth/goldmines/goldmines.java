/* IMPORTANT: class must not be public. */


import java.io.BufferedReader;
import java.io.InputStreamReader;


class GoldMines {
    public static void main(String args[] ) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();

        long[][] sum_grid;

        {
            final int[] dim = convertStringToInt(line, 0);
            sum_grid = new long[dim[0]][dim[1]];


            for (int i = 0; i < dim[0]; i++) {
                line = br.readLine();
                final int[] row_gold = convertStringToInt(line, 0);

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
        final int num_queries = Integer.parseInt(line);
        
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < num_queries; i++) {
            line = br.readLine();
            final int[] query = convertStringToInt(line, -1);

            long all_sum = sum_grid[query[2]][query[3]];
            if (query[0] != 0 && query[2] != 0)
                all_sum -= sum_grid[query[0]-1][query[3]];

            if (query[1] != 0)
                all_sum -= sum_grid[query[2]][query[1]-1];

            if (query[0] != 0 && query[1] != 0)
              all_sum += sum_grid[query[0]-1][query[1]-1];

            sb.append(all_sum + "\n");
        }

        System.out.println(sb.toString());
    }

    private static int[] convertStringToInt(final String str, final int offset) {
        final String strarray[] = str.split(" ");
        int[] intarray = new int[strarray.length];

        final int size = strarray.length;

        for (int i = 0; i < size; i++) {
            intarray[i] = Integer.parseInt(strarray[i])  + offset ;
        }
        return intarray;
    }


}

// Flags: -Xmx1024M -Xms128M