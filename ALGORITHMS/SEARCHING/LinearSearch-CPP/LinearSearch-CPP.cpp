// Linear Search    O(N)
// Language: C++

#include <iostream>

using std::cout;
using std::cin;
using std::endl;



// LINEAR SORT ALGORITHM
int linearSearch(int a[], const int ARRSIZE, const int VALUETOFIND)
{
    for (int i = 0; i < ARRSIZE; i++)
    {
        if (a[i] == VALUETOFIND)
        {
            return i;
        }
    }

    return -1;
}



// Array Generation
int* generateArray(int amountOfNumbers, int maximumValue)
{
    srand(time(NULL));

    int* arr = new int[amountOfNumbers];

    for (int i = 0; i < amountOfNumbers; i++)
    {
        arr[i] = rand() % 100;
    }

    return arr;
}

int main()
{
    // Generate array and print it
    int arrSize = 10;
    int *arr = generateArray(arrSize, 100);
    cout << "Initial Array: { ";
    for (int i = 0; i < arrSize; i++)
    {
        cout << arr[i];
        if (i < arrSize - 1)
        {
            cout << " ";
        }
    }
    cout << " }\n";

    // Input the number to find in the array
    int numberToFind;
    cout << "Enter number to find in the array: ";
    cin >> numberToFind;


    // SEARCH
    int foundValueIndex = linearSearch(arr, arrSize, numberToFind);


    // Prints out the result of performing a sequential/linear search
    if (foundValueIndex == -1)
    {
        cout << numberToFind << " is not in the array" << endl;
    }
    else
    {
        cout << numberToFind << " is in the array at index " << foundValueIndex << endl;
    }

    delete arr;
}