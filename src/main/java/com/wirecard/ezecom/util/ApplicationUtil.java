package com.wirecard.ezecom.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
@author WCCTTI-JANAHAN
 */

public class ApplicationUtil {
	
	 public static String get_SHA_256_SecurePassword(String passwordToHash, String salt)
	    {
	        String generatedPassword = null;
	        try {
	            MessageDigest md = MessageDigest.getInstance("SHA-256");
	            md.update(salt.getBytes());
	            byte[] bytes = md.digest(passwordToHash.getBytes());
	            StringBuilder sb = new StringBuilder();
	            for(int i=0; i< bytes.length ;i++)
	            {
	                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	            }
	            generatedPassword = sb.toString();
	        } 
	        catch (NoSuchAlgorithmException e) 
	        {
	            e.printStackTrace();
	        }
	        return generatedPassword;
	    }
	 
	//Add salt
	    private static String getSalt() 
	    {
	    	byte[] salt=null;
	    	try{
	        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
	        salt = new byte[16];
	        sr.nextBytes(salt);
	    	}
	    	 catch (NoSuchAlgorithmException e) 
	        {
	            e.printStackTrace();
	        }
	        return salt.toString();
	    }
	    /*
	   public static void main(String[] args) {
		System.out.println("SALT : "+ApplicationUtil.getSalt());
	}
	*/
}
