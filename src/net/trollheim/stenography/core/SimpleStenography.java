package net.trollheim.stenography.core;

import java.awt.image.BufferedImage;

public class SimpleStenography implements StenographyI {

	
	
	@Override
	public BufferedImage encode(BufferedImage container, BufferedImage image) {
		if (container.getWidth() < image.getWidth() * 3
				&& container.getHeight() < image.getHeight()) {
			throw new RuntimeException("Conntainer too small, minimum size is "
					+ (image.getWidth() * 3) + "x" + image.getHeight());
		}
		BufferedImage newImage = new BufferedImage(container.getWidth(),
				container.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < container.getWidth(); x++) {
			for (int y = 0; y < container.getHeight(); y++) {
			
				int pixelValue = container.getRGB(x, y);
				int alpha = (pixelValue & 0xFF000000) >>> 24;
				int red = (pixelValue & 0x00FF0000) >> 16;
				int green = (pixelValue & 0x0000FF00) >> 8;
				int blue = (pixelValue & 0x000000FF);
 

				int hiddenVal = 0;
				if (x / 3 < image.getWidth() && y < image.getHeight()) {
					hiddenVal = image.getRGB(x / 3, y);
					switch (x % 3) {
					case 0:
						hiddenVal = (hiddenVal & 0xFF0000) >> 16;
						break;
					case 1:
						hiddenVal = (hiddenVal & 0x00FF00) >> 8;
						break;
					case 2:
						hiddenVal = (hiddenVal & 0x0000FF);
						break;

					}

				}

 

				alpha = ((alpha & 0xfffffffc) | (hiddenVal & 3));
				red = ((red & 0xfffffffc) |  ((hiddenVal >> 2)& 3));
				green = ((green & 0xfffffffc) |  ((hiddenVal >> 4)& 3));
				 
				blue = ((blue & 0xfffffffc)| ((hiddenVal >> 6) & 3));
				pixelValue = (((alpha << 8 | red) << 8 | green) << 8) | blue;
				 

				 
				newImage.setRGB(x, y, pixelValue);
			}
		}

		return newImage;
	}

	@Override
	public BufferedImage decode(BufferedImage container) {
		BufferedImage newImage = new BufferedImage(container.getWidth() / 3,
				container.getHeight(), BufferedImage.TYPE_INT_ARGB);
		for (int x = 0; x < container.getWidth(); x = x + 3) {
			for (int y = 0; y < container.getHeight(); y++) {
				if (x+1>=container.getWidth())
				{
					continue;
				}
				
				int red = extractValue(container.getRGB(x, y));
				int green = extractValue(container.getRGB(x + 1, y));
				int blue = extractValue(container.getRGB(x + 2, y));

				int pixelValue = (((((0xFF << 8) | red) << 8 | green) << 8) | blue);

				
				 
				
				newImage.setRGB(x / 3, y, pixelValue);

			}
		}
		return newImage;
	}

	private int extractValue(int pixelValue) {
		int blue = (((pixelValue & 0xFF000000) >> 24) & 0x3); // 2
		int green = (((pixelValue & 0xFF0000) >> 16) & 0x3); // 4
		int red = (((pixelValue & 0x00FF00) >> 8) & 0x3); // 6
		int alpha = (((pixelValue & 0x0000FF)) & 0x3); // 8
		return ((((((alpha << 2) | red) << 2) | green) << 2) | blue);
	}

}
