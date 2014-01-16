#!/usr/bin/env ruby


class SimpleBenchmarker
  def self.go(how_many=1, &block)
    #puts "---------- Benchmarking started ----------"
    start_time = Time.now
    #puts "Start Time:\t#{start_time}\n\n"
    how_many.times do |a|
      #print "."
      block.call
    end
    #print "\n\n"
    end_time = Time.now
    #puts "End Time:\t#{end_time}\n"
    #puts "---------- Benchmarking finished ----------\n\n"
    puts "Result:\t\t#{end_time - start_time} seconds"
  end
end



SimpleBenchmarker.go 5 do
  dim = []
  grid = []
  num_query = 0
  results = []

  input = ARGF.readlines
  line_num = 1
  
  input.each do |line|

      if line_num == 1
        dim = line.scan(/\d+/).map(&:to_i)


        if dim[0] < 1 or dim[0] > 1000 or 
           dim[1] < 1 or dim[1] > 1000
          raise ArgumentError
        end
      elsif line_num <= dim[0] + 1
          grid[line_num-2] = line.scan(/\d+/).map do |x|
            gold = x.to_i
            # Amount of gold in each cell is an integer from 0 to 10^6
            if gold < 0 or gold > 10 ** 6
            	raise ArgumentError
            else
            	gold
            end
          end
      elsif line_num == dim[0] + 2
        results = Array.new(line.to_i)
      else

          query = line.scan(/\d+/).map(&:to_i)

          # 1 <= x1 <= x2 <= R
          if (query[0] < 1 or query[0] > dim[0])
            raise ArgumentError, "line number #{line_num}: #{query[0]} < 1 or #{query[0]} > #{dim[0]}"
          end

      	  # 1 <= y1 <= y2 <= C
          if (query[1] < 1 or query[1] > dim[1])
            raise ArgumentError, "line number #{line_num}: #{query[1]} < 1 or #{query[1]} > #{dim[1]}"
          end

          if query[0] > query[2] or query[1] > query[3]
            raise ArgumentError
          end

          row_lo = query[0]-1
          row_hi = query[2]-1
          col_lo = query[1]-1
          col_hi = query[3]-1

          sum = 0

          (row_lo..row_hi).each do |r|
            sum += grid[r][col_lo..col_hi].inject(:+)
          end

          results[line_num-dim[0]-3] = sum

      end
      line_num += 1
  end
  puts results
  #print results.join("\n")
  #$stdout.flush
end

