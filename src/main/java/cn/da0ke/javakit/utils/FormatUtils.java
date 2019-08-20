package cn.da0ke.javakit.utils;

public class FormatUtils {
	
	/**
	 * 隐藏手机号中间4位数字
	 * 
	 * @param mobile
	 * @return
	 */
	public static String secretMobile(String mobile) {
		if (cn.da0ke.javakit.utils.RegexUtils.isMobile(mobile)) {
			return mobile.substring(0, 3) + "****" + mobile.substring(7);
		} else {
			return mobile.substring(0, 3) + "****" + (mobile.length() < 8 ? "****" : mobile.substring(7));
		}
	}

	public static String secretEmail(String email) {
		int minLength = 5;
		
		String str;
		if (StringUtils.isEmpty(email)) {
			str = "";
		} else if (RegexUtils.isEmail(email)) {
			int splitIndex = email.lastIndexOf("@");
			String prefix = email.substring(0, splitIndex);
			String suffix = email.substring(splitIndex + 1);
			if (prefix.length() < minLength) {
				str = prefix + "*****" + suffix;
			} else {
				str = prefix.substring(0, 4) + "*****" + suffix;
			}
		} else {
			int len = email.length();
			if (len < minLength) {
				str = email + "*****";
			} else {
				str = email.substring(0, 4) + "*****" + email.substring(len - 5);
			}
		}

		return str;
	}
	
	public static String formatMobile(String mobile) {
		if (cn.da0ke.javakit.utils.RegexUtils.isMobile(mobile)) {
			return mobile.substring(0, 3) + " " + mobile.substring(3,7) + " " + mobile.substring(7);
		} else {
			return mobile;
		}
	}

}
