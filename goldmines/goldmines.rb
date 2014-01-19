#!/usr/bin/env ruby

require 'benchmark'

dim = []
sum_grid = []
results = []

Benchmark.bmbm do |benchmark|

  benchmark.report ("the whole thing") do
    ARGF.readlines.each_with_index do |line, line_num|

        if line_num == 0
          dim = line.scan(/\d+/).map(&:to_i)
        elsif line_num <= dim[0]
            accumulator = []
            j = 0
            line.scan(/\d+/).map(&:to_i).reduce(0) do |acc, current|
              sum = acc + current

              if (line_num == 1)
                accumulator.push(sum)
              else
                accumulator.push(sum_grid.last[j] + sum)
              end

              j += 1
              sum
            end
            sum_grid.push(accumulator)

        elsif line_num == dim[0] + 1
          #results = Array.new(line.to_i)
        else

            # -1 because the grid is zero-indexed
            query = line.scan(/\d+/).map{ |x| x.to_i - 1 }

            # let's do this
            all_sum = sum_grid[query[2]][query[3]]
            if (query[0] != 0 && query[2] != 0)
                all_sum -= sum_grid[query[0]-1][query[3]]
            end

            if (query[1] != 0)
                all_sum -= sum_grid[query[2]][query[1]-1]
            end

            if (query[0] != 0 && query[1] != 0)
              all_sum += sum_grid[query[0]-1][query[1]-1]
            end

            results.push(all_sum)

        end
    end

    puts results
    $stdout.flush
  end
end
