package tankWar;
import java.awt.*;

/**
 * 砖墙类（子弹可打穿）
 * 继承自Wall类
 */
public class BrickWall extends Wall{
	public BrickWall(int x, int y, GameFrame tc) {
		super(x, y, tc);
	}

	private static Image[] wallImags = null;
	static {
		wallImags = new Image[] { // 储存commonWall的图片
		tk.getImage(BrickWall.class.getResource("/Images/commonWall.gif")), };
	}
	public void draw(Graphics g) {// 画commonWall
		g.drawImage(wallImags[0], x, y, null);
	}
}
