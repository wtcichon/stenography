package net.trollheim.stenography.core;

import java.awt.image.BufferedImage;

public interface StenographyI {

	BufferedImage encode(BufferedImage container, BufferedImage image);

	BufferedImage decode(BufferedImage container);
}
