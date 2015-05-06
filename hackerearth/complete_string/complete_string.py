#!/usr/bin/env python

YES = "YES"
NO = "NO"
results = []
num_strings = int(raw_input())

for i in range(num_strings):
  uniq_chars = {x for x in raw_input() if x in 'abcdefghijklmnopqrstuvwxyz'}

  if len(uniq_chars) == 26:
    results.append(YES)
  else:
    results.append(NO)

for r in results:
  print r