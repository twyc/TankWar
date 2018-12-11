package tankWar;

import java.awt.*;
import java.util.*;

/**
 * ����̹����
 */

public class AutoTank extends Tank{
	public static int pause = 0;//��ͣʱ�� ���Ǽ�clock֮��Ĳ���
	public static int count = 0;

	private static Random r = new Random();
	private int step = r.nextInt(10) + 5; // ����һ�������,���ģ��̹�˵��ƶ�·��

	public void hit() {//������֮��ķ�Ӧ
		super.hit();
		live=false;
	}
	public AutoTank(int x, int y,Direction dir, GameFrame tc) {// Tank�Ĺ��캯��
		super(x, y, dir, tc, false);
	}

	public void draw(Graphics g) {
		if (!isLive()) {
			tc.tanks.remove(this); // ɾ����Ч��
			return;
		}
		super.draw(g);
	}

	void move() {
		if( pause > 0) {
			pause--;
			return;
		}

		super.move();
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

}