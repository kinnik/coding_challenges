#include <stdio.h>
#include <stdbool.h>

#define MAXLENGTH 100
#define YES "YES"
#define NO "NO"

int main ()
{
  unsigned short num_strings;

  scanf("%hu", &num_strings);

  for (size_t i = 0; i < num_strings; ++i)
  {
    unsigned short alphabet[26] = {0}; // initialises everything to 0  
    char input_string[MAXLENGTH];

    scanf("%s", &input_string);
    
    size_t num_chars = strlen(input_string);

    for (size_t c = 0; c < num_chars; ++c)
    {
      char ascii_value = input_string[c];
      if (ascii_value >= 97 && ascii_value <= 123)
      {
        alphabet[ascii_value - 97] = 1;
      }
    }

    // determine if all of the alphabet array is in the range a-z
    bool complete_string = true;
    for (size_t a = 0; a < 26; ++a)
    {
      if (alphabet[a] == 0)
      {
        complete_string = false;
        break;
      }
    }

    if (complete_string == true) 
    {
      printf("%s\n", YES);
    }
    else
    {
      printf("%s\n", NO);
    }
  }

  return 0;
}
// gcc -std=gnu99 -w -O2 -fomit-frame-pointer -lm