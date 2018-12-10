package tankWar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

/*
 * 玩家坦克类
 * 再三考虑 为了便于对这一个坦克进行操作 把它单独提取出来
 * 之后可对坦克图片进行修改 以便区分
 * 使用单例模式 避免多次创建
 * 为了不影响封装 不直接继承Tank类
 * 为了避免static和final属性带来的麻烦 不使用抽象类进行规范
 * 对单例模式有了更深的了解之后不用了 一是没必要 二是之后如果要添加双人游戏的部分就违背OCP了
 */
public class HomeTank extends Tank{
		protected boolean bL = false, bU = false, bR = false, bD = false;

		public HomeTank(int x, int y, Direction dir, GameFrame tc) {
			super(x, y, dir, tc,true);
		}

		private static int life = 200; // 初始生命值
		
		KeyTest kTest = new KeyTest();
		
		public void hit() {//被击中之后的反应
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
			new DrawBloodbBar().draw(g); // 玩家坦克的血量条
			super.draw(g);
		}

		void decideDirection() {
			if (!bL && !bU && bR && !bD) // 向右移动
				direction = Direction.R;
			else if (bL && !bU && !bR && !bD) // 向左移
				direction = Direction.L;
			else if (!bL && bU && !bR && !bD) // 向上移动
				direction = Direction.U;
			else if (!bL && !bU && !bR && bD) // 向下移动
				direction = Direction.D;
			else if (!bL && !bU && !bR && !bD)
				direction = Direction.STOP; // 没有按键，就保持不动
			else if (bL && !bU && !bR && bD) // 左下移动
				direction = Direction.LD;
			else if (bL && bU && !bR && !bD) // 左上移动
				direction = Direction.LU;
			else if (!bL && !bU && bR && bD) // 右下移动
				direction = Direction.RD;
			else if (!bL && bU && bR && !bD) // 右上移动
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
		public boolean fire(Direction dir) { // 开火方法
			int x = this.x + Tank.width / 2 - Bullets.width / 2; // 开火位置
			int y = this.y + Tank.length / 2 - Bullets.length / 2;
			Bullets m = new Bullets(x, y + 2, true, dir, this.tc);
			tc.bullets.add(m);
			return true;
		}

		//内部类 画血条
		private class DrawBloodbBar {
			public void draw(Graphics g) {
				Color c = g.getColor();
				g.setColor(Color.RED);
				g.drawRect(375, 585, width, 10);// 显示玩家坦克的血量条
				int w = width * life / 200;
				g.fillRect(375, 585, w, 10);// 显示玩家坦克的血量条
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
