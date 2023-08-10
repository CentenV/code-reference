// Selection Sort   O(n^2)
// Language: C++

#include <iostream>

using std::cin;
using std::cout;
using std::endl;

void swap(int &value1, int &value2);



// SELECTION SORT ALGORITHM
void selectionSort(int arr[], const int ARRSIZE)
{
    for (int i = 0; i < ARRSIZE - 1; i++)
    {
        // Find the least number from the remaining array
        int leastIndex = i;
        for (int j = i + 1; j < ARRSIZE; j++)
        {
            if (arr[j] < arr[leastIndex])
            {
                leastIndex = j;
            }
        }

        // Swap if there was a least number found
        if (leastIndex != i)
        {
            swap(arr[i], arr[leastIndex]);
        }
    }
}

void swap(int &value1, int &value2)
{
    int temp = value1;
    value1 = value2;
    value2 = temp;
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
    selectionSort(arr, arrSize);


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