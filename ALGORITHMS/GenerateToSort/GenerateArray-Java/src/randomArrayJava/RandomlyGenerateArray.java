package randomArrayJava;

public class RandomlyGenerateArray
{
    public static int[] generateArray(int amountOfNumbers, int maximumValue)
	{
		int[] arr = new int[amountOfNumbers];
		for (int i = 0; i < amountOfNumbers; i++)
		{
			arr[i] = (int)(Math.random()*maximumValue+1);
		}
		return arr;
	}
}