// Binary Search, Recursively, O(log n), TERMS HAVE TO BE SORTED
// Language: Java

package binarySearchRecursive;

// Package that contains the generateArray(integer, integer) method. MAKE SURE THE GenerateArray-Java PROJECT IS LOADED IN THE 
// ALGORITHMS/GenerateToSort/GenerateArray-Java FOLDER
import randomArrayJava.RandomlyGenerateArray;
import tempsort.TempSort;

import java.util.Scanner;

public class BinarySearchRecursive
{
	private static int binarySearch_Recursive(int start, int end, int[] array, int number)
	{
		while (start <= end)
		{
			int middle = (start + end) / 2;
			if (number < array[middle])
			{
				return binarySearch_Recursive(start, middle-1, array, number);
			}
			else if (number > array[middle])
			{
				return binarySearch_Recursive(middle+1, end, array, number);
			}
			else if (array[middle] == number)
			{
				return middle;
			}
		}
		
		return -1;
	}

	public static void main(String[] args)
	{
		// Generate array and print it
		int[] initArray = RandomlyGenerateArray.generateArray(10, 100);
		int[] array = TempSort.selectionSort(initArray); // The array needs to be sorted
		System.out.print("Initial Array: { ");
		for (int i : array)
		{
			System.out.print(i + " ");
		}
		System.out.println(" }");
		// Input the number to find in the array
		Scanner numberToFind = new Scanner(System.in);
		System.out.println("\nEnter a number to find in the array: ");
		int number = numberToFind.nextInt();
		numberToFind.close();

		// Searching through array and outputting result
		int foundIndex = binarySearch_Recursive(0, array.length-1, array, number);
		if (foundIndex != -1)
		{
			System.out.println("\n" + number + " is at " + foundIndex);
		} else
		{
			System.out.println("\n" + number + " is not in the array.");
		}
	}

}
