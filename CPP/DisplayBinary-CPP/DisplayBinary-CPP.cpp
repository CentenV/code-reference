// Displaying a variable in binary form
// Language: C++

#include <iostream>
#include <bitset>

using std::cout;
using std::bitset;

int main()
{
    float variable = 58.21;
    cout << bitset<(sizeof(variable)*8)>(variable);
}