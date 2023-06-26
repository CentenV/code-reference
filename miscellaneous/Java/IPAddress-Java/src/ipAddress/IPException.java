package ipAddress;

public class IPException extends Exception
{
	String msg;
	public IPException(String errorMessage)
	{
		super(errorMessage);
		msg = errorMessage;
	}
	public String toString()
	{
		return "Error: " + msg;
	}
}


//CLASSES FOR EXCEPTIONS
/* 
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
		System.out.println("\nError: Invalid Subnet Mask, Octet numbers are invalid");
		return false;
	}
	public static boolean subnetOutofBoundsError() 
	{
		System.out.println("\nError: Invalid Subnet Mask, Octet numbers are out of bounds 0-255");
		return false;
	}
	public static boolean subnetIncorrectBinaryNumber() 
	{
		System.out.println("\nError: Invalid Subnet Mask, Octet numbers do not meet the proper binary number requirement");
		return false;
	}
	public static boolean cidrNotationError(String additionalError)
	{
		System.out.println("\nError: Invalid CIDR Notation, " + additionalError);
		return false;
	}
}*/