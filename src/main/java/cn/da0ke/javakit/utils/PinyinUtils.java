package cn.da0ke.javakit.utils;

import com.github.promeg.pinyinhelper.Pinyin;

public class PinyinUtils {
	/**
	 * 如果c为汉字，则返回大写拼音；如果c不是汉字，则返回String.valueOf(c)
	 * @param c
	 * @return
	 */
	public static String toPinyin(char c) {
		return Pinyin.toPinyin(c);
	}
	
	/**
	 * 将输入字符串转为拼音，以字符为单位插入分隔符 例: "hello:中国!" 在separator为","时，输出： "h,e,l,l,o,:,ZHONG,GUO,!"
	 * @param str
	 * @param separator
	 * @return
	 */
	public static String toPinyin(String str, String separator) {
		return Pinyin.toPinyin(str, separator);
	}
	
	/**
	 * 是否为汉字
	 * @param c
	 * @return
	 */
	public static boolean isChinese(char c) {
		return Pinyin.isChinese(c);
	}
	
	/**
	 * 是否为字母
	 */
	public static boolean isLetter(char c) {
		String rule = "^[a-zA-Z]$";
		return String.valueOf(c).matches(rule);
	}
}
