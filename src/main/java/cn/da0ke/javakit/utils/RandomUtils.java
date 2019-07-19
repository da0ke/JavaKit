package cn.da0ke.javakit.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomUtils {

	/**
     * <p>Creates a random string whose length is the number of characters
     * specified.</p>
     *
     * <p>Characters will be chosen from the set of all characters.</p>
     *
     * @param count  the length of random string to create
     * @return the random string
     */
	public static String random(final int count) {
		return RandomStringUtils.randomAlphanumeric(10);
	}
	
}
