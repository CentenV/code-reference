// Selection Sort
// Language: C++
/*
Example:
{ 223, 564, 54, 123, 4, 24 } (ORIGINAL ARRAY)
4 564 54 123 223 24
4 24 54 123 223 564
*/

#include <iostream>

using std::cout;

void selectionSort()
{
    // TEMP ARRAY
    int arr[6] = { 223, 564, 54, 123, 4, 24 };
    int sizeofArray = (sizeof(arr) / sizeof(arr[1]));
    for (int i = 0; i < sizeofArray - 1; i++)
    {
        int leastNumber, leastNumber_Index = -1;
        for (int a = i; a < sizeofArray - 1; a++)
        {
            if (arr[a] < arr[a + 1])
            {
                leastNumber = arr[a];
                leastNumber_Index = a;
            }
        }
        if (leastNumber_Index != i)
        {
            int temp = arr[i];
            arr[i] = arr[leastNumber_Index];
            arr[leastNumber_Index] = temp;
        }
    }
}


int main()
{
    selectionSort();
}