// Insertion Sort   O(n^2)
// Language: C++

#include <iostream>

using std::cout;
using std::endl;



// INSERTION SORT ALGORITHM
void insertionSort(int arr[], const int ARRSIZE)
{
    for (int i = 1; i < ARRSIZE; i++)
    {
        int x = arr[i];
        int j = i - 1;
        while (j >= 0 && arr[j] > x)
        {
            arr[j + 1] = arr[j];
            j--;
        }
        arr[j + 1] = x;
    }
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


    // SORT
    insertionSort(arr, arrSize);


    // Prints out the sorted array
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

    delete arr;
}