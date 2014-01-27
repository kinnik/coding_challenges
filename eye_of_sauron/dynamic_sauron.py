#!/usr/bin/env python

from collections import deque

import sys
import time

start_time = time.clock();

input_nums = deque(map(int, sys.stdin.readlines()))
num_tests = input_nums.popleft()
M = 10**9 + 7

max_num = max(input_nums)

# power = [None] * max_num
results = []
cache = dict()

end_time = time.clock()


def alt_calc(max_num):
  n_2 = 1
  n_1 = 3
  cache[1] = n_2
  cache[3] = n_1

  index = 2
  while (index <= max_num):
    n = 3 * n_1 - n_2
    if index not in cache and index in input_nums:
      cache[index] = (((3 * n_1) % M) - (n_2 % M)) % M

    n_2 = n_1
    n_1 = n
    index += 1

def alt_process():
  for i in input_nums:
    results.append(cache[i])


# def cal_power(max_num):
#   power[0] = 1
#   power[1] = 3

#   index = 2
#   while (index < max_num):
#     power[index] = 3 * power[index-1] - power[index-2]
#     index += 1


# def process(num_tests):
#   for i in input_nums:
#     results.append(power[i-1] % m)


def print_results(results):
  for r in results:
    print r


init_time = end_time - start_time

start_time = time.clock()
alt_calc(max_num)
end_time = time.clock()
cal_power_time = end_time - start_time

start_time = time.clock()
alt_process()
end_time = time.clock()
process_time = end_time - start_time

start_time = time.clock()
print_results(results)
end_time = time.clock()
print_results_time = end_time - start_time


print "Init time: ", init_time
print "cal_power_time: ", cal_power_time
print "process_time: ", process_time
print "print_results_time: ", print_results_time