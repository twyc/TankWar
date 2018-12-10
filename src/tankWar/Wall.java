package tankWar;
import java.awt.*;

/**
 * שǽ�ࣨ�ӵ��ɴ򴩣�
 */
public class Wall implements StillObject{
	public static final int width = Info.getInstance().getWallWidth(); // ����ǽ�Ĺ̶�����
	public static final int length = Info.getInstance().getWallLength();
	protected int x, y;
	GameFrame tc;
	protected static Toolkit tk = Toolkit.getDefaultToolkit();

	public Wall(int x, int y, GameFrame tc) { // ���캯��
		this.x = x;
		this.y = y;
		this.tc = tc; // ��ý������
	}

	public void draw(Graphics g) {// ��commonWall
		return;
	}

	public Rectangle getRect() { // ����ָ�������ĳ�����ʵ��
		return new Rectangle(x, y, width, length);
	}

	@Override
	public boolean hit(Bullets b) {
		if (b.isLive() && getRect().intersects(b.getRect())) {
			b.setLive(false);
			return true;//ͨ������ֵ��֤���� ����Ĵ������Ƿ���GameFrame����ͨ���������Ĳ��������
		}
		return false;
	}


}