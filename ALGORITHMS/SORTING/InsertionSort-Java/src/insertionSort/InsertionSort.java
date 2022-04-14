// Insertion Sort (O(n^2))
// Language: Java
package insertionSort;

// Package that contains the generateArray(integer, integer) method. MAKE SURE THE GenerateArray-Java PROJECT IS LOADED IN THE 
// ALGORITHMS/GenerateToSort/GenerateArray-Java FOLDER
import randomArrayJava.RandomlyGenerateArray;

public class InsertionSort 
{
	public static int[] insertionSort(int[] arr)
	{
		for (int i = 1; i < arr.length; i++)
		{
			int currentValue = arr[i];
			int possibleIndex = i;
			
			while (possibleIndex > 0 && currentValue < arr[possibleIndex-1])
			{
				arr[possibleIndex] = arr[possibleIndex-1];
				possibleIndex--;
			}
			
			arr[possibleIndex] = currentValue;
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
		int[] sortedArray = insertionSort(arrayToBeSorted);
		System.out.print("Sorted Array: { ");
		for (int i : sortedArray) 
		{
			System.out.print(i + " ");
		}
		System.out.println("}");
	}
}
