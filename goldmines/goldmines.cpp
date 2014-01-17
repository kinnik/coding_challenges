#include <stdio.h>
#include <iostream>
#include <string>
#include <sstream>
#include <vector>
#include <time.h>

using namespace std;

int main()
{
  time_t start = time(NULL);
  int sec;

  string line;
  unsigned short line_num = 1;
  unsigned short num_rows, num_cols;
  unsigned short query_anchor = 0;
  unsigned short num_queries = 0;
  
  unsigned int* gold_data;
  unsigned int** gold;
  vector<unsigned long> results;

  // http://stackoverflow.com/questions/9371238/why-is-reading-lines-from-stdin-much-slower-in-c-than-python
  cin.sync_with_stdio(false);
  cout.sync_with_stdio(false);

  while (getline(cin, line))
  {
    stringstream ss(line);
    ss.sync_with_stdio(false);

    if (line_num == 1)
    {

      ss >> num_rows >> num_cols;
      
      gold_data = new unsigned int[num_rows * num_cols];
      gold = new unsigned int*[num_rows];

      for (unsigned short a = 0; a < num_rows; ++a)
        gold[a] = gold_data + num_cols * a;

      query_anchor = num_rows + 2;
    }
    else if (line_num < query_anchor)
    {
        string field;

        unsigned short j = 0;
        while(getline(ss, field, ' '))
        {
            stringstream strstm(field);
            strstm.sync_with_stdio(false);

            unsigned int g;
            strstm >> g;

            gold[line_num-2][j] = g;
            ++j;
        }
    }
    else if (line_num == query_anchor)
    {
      ss >> num_queries;
      results.resize(num_queries);
    }
    else
    { 
      unsigned short row_lo = 0;
      unsigned short col_lo = 0;
      unsigned short row_hi = 0;
      unsigned short col_hi = 0;

      ss >> row_lo >> col_lo >> row_hi >> col_hi;

      //printf ("%d %d %d %d", row_lo, col_lo, row_hi, col_hi);

      // remember, the grid is zero-indexed
      --row_lo;
      --col_lo;
      --row_hi;
      --col_hi;

      unsigned long total_gold = 0;

      for (unsigned short r=row_lo; r<= row_hi; ++r)
      {
        for (unsigned short c=col_lo; c<= col_hi; ++c) 
        {
          total_gold += gold[r][c];
        }
      }
      results[line_num-num_rows-3] = total_gold;
      // fprintf(stdout, "%lu\n", total_gold);
      // fflush(stdout);
      //cout << total_gold << endl;
    }
    ++line_num;
  }
  delete[] gold;
  delete[] gold_data;

  for (vector<unsigned long>::const_iterator i = results.begin(); i != results.end(); ++i)
   {  
      cout << *i << endl;
  //     fprintf(stdout, "%llu\n", *i);
  //     fflush(stdout);
   }
  sec = (int) time(NULL) - start;
  cout << sec << "seconds" << endl;

  return 0;
}

// Compile flags
// g++ -std=c++0x -w -O2 -formit-frame-pointer -lm goldmines.cpp