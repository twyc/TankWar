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
	public static final int width = 30, length = 30; // ȫ�־�̬��������
	private boolean live = true;

	private static Toolkit tk = Toolkit.getDefaultToolkit(); // ȫ�־�̬����
	private static Image[] homeImags = null;
	static {
		homeImags = new Image[] { tk.getImage(BrickWall.class.getResource("/Images/home.jpg")), };
	}
	private final static Home instance = new Home();
	private Home(GameFrame tc) {// ���캯��������Home�Ĳ�������ֵ
		Home.tc = tc; // ��ÿ���
	}
	private Home() {
	}
	public static Home getInstance(GameFrame tc) {
		Home.tc = tc;
		return instance;
	}
	
	public void gameOver(Graphics g) {

		tc.tanks.clear();// ������ҳ�湤��
		tc.metalWall.clear();
		tc.otherWall.clear();
		tc.bombTanks.clear();
		tc.theRiver.clear();
		tc.trees.clear();
		tc.bullets.clear();
		tc.homeTank.setLive(false);
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
			for (int i = 0; i < tc.homeWall.size(); i++) {
				BrickWall w = tc.homeWall.get(i);
				w.draw(g);
			}
		} else {
			gameOver(g); // ������Ϸ����
		}
	}
	public boolean isLive() { // �ж��Ƿ񻹻���
		return live;
	}

	public void setLive(boolean live) { // ��������
		this.live = live;
	}

	public Rectangle getRect() { // ���س�����ʵ��
		return new Rectangle(x, y, width, length);
	}

}
