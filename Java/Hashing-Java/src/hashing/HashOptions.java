package hashing;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public enum HashOptions
{
	SHA256,
	MD5,
	MD2,
	SHA1,
	SHA384,
	SHA512;
	
	private MessageDigest algorithm;
	private HashOptions()
	{
		try
		{
			algorithm = MessageDigest.getInstance(this.name());
		}
		catch (NoSuchAlgorithmException e)
		{
			System.err.println("Internal Algorithm Error");
			System.exit(1);
		}
	}
	
	public MessageDigest getAlgorithm()
	{
		return algorithm;
	}
	public void setAlgorithm(MessageDigest m)
	{
		algorithm = m;
	}
}
