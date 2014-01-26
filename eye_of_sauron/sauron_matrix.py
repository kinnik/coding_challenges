#!/usr/bin/env python

num_tests = int(raw_input())

def do_calc(n):
  the_matrix = [3, 1, -1, 0]
  return power(the_matrix, n)[1] % (10 ** 9 + 7)

def power(matrix, n):
  result = [1, 0, 0, 1]

  while (n != 0):
    if n % 2 != 0:
      result = multiply(result, matrix)
    n /= 2
    matrix = multiply(matrix, matrix)

  return result


def multiply(x, y):
  return [
    x[0] * y[0] + x[1] * y[2],
    x[0] * y[1] + x[1] * y[3],
    x[2] * y[0] + x[3] * y[2],
    x[2] * y[1] + x[3] * y[3]
        ]


cache = {}

for i in range(0, num_tests):
  input_num = int(raw_input())

  if input_num in cache:
    result = cache[input_num]
  else:
    result = do_calc(input_num)
    cache[input_num] = result

  print result
