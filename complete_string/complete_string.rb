#!/usr/bin/env ruby

require 'benchmark'
require 'awesome_print'

num_strings = 0
results = []

Benchmark.bmbm do |benchmark|

  benchmark.report ("the whole thing") do
    ARGF.readlines.each_with_index do |line, line_num|
        line.chomp!

        if line_num == 0
          num_strings = line.chomp.to_i

        elsif line_num <= num_strings

            arr = line.split("")

            if (arr.uniq.size == 26)
              results.push(:YES)
            else
              results.push(:NO)
            end
        end
    end

    puts results
    $stdout.flush
  end
end
