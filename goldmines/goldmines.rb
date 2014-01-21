#!/usr/bin/env ruby

require 'benchmark'

Benchmark.bmbm do |benchmark|

  benchmark.report ("the whole thing") do
  
    sum_grid = []
    results = []
    num_rows, num_cols = 0

    ARGF.readlines.each_with_index do |line, line_num|

        if line_num == 0
          num_rows, num_cols = line.scan(/\d+/).map(&:to_i)
          sum_grid = Array.new(num_rows) { Array.new(num_cols)}
        elsif line_num <= num_rows

            j = 0
            line.scan(/\d+/).map(&:to_i).reduce(0) do |acc, current|
              sum = acc + current

              if (line_num == 1)
                sum_grid[line_num-1][j] = sum
              else
                sum_grid[line_num-1][j] = sum_grid[line_num-2][j] + sum
              end

              j += 1
              sum
            end

        elsif line_num == num_rows + 1
          #results = Array.new(line.to_i)
        else

            # -1 because the grid is zero-indexed
            x1, y1, x2, y2 = line.scan(/\d+/).map{ |x| x.to_i - 1 }

            # let's do this
            all_sum = sum_grid[x2][y2]
            if (x1 != 0 && x2 != 0)
                all_sum -= sum_grid[x1-1][y2]
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
