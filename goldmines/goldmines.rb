#!/usr/bin/env ruby

dim = Array.new
hacc_grid = Array.new
vacc_grid = Array.new
results = Array.new

ARGF.readlines.each_with_index do |line, line_num|

    if line_num == 0
      dim = line.scan(/\d+/).map(&:to_i)
      hacc_grid = vacc_grid = Array.new(dim[0])
    elsif line_num <= dim[0]
        hacc = Array.new()
        vacc = Array.new()
        j = 0
        line.scan(/\d+/).map(&:to_i).reduce(0) do |acc, current|
          sum = acc + current
          hacc << sum

          if (line_num == 1)
            vacc << sum
          elsif (line_num > 1)
            vacc << vacc_grid[line_num-2][j] + sum
          end

          j += 1
          sum
        end
        hacc_grid[line_num-1] = hacc
        vacc_grid[line_num-1] = vacc
    elsif line_num == dim[0] + 1
      #results = Array.new(line.to_i)
      hacc = nil
    else

        # -1 because the grid is zero-indexed
        query = line.scan(/\d+/).map { |x| x.to_i - 1}

        all_sum = 0

          # for readibility
          x1 = query[0]
          y1 = query[1]
          x2 = query[2]
          y2 = query[3]

          first_subtraction = 0
          second_subtraction = 0

          left_bound = [0,0]
          top_bound = [0,0]
          vert_bound = [0,0]

          left_bound[0] = x2
          left_bound[1] = y1 - 1

          top_bound[0] = x1 - 1
          top_bound[1] = y2
          
          vert_bound[0] = x1 - 1
          vert_bound[1] = y1 - 1

          # let's do this
          if (top_bound[0] != -1)
            if (x2 != 0)
              first_subtraction = vacc_grid[x2][y2] - vacc_grid[top_bound[0]][top_bound[1]]
            end
          else
            first_subtraction = vacc_grid[x2][y2]
          end

          if (left_bound[1] != -1)
            if (y1 != 0)
              second_subtraction = vacc_grid[left_bound[0]][left_bound[1]]
            end
          end

          if (vert_bound[0] != -1 && vert_bound[1] != -1)
            second_subtraction -= vacc_grid[vert_bound[0]][vert_bound[1]]
          end


          all_sum = first_subtraction - second_subtraction


        results.push(all_sum)

    end
end
puts results
$stdout.flush
