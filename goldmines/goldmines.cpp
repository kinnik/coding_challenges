#include <stdio.h>
#include <time.h>
#include <vector>

using namespace std;

int main()
{
  // declarations & initialisations
  time_t start = time(NULL);
  int sec;

  unsigned short num_rows, num_cols;
  unsigned int num_queries;
  unsigned int num_executions = 0;

  // read the size
  scanf("%hu%hu", &num_rows, &num_cols);

  // build up the accumulated sums
  std::vector<std::vector<unsigned long>> sum_grid(num_rows, std::vector<unsigned long>(num_cols));
  for (unsigned short i=0; i<num_rows; ++i) 
  {
    unsigned long sum = 0;
    std::vector<unsigned long> accumulator(num_cols);

    for (unsigned short j=0; j<num_cols; ++j) 
    {
      unsigned int gold = 0;
      scanf("%u", &gold);
      sum += gold;

      if (i == 0)
        accumulator[j] = sum;
      else
        accumulator[j] = sum_grid[i-1][j] + sum;
    }
    sum_grid[i] = accumulator;
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

  sec = (int) time(NULL) - start;
  fprintf(stdout, "%d seconds\n", sec);

  return 0;
}

// Compile flags
// g++ -std=c++0x -w -O2 -formit-frame-pointer -lm goldmines.cpp