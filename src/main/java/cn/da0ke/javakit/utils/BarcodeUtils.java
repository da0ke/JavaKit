package cn.da0ke.javakit.utils;

import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;

public class BarcodeUtils {
	/**
	 * 生成二维码图片
	 * @param width
	 * @param height
	 * @param content
	 * @return
	 * @throws WriterException
	 */
	public static BufferedImage createQrCode(int width, int height, String content) throws Exception {
		Map<EncodeHintType, Object> hints=new HashMap<>(16);
		hints.put(EncodeHintType.MARGIN, 0);
		BufferedImage image;
		BitMatrix matrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
		image = MatrixToImageWriter.toBufferedImage(matrix);
		return image;
	}
}
