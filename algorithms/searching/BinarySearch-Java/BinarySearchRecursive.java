// Binary Search, Recursively, O(log n), TERMS HAVE TO BE SORTED
// Language: Java
import java.util.Scanner;

public class BinarySearchRecursive
{
	// BINARY SEARCH ALGORITHM (RECURSIVE)
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
		// Generate array, sort it, and print it
		int[] initArray = generateArray(10, 100);
		int[] array = selectionSort(initArray);
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
		}
		else
		{
			System.out.println("\n" + number + " is not in the array.");
		}
	}



	// Boilerplate Array Generation
	public static int[] generateArray(int amountOfNumbers, int maximumValue)
	{
		int[] arr = new int[amountOfNumbers];
		for (int i = 0; i < amountOfNumbers; i++)
		{
			arr[i] = (int)(Math.random()*maximumValue);
		}
		return arr;
	}
	// TEMPORARY Selection Sort Algorithm
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
}
