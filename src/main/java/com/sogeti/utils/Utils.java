package com.sogeti.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * @author aayoub
 *
 */
public class Utils {

	
	public static String EncryptMdp(final String pMdp) throws NoSuchAlgorithmException {
		
        String lGeneratedPassword = null;
        //This bytes[] has bytes in decimal format;
        //Convert it to hexadecimal format
        final StringBuilder lSb = new StringBuilder();
        
        try {
            // Create MessageDigest instance for MD5
            MessageDigest lMd = MessageDigest.getInstance("MD5");
            //Add password bytes to digest
            lMd.update(pMdp.getBytes());
            //Get the hash's bytes 
            byte[] lBytes = lMd.digest();
            for(int i=0; i< lBytes.length ;i++)
            {
                lSb.append(Integer.toString((lBytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            //Get complete hashed password in hex format
            lGeneratedPassword = lSb.toString();
            
        } 
        catch (NoSuchAlgorithmException e) 
        {
            throw new NoSuchAlgorithmException("Impossible de crypter le password !");
        }
        
        return lGeneratedPassword;
	}
	
}
