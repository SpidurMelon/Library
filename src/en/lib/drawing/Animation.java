package en.lib.drawing;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {
	public ArrayList<BufferedImage> sprites = new ArrayList<BufferedImage>();
	public int spriteWidth = 0, spriteHeight = 0;
	private double timer = 1;
	private int frameDelay = 1, spriteNumber = 0;
	public boolean repeating = true, finished = false;
	public Animation(SpriteMap sm, int startX, int startY, int endX, int endY, int frameDelay, boolean repeating) {
		this.frameDelay = frameDelay;
		this.repeating = repeating;
		spriteWidth = sm.spriteWidth;
		spriteHeight = sm.spriteHeight;
		for (int y = startY; y <= endY; y++) {
			for (int x = startX; x <= endX; x++) {
				sprites.add(sm.getSprite(x, y));
			}
		}
	}
	
	public Animation(ArrayList<BufferedImage> sprites, int frameDelay, boolean repeating) {
		this.frameDelay = frameDelay;
		this.repeating = repeating;
		spriteWidth = sprites.get(0).getWidth();
		spriteHeight = sprites.get(0).getHeight();
		this.sprites = sprites;
	}

	public void tick(double delta) {
		if (!finished) {
			if (timer >= frameDelay) {
				switchSprite();
				timer = timer-frameDelay;
			}
			timer+=delta;
		}
	}
	
	private void switchSprite() {
		if (spriteNumber < sprites.size()-1) {
			spriteNumber += 1;
		} else {
			if (repeating) {
				reset();
			} else {
				finish();
			}
		}
	}

	public void reset() {
		timer = 0;
		spriteNumber = 0;
		finished = false;
	}

	private void finish() {
		finished = true;
	}
	
	public Animation clone() {
		return new Animation(sprites, frameDelay, repeating);
	}
	
	public void draw(int x, int y, Graphics2D g2) {
		if (!sprites.isEmpty()) {
			g2.drawImage(sprites.get(spriteNumber), x, y, spriteWidth, spriteHeight, null);
		}
	}
	
	public void draw(AffineTransform at, Graphics2D g2) {
		if (!sprites.isEmpty()) {
			g2.drawImage(sprites.get(spriteNumber), at, null);
		}
	}
	
	public void draw(int x, int y, int width, int height, Graphics2D g2) {
		if (!sprites.isEmpty()) {
			g2.drawImage(sprites.get(spriteNumber), x, y, width, height, null);
		}
	}
}
