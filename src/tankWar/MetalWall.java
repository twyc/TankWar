package tankWar;
import java.awt.*;

/**
 * שǽ�ࣨ�ӵ��ɴ򴩣�
 */
public class MetalWall extends Wall{
	public MetalWall(int x, int y, GameFrame tc) {
		super(x, y, tc);
	}

	private static Image[] wallImags = null;
	static {
		wallImags = new Image[] { // ����commonWall��ͼƬ
		tk.getImage(MetalWall.class.getResource("/Images/metalWall.gif")), };
	}
	public void draw(Graphics g) {// ��commonWall
		g.drawImage(wallImags[0], x, y, null);
	}
}
