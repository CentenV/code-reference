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

void sort();
void swap(int& value1, int& value2);

void sort()
{
    int arr[] = { 145, 76345, 7145, 53246, 132, 65346, 3214, 4235, 1354 };
    int size = (sizeof(arr) / sizeof(arr[1]));
    for (int i = 0; i < size - 1; i++)
    {
        for (int a = 0; a < a - i - 1; a++)
        {
            if (arr[a] < arr[a+1]) 
            {
                swap(arr[a], arr[a + 1]);
            }
        }
    }

    for (int i = 0; i < size; i++)
    {
        cout << arr[i] << " ";
    }
}

void swap(int& value1, int& value2)
{
    int tmp = value1;
    value1 = value2;
    value2 = tmp;
}

int main()
{
    //int integerArray[] = { 145, 76345, 7145, 53246, 132, 65346, 3214, 4235, 1354 };
    //int numberofElements = sizeof(integerArray) / sizeof(integerArray[0]);

    sort();
}