// Finding the Subnet and Broadcast Address (Starting and Ending Address) of a Network
// Language: Java
package ipAddress;

import ipAddress.IPException;
import java.io.Console;

// Main Class
public class IPAddress 
{
	final static int[] qualifiedNumbers = {0b00000000, 0b10000000, 0b11000000, 0b11100000, 0b11110000, 0b11111000, 0b11111100, 0b11111110, 0b11111111};

	// Checks whether a string contains only numbers and 3 periods/4 octets and has no other characters
	public static void checkString(String str, boolean isSubnetAddress) throws IPException 
	{
		// CIDR Notation Checking
		boolean cidrNotation = false;
		if (isSubnetAddress) 
		{
			if (str.charAt(0) == '/') 
			{
				cidrNotation = true;
				// Checking that the string meets the length requirements for cidr notation
				if (str.length() < 2 || str.length() > 3) 
				{
					throw new IPException("Invalid CIDR Notation, Not in range");
				}
				// Checking that there is only one /
				int slashCounter = 1;
				for (int i = 1; i < str.length(); i++) 
				{
					if (Character.isDigit(str.charAt(i))) 
					{
						continue;
					} 
					else if (str.charAt(i) == '/') 
					{
						slashCounter++;
					} 
					else 
					{
						throw new IPException("Invalid Characters Entered");
					}

					if (slashCounter != 1) 
					{
						throw new IPException("More than one /");
					}
				}
				return;
			}
		}
		// Standard Notation Checking
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
				throw new IPException("Invalid Characters Entered");				
			}
		}
		if (periodCounter != 3 && !cidrNotation)
		{
			throw new IPException("Not the correct format. Must be in #.#.#.# format");
		}
	}

	// Splits the inputted address from string to integer arrays that can be managed by code
	public static int[] parse(String str) throws IPException
	{
		int[] octet = new int[4];
		String[] octetString = {"", "", "", ""};
		
		// CIDR Notation Conversion and Checking
		if (str.contains("/"))
		{
			Integer cidrInteger = Integer.parseInt(str.substring(1));
			if (cidrInteger > 32 || cidrInteger < 0)
			{
				throw new IPException("Cidr notation invalid, number out of range");
			}
			for (int c = 0; c < octet.length; c++)
			{
				if (cidrInteger >= 8)
				{
					octet[c] = qualifiedNumbers[8];
					cidrInteger -= 8;
				}
				else
				{
					octet[c] = qualifiedNumbers[cidrInteger];
				}
			}
			return octet;
		}
		
		// Standard IP Checking
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
	public static boolean checkAddress(int[] subnet, boolean isSubnetAddress) throws IPException
	{
		// Checking whether octets are in bounds
		for (int f : subnet)
		{
			if (f < 0 || f > 255)
			{
				throw new IPException("Octet numbers are out of bounds 0-255");
			}
		}

		if (isSubnetAddress)
		{
			// Checking subnet mask to make sure all octets are in order (i.e. not 255.0.240.0, 255.248.128.0)
			for (int i = 1; i < subnet.length; i++)
			{
				if (subnet[i] > subnet[i-1])
				{
					throw new IPException("Invalid Subnet Mask, Octet numbers are invalid");
				}
				if (subnet[i-1] != 255 && subnet[i] > 0)
				{
					throw new IPException("Invalid Subnet Mask, Octet numbers are invalid");
				}
			}

			// Checking whether the subnet mask is in the valid bit order
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
						throw new IPException("Invalid Subnet Mask, Octet numbers do not meet the proper binary number requirement");
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

	
	// Determines what class 
	public static String determineIPClass(int[] ip, int[] subnet)
	{
		// Calculate Total Number of Bits
		int numberOfBits = 0;
		for (int i = 0; i < subnet.length; i++)
		{
			if (subnet[i] == 0)
			{
				continue;
			}
			for (int z = 1; z < qualifiedNumbers.length; z++)
			{
				if (subnet[i] == qualifiedNumbers[z])
				{
					numberOfBits += z;
				}
			}
		}
		System.out.println("\nNumber of Bits in the Subnet Address: " + numberOfBits);
		
		// Return Letter Type
		if ((ip[0] >= 0 && ip[0] <= 126) && (numberOfBits == 8))
		{
			return "Class A";
		}
		else if (ip[0] == 127)
		{
			return "Loopback Address, Localhost";
		}
		else if ((ip[0] >= 128 && ip[0] <= 191) && (numberOfBits == 16))
		{
			return "Class B";
		}
		else if ((ip[0] >= 192 && ip[0] <= 223) && (numberOfBits == 24))
		{
			return "Class C";
		}
		else if (ip[0] >= 224 && ip[0] <= 239)
		{
			return "Class D";
		}
		else if (ip[0] >= 240 && ip[0] <= 255)
		{
			return "Class E";
		}
		else
		{
			return "Classless";
		}
	}

	
	// Calculates how many possible IPs there are
	public static int numberOfPossibleIPs(String startIPString, String endIPString)
	{
		// Parsing the IPs back into integer arrays
		int[] startIP = null;
		int[] endIP = null;
		try
		{
			startIP = parse(startIPString);
			endIP = parse(endIPString);
		}
		catch (IPException e)
		{
			System.out.println("Error Calculating Number of Possible IPs: String not properly parsed");
			return (Integer) null;
		}
		
		// Finding the difference between each octet
		int[] octetDifferences = new int[4];
		for (int i = 0; i < 4; i++)
		{
			octetDifferences[i] = endIP[i] - startIP[i];
		}

		// Calculation of total IPs between the first and last address of the network
		int totalNumberOfIPs = 1;
		for (int z = 0; z < octetDifferences.length; z++)
		{
			if (octetDifferences[z] > 0)
			{
				totalNumberOfIPs *= octetDifferences[z];
			}
			if (z == octetDifferences.length - 1 && octetDifferences[z] == 0)
			{
				totalNumberOfIPs *= octetDifferences[z];
			}
		}
		
		if (totalNumberOfIPs-2 < 0)
		{
			return 0;
		}
		else
		{
			return totalNumberOfIPs-2;
		}
	}

	
	// Running the program
	private static void selfExecuteIpAddress()
	{
		int[] ipAddress = null;
		int[] subnetMask = null;
		
		// Get input from user and check it immediately
		Console getData = System.console();
		if (getData == null)
		{
			System.out.println("No console is available");
			System.exit(1);
		}
		
		System.out.println("FINDING THE SUBNET (FIRST) AND BROADCAST (LAST) IP OF A NETWORK\n");
		
		System.out.print("IP Address: ");
		String ipAddr = getData.readLine();
		try
		{
			// Checks IP address string to make sure they contain only numbers and periods
			checkString(ipAddr, false);
			// Convert IP string to integer arrays
			ipAddress = parse(ipAddr);
			// Checks the validity of IP address
			checkAddress(ipAddress, false);
		}
		catch (IPException e)
		{
			System.out.println("\n" + e);
			return;
		}
		
		System.out.print("Subnet Mask: ");
		String subnetAddr = getData.readLine();
		try
		{
			// Checks subnet address string to make sure they contain only numbers and periods
			checkString(subnetAddr, true);
			// Convert subnet string to integer arrays
			subnetMask = parse(subnetAddr);
			// Checks the validity of subnet address
			checkAddress(subnetMask, true);
		}
		catch (IPException e)
		{
			System.out.println("\n" + e);
			return;
		}

		// Calculate and Output Subnet and Broadcast Address			(IMPLEMENT IP CLASS)
		System.out.println("\nSubnet Address (First Address): " + findSubnetAddress(ipAddress, subnetMask));
		System.out.println("Broadcast Address (Last Address): " + findBroadcastAddress(ipAddress, subnetMask));
		System.out.println("Class/Type: " + determineIPClass(ipAddress, subnetMask));
		System.out.println("There are " + numberOfPossibleIPs(findSubnetAddress(ipAddress, subnetMask), findBroadcastAddress(ipAddress, subnetMask)) + " possible host(s)");
	}


	public static boolean replay()
	{
		Console getRun = System.console();
		System.out.print("\n\n Enter Y to enter a new IP: ");
		String runStatus = getRun.readLine();
		if (runStatus.equals("Y"))
		{
			for (int t = 0; t < 10; t++)
			{
				System.out.println("");
			}
			return true;
		}
		else
		{
			return false;
		}
	}


	public static void main(String[] args) 
	{
		boolean run = true;
		while (run)
		{
			selfExecuteIpAddress();
			run = replay();
			System.out.print("\033[H\033[2J");  
		}
	}

}