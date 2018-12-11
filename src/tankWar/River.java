package tankWar;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

/**
 * ������
 * ��Ȼ�ڳ�ʼ�����ŵ�ͼ��ֻ��һ��ʵ�� 
 * �������Ժ�ʹ��������ͼʱ���п����ٴδ���ʵ�� 
 * ��˲�ʹ�õ���ģʽ
 */
public class River implements StillObject{
	public static final int riverWidth = Info.getInstance().getriverWidth();
	public static final int riverLength = Info.getInstance().getriverLength();// ��̬ȫ�ֱ���
	private int x, y;
	GameFrame tc;

	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] riverImags = null;

	static { // �洢ͼƬ
		riverImags = new Image[] { tk.getImage(BrickWall.class.getResource("/Images/river.jpg")), };
	}

	public River(int x, int y, GameFrame tc) { // River�Ĺ��췽��
		this.x = x;
		this.y = y;
		this.tc = tc; // ��ÿ���
	}

	public void draw(Graphics g) {
		g.drawImage(riverImags[0], x, y, null); // �ڶ�ӦX��Y������
	}

	public static int getRiverWidth() {
		return riverWidth;
	}

	public static int getRiverLength() {
		return riverLength;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Rectangle getRect() {
		return new Rectangle(x, y, riverWidth, riverLength);
	}

	@Override
	public boolean hit(Bullets b) {
		// TODO Auto-generated method stub
		return false;
	}

}
