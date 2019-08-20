package cn.da0ke.javakit.utils;

public class NumberUtils {

	public static boolean isInteger(String s) {
		String rule = "^-?[1-9]\\d*$";
		return s!=null && s.matches(rule);
	}
	
	public static int string2int(String s) {
		return string2int(s, 0);
	}
	
	public static int string2int(String s, int def) {
		int rs;
		try {
			rs = Integer.parseInt(s);
		} catch(NumberFormatException e) {
			rs = def;
		}
		return rs;
	}
	
}
