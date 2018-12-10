package tankWar;

import java.awt.*;
import java.util.*;

/**
 * 坦克类（适用敌方坦克和玩家坦克）
 */

public class AutoTank extends Tank{
	public static int pause = 0;
	public static int count = 0;

	private static Random r = new Random();
	private int step = r.nextInt(10) + 5; // 产生一个随机数,随机模拟坦克的移动路径


	public AutoTank(int x, int y,Direction dir, GameFrame tc) {// Tank的构造函数
		super(x, y, dir, tc, false);
	}

	public void draw(Graphics g) {
		if (!isLive()) {
			tc.tanks.remove(this); // 删除无效的
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
			step = r.nextInt(12) + 3; // 产生随机路径
			int rn = r.nextInt(directons.length);
			direction = directons[rn]; // 产生随机方向
		}
		step--;

		if (r.nextInt(40) > 38)// 产生随机数，控制敌人开火
			this.fire();
	}

}