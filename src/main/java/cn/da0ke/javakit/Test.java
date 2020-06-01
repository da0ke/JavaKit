package cn.da0ke.javakit;

import cn.da0ke.javakit.utils.EncryptUtils;

public class Test {
	
	public static void main(String[] args) {
		String s = EncryptUtils.md5("123456");
		
		System.out.println(s);
	}

}
