package com.sogeti.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author aayoub
 *
 */
public class Utils {

	
	public static String encryptMdp(final String pMdp) throws NoSuchAlgorithmException {
		
		final int radix = 16;
		
        String generatedPassword = null;
        //This bytes[] has bytes in decimal format;
        //Convert it to hexadecimal format
        final StringBuilder sb = new StringBuilder();
        
        try {
            // Create MessageDigest instance for MD5
            MessageDigest md = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            md.update(pMdp.getBytes());
            //Get the hash's bytes 
            byte[] bytes = md.digest();
            for(int i=0; i< bytes.length ;i++)
            {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, radix).substring(1));
            }
            //Get complete hashed password in hex format
            generatedPassword = sb.toString();
            
        } 
        catch (NoSuchAlgorithmException ex) 
        {
            throw new NoSuchAlgorithmException("Impossible de crypter le password !");
        }
        
        return generatedPassword;
	}
	
	
}
