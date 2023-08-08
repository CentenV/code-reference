// Bubble Sort      O(n^2)
// Language: C++

#include <iostream>

using std::cout;
using std::endl;

void swap(int*, int*);



// BUBBLE SORT ALGORITHM
void bubbleSort(int arr[], int size)
{
    // Loop for traversing through the array once each time
    for (int i = 0; i < size - 1; i++)
    {
        // Loop for "bubbling" a variable and moving it up
        for (int z = 0; z < size - i - 1; z++)
        {
            // If the number is greater than the next number in the index, it will swap them (moving the greater number up)
            if (arr[z] > arr[z + 1])
            {
                swap(&arr[z], &arr[z + 1]);
            }
        }
    }
}

void swap(int *value1, int *value2)
{
    int tmp = *value1;
    *value1 = *value2;
    *value2 = tmp;
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
    bubbleSort(arr, arrSize);


    // Print out sorted array
    cout << "Sorted Array: { ";
    for (int i = 0; i < arrSize; i++)
    {
        cout << arr[i];
        if (i < arrSize - 1)
        {
            cout << " ";
        }
    }
    cout << " }" << endl;
}