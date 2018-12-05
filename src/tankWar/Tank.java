package tankWar;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * ̹���ࣨ���õз�̹�˺����̹�ˣ�
 */

public class Tank {
	public static int pause = 0;
	public static int speed = 6; // ��̬ȫ�ֱ����ٶ�
	public static int count = 0;
	public static final int width = 35, length = 35; // ̹�˵�ȫ�ִ�С�����в��ɸı���
	private Direction direction = Direction.STOP; // ��ʼ��״̬Ϊ��ֹ
	private Direction Kdirection = Direction.U; // ��ʼ������Ϊ����
	GameFrame tc;

	private int x, y;
	private int oldX, oldY;
	private boolean live = true; // ��ʼ��Ϊ����

	private static Random r = new Random();
	private int step = r.nextInt(10) + 5; // ����һ�������,���ģ��̹�˵��ƶ�·��

	private boolean bL = false, bU = false, bR = false, bD = false;

	private static Toolkit tk = Toolkit.getDefaultToolkit();// �������
	private static Image[] tankImags = null; // �洢ȫ�־�̬
	static {
		tankImags = new Image[] { 
				tk.getImage(BombTank.class.getResource("/Images/tankD.gif")),
				tk.getImage(BombTank.class.getResource("/Images/tankU.gif")),
				tk.getImage(BombTank.class.getResource("/Images/tankL.gif")),
				tk.getImage(BombTank.class.getResource("/Images/tankR.gif")), 
				tk.getImage(BombTank.class.getResource("/Images/tankLU.gif")), 
				tk.getImage(BombTank.class.getResource("/Images/tankLD.gif")), 
				tk.getImage(BombTank.class.getResource("/Images/tankRU.gif")), 
				tk.getImage(BombTank.class.getResource("/Images/tankRD.gif")), 
				};
	}

	public Tank(int x, int y,Direction dir, GameFrame tc) {// Tank�Ĺ��캯��
		this.x = x;
		this.y = y;
		this.oldX = x;
		this.oldY = y;
		this.direction = dir;
		this.tc = tc;
	}

	public void draw(Graphics g) {
		if (!live) {
			tc.tanks.remove(this); // ɾ����Ч��
			return;
		}

		switch (Kdirection) {
		// ���ݷ���ѡ��̹�˵�ͼƬ
		case D:
			g.drawImage(tankImags[0], x, y, null);
			break;
		case U:
			g.drawImage(tankImags[1], x, y, null);
			break;
		case L:
			g.drawImage(tankImags[2], x, y, null);
			break;
		case R:
			g.drawImage(tankImags[3], x, y, null);
			break;
		case LU:
			g.drawImage(tankImags[4], x, y, null);
			break;
		case LD:
			g.drawImage(tankImags[5], x, y, null);
			break;
		case RU:
			g.drawImage(tankImags[6], x, y, null);
			break;
		case RD:
			g.drawImage(tankImags[7], x, y, null);
			break;
		default:
			break;
		}

		move(); // ����move����
	}

	void move() {
		if( pause > 0) {
			pause--;
			return;
		}
		this.oldX = x;
		this.oldY = y;

		switch (direction) { // ѡ���ƶ�����
		case L:
			x -= speed;
			break;
		case U:
			y -= speed;
			break;
		case R:
			x += speed;
			break;
		case D:
			y += speed;
			break;
		default://�����stop�ĳ������� �涨̹�˲���������˷�
			break;
		}

		if (this.direction != Direction.STOP) {
			this.Kdirection = this.direction;
		}

		if (x < 0)
			x = 0;
		if (y < 40) // ��ֹ�߳��涨����
			y = 40;
		if (x + Tank.width > GameFrame.Fram_width) // ����������ָ����߽�
			x = GameFrame.Fram_width - Tank.width;
		if (y + Tank.length > GameFrame.Fram_length)
			y = GameFrame.Fram_length - Tank.length;

			Direction[] directons = Direction.values();
			if (step == 0) {
				step = r.nextInt(12) + 3; // �������·��
				int rn = r.nextInt(directons.length);
				direction = directons[rn]; // �����������
			}
			step--;

			if (r.nextInt(40) > 38)// ��������������Ƶ��˿���
				this.fire();
	}

	protected void changToOldDir() {
		x = oldX;
		y = oldY;
	}



	public Bullets fire() { // ���𷽷�
		if (!live)
			return null;
		int x = this.x + Tank.width / 2 - Bullets.width / 2; // ����λ��
		int y = this.y + Tank.length / 2 - Bullets.length / 2;
		Bullets m = new Bullets(x, y + 2, false, Kdirection, this.tc); // û�и�������ʱ����ԭ���ķ��򷢻�
		tc.bullets.add(m);
		return m;
	}

	public Rectangle getRect() {
		return new Rectangle(x, y, width, length);
	}

	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}


	public boolean collideWithWall(BrickWall w) { // ��ײ����ͨǽʱ
		if (this.live && this.getRect().intersects(w.getRect())) {
			this.changToOldDir(); // ת����ԭ���ķ�����ȥ
			return true;
		}
		return false;
	}

	public boolean collideWithWall(MetalWall w) { // ײ������ǽ
		if (this.live && this.getRect().intersects(w.getRect())) {
			this.changToOldDir();
			return true;
		}
		return false;
	}

	public boolean collideRiver(River r) { // ײ��������ʱ��
		if (this.live && this.getRect().intersects(r.getRect())) {
			this.changToOldDir();
			return true;
		}
		return false;
	}

	public boolean collideHome(Home h) { // ײ���ҵ�ʱ��
		if (this.live && this.getRect().intersects(h.getRect())) {
			this.changToOldDir();
			return true;
		}
		return false;
	}

	public boolean collideWithTanks(java.util.List<Tank> tanks) {// ײ��̹��ʱ
		for (int i = 0; i < tanks.size(); i++) {
			Tank t = tanks.get(i);
			if (this != t) {
				if (this.live && t.isLive() && this.getRect().intersects(t.getRect())) {
					this.changToOldDir();
					t.changToOldDir();
					return true;
				}
			}
		}
		return false;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}