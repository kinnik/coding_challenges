#include <stdio.h>
#include <iostream>
#include <string>
#include <sstream>

using namespace std;

int main()
{
  string line;
  unsigned short line_num = 1;
  unsigned short dim[2];
  string field;
  unsigned short query_anchor = 0;
  unsigned short num_queries = 0;
  
  unsigned long* gold_data;
  unsigned long** gold;

  while (getline(cin, line))
  {
    stringstream ss(line);

    if (line_num == 1)
    {
      ss >> dim[0] >> dim[1];
    	
	    gold_data = new unsigned long[dim[0] * dim[1]];
      gold = new unsigned long*[dim[0]];

      for (unsigned long a; a < dim[0]; ++a)
		    gold[a] = gold_data + dim[1] * a;

      query_anchor = dim[0] + 2;
    }
    else if (line_num < query_anchor)
    {
        unsigned short j = 0;
        while(getline(ss, field, ' '))
        {
		        stringstream strstm(field);
            unsigned long g;
            strstm >> g;
            gold[line_num-2][j] = g;
			      ++j;
        }
    }
    else if (line_num == query_anchor)
    {
      ss >> num_queries;
    }
    else
    {
      
      unsigned short query_array[4];
      ss >> query_array[0] >> query_array[1] >> query_array[2] >> query_array[3];

      unsigned short row_lo = --query_array[0];
      unsigned short col_lo = --query_array[1];
      unsigned short row_hi = --query_array[2];
      unsigned short col_hi = --query_array[3];

      unsigned long total_gold = 0;

      for (unsigned short r=row_lo; r<= row_hi; ++r)
      {
        for (unsigned short c=col_lo; c<= col_hi; ++c) 
        {
          total_gold += gold[r][c];
        }
      }
      fprintf(stdout, "%lu\n", total_gold);
      fflush(stdout);
    }
    ++line_num;
  }
  delete[] gold;
  delete[] gold_data;
  return 0;
}
