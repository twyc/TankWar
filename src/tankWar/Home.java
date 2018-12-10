package tankWar;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

/**
 * ��Ӫ��
 * ��home��λ���ڳ�������д�� ��Ϊһ�������м� ���Դ�������Ժ����չ��Ҳ�������
 * ��̹�˴�ս��Ϸ�� һ��ֻ��һ��home ��ô����ʹ�õ���ģʽ��ֹ����δ���
 */

public class Home {
	private static final int x = 373;
	private static final int y = 545;
	private static GameFrame tc;
	public static final int width = 43, length = 43; // ȫ�־�̬��������
	private boolean live = true;
	private static boolean flag = false;//�ж���Ϸ�Ƿ��Ѿ����� ��ֹʤ�����ٳ���ʧ�ܵ����
	
	private static Toolkit tk = Toolkit.getDefaultToolkit(); // ȫ�־�̬����
	private static Image[] homeImags = null;
	static {
		homeImags = new Image[] { tk.getImage(BrickWall.class.getResource("/Images/home.jpg")), };
	}
	private final static Home instance = new Home();
	private Home(GameFrame tc) {// ˽�й��캯��������Home�Ĳ�������
		Home.tc = tc; // ��ÿ���
	}
	public static boolean isFlag() {
		return flag;
	}
	public static void setFlag(boolean flag) {
		Home.flag = flag;
	}
	private Home() {
	}
	//˭Ҫ��˭
	public static Home getInstance(GameFrame tc) {
		Home.tc = tc;
		return instance;
	}
	
	public void gameOver(Graphics g) {
		tc.tanks.clear();// ������ҳ�湤��
		tc.metalWall.clear();
		tc.otherWall.clear();
		tc.homeWall.clear();
		tc.bombTanks.clear();
		tc.theRiver.clear();
		tc.trees.clear();
		tc.bullets.clear();
		tc.props.clear();
		Color c = g.getColor(); // ���ò���
		g.setColor(Color.green);
		Font f = g.getFont();
		g.setFont(new Font(" ", Font.PLAIN, 40));
		g.drawString("�����ˣ�", 220, 250);
		g.drawString("  ��Ϸ������ ", 220, 300);
		g.setFont(f);
		g.setColor(c);

	}

	public void draw(Graphics g) {
		if (live) { // ������ţ��򻭳�home
			g.drawImage(homeImags[0], x, y, null);
		} else {
			gameOver(g); // ������Ϸ����
		}
	}
	public boolean isLive() { // �ж��Ƿ񻹻���
		return live|flag;
	}

	public void setLive(boolean live) { // ��������
		this.live = live;
	}

	public Rectangle getRect() { // ���س�����ʵ��
		return new Rectangle(x, y, width, length);
	}

}
