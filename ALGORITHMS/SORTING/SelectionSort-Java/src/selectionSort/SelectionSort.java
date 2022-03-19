// Selection Sort
// Language: Java

package selectionSort;

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
		int[] arrayToBeSorted = generateArray(10);
		System.out.print("{ ");
		for (int i : arrayToBeSorted)
		{
			System.out.print(i + " ");
		}
		System.out.println(" }");
		
		
		// Sorting the array
		int[] sortedArray = selectionSort(arrayToBeSorted);
		System.out.print("{ ");
		for (int i : sortedArray)
		{
			System.out.print(i + " ");
		}
		System.out.println(" }");
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
