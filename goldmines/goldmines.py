#!/usr/bin/env python

dim = []
sum_grid = []
results = []

dim = map(int, raw_input().split())

for line_num in range(dim[0]):

  row_gold = map(int, raw_input().split())

  sum = 0;
  accumulator = [];

  for i in range(0, len(row_gold)):
    sum += row_gold[i]
    
    if line_num == 0:
      accumulator.append(sum)
    else:
      accumulator.append(sum_grid[-1][i] + sum)

  sum_grid.append(accumulator)


num_queries = int(raw_input())

for q in range(num_queries):
  query = map(lambda i: int(i) - 1, raw_input().split())

  sum = sum_grid[query[2]][query[3]]

  if (query[0] != 0 and query[2] != 0):
    sum -= sum_grid[query[0]-1][query[3]]

  if (query[1] != 0):
    sum -= sum_grid[query[2]][query[1]-1]

  if (query[0] != 0 and query[1] != 0):
    sum += sum_grid[query[0]-1][query[1]-1]

  results.append(sum)

for r in results:
  print r