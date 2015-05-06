#include <stdio.h>
#include <iostream>
#include <string>
#include <sstream>
#include <vector>
#include <time.h>

using namespace std;

int main()
{
  // declarations & initialisations
  time_t start = time(NULL);
  int sec;

  const string YES = "YES";
  const string NO = "NO";

  unsigned short num_strings;
  string line;

  // http://stackoverflow.com/questions/9371238/why-is-reading-lines-from-stdin-much-slower-in-c-than-python
  cin.sync_with_stdio(false);
  cout.sync_with_stdio(false);


  // read the number of strings
  getline(cin, line);
  stringstream ss(line);
  ss.sync_with_stdio(false);

  ss >> num_strings;

  std::vector<string> results(num_strings);

  for (size_t i = 0; i < num_strings; ++i)
  {
    unsigned short alphabet[26] = {0}; // initialises everything to 0

    getline(cin, line);
    stringstream ss(line);
    string input_string;

    ss >> input_string;

    // c++ allows subscripting of string
    size_t string_length = input_string.length();

    for (size_t i = 0; i < string_length; ++i)
      if (input_string[i] >= 'a' && input_string[i] <= 'z')
        alphabet[input_string[i] - 'a'] = 1;

    bool complete_string = true;
    for (size_t i = 0; i < 26; ++i)
      if (alphabet[i] == 0)
      {
        complete_string = false;
        break;
      }

    complete_string ? results[i] = YES : results[i] = NO;
  }

  for (std::vector<string>::const_iterator it = results.begin();
       it != results.end(); ++it)
    cout << *it << endl;

  sec = (int) time(NULL) - start;
  cout << sec << "seconds" << endl;

  return 0;
}

// Compile flags
// g++ -std=c++0x -w -O2 -formit-frame-pointer -lm complete_string.cpp -o complete_string