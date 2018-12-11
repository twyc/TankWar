package tankWar;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;

/*
 * ��Ϸ������
 * �ݶ�Ҫ��Ѫ�� ��� ը��
 */
public class Prop {

	public final int width = Info.getInstance().getPropWidth();
	public final int length = Info.getInstance().getPropLength();

	protected int x, y;
	GameFrame tc;
	protected static Random r = new Random();

	protected boolean live = false;

	protected static Toolkit tk = Toolkit.getDefaultToolkit();
	public Prop(GameFrame tc) {
		// TODO Auto-generated constructor stub
		this.tc = tc;
	}
	public void draw(Graphics g) {
		return;
	}
	public void fun() {
		return;
	}
	protected void move() {
		 do{
			x = r.nextInt(GameFrame.Fram_length);
			y = r.nextInt(GameFrame.Fram_width);
		}while (!isOk(x,y));
	}
	private boolean isOk(int x, int y) {//�ж����λ���ǲ��ǻ������������ײ
		if(x-width<0||y-length<0||x+width>GameFrame.Fram_width||y+width>GameFrame.Fram_length) {
			return false;
		}
		for(Prop prop : tc.props) {//����֮�䲻Ҫ�ص�
			if(prop!=this) {
				if(prop.getRect().intersects(getRect())) {
					return false;
				}
			}
		}
		for (int j = 0; j < tc.homeWall.size(); j++) {
			Wall cw = tc.homeWall.get(j);
			if(cw.getRect().intersects(getRect())) {
				return false;
			}
		}
		for (int j = 0; j < tc.otherWall.size(); j++) { // ÿһ��̹��ײ���������ǽ
			BrickWall cw = tc.otherWall.get(j);
			if(cw.getRect().intersects(getRect())) {
				return false;
			}
		}
		for (int j = 0; j < tc.metalWall.size(); j++) { // ÿһ��̹��ײ������ǽ
			MetalWall cw = tc.metalWall.get(j);
			if(cw.getRect().intersects(getRect())) {
				return false;
			}
		}
		for (int j = 0; j < tc.theRiver.size(); j++) {
			River cw = tc.theRiver.get(j); // ÿһ��̹��ײ������ʱ
			if(cw.getRect().intersects(getRect())) {
				return false;
			}
		}
		return !Home.getInstance(tc).getRect().intersects(getRect());
	}
	public Rectangle getRect() {
		return new Rectangle(x, y, width, length);
	}

	public boolean isLive() {// �ж��Ƿ񻹻���
		return live;
	}

	public void setLive(boolean live) { // ��������
		this.live = live;
	}
}