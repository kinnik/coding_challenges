#!/usr/bin/env ruby


class SimpleBenchmarker
  def self.go(how_many=1, desc, &block)
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
    #puts "#{desc}:\t\t#{end_time - start_time} seconds"
  end
end

dim = []
grid = []
num_query = 0


input = ARGF.read
index = -1


  input.each_line do |line|

      index += 1

      if index == 0
        dim = line.split(' ').map { |x| x.to_i }

        if dim[0] < 1 or dim[0] > 1000
    	  raise ArgumentError
        end
        if dim[1] < 1 or dim[1] > 1000
          raise ArgumentError
        end
        next
      end

      if index <= dim[0]
        SimpleBenchmarker.go(1, "Reading Gold") do 
          grid[index-1] = line.chomp.split(' ').map do |x|
            gold = x.to_i
            # Amount of gold in each cell is an integer from 0 to 10^6
            if gold < 0 or gold > 10 ** 6
            	raise ArgumentError
            else
            	gold
            end
          end
        end
      elsif index == dim[0] + 1
        num_query = line.chomp.to_i
      else


          query = line.chomp.split(' ').map { |l| l.to_i }

          # 1 <= x1 <= x2 <= R
          if (query[0] < 1 or query[0] > dim[0])
            raise ArgumentError, "line number #{index+1}: #{query[0]} < 1 or #{query[0]} > #{dim[0]}"
          end
          if query[1] > query[3]
            raise ArgumentError
          end

      	  # 1 <= y1 <= y2 <= C
          if (query[1] < 1 or query[1] > dim[1])
            raise ArgumentError, "line number #{index+1}: #{query[1]} < 1 or #{query[1]} > #{dim[1]}"
          end
          if query[0] > query[2]
            raise ArgumentError
          end


          row_lo = query[0]-1
          row_hi = query[2]-1
          col_lo = query[1]-1
          col_hi = query[3]-1

          grid_rows = row_hi - row_lo + 1
          grid_cols = col_hi - col_lo + 1


          SimpleBenchmarker.go(1, "Querying & Calculating #{grid_rows}x#{grid_cols}") do 

            sum = 0

            if col_lo == col_hi-1
              sum += grid[row_lo][col_lo] + grid[row_lo][col_hi]

          	  if row_lo != row_hi
          		  if row_lo + 1 < row_hi
          	      	row_lo += 1
        	      else
            			sum += grid[row_hi][col_lo] + grid[row_hi][col_hi]
            			puts sum
                  next
                end
          	  else
          	      puts sum
          	      next
          	  end
            end

            if row_lo == row_hi && col_lo == col_hi
              sum += grid[row_lo][col_lo]
              puts sum
              next
            end


            (row_lo..row_hi).each do |r|
              sum += grid[r][col_lo..col_hi].inject(:+)
            end


            puts sum
          end

      end
  end

