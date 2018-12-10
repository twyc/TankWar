package tankWar;
import java.awt.*;

/**
 * ×©Ç½Àà£¨×Óµ¯¿É´ò´©£©
 */
public class BrickWall extends Wall{
	public BrickWall(int x, int y, GameFrame tc) {
		super(x, y, tc);
	}

	private static Image[] wallImags = null;
	static {
		wallImags = new Image[] { // ´¢´æcommonWallµÄÍ¼Æ¬
		tk.getImage(BrickWall.class.getResource("/Images/commonWall.gif")), };
	}
	public void draw(Graphics g) {// »­commonWall
		g.drawImage(wallImags[0], x, y, null);
	}
}
