// Selection Sort (O(n^2)) also called Linear Sort 
// Language: Java
package selectionSort;

// Package that contains the generateArray(integer, integer) method. MAKE SURE THE GenerateArray-Java PROJECT IS LOADED IN THE 
// ALGORITHMS/GenerateToSort/GenerateArray-Java FOLDER
import randomArrayJava.RandomlyGenerateArray;

public class SelectionSort 
{
	// SELECTION SORT ALGORITHM
	public static int[] selectionSort(int[] arr)
	{
		for (int i = 0; i < arr.length; i++)
		{
			int smallestNumberIndex = i;
			for (int j = i + 1; j < arr.length; j++)
			{
				if (arr[j] < arr[smallestNumberIndex])
				{
					smallestNumberIndex = j;
				}
			}
			int temp = arr[i];
			arr[i] = arr[smallestNumberIndex];
			arr[smallestNumberIndex] = temp;
		}
		return arr;
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
		
		
		// Sorting the array
		int[] sortedArray = selectionSort(arrayToBeSorted);
		System.out.print("Sorted Array: { ");
		for (int i : sortedArray)
		{
			System.out.print(i + " ");
		}
		System.out.println(" }");
	}
}
