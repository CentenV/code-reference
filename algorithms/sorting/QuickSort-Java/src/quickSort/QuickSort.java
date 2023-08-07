// QUICK SORT (O(nlog(n)))
// Java

package quickSort;

public class QuickSort
{
	public static void quickSort(int[] arr, int start, int end)
	{
		if (start < end)
		{
			int partition_index = partition(arr, start, end);
			quickSort(arr, start, partition_index - 1);
			quickSort(arr, partition_index + 1, end);
		}
	}
	
	public static int partition(int[] arr, int start, int end)
	{
		// Last element used as pivot
		int pivot = arr[end];
		// Index of the greater number
		int lower_index = start - 1;
		
		for (int i = start; i < end; i++)
		{
			if (arr[i] < pivot)
			{
				lower_index++;
				
				// Swap
				swap(arr, lower_index, i);
			}
		}
		
		// Swap
		swap(arr, lower_index + 1, end);
		
		return lower_index + 1;
	}
	
	public static void swap(int[] arr, int i1, int i2)
	{
		int temp = arr[i1];
		arr[i1] = arr[i2];
		arr[i2] = temp;
	}
	
	
	public static void main(String[] args)
	{
		int array[] = {8, 17, 38, 48, 88, 70, 91, 22, 48, 49, 36, 83, 26, 3, 4};
		
		// Initial Array
		System.out.println("Initial array:");
		for (int i : array)
		{
			System.out.print(i + " ");
		}
		
		
		quickSort(array, 0, array.length - 1);
		
		
		// Sorted Array
		System.out.println("\nSorted array:");
		for (int i : array)
		{
			System.out.print(i + " ");
		}
	}
}
