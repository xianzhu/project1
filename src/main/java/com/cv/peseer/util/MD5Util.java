package com.cv.peseer.util;

import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MD5Util {
	private static final Logger LOGGER = LoggerFactory.getLogger(MD5Util.class);
	public static String MD5Encode(String str) {
        MessageDigest messageDigest = null;
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes("UTF-8"));
        } catch (Exception e) {
            LOGGER.error( e.toString());
            return null;
        }

        byte[] byteArray = messageDigest.digest();
        StringBuffer md5StrBuff = new StringBuffer();
        for (int i = 0; i < byteArray.length; i++) {
            if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
            else
                md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
        }
        return md5StrBuff.toString();
    }

	public static boolean strMD5Validation(String str, String MD5){
		if(StringUtil.isNullOrEmpty(str) || StringUtil.isNullOrEmpty(MD5)){
			return false;
		}

		return MD5.equals(MD5Encode(str));
	}


	public static void main(String[] args){
		if(strMD5Validation("cNSmwX9K","05de930bed07b3882a85bc6ef3ac52ef")){
			System.out.println("match!");
		}else{
			System.out.println("error!");
		}

	}
}
