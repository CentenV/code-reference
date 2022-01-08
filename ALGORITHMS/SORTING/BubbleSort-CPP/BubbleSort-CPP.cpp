// Sorting an array using the BUBBLE SORT method
// Language: C++
/*
Example:
{ 145, 76345, 7145, 53246, 132, 65346, 3214, 4235, 1354 } (ORIGINAL ARRAY)
145 76345 7145 53246 132 65346 3214 4235 1354
145 7145 53246 132 65346 3214 4235 1354 76345
145 7145 132 53246 3214 4235 1354 65346 76345
145 132 7145 3214 4235 1354 53246 65346 76345
132 145 3214 4235 1354 7145 53246 65346 76345
132 145 3214 1354 4235 7145 53246 65346 76345
132 145 1354 3214 4235 7145 53246 65346 76345
132 145 1354 3214 4235 7145 53246 65346 76345
*/

#include <iostream>

using namespace std;

// Functions used
void bubbleSort(int arr[], int size);
void swap(int* value1, int* value2);

// Bubble Sort Algorithm
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
                swap(arr[a], arr[a + 1]);
            }
        }
    }
}

// Swapping the two values when the number precedence condition is met
void swap(int* value1, int* value2)
{
    int tmp = *value1;
    *value1 = *value2;
    *value2 = tmp;
}

// Main Code Execution
int main()
{
    int integerArray[] = { 145, 76345, 7145, 53246, 132, 65346, 3214, 4235, 1354 };
    int size = (sizeof(integerArray) / sizeof(integerArray[1]));
    bubbleSort(integerArray, size);
    for (int x : integerArray)
    {
        cout << x << " ";
    }
}