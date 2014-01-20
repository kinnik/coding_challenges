#!/usr/bin/env python
results = []
num_strings = int(raw_input())

for i in range(num_strings):
  uniq_chars = set(list(raw_input()))

  if len(uniq_chars) == 26:
    results.append("YES")
  else:
    results.append("NO")

for r in results:
  print r

