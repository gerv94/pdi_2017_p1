package pdi_2017_p1;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class ImagePDI {

	public static double[] getSaturation(BufferedImage bufferedImage) {
		int width = bufferedImage.getWidth();
		int height = bufferedImage.getHeight();
		int[] RGBs = bufferedImage.getRGB(0, 0, width, height, null, 0, width);
		double totalPixels = 1.0 / (width * height);
		double redSaturation = 0;
		double greenSaturation = 0;
		double blueSaturation = 0;
		Color c;
		for (int rgb : RGBs) {
			c = new Color(rgb);
			redSaturation += c.getRed();
			greenSaturation += c.getGreen();
			blueSaturation += c.getBlue();
		}
		redSaturation *= totalPixels;
		greenSaturation *= totalPixels;
		blueSaturation *= totalPixels;
		double[] res = new double[3];
		res[0] = redSaturation;
		res[1] = greenSaturation;
		res[2] = blueSaturation;
		return res;
	}

}
