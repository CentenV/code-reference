// Linear Search (multi occurrance)
// Language: Java
import java.util.ArrayList;
import java.util.Scanner;

public class LinearSearchMultiOccur 
{
    // LINEAR SEARCH ALGORITHM
	public static ArrayList<Integer> linearSearch(int[] arr, int target)
	{
		ArrayList<Integer> indexFound = new ArrayList<>();

		for (int i = 0; i < arr.length; i++)
		{
			if (arr[i] == target)
			{
				indexFound.add(i);
			}
		}
		
		return indexFound;
	}



    public static void main(String[] args) 
    {
        // Generate array and print it
		int[] array = generateArray(10, 100);
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

		// Prints out the result of performing a sequential/linear search
		ArrayList<Integer> foundIndexes = linearSearch(array, number);
		
		if (foundIndexes.size() > 0)
		{
			System.out.print("\n" + number + " is at: " + foundIndexes);
		}
		else
		{
			System.out.print("\n" + number + " does not occur.");
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
}
