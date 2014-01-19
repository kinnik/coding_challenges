#include <stdio.h>

int main()
{
  // declarations & initialisations

  unsigned short num_rows, num_cols;
  unsigned int num_queries;
  unsigned int num_executions = 0;

  // read the size
  scanf("%hu%hu", &num_rows, &num_cols);

  // build up the accumulated sums
  unsigned long sum_grid[num_rows][num_cols];
  for (unsigned short i=0; i<num_rows; ++i) 
  {
    unsigned long sum = 0;

    for (unsigned short j=0; j<num_cols; ++j) 
    {
      unsigned int gold = 0;
      scanf("%u", &gold);
      sum += gold;

      if (i == 0)
        sum_grid[i][j] = sum;
      else
        sum_grid[i][j] = sum_grid[i-1][j] + sum;
    }
  }

  // read the number of queries
  scanf("%u", &num_queries);

  // process the queries
  while (num_executions < num_queries)
  {
    unsigned short x1, y1, x2, y2;
    // read and process the queries
    scanf("%hu%hu%hu%hu", &x1, &y1, &x2, &y2);
      // remember, the grid is zero-indexed
    --x1;
    --y1;
    --x2;
    --y2;

    unsigned long total_gold = sum_grid[x2][y2];

    if (x1 != 0 && x2 != 0) {
      total_gold -= sum_grid[x1-1][y2];
    }
    if (y1 != 0) {
      total_gold -= sum_grid[x2][y1-1];
    }
    if (x1 != 0 && y1 != 0) {
      total_gold += sum_grid[x1-1][y1-1];
    }
    ++num_executions;

    fprintf(stdout, "%lu\n", total_gold);
    fflush(stdout);

  }

  return 0;
}

// Compiler flags
// -std=gnu99 -w -O2 -fomit-frame-pointer -lm
