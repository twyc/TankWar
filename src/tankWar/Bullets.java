package tankWar;

import java.awt.*;

/**
 * �ӵ���
 */

public class Bullets {
	public static int speed = Info.getInstance().getBulletSpeed(1);

	public static final int width = Info.getInstance().getBulletWidth();
	public static final int length = Info.getInstance().getBulletLength();

	///�ӵ�������
	private int x, y;///λ��
	Direction diretion;///ö�����͵ķ���

	private boolean good;
	public boolean isGood() {
		return good;
	}

	public void setGood(boolean good) {
		this.good = good;
	}

	private boolean live = true;///�Ƿ����

	private GameFrame tc;

	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] bulletImages = null;
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

	public Bullets(int x, int y, boolean good, Direction dir, GameFrame tc) {
		this.x = x;
		this.y = y;
		this.diretion = dir;
		this.good = good;
		this.tc = tc;
		this.live = true;
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
			x -= speed;
			y -= speed;
			break;
		case LD:
			x -= speed;
			y += speed;
			break;
		case RU:
			x += speed;
			y -= speed;
			break;
		case RD:
			x += speed;
			y += speed;
			break;
		case STOP: // ��Ϸ��ͣ
			break;
		}

		if (x < 0 || y < 0 || x > GameFrame.Fram_width || y > GameFrame.Fram_width) {
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
		default:
			break;
		}

		move(); // �����ӵ�move()����
	}

	public boolean isLive() { // �Ƿ񻹻���
		return live;
	}
	public void setLive(boolean state) {
		live = state;
	}
	public Rectangle getRect() {
		return new Rectangle(x, y, width, length);
	}

	public boolean hitWall(Wall cw) { // �ӵ���CommonWall��
		if (this.live && this.getRect().intersects(cw.getRect())) {
			if(cw instanceof BrickWall) {
				tc.otherWall.remove(cw); // �ӵ���CommonWallǽ��ʱ���Ƴ��˻���ǽ
				tc.homeWall.remove(cw);
			}
			return true;
		}
		return false;
	}

	public boolean hitWall(MetalWall w) { // �ӵ��򵽽���ǽ��
		if (this.live && this.getRect().intersects(w.getRect())) {
			this.live = false;
			return true;
		}
		return false;
	}

	public boolean hitHome() { // ���ӵ��򵽴�Ӫʱ
		if (this.live && this.getRect().intersects(tc.home.getRect())) {
			this.live = false;
			this.tc.home.setLive(false|Home.isFlag()); // ����Ӫ����һǹʱ�ͻ���
			return true;
		}
		return false;
	}

}
