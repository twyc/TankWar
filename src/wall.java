import java.awt.*;

/**
 * 墙类
 */

/*
	把普通墙和金属墙两个类的公共属性提取出来 将这个墙类作为父类 普通墙和金属墙
*/
public class Wall {
	public static final int width = 20; // 设置墙的固定参数
	public static final int length = 20;
	int x, y;

	GameFrame tc;
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] wallImags = null;
	static {
		wallImags = new Image[] { // 储存commonWall的图片
				tk.getImage(Wall.class.getResource("Images/commonWall.gif")), };
	}

	public Wall(int x, int y, GameFrame tc) { // 构造函数
		this.x = x;
		this.y = y;
		this.tc = tc; // 获得界面控制
	}

	public void draw(Graphics g) {// 画commonWall
		g.drawImage(wallImags[0], x, y, null);
	}

	public Rectangle getRect() { // 构造指定参数的长方形实例
		return new Rectangle(x, y, width, length);
	}
}
