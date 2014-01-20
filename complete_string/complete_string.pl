#!/usr/bin/env perl -w

$|=1;

use strict;
use warnings;

use constant YES => "YES";
use constant NO => "NO";

my @results;

my @input = <STDIN>;
chomp @input;


my $num_strings = $input[0];

foreach (1..$num_strings) {

  # perl doesn't have sets, so i'm using a hash with just keys and no values
  my %h = map { ($_, undef) if (m/[a-z]/) } split //, $input[$_];

  scalar keys %h == 26 ? push @results, YES : push @results, NO;

}

print STDOUT join "\n", @results;
print STDOUT "\n";