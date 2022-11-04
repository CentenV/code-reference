// QUICK SORT (O(nlog(n)))
#include <iostream>

using std::cout;
using std::endl;


// All required functions 
void quickSort(int arr[], int start, int end);
int partition(int arr_p[], int start, int end);

void swap(int* n1, int* n2);


void quickSort(int arr[], int start, int end)
{
    if (start < end)
    {
        int partition_index = partition(arr, start, end);

        quickSort(arr, start, partition_index - 1);
        quickSort(arr, partition_index + 1, end);
    }
}

int partition(int arr_p[], int start, int end)
{
    int pivot = arr_p[end];
    int lower_index = start - 1;

    for (int i = start; i < end; i++)
    {
        if (arr_p[i] < pivot)
        {
            lower_index++;  
            swap(&arr_p[lower_index], &arr_p[i]);
        }
    }

    swap(&arr_p[lower_index + 1], &arr_p[end]);

    return lower_index + 1;
}


void swap(int *n1, int *n2)
{
    int temp = *n1;
    *n1 = *n2;
    *n2 = temp;
}


int main()
{
    int unsorted_list[] = { 1, 64, 32, 3, 4, 5, 10, 34, 65, 7, 8, 1 };

    // Initial arr
    cout << "Initial array" << endl;
    for (int x : unsorted_list)
    {
        cout << x << " ";
    }
    cout << endl;


    quickSort(unsorted_list, 0, (sizeof(unsorted_list) / sizeof(unsorted_list[0])-1));


    // Sorted 
    cout << "Sorted array" << endl;
    for (int x : unsorted_list)
    {
        cout << x << " ";
    }
    cout << endl;
}