package en.lib.drawing;

import java.awt.Graphics2D;
import java.awt.Point;

public interface Drawable {
	public abstract void draw(Graphics2D g2);
	public abstract Point getCenter();
	public abstract int getDrawHeight();
}
