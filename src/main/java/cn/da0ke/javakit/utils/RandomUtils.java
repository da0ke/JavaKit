package cn.da0ke.javakit.utils;

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
		return org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric(10);
	}
	
	/**
     * <p>
     * Returns a random integer within the specified range.
     * </p>
     *
     * @param startInclusive
     *            the smallest value that can be returned, must be non-negative
     * @param endExclusive
     *            the upper bound (not included)
     * @throws IllegalArgumentException
     *             if {@code startInclusive > endExclusive} or if
     *             {@code startInclusive} is negative
     * @return the random integer
     */
	public static int nextInt(final int startInclusive, final int endExclusive) {
		return org.apache.commons.lang3.RandomUtils.nextInt(startInclusive, endExclusive);
	}
}
