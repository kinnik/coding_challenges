#!/usr/bin/env python

import sys

sum_grid = []
results = []
input_text = sys.stdin.readlines()


num_rows, num_cols = map(int, input_text[0].split())

for line_num in range(1,num_rows+1):

  row_gold = map(int, input_text[line_num].split())

  sum = 0;
  accumulator = [];

  for i in range(0, len(row_gold)):
    sum += row_gold[i]
    
    if line_num == 1: #first line of the gold in the grid
      accumulator.append(sum)
    else:
      accumulator.append(sum_grid[-1][i] + sum)

  sum_grid.append(accumulator)


num_queries = int(input_text[num_rows + 1])

for q in range(num_rows+2,num_rows+2+num_queries): #python's range exclude the upper bound
  x1, y1, x2, y2 = map(lambda i: int(i) - 1, input_text[q].split())

  sum = sum_grid[x2][y2]

  if (x1 != 0 and x2 != 0):
    sum -= sum_grid[x1-1][y2]

  if (y1 != 0):
    sum -= sum_grid[x2][y1-1]

  if (x1 != 0 and y1 != 0):
    sum += sum_grid[x1-1][y1-1]

  results.append(sum)

for r in results:
  print r