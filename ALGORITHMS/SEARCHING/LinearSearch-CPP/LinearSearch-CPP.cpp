// Linear Search
// Language: C++

#include <iostream>

using std::cout;
using std::cin;
using std::endl;

int linearSort(int a[], int aSize, int numberToFind, int &foundNumberIndex)
{
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

int main()
{
    int arr[] = {2, 3, 5, 7, 8, 9};
    int arrSize = (sizeof(arr) / sizeof(arr[0]));
    int numberToFind;
    int foundNumberIndex = -1;

    cout << "Enter number to find in the array: ";
    cin >> numberToFind;
    cout << "\n";

    if (linearSort(arr, arrSize, numberToFind, foundNumberIndex) != -1)
    {
        cout << "Number " << numberToFind << " is in the array at index " << foundNumberIndex << endl;
    }
    else if (linearSort(arr, arrSize, numberToFind, foundNumberIndex) == -1)
    {
        cout << "Number " << numberToFind << " is not in the array" << endl;
    }
}