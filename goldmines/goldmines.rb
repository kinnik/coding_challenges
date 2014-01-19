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

            all_sum = 0

            # for readibility
            x1 = query[0]
            y1 = query[1]
            x2 = query[2]
            y2 = query[3]

            # let's do this
            if (x1 != 0)
              if (x2 != 0)
                all_sum = sum_grid[x2][y2] - sum_grid[x1-1][y2]
              end
            else
              all_sum = sum_grid[x2][y2]
            end

            if (y1 != 0)
                all_sum -= sum_grid[x2][y1-1]
            end

            if (x1 != 0 && y1 != 0)
              all_sum += sum_grid[x1-1][y1-1]
            end

            results.push(all_sum)

        end
    end

    puts results
    $stdout.flush
  end
end
