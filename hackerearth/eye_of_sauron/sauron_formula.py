#!/usr/bin/env python

import math


x = (3 + math.sqrt(5)) / 2
y = (3 - math.sqrt(5)) / 2
xmy = x - y

A = (3 - y) / xmy
B = (x - 3) / xmy

num_tests = int(raw_input())

def do_calc(n):
  return (A * math.pow(x,n) + B * math.pow(y,n))# % (10 ** 9 + 7)

cache = {}

for i in range(0, num_tests):
  input_num = int(raw_input()) - 1

  if input_num in cache:
    result = cache[input_num]
  else:
    result = do_calc(input_num)
    cache[input_num] = result


  print result
