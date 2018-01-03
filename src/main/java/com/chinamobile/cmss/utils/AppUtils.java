package com.chinamobile.cmss.utils;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author 杜奎
 * @date 2017/11/9
 * @version v0.0.1
 * @description 静态工具类
 */

public class AppUtils {
	
	private static AtomicInteger atomicInteger = new AtomicInteger(0);
	
	// AlarmUniqueId generator
	public static String generateUniqueId(String metrics) {    // 相同的metrics定会产生相同的uniqueID
		String metricsID = DigestUtils.sha1Hex(metrics + atomicInteger.incrementAndGet());    // 40个字符组成
		char[] _chars = metricsID.toCharArray();
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < _chars.length; i ++ ) {
			if(i % 10 == 0 && i != 0) {	// 10、20和30
				sb.append("-");
			}
			sb.append(String.valueOf(_chars[i]));
		}
		
		return sb.toString();
	}
}