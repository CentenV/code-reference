// Finding the Subnet and Broadcast Address (Starting and Ending Address) of a Network
// Language: Java
package ipAddress;

import java.util.Scanner;

public class IPAddress 
{
	/*public static boolean checkString(String str) 
	{
		for (String i : str)
		{
			if (str.equals(""))
		}
	}*/

	public static int[] parse(String str)
	{
		int[] octet = new int[4];
		String[] octetString = {"", "", "", ""};
		int currentOctet = 0;
		for (int i = 0; i < str.length(); i++)
		{
			System.out.println(str.charAt(i));
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
	
	public static String findBroadcastAddress(int[] ipAddress, int[] subnetMask)
	{
		String broadcastAddress = new String();
		for (int x = 0; x < 4; x++)
		{
			Integer number;
			System.out.println(bitflip(subnetMask[x]));
			number = ipAddress[x] | bitflip(subnetMask[x]);
			broadcastAddress += number.toString();
			if (x != 3)
			{
				broadcastAddress += ".";
			}
		}
		return broadcastAddress;
	}
	
	public static void main(String[] args) 
	{/*
		Scanner getIpAddress = new Scanner(System.in);
		Scanner getSubnetMask = new Scanner(System.in);
		System.out.print("IP Address: ");
		String userIpAddress = getIpAddress.nextLine();
		System.out.print("Subnet Mask: ");
		String userSubnetMask = getSubnetMask.nextLine();*/
		
		
		int[] testipaddr = {192,168,1,109};
		int[] testsubnet = {255, 255, 255, 0};
		
		System.out.println(findBroadcastAddress(testipaddr, testsubnet));
		
		/*String testipaddr = "255.255.255.255";
		
		int[] test = parse(testipaddr);
		for (int i : test)
		{
			System.out.print(i + " ");
		}*/
		
;	}
	
	public static int bitflip(int integer)
	{
		int x = (int)(Math.log(integer) / Math.log(2)) + 1;
        for (int i = 0; i < x; i++)
        {
        	integer = (integer ^ (1 << i));
        }
        return integer;
	}

}
