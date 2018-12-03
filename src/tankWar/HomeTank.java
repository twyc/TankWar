package tankWar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.Random;

/*
 * ���̹����
 * �������� Ϊ�˱��ڶ���һ��̹�˽��в��� ����������ȡ����
 * ֮��ɶ�̹��ͼƬ�����޸� �Ա�����
 * ʹ�õ���ģʽ �����δ���
 * Ϊ�˲�Ӱ���װ ��ֱ�Ӽ̳�Tank��
 * Ϊ�˱���static��final���Դ������鷳 ��ʹ�ó�������й淶
 * ���Ǵ��ڴ�����Tank�ظ�������Ĵ��� ��Ҳ��֪����ô�졣��
 */
public class HomeTank {
		public static int speed = 6; // ��̬ȫ�ֱ����ٶ�
		public static int count = 0;
		public static final int width = 35, length = 35; // ̹�˵�ȫ�ִ�С�����в��ɸı���
		private static Direction direction = Direction.STOP; // ��ʼ��״̬Ϊ��ֹ
		private static Direction Kdirection = Direction.U; // ��ʼ������Ϊ����
		static GameFrame tc;

		private static int x, y;
		private static int oldX, oldY;
		private static boolean live = true; // ��ʼ��Ϊ����
		private static int life = 200; // ��ʼ����ֵ

		private boolean bL = false, bU = false, bR = false, bD = false;
		//�����home��Ĵ�������һ�� ���Ե�ʱ��ǵý��ж��ռ��
		private final static HomeTank instance = new HomeTank();

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
		public void init() {
			life=200;
			live = true;
			x=oldX=300;
			y=oldY=560;
			direction=Direction.STOP;
			Kdirection=Direction.U;
		}
		public HomeTank() {
			init();
		}
		public static HomeTank getInstance(GameFrame tc) {
			instance.tc = tc;
			return instance;
		}
		public void draw(Graphics g) {
			new DrawBloodbBar().draw(g); // ���̹�˵�Ѫ����

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

			HomeTank.oldX = x;
			HomeTank.oldY = y;

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
			default:
				break;
			}

			if (HomeTank.direction != Direction.STOP) {
				HomeTank.Kdirection = HomeTank.direction;
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

		private static void changToOldDir() {
			x = oldX;
			y = oldY;
		}

		void decideDirection() {
			if (!bL && !bU && bR && !bD) // �����ƶ�
				direction = Direction.R;
			else if (bL && !bU && !bR && !bD) // ������
				direction = Direction.L;
			else if (!bL && bU && !bR && !bD) // �����ƶ�
				direction = Direction.U;
			else if (!bL && !bU && !bR && bD) // �����ƶ�
				direction = Direction.D;
			else if (!bL && !bU && !bR && !bD)
				direction = Direction.STOP; // û�а������ͱ��ֲ���
			else if (bL && !bU && !bR && bD) // �����ƶ�
				direction = Direction.LD;
			else if (bL && bU && !bR && !bD) // �����ƶ�
				direction = Direction.LU;
			else if (!bL && !bU && bR && bD) // �����ƶ�
				direction = Direction.RD;
			else if (!bL && bU && bR && !bD) // �����ƶ�
				direction = Direction.RU;
		}

		public void keyReleased(KeyEvent e) { // �����ͷż���
			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_A:
				superFire();
				break;
			case KeyEvent.VK_F:
				fire();
				break;

			case KeyEvent.VK_RIGHT:
				bR = false;
				break;

			case KeyEvent.VK_LEFT:
				bL = false;
				break;

			case KeyEvent.VK_UP:
				bU = false;
				break;

			case KeyEvent.VK_DOWN:
				bD = false;
				break;

			}
			decideDirection(); // �ͷż��̺�ȷ���ƶ�����
		}
		private void superFire() {
			Direction[] dirs = Direction.values();
			for(int i=0; i<dirs.length; i++) {
				fire(dirs[i]);
			}
		}
		public Bullets fire() { // ���𷽷�
			if (!live)
				return null;
			int x = HomeTank.x + Tank.width / 2 - Bullets.width / 2; // ����λ��
			int y = HomeTank.y + Tank.length / 2 - Bullets.length / 2;
			Bullets m = new Bullets(x, y + 2, true, Kdirection, HomeTank.tc); // û�и�������ʱ����ԭ���ķ��򷢻�
			tc.bullets.add(m);
			return m;
		}
		public Bullets fire(Direction dir) { // ���𷽷�
			if (!live)
				return null;
			int x = HomeTank.x + Tank.width / 2 - Bullets.width / 2; // ����λ��
			int y = HomeTank.y + Tank.length / 2 - Bullets.length / 2;
			Bullets m = new Bullets(x, y + 2, true, dir, HomeTank.tc); // û�и�������ʱ����ԭ���ķ��򷢻�
			tc.bullets.add(m);
			return m;
		}

