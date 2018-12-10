package tankWar;
import java.awt.*;

/**
 * 砖墙类（子弹可打穿）
 */
public class Wall {
	public static final int width = Info.getInstance().getWallWidth(); // 设置墙的固定参数
	public static final int length = Info.getInstance().getWallLength();
	protected int x, y;
	GameFrame tc;
	protected static Toolkit tk = Toolkit.getDefaultToolkit();

	public Wall(int x, int y, GameFrame tc) { // 构造函数
		this.x = x;
		this.y = y;
		this.tc = tc; // 获得界面控制
	}

	public void draw(Graphics g) {// 画commonWall
		return;
	}

	public Rectangle getRect() { // 构造指定参数的长方形实例
		return new Rectangle(x, y, width, length);
	}
}
