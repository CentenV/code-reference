// Sorting an array using the BUBBLE SORT method
// Language: C++

#include <iostream>

using std::cout;
using std::endl;


// All required functions 
void bubbleSort(int arr[], int size);
void swap(int* value1, int* value2);


void bubbleSort(int arr[], int size)
{
    // Loop for traversing through the array once each time
    for (int i = 0; i < size - 1; i++)
    {
        // Loop for "bubbling" a variable and moving it up
        for (int a = 0; a < size - 1; a++)
        {
            // If the number is greater than the next number in the index, it will swap them (moving the greater number up)
            if (arr[a] > arr[a + 1])
            {
                swap(&arr[a], &arr[a + 1]);
            }
        }
    }
}

void swap(int* value1, int* value2)
{
    int tmp = *value1;
    *value1 = *value2;
    *value2 = tmp;
}


int main()
{
    int unsorted_list[] = { 14, 5, 76, 34, 5, 71, 45, 53, 2, 46, 1, 32 };

    // Initial Array
    cout << "Initial array" << endl;
    for (int x : unsorted_list)
    {
        cout << x << " ";
    }
    cout << endl;


    bubbleSort(unsorted_list, sizeof(unsorted_list) / sizeof(unsorted_list[0]));



    // Sorted Array
    cout << "Sorted array" << endl;
    for (int x : unsorted_list)
    {
        cout << x << " ";
    }
    cout << endl;
}