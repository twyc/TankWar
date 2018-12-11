package tankWar;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

/**
 * 树（丛林）类
 * 仍然让它实现接口的目的还是OCP
 * 这次的说法是以后万一有能烧树的功能呢= =
 */

public class Tree implements StillObject{
	public static final int width = Info.getInstance().gettreeWidth();
	public static final int length = Info.getInstance().gettreeLength();
	private int x, y;
	GameFrame tc;
	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] treeImags = null;
	static {
		treeImags = new Image[] { tk.getImage(BrickWall.class.getResource("/Images/tree.gif")), };
	}

	public Tree(int x, int y, GameFrame tc) { // Tree的构造方法，传递x，y和tc对象
		this.x = x;
		this.y = y;
		this.tc = tc;
	}

	public void draw(Graphics g) { // 画出树
		g.drawImage(treeImags[0], x, y, null);
	}

	@Override
	public Rectangle getRect() {
		return null;
	}

	@Override
	public boolean hit(Bullets b) {
		return false;
	}

}
