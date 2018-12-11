package tankWar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

/*
 * ���̹����
 * �������� Ϊ�˱��ڶ���һ��̹�˽��в��� ����������ȡ����
 * ֮��ɶ�̹��ͼƬ�����޸� �Ա�����
 * ʹ�õ���ģʽ �����δ���
 * Ϊ�˲�Ӱ���װ ��ֱ�Ӽ̳�Tank��
 * Ϊ�˱���static��final���Դ������鷳 ��ʹ�ó�������й淶
 * �Ե���ģʽ���˸�����˽�֮������ һ��û��Ҫ ����֮�����Ҫ���˫����Ϸ�Ĳ��־�Υ��OCP��
 */
public class HomeTank extends Tank{
		protected boolean bL = false, bU = false, bR = false, bD = false;

		public HomeTank(int x, int y, Direction dir, GameFrame tc) {
			super(x, y, dir, tc,true);
		}

		private static int life = 200; // ��ʼ����ֵ
		
		KeyTest kTest = new KeyTest();
		
		public void hit() {//������֮��ķ�Ӧ
			super.hit();
			life-=50;
			if(life==0) {
				live=false;
			}
		}
		protected void keyReleased(KeyEvent e) {
			kTest.keyReleased(e, this);
		}
		protected void keyPressed(KeyEvent e) {
			kTest.keyPressed(e, tc, this);
		}
		public void draw(Graphics g) {
			new DrawBloodbBar().draw(g); // ���̹�˵�Ѫ����
			super.draw(g);
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

		void superFire() {
			Direction[] dirs = Direction.values();
			for(int i=0; i<dirs.length; i++) {
				if(dirs[i]!=Direction.STOP) {
					fire(dirs[i]);
				}
			}
		}
		public boolean fire(Direction dir) { // ���𷽷�
			int x = this.x + Tank.width / 2 - Bullets.width / 2; // ����λ��
			int y = this.y + Tank.length / 2 - Bullets.length / 2;
			Bullets m = new Bullets(x, y + 2, true, dir, this.tc);
			tc.bullets.add(m);
			return true;
		}

		//�ڲ��� ��Ѫ��
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

		public boolean eat(Prop b) {
			if (live && b.isLive() && getRect().intersects(b.getRect())) {
				b.fun();
				b.setLive(false);
				return true;
			}
			return false;
		}
		public int getLife() {
			return life;
		}

		public void setLife(int i) {
			life = i;
		}
}
