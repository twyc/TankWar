package tankWar;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.Random;

/*
 * 玩家坦克类
 * 再三考虑 为了便于对这一个坦克进行操作 把它单独提取出来
 * 之后可对坦克图片进行修改 以便区分
 * 使用单例模式 避免多次创建
 * 为了不影响封装 不直接继承Tank类
 * 为了避免static和final属性带来的麻烦 不使用抽象类进行规范
 * 但是存在大量和Tank重复的冗余的代码 我也不知道怎么办。。
 */
public class HomeTank {
		public static int speed = 6; // 静态全局变量速度
		public static int count = 0;
		public static final int width = 35, length = 35; // 坦克的全局大小，具有不可改变性
		private static Direction direction = Direction.STOP; // 初始化状态为静止
		private static Direction Kdirection = Direction.U; // 初始化方向为向上
		static GameFrame tc;

		private static int x, y;
		private static int oldX, oldY;
		private static boolean live = true; // 初始化为活着
		private static int life = 200; // 初始生命值

		private boolean bL = false, bU = false, bR = false, bD = false;
		//这里和home类的处理方法不一样 测试的时候记得进行对照检测
		private final static HomeTank instance = new HomeTank();

		private static Toolkit tk = Toolkit.getDefaultToolkit();// 控制面板
		private static Image[] tankImags = null; // 存储全局静态
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
			new DrawBloodbBar().draw(g); // 玩家坦克的血量条

			switch (Kdirection) {
			// 根据方向选择坦克的图片
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

			move(); // 调用move函数
		}

		void move() {

			HomeTank.oldX = x;
			HomeTank.oldY = y;

			switch (direction) { // 选择移动方向
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
			if (y < 40) // 防止走出规定区域
				y = 40;
			if (x + Tank.width > GameFrame.Fram_width) // 超过区域则恢复到边界
				x = GameFrame.Fram_width - Tank.width;
			if (y + Tank.length > GameFrame.Fram_length)
				y = GameFrame.Fram_length - Tank.length;

		}

		private static void changToOldDir() {
			x = oldX;
			y = oldY;
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

		public void keyReleased(KeyEvent e) { // 键盘释放监听
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
			decideDirection(); // 释放键盘后确定移动方向
		}
		private void superFire() {
			Direction[] dirs = Direction.values();
			for(int i=0; i<dirs.length; i++) {
				fire(dirs[i]);
			}
		}
		public Bullets fire() { // 开火方法
			if (!live)
				return null;
			int x = HomeTank.x + Tank.width / 2 - Bullets.width / 2; // 开火位置
			int y = HomeTank.y + Tank.length / 2 - Bullets.length / 2;
			Bullets m = new Bullets(x, y + 2, true, Kdirection, HomeTank.tc); // 没有给定方向时，向原来的方向发火
			tc.bullets.add(m);
			return m;
		}
		public Bullets fire(Direction dir) { // 开火方法
			if (!live)
				return null;
			int x = HomeTank.x + Tank.width / 2 - Bullets.width / 2; // 开火位置
			int y = HomeTank.y + Tank.length / 2 - Bullets.length / 2;
			Bullets m = new Bullets(x, y + 2, true, dir, HomeTank.tc); // 没有给定方向时，向原来的方向发火
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
		
		public void keyPressed(KeyEvent e) { // 接受键盘事件
			int key = e.getKeyCode();
			switch (key) {
			case KeyEvent.VK_R: // 当按下R时，重新开始游戏
				tc.tanks.clear(); // 清理
				tc.bullets.clear();
				tc.trees.clear();
				tc.otherWall.clear();
				tc.homeWall.clear();
				tc.metalWall.clear();
				tc.homeTank.setLive(false);
				if (tc.tanks.size() == 0) { // 当在区域中没有坦克时，就出来坦克
					for (int i = 0; i < 20; i++) {
						if (i < 9) // 设置坦克出现的位置
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
				new GameFrame(); // 重新创建面板
				break;
			case KeyEvent.VK_RIGHT: // 监听向右键
				bR = true;
				break;

			case KeyEvent.VK_LEFT:// 监听向左键
				bL = true;
				break;

			case KeyEvent.VK_UP: // 监听向上键
				bU = true;
				break;

			case KeyEvent.VK_DOWN:// 监听向下键
				bD = true;
				break;
			}
			decideDirection();// 调用函数确定移动方向
		}

		public boolean collideWithWall(BrickWall w) { // 碰撞到普通墙时
			if (HomeTank.live && HomeTank.getRect().intersects(w.getRect())) {
				HomeTank.changToOldDir(); // 转换到原来的方向上去
				return true;
			}
			return false;
		}

		public boolean collideWithWall(MetalWall w) { // 撞到金属墙
			if (HomeTank.live && HomeTank.getRect().intersects(w.getRect())) {
				HomeTank.changToOldDir();
				return true;
			}
			return false;
		}

		public boolean collideRiver(River r) { // 撞到河流的时候
			if (HomeTank.live && HomeTank.getRect().intersects(r.getRect())) {
				HomeTank.changToOldDir();
				return true;
			}
			return false;
		}

		public boolean collideHome(Home h) { // 撞到家的时候
			if (HomeTank.live && HomeTank.getRect().intersects(h.getRect())) {
				HomeTank.changToOldDir();
				return true;
			}
			return false;
		}

		public boolean collideWithTanks(java.util.List<Tank> tanks) {// 撞到坦克时
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
				g.drawRect(375, 585, width, 10);// 显示玩家坦克的血量条
				int w = width * life / 200;
				g.fillRect(375, 585, w, 10);// 显示玩家坦克的血量条
				g.setColor(c);
			}
		}

		public boolean eat(Blood b) {
			if (HomeTank.live && b.isLive() && HomeTank.getRect().intersects(b.getRect())) {
				if (HomeTank.life <= 100)
					HomeTank.life = HomeTank.life + 100; // 每吃一个，增加100生命点
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
