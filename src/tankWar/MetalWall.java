package tankWar;
import java.awt.*;

/**
 * ×©Ç½Àà£¨×Óµ¯¿É´ò´©£©
 */
public class MetalWall extends Wall{
	public MetalWall(int x, int y, GameFrame tc) {
		super(x, y, tc);
	}

	private static Image[] wallImags = null;
	static {
		wallImags = new Image[] { // ´¢´æcommonWallµÄÍ¼Æ¬
		tk.getImage(MetalWall.class.getResource("/Images/metalWall.gif")), };
	}
	public void draw(Graphics g) {// »­commonWall
		g.drawImage(wallImags[0], x, y, null);
	}
}
