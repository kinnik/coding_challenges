#!/usr/bin/env python

import time
import sys

start = time.clock();

M = 10 ** 9 + 7
MAX_T = 10000
MAX_N = 10**12
cache = {}


def retrieve_input():
  input_text = map(int, sys.stdin.readlines())

  num_tests = input_text[0]
  if num_tests < 1 or num_tests > MAX_T:
    raise ValueError("T is out of range, breaking the constraint 1 <= T <= MAX_T.")

  input_nums = [x for x in input_text[1:num_tests+1] if x >= 1 and x <= MAX_N]
  if len(input_nums) != num_tests:
    raise ValueError("N is out of range, breaking the constraint 1 <= N <= MAX_N.")

  return input_nums


def process(input_nums):
  results = []
  for i in input_nums:
    results.append(calc(i))
  return results


# Fast matrix method.
# [ 3 1]^n = [ F(n+1) F(n)  ]
# [-1 0]   = [-F(n)   F(n-1)]
def calc(n):
  if n in cache:
    return cache[n]

  the_matrix = [3, 1, -1, 0]
  result = power(the_matrix, n)[1]
  
  cache[n] = result

  return result


# Exponentiation by squaring
def power(matrix, n):
  if (n < 0):
    raise ValueError("n is out of range, breaking the constraint n >= 0.")

  result = [1, 0, 0, 1]

  while (n != 0):
    if n % 2 != 0:
      result = multiply(result, matrix)
    n /= 2
    matrix = multiply(matrix, matrix)

  return result

# http://stackoverflow.com/a/12235054/3104465
# (A * B) % C == ((A % C) * (B % C)) % C
# matrix multiplication combined with modulus operation
def multiply(x, y):
  a = ((x[0] % M * y[0] % M) % M + (x[1] % M * y[2] % M) % M) % M
  b = ((x[0] % M * y[1] % M) % M + (x[1] % M * y[3] % M) % M) % M
  c = ((x[2] % M * y[0] % M) % M + (x[3] % M * y[2] % M) % M) % M
  d = ((x[2] % M * y[1] % M) % M + (x[3] % M * y[3] % M) % M) % M

  return [a, b, c, d];


def print_results(results):
  for r in results:
    print r


print_results(process(retrieve_input()))

print time.clock() - start, " processing time."