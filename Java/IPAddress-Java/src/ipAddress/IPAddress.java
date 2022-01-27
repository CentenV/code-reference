// Finding the Subnet and Broadcast Address (Starting and Ending Address) of a Network
// Language: Java
package ipAddress;

import java.util.Scanner;

public class IPAddress 
{
	class Error
	{
		public static boolean characterError()
		{
			System.out.println("\nError: Invalid Characters Entered.");
			return false;
		}
		public static boolean octetError()
		{
			System.out.println("\nError: Not the correct format. Must be in #.#.#.# format");
			return false;
		}
		public static boolean outofBoundsError()
		{
			System.out.println("\nError: Octet numbers are out of bounds 0-255");
			return false;
		}
		public static boolean subnetOrderError()
		{
			System.out.println("\nError: Invalid Subnet Mask: Octet numbers are invalid");
			return false;
		}
		public static boolean subnetOutofBoundsError() 
		{
			System.out.println("\nError: Invalid Subnet Mask: Octet numbers are out of bounds 0-255");
			return false;
		}
		public static boolean subnetIncorrectBinaryNumber() 
		{
			System.out.println("\nError: Invalid Subnet Mask: Octet numbers do not meet the proper binary number requirement");
			return false;
		}
	}
	
	
	// Checks whether a string contains only numbers and 3 periods/4 octets and has no other characters  
	public static boolean checkString(String str) 
	{
		int periodCounter = 0;		
		for (int i = 0; i < str.length(); i++)
		{
			if (Character.isDigit(str.charAt(i)) || (str.charAt(i) == '.'))
			{
				if (str.charAt(i) == '.')
				{
					periodCounter++;
				}
			}
			else
			{
				return Error.characterError();				
			}
		}
		if (periodCounter != 3)
		{
			return Error.octetError();
		}
		return true;
	}

	// Splits the inputted address from string to integer arrays that can be managed by code
	public static int[] parse(String str)
	{
		int[] octet = new int[4];
		String[] octetString = {"", "", "", ""};
		
		int currentOctet = 0;
		for (int i = 0; i < str.length(); i++)
		{
			if (str.charAt(i) == '.')
			{
				octet[currentOctet] = Integer.parseInt(octetString[currentOctet]);
				currentOctet++;
				continue;
			}
			octetString[currentOctet] += str.charAt(i);
		}
		octet[currentOctet] = Integer.parseInt(octetString[currentOctet]);
		return octet;
	}

	// Checks whether the parsed address is in bounds. If it is a subnet address, also checks that octets are numerically correct  
	public static boolean checkParsedAddress(int[] subnet, boolean isSubnetAddress)
	{
		// Checking whether octets are in bounds
		for (int f : subnet)
		{
			if (f < 0 || f > 255)
			{
				return Error.outofBoundsError();
			}
		}

		if (isSubnetAddress)
		{
			// Checking subnet mask to make sure all octets are in order (i.e. not 255.0.240.0)
			for (int i = 1; i < subnet.length; i++)
			{
				if (subnet[i] > subnet[i-1])
				{
					return Error.subnetOrderError();
				}
			}

			// Checking whether the subnet mask is in the valid bit order
			int[] qualifiedNumbers = {0b11111111, 0b11111110, 0b11111100, 0b11111000, 0b11110000, 0b11100000, 0b11000000, 0b10000000, 0b00000000};
			for (int i : subnet)
			{
				for (int z = 0; z < qualifiedNumbers.length; z++)
				{
					if (i == qualifiedNumbers[z])
					{
						break;
					}
					else if (z == qualifiedNumbers.length-1)
					{
						return Error.subnetIncorrectBinaryNumber();
					}
				}
			}
		}
		return true;
	}
	
	
	// Calculates the subnet (first) address and returns the string in proper address form
	public static String findSubnetAddress(int[] ipAddress, int[] subnetMask)
	{
		String subnetAddress = new String();
		for (int x = 0; x < 4; x++)
		{
			Integer number;
			number = ipAddress[x] & subnetMask[x];
			subnetAddress += number.toString();
			if (x != 3)
			{
				subnetAddress += ".";
			}
		}
		return subnetAddress;
	}
	
	// Calculates the broadcast (last) address and returns the string in proper address form
	public static String findBroadcastAddress(int[] ipAddress, int[] subnetMask)
	{
		String broadcastAddress = new String();
		for (int x = 0; x < 4; x++)
		{
			Integer number;
			number = ipAddress[x] | (~subnetMask[x] & 255);
			broadcastAddress += number.toString();
			if (x != 3)
			{
				broadcastAddress += ".";
			}
		}
		return broadcastAddress;
	}
	
	
	public static void calculateIpAddresses(String ipAddr, String subnetAddr)
	{
		if (!checkString(ipAddr) || !checkString(subnetAddr))
		{
			return;
		}
		
		int[] ipAddress = parse(ipAddr);
		int[] subnetMask = parse(subnetAddr);
		
		if (!checkParsedAddress(ipAddress, false) || !checkParsedAddress(subnetMask, true))
		{
			return;
		}
		
		System.out.println("\nSubnet Address (First Address): " + findSubnetAddress(ipAddress, subnetMask));
		System.out.println("Broadcast Address (Last Address): " + findBroadcastAddress(ipAddress, subnetMask));
	}
	
	public static void main(String[] args) 
	{
		boolean run = true;
		while (run)
		{
			Scanner getIpAddress = new Scanner(System.in);
			Scanner getSubnetMask = new Scanner(System.in);
			System.out.print("IP Address: ");
			String ipAddress_str = getIpAddress.nextLine();
			System.out.print("Subnet Mask: ");
			String subnetMask_str = getSubnetMask.nextLine();

			calculateIpAddresses(ipAddress_str, subnetMask_str);
			
			Scanner getRun = new Scanner(System.in);
			System.out.print("\n Enter Y to enter a new IP: ");
			String runStatus = getRun.nextLine();
			if (runStatus.equals("Y"))
			{
				for (int t = 0; t < 10; t++)
				{
					System.out.println("");
				}
				run = true;
				continue;
			}
			else
			{
				run = false;
				break;
			}
		}
	}

}
