// Common User Data (username, password [hashed], credit-card number [hashed], level, ) Interaction w/CSV and Postgres
package userData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserData
{
	private static final String url = "jdbc:postgresql://192.168.1.176:5432/test";
	private static Connection connection = null;
	
	public static void databaseConnect()
	{
		Properties dbProperties = new Properties();
		dbProperties.setProperty("user", "postgres");
		dbProperties.setProperty("password", "postgres");
		try
		{
			connection = DriverManager.getConnection(url, dbProperties);
		}
		catch (SQLException e)
		{
			System.out.println("Database Error");
			e.printStackTrace();
		}
		System.out.println("successfuly connected");
	}
	
	public static void databaseDisconnect()
	{
		if (connection == null)
		{
			try
			{
				connection.close();
			}
			catch (SQLException e)
			{
				System.out.println("Database Disconnect Error");
				e.printStackTrace();
			}
		}
	}
	
	private static final Pattern noSymbol = Pattern.compile("[A-Za-z0-9]");
	public static void createNewUser()
	{
		System.out.println("NEW USER");
		
		while (true)
		{
			System.out.print("\nUsername:");
			String usernameInput = System.console().readLine();
			Matcher m = noSymbol.matcher(usernameInput);
			
			if (usernameInput.contains(" "))
			{
				System.out.println("Enter a valid username: no spaces allowed");
				continue;
			}
			else if (m.find())
			{
				System.out.println("Enter a valid username: no symbols allowed");
				continue;
			}
			else
			{
				
			}
		}
		
		
	}

	
	public static void main(String[] args)
	{
		if (System.console() == null)
		{
			System.out.println("No console found");
			System.exit(1);
		}
		
		databaseConnect();
		
		databaseDisconnect();
		/*
		while (true)
		{
			System.out.println("1) Create new user");
			System.out.println("2) Login");
			System.out.println("3) Exit");
			String input = System.console().readLine();
			
			int option;
			try
			{
				option = Integer.parseInt(input);
			}
			catch (NumberFormatException e) 
			{
				System.out.println("Enter a valid input");
			}
			clearScreen();
		}*/
	}
	
	
	// Get the Operating System type
	private final static String osType = System.getProperty("os.name");
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
