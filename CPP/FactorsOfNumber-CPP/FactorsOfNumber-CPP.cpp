// Finding the factors of a number
// Language: C++

#include <iostream>

using namespace std;

int main()
{
    int number, factorCount = 0;

    cout << "Enter a number to find all the factors: ";
    cin >> number;
    cout << "\nThe factors of " << number << " are: ";

    for (int i = 1; i <= number; i++)
    {
        if (number % i == 0)
        {
            cout << i << " ";
            factorCount++;
        }
    }

    cout << "\nThere are " << factorCount << " factors of " << number << endl;
}