		public static Rectangle getRect() {
			return new Rectangle(x, y, width, length);
		}

		public boolean isLive() {
			return live;
		}

		public void setLive(boolean live) {
			HomeTank.live = live;
		}
		
		public void keyPressed(KeyEvent e) { // ���ܼ����¼�
			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_R: // ������Rʱ�����¿�ʼ��Ϸ
				Home.setFlag(false);
				tc.tanks.clear(); // ����
				tc.bullets.clear();
				tc.trees.clear();
				tc.otherWall.clear();
				tc.homeWall.clear();
				tc.metalWall.clear();
				tc.homeTank.setLive(false);
				if (tc.tanks.size() == 0) { // ����������û��̹��ʱ���ͳ���̹��
					for (int i = 0; i < 20; i++) {
						if (i < 9) // ����̹�˳��ֵ�λ��
							tc.tanks.add(new Tank(150 + 70 * i, 40, false, Direction.R, tc));
						else if (i < 15)
							tc.tanks.add(new Tank(700, 140 + 50 * (i - 6), false, Direction.D, tc));
						else
							tc.tanks.add(new Tank(10, 50 * (i - 12), false, Direction.L, tc));
					}
				}
				instance.init();
				tc.home.setLive(true);
				tc.dispose();
				new GameFrame(); // ���´������
				break;
			case KeyEvent.VK_RIGHT: // �������Ҽ�
				bR = true;
				break;

			case KeyEvent.VK_LEFT:// ���������
				bL = true;
				break;

			case KeyEvent.VK_UP: // �������ϼ�
				bU = true;
				break;

			case KeyEvent.VK_DOWN:// �������¼�
				bD = true;
				break;
			}
			decideDirection();// ���ú���ȷ���ƶ�����
		}

		public boolean collideWithWall(BrickWall w) { // ��ײ����ͨǽʱ
			if (HomeTank.live && HomeTank.getRect().intersects(w.getRect())) {
				HomeTank.changToOldDir(); // ת����ԭ���ķ�����ȥ
				return true;
			}
			return false;
		}

		public boolean collideWithWall(MetalWall w) { // ײ������ǽ
			if (HomeTank.live && HomeTank.getRect().intersects(w.getRect())) {
				HomeTank.changToOldDir();
				return true;
			}
			return false;
		}

		public boolean collideRiver(River r) { // ײ��������ʱ��
			if (HomeTank.live && HomeTank.getRect().intersects(r.getRect())) {
				HomeTank.changToOldDir();
				return true;
			}
			return false;
		}

		public boolean collideHome(Home h) { // ײ���ҵ�ʱ��
			if (HomeTank.live && HomeTank.getRect().intersects(h.getRect())) {
				HomeTank.changToOldDir();
				return true;
			}
			return false;
		}

		public boolean collideWithTanks(java.util.List<Tank> tanks) {// ײ��̹��ʱ
			for (int i = 0; i < tanks.size(); i++) {
				Tank t = tanks.get(i);
					if (HomeTank.live && t.isLive() && HomeTank.getRect().intersects(t.getRect())) {
						HomeTank.changToOldDir();
						t.changToOldDir();
						return true;
					}
			}
			return false;
		}

		public int getLife() {
			return life;
		}

		public void setLife(int life) {
			HomeTank.life = life;
		}

		private class DrawBloodbBar {
			public void draw(Graphics g) {
				Color c = g.getColor();
				g.setColor(Color.RED);
				g.drawRect(375, 585, width, 10);// ��ʾ���̹�˵�Ѫ����
				int w = width * life / 200;
				g.fillRect(375, 585, w, 10);// ��ʾ���̹�˵�Ѫ����
				g.setColor(c);
			}
		}

		public boolean eat(Blood b) {
			if (HomeTank.live && b.isLive() && HomeTank.getRect().intersects(b.getRect())) {
				if (HomeTank.life <= 100)
					HomeTank.life = HomeTank.life + 100; // ÿ��һ��������100������
				else
					HomeTank.life = 200;
				b.setLive(false);
				return true;
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
