// QUICK SORT (O(nlog(n)))
// Language: C++

#include <iostream>

using std::cout;
using std::endl;


// MERGE SORT ALGORITHM
void merge(int arr[], int start, int middle, int end)
{
	int mergeArrSize = end - start + 1;
	int *tempArr = new int[mergeArrSize];
	int i1 = start;
	int i2 = middle + 1;
	int j = 0;

	while (i1 <= middle && i2 <= end)
	{
		if (arr[i1] < arr[i2])
		{
			tempArr[j] = arr[i1];
			i1++;
		}
		else
		{
			tempArr[j] = arr[i2];
			i2++;
		}
		j++;
	}

	while (i1 <= middle)
	{
		tempArr[j] = arr[i1];
		i1++;
		j++;
	}
	while (i2 <= middle)
	{
		tempArr[j] = arr[i2];
		i2++;
		j++;
	}

	for (int i = 0; i < mergeArrSize; i++)
	{
		arr[start + i] = tempArr[i];
	}

	delete[] tempArr;
}

void mergeSort(int arr[], int start, int end)
{
	if (start >= end)
	{
		return;
	}

	int middle = (start + end) / 2;

	mergeSort(arr, start, middle);
	mergeSort(arr, middle + 1, end);
	merge(arr, start, middle, end);
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
		cout << arr[i] << " ";
	}
	cout << " }" << endl;

	mergeSort(arr, 0, arrSize - 1);

	cout << "Sorted Array: { ";
	for (int i = 0; i < arrSize; i++)
	{
		cout << arr[i] << " ";
	}
	cout << " }" << endl;
}