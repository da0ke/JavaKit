package cn.da0ke.javakit.utils;

public class ObjectUtils {

	public static boolean isBool(String s) {
		return s!=null && ("true".equals(s) || "false".equals(s));
	}
	
	public static boolean isTrue(Boolean b) {
		return b!=null && b;
	}

	public static boolean isFlase(Boolean b) {
		return !isTrue(b);
	}
	
}
