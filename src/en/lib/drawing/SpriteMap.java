package en.lib.drawing;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteMap {
	private BufferedImage[][] sprites;
	public final int spriteWidth, spriteHeight, spriteMapWidth, spriteMapHeight;
	
	public SpriteMap(int spriteWidth, int spriteHeight, int gap, File spriteFile) {
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;
		BufferedImage spriteImage = null;
		try {
			spriteImage = ImageIO.read(spriteFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
		int imageWidth = spriteImage.getWidth(), imageHeight = spriteImage.getHeight();
		
		spriteMapWidth = imageWidth/(spriteWidth+gap);
		spriteMapHeight = imageHeight/(spriteHeight+gap);
		sprites = new BufferedImage[spriteMapHeight][spriteMapWidth];
		for (int y = 0; y < sprites.length; y++) {
			for (int x = 0; x < sprites[0].length; x++) {
				sprites[y][x] = spriteImage.getSubimage(x*(spriteWidth+gap), y*(spriteHeight+gap), spriteWidth, spriteHeight);
			}
		}
	}
	
	public BufferedImage getSprite(int x, int y) {
		return sprites[y][x];
	}
	
	public int getWidth() {
		return spriteMapWidth;
	}
	
	public int getHeight() {
		return spriteMapHeight;
	}
}
