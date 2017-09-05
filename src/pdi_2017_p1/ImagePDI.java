package pdi_2017_p1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class ImagePDI extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private BufferedImage img;

	public ImagePDI() {
		setBackground(Color.WHITE);
	}

	public void setImage(Image image) {
		img = (BufferedImage) image;
		Dimension dimension = new Dimension(img.getWidth(), img.getHeight());
		setSize(dimension);
		setPreferredSize(dimension);
	}

	public double[] getSaturation() {
		int width = img.getWidth();
		int height = img.getHeight();
		int[] RGBs = img.getRGB(0, 0, width, height, null, 0, width);
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

	public int getRGB(int x, int y) {
		return x >= 0 && x < img.getWidth() && y >= 0 && y < img.getHeight() ? img.getRGB(x, y) : Color.WHITE.getRGB();
	}

	public void addImageAtFirstQuadrant(Image image) {
		int maxX = img.getWidth() / 2;
		int maxY = img.getHeight() / 2;
		BufferedImage image2 = (BufferedImage) image;
		int width = image2.getWidth();
		int height = image2.getHeight();
		int[] RGBs = image2.getRGB(0, 0, width, height, null, 0, width);
		maxX = maxX > width ? width : maxX;
		maxY = maxY > height ? height : maxY;
		img.setRGB(0, 0, maxX, maxY, RGBs, 0, width);
		repaint();
	}

	public void rotateFirstQuadrant() {
		int maxX = img.getWidth() / 2;
		int maxY = img.getHeight() / 2;
		int width = img.getWidth();
		int height = img.getHeight();
		int offset = 0;
		int startY = 0;
		int startX = 0;
		int[] rgbArray = img.getRGB(startX, startY, maxX, maxY, null, offset, width);
		int temp, i1, i2, y2, x2;
		// How to get a pixel: rgbArray[offset + (y - startY) * width + (x - startX)]
		for (int y = 0; y <= maxY / 2; y++) {
			y2 = maxY - y - 1;
			for (int x = 0; x < (y == maxY / 2 ? maxX / 2 : maxX); x++) {
				x2 = maxX - x - 1;
				i1 = offset + (y - startY) * width + (x - startX);
				i2 = offset + (y2 - startY) * width + (x2 - startX);
				temp = rgbArray[i1];
				rgbArray[i1] = rgbArray[i2];
				rgbArray[i2] = temp;
			}
		}
		img.setRGB(maxX + width % 2, maxY + height % 2, maxX, maxY, rgbArray, 0, width);
		repaint();
	}

	public void mirrorVertical() {
		int maxX = img.getWidth() / 2;
		int maxY = img.getHeight();
		int width = img.getWidth();
		int offset = 0;
		int startY = 0;
		int startX = 0;
		int[] rgbArray = img.getRGB(startX, startY, maxX, maxY, null, offset, width);
		int temp, i1, i2, x2;
		for (int y = 0; y < maxY; y++) {
			for (int x = 0; x < maxX / 2; x++) {
				x2 = maxX - x - 1;
				i1 = offset + (y - startY) * width + (x - startX);
				i2 = offset + (y - startY) * width + (x2 - startX);
				temp = rgbArray[i1];
				rgbArray[i1] = rgbArray[i2];
				rgbArray[i2] = temp;
			}
		}
		img.setRGB(maxX + width % 2, startY, maxX, maxY, rgbArray, 0, width);
		repaint();
	}

	public void mirrorHorizontal() {
		int maxX = img.getWidth();
		int maxY = img.getHeight() / 2;
		int width = img.getWidth();
		int offset = 0;
		int startY = 0;
		int startX = 0;
		int[] rgbArray = img.getRGB(startX, startY, maxX, maxY, null, offset, width);
		int temp, i1, i2, y2;
		for (int y = 0; y < maxY / 2; y++) {
			y2 = maxY - y - 1;
			for (int x = 0; x < maxX; x++) {
				i1 = offset + (y - startY) * width + (x - startX);
				i2 = offset + (y2 - startY) * width + (x - startX);
				temp = rgbArray[i1];
				rgbArray[i1] = rgbArray[i2];
				rgbArray[i2] = temp;
			}
		}
		img.setRGB(startX, maxY, maxX, maxY, rgbArray, 0, width);
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (img != null) {
			g.drawImage(img, 0, 0, this);
		} else {
			g.setColor(Color.WHITE);
		}
	}

}
