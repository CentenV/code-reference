package tempsort;

public class TempSort
{
	// TEMPORARY SELECTION SORT ALGORITHM
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
