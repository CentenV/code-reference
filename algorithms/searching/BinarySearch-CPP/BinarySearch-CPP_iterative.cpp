// Binary Search    O(log n)
// Language: C++

#include <iostream>
#include <algorithm>

using std::cin;
using std::cout;
using std::endl;
using std::sort;
using std::begin;
using std::end;



// BINARY SEARCH ALGORITHM
int binarySearch(int arr[], int start, int end, const int TARGET)
{
    while (start <= end)
    {
        int middle = (start + end) / 2;

        // Determine equal and split to upper or lower
        if (arr[middle] == TARGET)
        {
            return middle;
        }
        else if (TARGET < arr[middle])
        {
            end = middle - 1;
        }
        else if (TARGET > arr[middle])
        {
            start = middle + 1;
        }
    }

    // Target not found
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

    // Array needs to be sorted for binary search
    sort(arr, arr + arrSize);
    cout << "Sorted Array: { ";
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
    int foundValueIndex = binarySearch(arr, 0, arrSize - 1, numberToFind);


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