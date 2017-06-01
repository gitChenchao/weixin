package com.cc.weixin.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class CheckUtil {
	//private static final String token = "chenchao";
	private static final String token = "ceshi";
	public static boolean checkSignature(String signature,String timestamp,String nonce){
		String[] arr = new String[]{token,timestamp,nonce};
		Arrays.sort(arr);
		StringBuffer content =new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			content.append(arr[i]);
		}
		 MessageDigest md = null;
	     String tmpStr = null;
	     try {
			md = MessageDigest.getInstance("SHA-1");
			 byte[] digest = md.digest(content.toString().getBytes());
			 tmpStr = byteToStr(digest);
	     } catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 }
	     content = null;
	     return tmpStr != null ? tmpStr.equals(signature.toUpperCase()): false;
	}
	
	 /**
     * 将字节数组转换为十六进制字符串
     * @param digest
     * @return
     */
    private static String byteToStr(byte[] digest) {
        // TODO Auto-generated method stub
        String strDigest = "";
        for(int i = 0; i < digest.length; i++){
            strDigest += byteToHexStr(digest[i]);
        }
        return strDigest;
    }
    
    /**
     * 将字节转换为十六进制字符串
     * @param b
     * @return
     */
    private static String byteToHexStr(byte b) {
        // TODO Auto-generated method stub
        char[] Digit = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(b >>> 4) & 0X0F];
        tempArr[1] = Digit[b & 0X0F];
        
        String s = new String(tempArr);
        return s;
    }
}
