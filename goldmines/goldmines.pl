#!/usr/bin/env perl -w

use strict;
use warnings;
use Data::Dumper;

my $index = 0;
my @dim;
my @grid;
my $num_query = 0;

while (<>)
{
  chomp;
  if ($index == 0) 
  {
    @dim = map { int $_ } split / /, $_;
    
    if ($dim[0] < 1 or $dim[0] > 1000)
    {
      exit -1;
    }
    if ($dim[1] < 1 or $dim[1] > 1000)
    {
      exit -1;
    }
    $index += 1;
    next;
  }

  if ($index <= $dim[0]) 
  {
    my @row_gold = map { int $_ } split / /, $_;
    push @{$grid[$index-1]}, @row_gold;
  }
  elsif ($index == $dim[0] + 1) 
  {
    $num_query = int $_;
  }
  else
  {
    my @query = map { int $_ } split / /, $_;

    if ($query[0] < 1 or $query[0] > $dim[1])
    {
      exit -1;
    }
    if ($query[0] > $query[2]) { exit -1; }

    if ($query[1] < 1 or $query[1] > $dim[0])
    {
      exit -1;
    }
    if ($query[1] > $query[3]) { exit -1; }

    my $row_lo = $query[1] - 1;
    my $row_hi = $query[3] - 1;
    my $col_lo = $query[0] - 1;
    my $col_hi = $query[2] - 1;

    my $sum = 0;

    foreach my $c($col_lo..$col_hi) {
      foreach my $r($row_lo..$row_hi) {
        $sum += $grid[$r][$c]
      }
    }
    print STDOUT "$sum\n";
  }

  $index += 1;
  next;
}

