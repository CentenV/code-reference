// Sorting an array using the an additional array method (inefficient)
// Language: C++
/*
Example:
{ 1234, 412, 45, 7, 27, 988, 8, 10, 8 } (ORIGINAL ARRAY)
1234 412 45 7 27 988 8 10 8
7 8 8 10 27 45 412 988 1234
*/

#include <iostream>

using namespace std;

/*
int* additionalArraySort(int arr, int size)
{
    int newArray[size];
}*/

int main()
{
    int integerArray[] = { 1234, 412, 45, 7, 27, 988, 8, 10, 8 };
    const int arrSize = (sizeof(integerArray) / sizeof(integerArray[0]));

    int newIntegerArray[arrSize];
    for (int i = 0; i < arrSize; i++)
    {
        int leastNumber = integerArray[i];
        int leastNumberIndex;
        for (int z = i + 1; i < arrSize - 1; i++)
        {
            if ((integerArray[z] < leastNumber) && (integerArray[z] != NULL))
            {
                leastNumber = integerArray[z];
                leastNumberIndex = z;
            }
        }
        newIntegerArray[i] = leastNumber;
    }
    for (int d : newIntegerArray)
    {
        cout << d << " ";
    }

    //int* newIntegerArray = additionalArraySort(integerArray, arrSize);
}