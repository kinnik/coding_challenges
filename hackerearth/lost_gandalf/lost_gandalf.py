#!/usr/bin/env python

import sys
import time
from bisect import bisect_left

big_time = start_time = time.clock();

MIN_N = 1
MAX_N = 1000000
MIN_Q = 1
MAX_Q = 1000000
MIN_W = 2
MAX_W = 10


def retrieve_num_words(input_text):
  num_words = int(input_text[0])
  if num_words < MIN_N or num_words > MAX_N:
    raise ValueError("N is out of range. 1 <= N <= MAX_N does not hold.")

  return num_words

def retrieve_words(input_text, num_words):
  words = filter(lambda w: len(w) >= MIN_W and len(w) <= MAX_W, map(lambda m: m.strip(), input_text[1:1+num_words]))

  if len(words) != num_words:
    raise ValueError("W is out of range. 2 <= length(W) <= MAX_W does not hold.")

  return words


def sort(list_of_words):
  return quicksort(list_of_words)

def quicksort(list_of_words):
  """Quicksort using list comprehensions"""
  if list_of_words == []: 
      return []
  else:
      pivot = list_of_words[0]
      lesser = quicksort([x for x in list_of_words[1:] if x < pivot])
      greater = quicksort([x for x in list_of_words[1:] if x >= pivot])
      return lesser + [pivot] + greater

def retrieve_num_queries(input_text, offset):
  num_queries = int(input_text[offset]);

  if num_queries <= MIN_Q or num_queries >= MAX_Q:
    raise ValueError("Q is out of range. 1 <= Q <= MAX_Q does not hold.")

  return num_queries

def retrieve_queries(input_text, start, length):

  queries = filter(lambda w: len(w) >= MIN_W and len(w) <= MAX_W, map(lambda m: m.strip(), input_text[start:start+length]))

  if len(queries) != length:
    raise ValueError("W is out of range. 2 <= length(Q) <= MAX_W does not hold.")

  return queries


def process(list_of_words, queries):
  results = []

  for q in queries:
    results.append(search(q, list_of_words))

  return results


def search(word, list_of_words):
  result = binary_search(list_of_words, word)
  if result >= 0:
    result += 1
  return result

#http://stackoverflow.com/questions/212358/binary-search-in-python
def binary_search(list_of_words, word, lo=0, hi=None):
    hi = hi if hi is not None else len(list_of_words) # hi defaults to len(a)   
    pos = bisect_left(list_of_words,word,lo,hi)          # find insertion position
    return (pos if pos != hi and list_of_words[pos] == word else -1) # don't walk off the end

def print_results(results):
  for r in results:
    print r


def run():
  input_text = sys.stdin.readlines()
  num_words = retrieve_num_words(input_text)
  sorted_list_of_words = sort(retrieve_words(input_text, num_words))

  num_queries = retrieve_num_queries(input_text, num_words + 1)
  queries = retrieve_queries(input_text, num_words+2, num_queries)

  print_results(process(sorted_list_of_words, queries))

run()