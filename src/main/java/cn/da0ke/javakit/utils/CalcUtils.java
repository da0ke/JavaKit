package cn.da0ke.javakit.utils;

import java.util.Calendar;

public class CalcUtils {
	
	/**
	 * 根据出生年份计算年龄-虚岁
	 */
	public static int getAgeByBirthYear(int birthYear) {
		return getFullAgeByBirthYear(birthYear) + 1;
	}
	
	/**
	 * 根据出生年份计算年龄-周岁
	 */
	public static int getFullAgeByBirthYear(int birthYear) {
		Calendar calendar = Calendar.getInstance();
		int thisYear = calendar.get(Calendar.YEAR);
		return (thisYear - birthYear);
	}
	
	
	/**
	 * 根据虚岁计算出生年份
	 * @param age
	 * @return
	 */
	public static int getBirthYearByAge(int age) {
		Calendar calendar = Calendar.getInstance();
		int thisYear = calendar.get(Calendar.YEAR);
		return thisYear - age + 1;
	}

}
