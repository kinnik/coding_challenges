#!/usr/bin/env python

import sys

def run():
  results = []
  input_text = sys.stdin.readlines()

  num_rows, num_cols = map(int, input_text[0].split())
  sum_grid = [[None] * num_cols for r in range(num_rows)]

  for i in range(0, num_rows):
    sum = 0;

    row_gold = map(int, input_text[i+1].split())

    for j in range(0, num_cols):
      sum += row_gold[j];

      if i == 0: #first line of the gold in the grid
        sum_grid[i][j] = sum
      else:
        sum_grid[i][j] = sum_grid[i-1][j] + sum


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

  return results

def print_results(results):
  for r in results:
    print r

results = run()
print_results(results)