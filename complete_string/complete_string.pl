#!/usr/bin/env perl -w

use strict;
use warnings;

use constant YES => "YES";
use constant NO => "NO";

my @results;

my @input = <STDIN>;
chomp @input;


my $num_strings = $input[0];

foreach (1..$num_strings) {

  my %h = map { ($_, undef) if (m/[a-z]/) } split //, $input[$_];

  if (scalar keys %h == 26) { 
    push @results, YES;
  } else {
    push @results, NO;
  }

}

print STDOUT join "\n", @results;
print STDOUT "\n";