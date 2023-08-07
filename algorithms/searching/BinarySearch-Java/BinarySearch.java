// Binary Search O(log n), TERMS HAVE TO BE SORTED
// Language: Java
import java.util.Scanner;

public class BinarySearch 
{
	// BINARY SEARCH ALGORITHM
	public static int binarySearch(int[] arr, int targetValue)
	{
		int leftBound = 0;
		int rightBound = arr.length - 1;
		while (leftBound <= rightBound) 
		{
			int middle = rightBound - leftBound;
			if (arr[middle] == targetValue)
			{
				return middle;
			}
			else if (targetValue < arr[middle]) 
			{
				rightBound = middle - 1;
			}
			else if (targetValue > arr[middle]) 
			{
				leftBound = middle + 1;
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
		int foundIndex = binarySearch(array, number);
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
