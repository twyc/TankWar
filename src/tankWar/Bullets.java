package tankWar;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * �ӵ���
 */

public class Bullets {
	/**
	 * �ӵ���ȫ������
	   �������ٶ����Ժϲ�Ϊһ��
	 */
	public static int speed = 15;

	public static final int width = 10;
	public static final int length = 10;

	///�ӵ�������
	private int x, y;///λ��
	Direction diretion;///ö�����͵ķ���

	private boolean good;
	private boolean live = true;///�Ƿ����

	private GameFrame tc;

	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] bulletImages = null;
	private static Map<String, Image> imgs = new HashMap<String, Image>(); // ����Map��ֵ�ԣ��ǲ�ͬ�����Ӧ��ͬ�ĵ�ͷ

	static {
		bulletImages = new Image[] { // ��ͬ������ӵ�
		///ȡ��Դ�ļ�
				tk.getImage(Bullets.class.getClassLoader().getResource("Images/bulletL.gif")),
				tk.getImage(Bullets.class.getClassLoader().getResource("Images/bulletU.gif")),
				tk.getImage(Bullets.class.getClassLoader().getResource("Images/bulletR.gif")),
				tk.getImage(Bullets.class.getClassLoader().getResource("Images/bulletD.gif")),
				tk.getImage(Bullets.class.getClassLoader().getResource("Images/bulletLU.gif")),
				tk.getImage(Bullets.class.getClassLoader().getResource("Images/bulletLD.gif")),
				tk.getImage(Bullets.class.getClassLoader().getResource("Images/bulletRU.gif")),
				tk.getImage(Bullets.class.getClassLoader().getResource("Images/bulletRD.gif")),
		};
	}

	public Bullets(int x, int y, Direction dir) { // ���캯��1������λ�úͷ���
		this.x = x;
		this.y = y;
		this.diretion = dir;
	}

	// ���캯��2������������������
	public Bullets(int x, int y, boolean good, Direction dir, GameFrame tc) {
		this(x, y, dir);
		this.good = good;
		this.tc = tc;
	}

	private void move() {

		switch (diretion) {
		case L:
			x -= speed; // �ӵ������������
			break;
		case U: // �ӵ��������Ͻ���
			y -= speed;
			break;
		case R:
			x += speed; // �ӵ��������ҽ���
			break;
		case D: // �ӵ��������½���
			y += speed;
			break;
		case LU:
			x -= speed; // �ӵ������������
			y -= speed;
			break;
		case LD:
			x -= speed; // �ӵ������������
			y += speed;
			break;
		case RU:
			x += speed; // �ӵ������������
			y -= speed;
			break;
		case RD:
			x += speed; // �ӵ������������
			y += speed;
			break;
		case STOP: // ��Ϸ��ͣ
			break;
		}

		if (x < 0 || y < 0 || x > GameFrame.Fram_width || y > GameFrame.Fram_length) {
			live = false;
		}
	}

	public void draw(Graphics g) {
		if (!live) {
			tc.bullets.remove(this);
			return;
		}

		switch (diretion) { // ѡ��ͬ������ӵ�
		case L:
			g.drawImage(bulletImages[0], x, y, null);
			break;
		case U:
			g.drawImage(bulletImages[1], x, y, null);
			break;
		case R:
			g.drawImage(bulletImages[2], x, y, null);
			break;
		case D:
			g.drawImage(bulletImages[3], x, y, null);
			break;
		case LU:
			g.drawImage(bulletImages[4], x, y, null);
			break;
		case LD:
			g.drawImage(bulletImages[5], x, y, null);
			break;
		case RU:
			g.drawImage(bulletImages[6], x, y, null);
			break;
		case RD:
			g.drawImage(bulletImages[7], x, y, null);
			break;
		}

		move(); // �����ӵ�move()����
	}

	public boolean isLive() { // �Ƿ񻹻���
		return live;
	}

	public Rectangle getRect() {
		return new Rectangle(x, y, width, length);
	}

	public boolean hitTanks(List<Tank> tanks) {// ���ӵ���̹��ʱ
		for (int i = 0; i < tanks.size(); i++) {
			if (hitTank(tanks.get(i))) { // ��ÿһ��̹�ˣ�����hitTank
				return true;
			}
		}
		return false;
	}

	public boolean hitTank(Tank t) { // ���ӵ���̹����

		if (this.live && this.getRect().intersects(t.getRect()) && t.isLive() && this.good != false) {

			BombTank e = new BombTank(t.getX(), t.getY(), tc);
			tc.bombTanks.add(e);
			t.setLive(false);
			this.live = false;
			return true; // ����ɹ�������true
		}
		return false; // ���򷵻�false
	}
	
	public boolean hitTank(HomeTank t) { // ���ӵ���̹����

		if (this.live && this.getRect().intersects(t.getRect()) && t.isLive() && this.good != true) {

			BombTank e = new BombTank(t.getX(), t.getY(), tc);
			tc.bombTanks.add(e);
				t.setLife(t.getLife() - 50); // ��һ���ӵ���������50����4ǹ������������ֵ200
				if (t.getLife() <= 0)
					t.setLive(false); // ������Ϊ0ʱ����������Ϊ����״̬
			this.live = false;
			return true; // ����ɹ�������true
		}
		return false; // ���򷵻�false
	}

	public boolean hitWall(BrickWall w) { // �ӵ���CommonWall��
		if (this.live && this.getRect().intersects(w.getRect())) {
			this.live = false;
			this.tc.otherWall.remove(w); // �ӵ���CommonWallǽ��ʱ���Ƴ��˻���ǽ
			this.tc.homeWall.remove(w);
			return true;
		}
		return false;
	}

	public boolean hitWall(MetalWall w) { // �ӵ��򵽽���ǽ��
		if (this.live && this.getRect().intersects(w.getRect())) {
			this.live = false;
			// this.tc.metalWall.remove(w); //�ӵ����ܴ�Խ����ǽ
			return true;
		}
		return false;
	}

	public boolean hitHome() { // ���ӵ��򵽴�Ӫʱ
		if (this.live && this.getRect().intersects(tc.home.getRect())) {
			this.live = false;
			this.tc.home.setLive(false); // ����Ӫ����һǹʱ�ͻ���
			return true;
		}
		return false;
	}

}
