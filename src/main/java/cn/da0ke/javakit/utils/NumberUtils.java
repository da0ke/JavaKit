package cn.da0ke.javakit.utils;

import java.math.BigDecimal;

public class NumberUtils {
	
	/**
	 * 是否为Integer
	 * @param s
	 * @return
	 */
	@Deprecated
	public static boolean isInteger(String str) {
		if (str == null) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (final NumberFormatException nfe) {
            return false;
        }
	}
	
	/**
	 * 是否为int
	 * @param str 字符串
	 * @return boolean
	 */
	public static boolean isInt(String str) {
		if (str == null) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (final NumberFormatException nfe) {
            return false;
        }
	}
	
	/**
	 * 字符串转换为int
	 * @param str 字符串
	 * @param defaultValue 默认值
	 * @return int
	 */
	public static int string2int(String str, int defaultValue) {
		if (str == null) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(str);
        } catch (final NumberFormatException nfe) {
            return defaultValue;
        }
	}
	
	public static int string2int(String str) {
		return string2int(str, 0);
	}
	
	/**
	 * 去除多余的0
	 * @param number
	 * @return
	 */
	public static String getTrimNumber(double number) {  
	    return BigDecimal.valueOf(number)
	            .stripTrailingZeros().toPlainString();  
	} 
}
