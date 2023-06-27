// Linear Search
// Language: C++

#include <iostream>

using std::cout;
using std::cin;
using std::endl;

// LINEAR SORT ALGORITHM
int linearSort(int a[], int aSize, int numberToFind)
{
    int foundNumberIndex = -1;

    for (int i = 0; i < aSize; i++)
    {
        if (a[i] == numberToFind)
        {
            foundNumberIndex = i;
            return i;
        }
    }

    return foundNumberIndex;
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
        cout << arr[i] << " ";
    }
    cout << " }";

    

    // Input the number to find in the array
    int numberToFind;
    cout << "\nEnter number to find in the array: ";
    cin >> numberToFind;
    // cout << "\n";

    // Prints out the result of performing a sequential/linear search
    int foundNumberIndex = linearSort(arr, arrSize, numberToFind);
    if (foundNumberIndex != -1)
    {
        cout << "Number " << numberToFind << " is in the array at index " << foundNumberIndex << endl;
    }
    else if (foundNumberIndex == -1)
    {
        cout << "Number " << numberToFind << " is not in the array" << endl;
    }
    delete arr;
}


