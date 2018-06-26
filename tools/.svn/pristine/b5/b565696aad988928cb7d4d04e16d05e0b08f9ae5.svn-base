package com.tools.encode;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class CreateQRCode {
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static void main(String[] args) {
		int width = 300;
		int height = 300;

		String format = "gif";
		//String content = "http://www.baidu.com/s?wd=北京";
		String content = "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1518095375509&di=a431653d742b20216e4738e07aa8fe98&imgtype=0&src=http%3A%2F%2Fcdn.duitang.com%2Fuploads%2Fitem%2F201501%2F25%2F20150125100433_KrHsQ.jpeg";
		// 定义二维码的参数
		@SuppressWarnings("rawtypes")
		HashMap hints = new HashMap<>();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
		try {
			BitMatrix matrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
			File file = new File("C:/Users/HUZHIHUI/Desktop/" + File.separator + "new.gif");
			MatrixToImageWriter.writeToFile(matrix, format, file);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

class ReadQRCode {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {

		MultiFormatReader formatReader = new MultiFormatReader();
		File file = new File("C:/Users/HUZHIHUI/Desktop/" + File.separator + "new.gif");
		try {
			BufferedImage image = ImageIO.read(file);
			BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
			BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));
			Map hints = new HashMap<>();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");

			Result result = formatReader.decode(binaryBitmap, hints);
			System.out.println(result.toString());
			System.out.println(result.getBarcodeFormat());
			System.out.println(result.getText());
		} catch (NotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
