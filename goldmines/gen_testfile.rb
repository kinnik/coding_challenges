#!/usr/bin/env ruby

# generate grid size
num_rows = 1 + rand(1000)
num_cols = 1 + rand(1000)

# uncomment the two lines below to test the worst case
# num_rows = 1000
# num_cols = 1000

puts "#{num_rows} #{num_cols}"

# generate gold
(1..num_rows).each do |r|
  (1..num_cols).each do |c|
    gold = 1 + rand(10 ** 6)
    print gold.to_s + " "
  end
  print "\n"
end

# generate number of queries
num_queries = 1 + rand(1000)

# uncomment the line below to test the worst case
# num_queries = 1000

puts "#{num_queries}"

#x1 y1 x2 y2
#1 <= x1 <= x2 <= num_cols
#1 <= y1 <= y2 <= num_rows
(1..num_queries).each do |q|
  x1 = 1 + rand(num_cols)
  y1 = 1 + rand(num_rows)

  x2 = x1 + Random.rand(num_cols + 1 - x1)
  y2 = y1 + Random.rand(num_rows + 1 - y1)

  puts "#{x1} #{y1} #{x2} #{y2}"
end
