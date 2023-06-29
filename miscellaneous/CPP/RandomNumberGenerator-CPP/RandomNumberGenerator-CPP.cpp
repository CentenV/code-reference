// Generating random integers from a range of [min,max]
// Language: C++

#include <iostream>
#include <cstdlib>
#include <ctime>

using std::cout;
using std::endl;
using std::time;

const int RANDOM_MIN = 1;
const int RANDOM_MAX = 100;

int main()
{
    // Seeding the random number generator
    srand(time(0));
    // Throwing out the first rand()
    rand();

    // Printing a random number within the range
    cout << rand() % (RANDOM_MAX - RANDOM_MIN + 1) + RANDOM_MIN << endl;
}