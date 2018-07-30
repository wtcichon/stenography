package net.trollheim.stenography.core;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Util {

	public BufferedImage load(String path) throws IOException
	{
		return  ImageIO.read((new File(path)));
	}
	
	public BufferedImage load(File file) throws IOException {
	 	return ImageIO.read(file);
	}
	
	public void save(BufferedImage image,String path)
	{ 
		 
	   

		try {
			  File outputfile = new File(path);
			    //temporary png
			ImageIO.write(image, "png", outputfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void save(BufferedImage image,File outputfile)
	{ 
		 
	   

		try {
			 
			    //temporary png
			ImageIO.write(image, "png", outputfile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

 
}
