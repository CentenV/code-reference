package linearSearch;

import java.util.ArrayList;
import java.util.Scanner;

public class LinearSearch 
{
	// Linear Search Algorithm
	public static ArrayList<Integer> linearSearch(int[] arr, int number)
	{
		ArrayList<Integer> indexFound = new ArrayList<>();

		for (int i = 0; i < arr.length; i++)
		{
			if (arr[i] == number)
			{
				indexFound.add(i);
			}
		}
		
		return indexFound;
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

		// Prints out the result of performing a sequential/linear search
		ArrayList<Integer> foundIndexes = linearSearch(array, number);
		if (foundIndexes.size() > 0)
		{
			System.out.print("\n" + number + " is at: ");
			for (Integer a : foundIndexes)
			{
				System.out.print(a + " ");
			}
		}
		else
		{
			if (foundIndexes.size() == 0)
			{
				System.out.print("\n" + number + " does not occur.");
			}
		}
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