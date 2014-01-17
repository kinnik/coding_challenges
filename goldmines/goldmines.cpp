#include <stdio.h>
#include <time.h>

using namespace std;

int main()
{
  // declarations & initialisations
  time_t start = time(NULL);
  int sec;

  unsigned short num_rows, num_cols = 0;
  unsigned short num_queries = 0;
  unsigned short num_executions = 0;
  
  unsigned int* gold_data;
  unsigned int** gold;

  register unsigned short row_lo = 0;
  register unsigned short col_lo = 0;
  register unsigned short row_hi = 0;
  register unsigned short col_hi = 0;

  // read rows & columns
  scanf("%hu%hu", &num_rows, &num_cols);

  // declare and initialise the contriner
  gold_data = new unsigned int[num_rows * num_cols];
  gold = new unsigned int*[num_rows];

  for (unsigned short a = 0; a < num_rows; ++a)
    gold[a] = gold_data + num_cols * a;

  // read in the gold
  for (unsigned short i=0; i<num_rows; ++i) {
    for (unsigned short j=0; j<num_cols; ++j) {
      scanf("%u", &gold[i][j]);
    }
  }

  // read the number of queries and size the results vector
  scanf("%hu", &num_queries);
  //results.resize(num_queries);

  while (num_executions < num_queries)
  {
    // read and process the queries
    scanf("%hu%hu%hu%hu", &row_lo, &col_lo, &row_hi, &col_hi);
      // remember, the grid is zero-indexed
    --row_lo;
    --col_lo;
    --row_hi;
    --col_hi;

    register unsigned long total_gold = 0;

    for (unsigned short r=row_lo; r<= row_hi; ++r)
    {
      for (unsigned short c=col_lo; c<= col_hi; ++c) 
      {
         total_gold += gold[r][c];
      }
    }
    fprintf(stdout, "%lu\n", total_gold);
    fflush(stdout);
    ++num_executions;
  }

  sec = (int) time(NULL) - start;
  fprintf(stdout, "%d seconds\n", sec);

  return 0;
}

// Compile flags
// g++ -std=c++0x -w -O2 -formit-frame-pointer -lm goldmines.cpp