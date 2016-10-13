package com.cyyun.manage.utils;

import java.security.MessageDigest;

public class MD5Util {
    
	private final static String SECURITY_KEY = "cyyunManage";
	
	public final static String MD5(String s) {
        char hexDigits[] = { '0', '1', '2', '3', '4',
                             '5', '6', '7', '8', '9',
                             'A', 'B', 'C', 'D', 'E', 'F' };
        try {
        	s = s + SECURITY_KEY;
            byte[] btInput = s.getBytes();
   
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
    
            mdInst.update(btInput);
    
            byte[] md = mdInst.digest();
    
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public static void main(String[] args) {
        System.out.print(MD5Util.MD5("cyyun888"));
    }
}
