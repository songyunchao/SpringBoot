package com.fsun.common.utils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public class MD5Utils {
	
	//普通加密
	public static String md5Encode(String password) {
		MessageDigest md5 = null;
		try {
			md5 = MessageDigest.getInstance("MD5");
		} catch (Exception e) {
			System.out.println(e.toString());
			e.printStackTrace();
			return "";
		}
		byte[] byteArray = null;
		try {
			byteArray = password.getBytes("UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		byte[] md5Bytes = md5.digest(byteArray);
		StringBuffer hexValue = new StringBuffer();
		for (int i = 0; i < md5Bytes.length; i++) {
			int val = md5Bytes[i] & 0xff;
			if (val < 16) {
				hexValue.append("0");
			}
			hexValue.append(Integer.toHexString(val));
		}
		return hexValue.toString();
	}
	
	//盐值加密
	public static String md5SaltEncode(String password,  String salt) {
		String passwordSalt = "";
		if(StringUtils.isEmpty(password) || StringUtils.isEmpty(salt)){
			return "";
		}else{
			passwordSalt = (password + "{"+ salt +"}");
		}
		return md5Encode(passwordSalt);
	}

	public static void main(String[] args) {
		
		String password = "123456{xw}";
		
		String ss0 = MD5Utils.md5Encode(password);
		System.out.println(ss0);
		
		String ss = MD5Utils.md5SaltEncode("123456", "xw");
		System.out.println(ss);

	}

}
