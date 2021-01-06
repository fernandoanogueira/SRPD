package br.com.fnogueira.xpdlparser.plantuml;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

public class ImageHandler {

	private int width = 1000;
	private int height = 600;
	private int x = 20;
	private int y = 20;

	public void handleImages(List<String> images) {
		BufferedImage result = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics g = result.getGraphics();

		for (String image : images) {
			BufferedImage bi = null;
			try {
				bi = ImageIO.read(new File(image));
			} catch (IOException e) {
				e.printStackTrace();
			}
			g.drawImage(bi, x, y, null);
			x += bi.getWidth() + 10;
			//y += bi.getHeight();
			/*if (x > result.getWidth()) {
				x = 0;
				y += bi.getHeight();
			}*/
		}

		try {
			ImageIO.write(result, "png", new File("E:\\TESTE\\Combined.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void handleImagesSideBySide(List<String> images) {

		int rows = 2; // we assume the no. of rows and cols are known and each
						// chunk has equal width and height
		int cols = 1;
		int chunks = rows * cols;

		int chunkWidth, chunkHeight;
		int type;
		// fetching image files
		File[] imgFiles = new File[chunks];
		for (int i = 0; i < chunks; i++) {
			imgFiles[i] = new File(images.get(i));
		}

		// creating a bufferd image array from image files
		BufferedImage[] buffImages = new BufferedImage[chunks];
		for (int i = 0; i < chunks; i++) {
			try {
				buffImages[i] = ImageIO.read(imgFiles[i]);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		type = buffImages[0].getType();
		chunkWidth = 500;//buffImages[0].getWidth();
		chunkHeight = 500;//buffImages[0].getHeight();

		// Initializing the final image
		BufferedImage finalImg = new BufferedImage(chunkWidth * cols,
				chunkHeight * rows, type);

		int num = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				finalImg.createGraphics().drawImage(buffImages[num],
						chunkWidth * j, chunkHeight * i, null);
				num++;
			}
		}
		try {
			ImageIO.write(finalImg, "jpeg", new File("E:\\TESTE\\CombinedSideBySide.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
