package com.chenjb.util;

import java.util.Collection;

@SuppressWarnings("rawtypes")
public class ValidateUtil {

	// 判断字符串有效性
	public static boolean isValid(String str) {
		if (str == null || str.trim().equals("")) {
			return false;
		}
		return true;
	}

	// 判断集合的有效性
	public static boolean isValid(Collection coll) {
		if (coll == null || coll.isEmpty()) {
			return false;
		}
		return true;
	}

	// 判断集合的有效性
	public static boolean isValid(Object[] arr) {
		if (arr == null || arr.length == 0) {
			return false;
		}
		return true;
	}
}
