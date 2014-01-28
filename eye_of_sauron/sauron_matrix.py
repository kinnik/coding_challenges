
import sys

M = 10 ** 9 + 7
MAX_T = 10000
MAX_N = 10**12
_CUTOFF = 1536
cache = {}
results = []

def do_calc(n):
  if n in cache:
    return cache[n]

  the_matrix = [3, 1, -1, 0]
  result = power(the_matrix, n)[1]

  if n not in cache:
    cache[n] = result

  return result

def power(matrix, n):
  result = [1, 0, 0, 1]

  while (n != 0):
    if n % 2 != 0:
      result = multiply(result, matrix)
    n /= 2
    matrix = multiply(matrix, matrix)

  return result


def multiply(x, y):

  a = ((x[0] % M * y[0] % M) % M + (x[1] % M * y[2] % M) % M) % M
  b = ((x[0] % M * y[1] % M) % M + (x[1] % M * y[3] % M) % M) % M
  c = ((x[2] % M * y[0] % M) % M + (x[3] % M * y[2] % M) % M) % M
  d = ((x[2] % M * y[1] % M) % M + (x[3] % M * y[3] % M) % M) % M


  return [a, b, c, d];
  

input_text = map(int, sys.stdin.readlines())
num_tests = input_text[0]
input_nums = [x for x in input_text[1:num_tests+1] if x >= 1 and x <= MAX_N]
input_text = None # don't need this anymore
max_num = max(input_nums)
do_calc(max_num)

for i in input_nums:
  results.append(do_calc(i))


for r in results:
  print r
