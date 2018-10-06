package cn.da0ke.javakit.utils;

/**
 * 正则工具类
 * @author da0ke
 *
 */
public class RegexUtils {
	
	/**
	 * 验证密码 6-20位，大小写英文字母、数字及符号组成
	 * 
	 * @param password
	 * @return
	 */
	public static boolean isPassword(String password) {
		String rule = "^[^\u4e00-\u9fa5]{6,20}$";
		return password.matches(rule);
	}

	/**
	 * 验证手机号码格式
	 */
	public static boolean isMobile(String mobile) {
		String rule = "^1[3456789][0-9]{9}$";
		return mobile.matches(rule);
	}

	/**
	 * 验证固定电话格式
	 */
	public static boolean isTel(String tel) {
		String rule = "^[0-9-+]{6,}$";
		return tel.matches(rule);
	}

	/**
	 * 验证邮箱格式
	 */
	public static boolean isEmail(String email) {
		String rule = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";
		return email.matches(rule);
	}

	/**
	 * 验证二代身份证号码(18位)
	 * 
	 * @param number
	 * @return
	 */
	public static boolean isIdCardNumber(String number) {
		String rule = "^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9Xx])$";
		return number.matches(rule);
	}

	/**
	 * 是否为单个字母
	 * 
	 * @param letter
	 * @return
	 */
	public static boolean isLetter(char letter) {
		String rule = "^[a-zA-Z]$";
		return String.valueOf(letter).matches(rule);
	}

	/**
	 * 是否是无符号整数
	 */
	public static boolean isUnsignedInt(String mobile) {
		String rule = "^(0|([1-9]\\d*))$";
		return mobile.matches(rule);
	}

}
