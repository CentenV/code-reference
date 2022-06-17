package hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hashing
{
	public static String md5(String str)
	{
		try
		{
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			byte[] strBytes = md5.digest(str.getBytes());
			return bytesToHex(strBytes);
		}
		catch (NoSuchAlgorithmException e)
		{
			System.out.println("Internal Error, Bad Algorithm. MD5");
		}
		
		return "";
	}
	
	public static String sha256(String str)
	{
		try
		{
			MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
			byte[] strBytes = sha256.digest(str.getBytes());
			return bytesToHex(strBytes);
		}
		catch (NoSuchAlgorithmException e)
		{
			System.out.println("Internal Error, Bad Algorithm. SHA-256");
		}
		
		return "";
	}
	
	
	public static void main(String[] args)
	{
		if (System.console() == null)
		{
			System.out.println("No console found");
			return;
		}
		
		while (true)
		{
			System.out.println("1) Hash Text");
			System.out.println("2) Hash File");
			System.out.println("3) Exit\n");
			
			System.out.print("Enter Option: ");
			System.console().readLine();
		}
	}
	
	
	public static String bytesToHex(byte[] bytes)
	{
		StringBuilder hex = new StringBuilder();
		for (byte b : bytes)
		{
			hex.append(String.format("%02x", b));
		}
		return hex.toString();
	}
}
