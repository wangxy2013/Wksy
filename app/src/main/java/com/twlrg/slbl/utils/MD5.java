package com.twlrg.slbl.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5
{

	public final static String MD5_KEY = "MIICWwIBAAKBgQDCWFeFWXAjkVXsLYuv";

	public static String md5(String body)
	{
		String str = body + MD5_KEY;
		try
		{
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(str.getBytes("UTF-8"));
			byte b[] = md.digest();

			int i;

			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++)
			{
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			str = buf.toString();
		} catch (Exception e)
		{
			e.printStackTrace();

		}
		return str;
	}
}
