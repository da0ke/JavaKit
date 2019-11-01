package cn.da0ke.javakit.utils;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.digest.DigestUtils;

public class EncryptUtils {
	
	/**
	 * md5
	 * @param text 明文
	 * @return 密文
	 */
	public static String md5(String text) {
		return DigestUtils.md5Hex(text).toUpperCase();
	}

    public static String encryptAes(String text, String key) {
        if (StringUtils.isEmpty(key) || StringUtils.isEmpty(text)
        		|| key.length()!=16) {
            return "";
        }
        
        byte[] raw;
		try {
			raw = key.getBytes("utf-8");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			//"算法/模式/补码方式"
	        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
	        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
	        byte[] encrypted = cipher.doFinal(text.getBytes("utf-8"));
	        //此处使用BASE64做转码功能，同时能起到2次加密的作用。
	        return Base64.getEncoder().encodeToString(encrypted);
		} catch (UnsupportedEncodingException | InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException e) {
			return "";
		}
    }

    public static String decryptAes(String text, String key) {
        try {
        	 if (StringUtils.isEmpty(key) || StringUtils.isEmpty(key)
             		|| key.length()!=16) {
                 return "";
             }
            byte[] raw = key.getBytes("utf-8");
            SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, skeySpec);
     
            //先用base64解密
            byte[] encrypted1 = Base64.getDecoder().decode(text);
            try {
                byte[] original = cipher.doFinal(encrypted1);
                String originalString = new String(original,"utf-8");
                return originalString;
            } catch (Exception e) {
                return "";
            }
        } catch (Exception ex) {
            return "";
        }
    }

}
