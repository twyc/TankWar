package tankWar;

import java.awt.*;
import java.util.List;

/**
 * ̹���ࣨ���õз�̹�˺����̹�ˣ�
 * ����
 */

public class Tank {
	public static int speed = Info.getInstance().getTankSpeed(1); // ��̬ȫ�ֱ����ٶ�
	public static final int width = Info.getInstance().getTankWidth(), length = Info.getInstance().getTankLength(); // ̹�˵�ȫ�ִ�С�����в��ɸı���
	protected Direction direction = Direction.STOP; // ��ʼ��״̬Ϊ��ֹ
	protected Direction Kdirection = Direction.U; // ��ʼ������Ϊ����
	GameFrame tc;

	protected int x, y;
	protected int oldX, oldY;
	protected boolean live = true; // ��ʼ��Ϊ����
	protected boolean good;
	protected static Toolkit tk = Toolkit.getDefaultToolkit();// �������
	protected static Image[] tankImags = null; // �洢ȫ�־�̬
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

	public Tank(int x, int y,Direction dir, GameFrame tc,boolean good) {// Tank�Ĺ��캯��
		this.x = x;
		this.y = y;
		this.oldX = x;
		this.oldY = y;
		this.direction = dir;
		this.tc = tc;
		this.good = good;
	}

	public void draw(Graphics g) {
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
		oldX = x;
		oldY = y;

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
		
	}

	protected void changToOldDir() {
		x = oldX;
		y = oldY;
	}

	public void hit() {//������֮��Ķ���
	}

	public Bullets fire() { // ���𷽷�
		if (!live) {
			return null;
		}
		int x = this.x + Tank.width / 2 - Bullets.width / 2; // ����λ��
		int y = this.y + Tank.length / 2 - Bullets.length / 2;
		Bullets m = new Bullets(x, y + 2, good, Kdirection, this.tc); // û�и�������ʱ����ԭ���ķ��򷢻�
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

//�����˲���ģʽ ����ȥ������ײ��ⷽ��

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}