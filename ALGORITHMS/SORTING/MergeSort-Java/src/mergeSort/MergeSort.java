// Merge Sort (O(n log(n)))
// Language: Java
package mergeSort;

// Package that contains the generateArray(integer, integer) method. MAKE SURE THE GenerateArray-Java PROJECT IS LOADED IN THE 
// ALGORITHMS/GenerateToSort/GenerateArray-Java FOLDER
import randomArrayJava.RandomlyGenerateArray;

public class MergeSort
{
	// MERGE SORT ALGORITHM
	public static void mergeSort(int[] arr)
	{
		partition(arr, 0, arr.length-1);
	}
		// Partition for the merge sort
	private static void partition(int[] arr, int start, int end)
	{
		if (start < end)
		{
			int middle = (start + end) / 2;
			partition(arr, start, middle);
			partition(arr, middle+1, end);
			
		}
	}
		// Combining the numbers
	private static int[] merge(int[] arr)
	{
		
	}

	public static void main(String[] args)
	{
		// Generate array and print it
		int[] arrayToBeSorted = RandomlyGenerateArray.generateArray(10, 100);
		System.out.print("Initial Array: { ");
		for (int i : arrayToBeSorted)
		{
			System.out.print(i + " ");
		}
		System.out.println(" }");
	}

}
