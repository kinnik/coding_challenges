#!/usr/bin/env ruby


class SimpleBenchmarker
  def self.go(how_many=1, &block)
    puts "---------- Benchmarking started ----------"
    start_time = Time.now
    puts "Start Time:\t#{start_time}\n\n"
    how_many.times do |a|
      print "."
      block.call
    end
    print "\n\n"
    end_time = Time.now
    puts "End Time:\t#{end_time}\n"
    puts "---------- Benchmarking finished ----------\n\n"
    puts "Result:\t\t#{end_time - start_time} seconds"
  end
end

=begin
SimpleBenchmarker.go 5 do
  time = rand(0.1..1.0)
  sleep time
end
=end


dim = []
grid = []
num_query = 0


$stdin.each_with_index do |line, index|

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
    grid[index-1] = line.chomp.split(' ').map { |x| x.to_i }
  elsif index == dim[0] + 1
    num_query = line.chomp.to_i
  else
    query = line.chomp.split(' ').map { |x| x.to_i }

    if (query[0] < 1 or query[0] > dim[0])
      raise ArgumentError
    end

    if (query[1] < 1 or query[1] > dim[1])
      raise ArgumentError
    end
    
    row_lo = query[0]-1
	row_hi = query[2]-1
    col_lo = query[1]-1
	col_hi = query[3]-1

    sum = 0

	SimpleBenchmarker.go do
   	  (col_lo..col_hi).each do |c|
        (row_lo..row_hi).each do |r|
          sum += grid[r][c]
        end
      end
    end

    puts sum
  end
end
