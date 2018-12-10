package tankWar;

import java.awt.Graphics;
import java.awt.Rectangle;

public interface StillObject {
	public void draw(Graphics g);
	public Rectangle getRect();
	public boolean hit(Bullets b);
}