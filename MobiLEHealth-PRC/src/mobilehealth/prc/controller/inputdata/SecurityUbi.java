package mobilehealth.prc.controller.inputdata;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException; 

public class SecurityUbi 
{
    public static String encodeMD5(String s)
    {  
            String sen = "";  
            MessageDigest md = null;  
            
            try 
            {  
                md = MessageDigest.getInstance("MD5");  
            } 
            catch (NoSuchAlgorithmException e) 
            {  
                e.printStackTrace();  
            }  
            
            BigInteger hash = new BigInteger(1, md.digest(s.getBytes()));  
            sen = hash.toString(16);  
            
            return sen;  
    }      
	
}
