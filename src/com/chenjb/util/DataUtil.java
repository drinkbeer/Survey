package com.chenjb.util;

import java.security.MessageDigest;

public class DataUtil {

	/**
	 * 对数据进行MD5加密
	 * @param src
	 * @return 加密之后的数据
	 */
	public static String md5(String src) {
		StringBuffer buffer = new StringBuffer();
		char[] chars = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
				'B', 'C', 'D', 'E', 'F' };
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = md.digest(src.getBytes());
			for (byte b : bytes) {
				buffer.append(chars[b >> 4 & 0xf]);
				buffer.append(chars[b & 0xf]);
			}
			return buffer.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
