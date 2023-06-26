package hashing;

import hashing.HashOptions;		// Custom enum for all hashing options 

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing
{
	private static String hash(HashOptions algorithm, byte[] data)
	{
		MessageDigest m = algorithm.getAlgorithm();
		byte[] hashedBytes = m.digest(data);
		return bytesToHex(hashedBytes);
	}
	
	private static String bytesToHex(byte[] bytes)
	{
		StringBuilder hex = new StringBuilder();
		for (byte b : bytes)
		{
			hex.append(String.format("%02x", b));
		}
		return hex.toString();
	}
	
	
	public static void main(String[] args)
	{
		// Test hash
		// d7444e8afb74b9b3c8c8be9f15fb64eddc0414960d9e2691c465740d58573eff
		// hashFile();
		// hashText();

		if (System.console() == null)
		{
			System.out.println("No console found");
			return;
		}

		while (true)
		{
			System.out.println("\nHASHING");
			System.out.println("1) Hash Text");
			System.out.println("2) Hash File");
			System.out.println("3) Exit");

			System.out.print("\nEnter Option: ");
			String optionInput = System.console().readLine();

			int option;
			try
			{
				option = Integer.parseInt(optionInput);
			}
			catch (NumberFormatException e)
			{
				System.out.println("ENTER A VALID OPTION");
				continue;
			}

			switch (option)
			{
				case 1:
					// Text Hashing
					hashText();
					break;
				case 2:
					// File Hashing
					hashFile();
					break;
				case 3:
					System.out.println("Exiting.");
					System.exit(0);
				default:
					System.out.println("ENTER A VALID OPTION");
					break;
			}
		}
	}
	
	private static void hashText()
	{
		System.out.println("\n\n\n\n\nTEXT HASHING");
		System.out.print(": ");
		String input = System.console().readLine();
		
		HashOptions hashAlg = hashOptionsMenu();
		
		System.out.println(hash(hashAlg, input.getBytes()));
		System.out.println("\n\n\n");
	}
	
	private static void hashFile()
	{
		File file;
		FileInputStream readFile;
		System.out.println("\n\n\n\n\nFILE CHECKSUM");
		
		while (true)
		{
			System.out.print(": ");
			String filePath = System.console().readLine();
			
			try
			{
				file = new File(filePath);
				readFile = new FileInputStream(file);
			}
			catch (FileNotFoundException e)
			{
				System.out.println("Enter a valid file");
				continue;
			}
			break;
		}
		
		byte[] chunkData = new byte[1024];
		int numberOfBytes = 0;
		
		HashOptions hashAlg = hashOptionsMenu();
		
		try
		{
			MessageDigest m = hashAlg.getAlgorithm();
			
			while ((numberOfBytes = readFile.read(chunkData)) != -1)
			{
				if (m != null)
				{
					m.update(chunkData, 0, numberOfBytes);
				}
			}
			
			byte[] b = m.digest();
			System.out.println(bytesToHex(b));
			System.out.println("\n\n\n");
			
			readFile.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	
	private static HashOptions hashOptionsMenu()
	{
		System.out.println("\nChoose Hashing Algorithm");
		System.out.println("1) SHA-256");
		System.out.println("2) MD5");
		System.out.println("3) MD2");
		System.out.println("4) SHA-1");
		System.out.println("5) SHA-384");
		System.out.println("6) SHA-512");
		
		int option;
		while (true)
		{
			System.out.print("\n: ");
			String input = System.console().readLine();
			
			try
			{
				option = Integer.parseInt(input);
			}
			catch (NumberFormatException e) 
			{
				System.out.println("Enter a valid input");
				continue;
			}
			
			switch (option)
			{
				case 1:
					return HashOptions.SHA256;
				case 2:
					return HashOptions.MD5;
				case 3:
					return HashOptions.MD2;
				case 4:
					return HashOptions.SHA1;
				case 5:
					return HashOptions.SHA384;
				case 6:
					return HashOptions.SHA512;
				default:
					System.out.println("Enter a valid input");
			}
		}
	}
}