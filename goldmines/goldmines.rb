#!/usr/bin/env ruby

require 'benchmark'

dim = Array.new
sum_grid = Array.new
results = Array.new

Benchmark.bmbm do |benchmark|

  benchmark.report ("the whole thing") do
    ARGF.readlines.each_with_index do |line, line_num|

        if line_num == 0
          dim = line.scan(/\d+/).map(&:to_i)
          
        elsif line_num <= dim[0]
          
            accumulator = Array.new()
            j = 0
            line.scan(/\d+/).map(&:to_i).reduce(0) do |acc, current|
              sum = acc + current

              if (line_num == 1)
                accumulator.push(sum)
              else
                accumulator.push(sum_grid[line_num-2][j] + sum)
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

            first_subtraction = 0
            second_subtraction = 0
            all_sum = 0

            # for readibility
            x1 = query[0]
            y1 = query[1]
            x2 = query[2]
            y2 = query[3]

            col_bound = y1 - 1
            row_bound = x1 - 1

            # let's do this
            if (row_bound != -1)
              if (x2 != 0)
                first_subtraction = sum_grid[x2][y2] - sum_grid[row_bound][y2]
              end
            else
              first_subtraction = sum_grid[x2][y2]
            end

            if (col_bound != -1)
              if (y1 != 0)
                second_subtraction = sum_grid[x2][col_bound]
              end
            end

            if (row_bound != -1 && col_bound != -1)
              second_subtraction -= sum_grid[row_bound][col_bound]
            end

            all_sum = first_subtraction - second_subtraction

            results.push(all_sum)

        end
    end


    puts results
    $stdout.flush
  end
end
