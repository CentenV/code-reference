// Binary Search
// Language: Java

package binarySearch;

import java.util.Scanner;

public class BinarySearch 
{
	// BINARY SEARCH ALGORITHM
	public static int binarySearch(int[] arr, int target)
	{
		int leftBound = 0;
		int rightBound = arr.length;
		while (leftBound <= rightBound) 
		{
			int middle = rightBound - leftBound;
			if (arr[middle] == target)
			{
				return middle;
			}
			else if (target < arr[middle]) 
			{
				rightBound = middle - 1;
			}
			else if (target > arr[middle]) 
			{
				leftBound = middle + 1;
			}
		}
		return -1;
	}

	public static void main(String[] args) 
	{
		// Generate array
		int[] array = generateArray(20);
		System.out.print("The array: { ");
		for (int i : array)
		{
			System.out.print(i + " ");
		}
		System.out.println("}");

		// Input the number to find in the array
		Scanner numberToFind = new Scanner(System.in);
		System.out.println("\nEnter a number to find in the array: ");
		int number = numberToFind.nextInt();
	}



	// Randomly Generate the Array
	public static int[] generateArray(int amountOfNumbers)
	{
		int[] arr = new int[amountOfNumbers];
		for (int i = 0; i < amountOfNumbers; i++)
		{
			arr[i] = (int)(Math.random()*20+1);
		}
		return arr;
	}

}
