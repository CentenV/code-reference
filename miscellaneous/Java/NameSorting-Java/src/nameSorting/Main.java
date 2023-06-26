// Alphabetic/Numeric Sorting of Strings (Sorted Linearly, Selection Sort)
// Language: Java

package nameSorting;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Main
{
	static File namesFile = null;
	// Get the Operating System type
	final static String osType = System.getProperty("os.name");
	
	// Printing the menu for options
	private static int printMenu()
	{
		clearScreen();
		
		// Display file/configuration information
		if (namesFile != null)
		{
			try
			{
				System.out.println("File: " + namesFile.getAbsolutePath());
				System.out.println("Names: " + namesFile.length() + "\n");
			}
			catch (SecurityException e)
			{
				System.out.println("File access denied.");
			}
		}
		else
		{
			System.out.println("File: <NO FILE LOADED>");
			System.out.println("Names: <NO FILE LOADED>\n");
		}
		
		// Options
		System.out.println("1) LOAD a list/file of names");
		System.out.println("2) PRINT names from a list/file");
		System.out.println("3) REMOVE names from a list/file");
		System.out.println("4) Reload Program (unloads file)");
		System.out.println("5} EXIT");
		
		while (true)
		{
			int option = -1;
			String optionInput = getInput("\nEnter Option: ");
			try
			{
				option = Integer.parseInt(optionInput);
			}
			catch (NumberFormatException e)
			{
				System.out.println("\nENTER A VALID INPUT");
				continue;
			}
			
			if (option >= 1 && option <= 5)
			{
				return option;
			}
			else
			{
				System.out.println("\nENTER A VALID INPUT WITHIN RANGE");
			}
		}
	}
	
	// File Handling
	private static void loadFile()
	{
		clearScreen();
		
		while (true)
		{
			// Getting the file path
			String filePath = getInput("File Path: ");
			
			// Loading the file
			try
			{
				namesFile = new File(filePath);
			} 
			catch (NullPointerException e)
			{
				System.out.println("File Unsuccessfully Imported");
			}

			if (namesFile.isDirectory())
			{
				namesFile = null;
				System.out.println("Path Entered is a Directory");
				continue;
			} 
			else
			{
				break;
			}
		}

		// Prompt to create new file if not already existing
		if (!namesFile.exists())
		{
			while (true)
			{
				String createFileOption = getInput("File does not exist.\nCreate new file? (Y / n): ");
				if (createFileOption.equals("Y"))
				{
					try
					{
						namesFile.createNewFile();
					} 
					catch (IOException e)
					{
						System.out.println("Fatal Error");
						System.exit(1);
					}
					catch (SecurityException e) 
					{
						System.out.println("Permission Denied");
						while (true)
						{
							if (getInput("\nPress ENTER to return to the MENU ").length() == 0)
							{
								return;
							}
						}
					}
					
					System.out.println("File SUCCESSFULLY created");
					break;
				}
				else
				{
					System.out.println("File NOT created");
					try
					{
						Thread.sleep(2000);
					} 
					catch (InterruptedException e)
					{
						System.exit(1);
					}
					break;
				}
			}
			
		}
	}
	private static void unloadFile()
	{
		namesFile = null;
	}
	
	private static void printFile()
	{
		FileReader readFile = null;
		try
		{
			readFile = new FileReader(namesFile);
		} 
		catch (FileNotFoundException e)
		{
			System.out.println("READ ERROR");
			return;
		}
		
		try
		{
			int i;
			try
			{
				while ((i = readFile.read()) != -1)
				{
					System.out.println(i);
				}
			} 
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try
			{
				readFile.close();
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		catch (NullPointerException e)
		{
			System.out.println("No File Specified");
		}
	}
	
	// Sort in ASCIIbetical order
	private String[] asciibeticalSort(String[] strArr)
	{
		for (int i = 0; i < strArr.length; i++)
		{
			int leastIndex = i;
			for (int z = i + 1; z < strArr.length; z++)
			{
				if (strArr[z].charAt(0) < strArr[leastIndex].charAt(0))
				{
					leastIndex = z;
				}
			}
			String strTemp = strArr[i];
			strArr[i] = strArr[leastIndex];
			strArr[leastIndex] = strTemp;
		}
		
		return strArr;
	}
	
	// Sort in Lexicographical order
	/*
	private String[] lexicographicalSort(String[] strArr)
	{
		for (int i = 0; i < strArr.length; i++)
		{
			int leastIndex = i;
			for (int z = i + 1; z < strArr.length; z++)
			{
				String currentStr = strArr[z].toUpperCase();
				String leastindexStr = strArr[leastIndex].toUpperCase();
				if (currentStr.charAt(0) < leastindexStr.charAt(0))
				{
					leastIndex = z;
				}
			}
			String strTemp = strArr[i];
			strArr[i] = strArr[leastIndex];
			strArr[leastIndex] = strTemp;
		}
		
		for (int x = 1; x < strArr.length; x++)
		{
			if (strArr[x-1].charAt(0) == strArr[x].charAt(0))
		}
		
		return strArr;
	}*/
	
	
	public static void main(String[] args)
	{
		if (System.console() == null)
		{
			System.out.println("No console available");
			return;
		}
		
		boolean runApp = true;
		while (runApp)
		{
			switch (printMenu())
			{
			case 1:
				loadFile();
				break;
			case 2:
				printFile();
			case 4:
				unloadFile();
				break;
			case 5:
				clearScreen();
				System.out.println("\n\nPROGRAM EXIT.");
				System.exit(0);
			}
		}
		
		
		
		/*
		try
		{
			File names = new File("names.txt");
			
		}*/
		
		/*// Testing
		String[] str = {"Hello", "Alex", "Soap", "another one", "nope", "ASCII"};
		String[] strArr = lexicographicalSort(str);
		
		for (String s : strArr)
		{
			System.out.println(s);
		}*/
	}
	
	
	private static String getInput()
	{
		return System.console().readLine();
	}
	private static String getInput(String message)
	{
		System.out.print(message);
		String str = System.console().readLine();
		System.out.println();
		return str;
	}
	private static void clearScreen()
	{
		try
		{
			if (osType.contains("Windows"))
			{
				new ProcessBuilder("cmd", "/C", "cls").inheritIO().start().waitFor();
			}
			else
			{
				Runtime.getRuntime().exec("clear");
			}
		}
		catch (Exception e)
		{
			System.out.println("\nClear Screen Error: " + e + "\nOS Version: " + osType);
		}
	}
}