package tankWar;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

/**
 * �������֣���
 * ��Ȼ����ʵ�ֽӿڵ�Ŀ�Ļ���OCP
 * ��ε�˵�����Ժ���һ���������Ĺ�����= =
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

	public Tree(int x, int y, GameFrame tc) { // Tree�Ĺ��췽��������x��y��tc����
		this.x = x;
		this.y = y;
		this.tc = tc;
	}

	public void draw(Graphics g) { // ������
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
