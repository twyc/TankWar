package tankWar;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * 坦克类（适用敌方坦克和玩家坦克）
 */

public class Tank {
	public static int pause = 0;
	public static int speed = 6; // 静态全局变量速度
	public static int count = 0;
	public static final int width = 35, length = 35; // 坦克的全局大小，具有不可改变性
	private Direction direction = Direction.STOP; // 初始化状态为静止
	private Direction Kdirection = Direction.U; // 初始化方向为向上
	GameFrame tc;

	private int x, y;
	private int oldX, oldY;
	private boolean live = true; // 初始化为活着

	private static Random r = new Random();
	private int step = r.nextInt(10) + 5; // 产生一个随机数,随机模拟坦克的移动路径

	private boolean bL = false, bU = false, bR = false, bD = false;

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

	public Tank(int x, int y,Direction dir, GameFrame tc) {// Tank的构造函数
		this.x = x;
		this.y = y;
		this.oldX = x;
		this.oldY = y;
		this.direction = dir;
		this.tc = tc;
	}

	public void draw(Graphics g) {
		if (!live) {
			tc.tanks.remove(this); // 删除无效的
			return;
		}

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
		if( pause > 0) {
			pause--;
			return;
		}
		this.oldX = x;
		this.oldY = y;

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
		default://这里从stop改成了所有 规定坦克不能走四面八方
			break;
		}

		if (this.direction != Direction.STOP) {
			this.Kdirection = this.direction;
		}

		if (x < 0)
			x = 0;
		if (y < 40) // 防止走出规定区域
			y = 40;
		if (x + Tank.width > GameFrame.Fram_width) // 超过区域则恢复到边界
			x = GameFrame.Fram_width - Tank.width;
		if (y + Tank.length > GameFrame.Fram_length)
			y = GameFrame.Fram_length - Tank.length;

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

	protected void changToOldDir() {
		x = oldX;
		y = oldY;
	}



	public Bullets fire() { // 开火方法
		if (!live)
			return null;
		int x = this.x + Tank.width / 2 - Bullets.width / 2; // 开火位置
		int y = this.y + Tank.length / 2 - Bullets.length / 2;
		Bullets m = new Bullets(x, y + 2, false, Kdirection, this.tc); // 没有给定方向时，向原来的方向发火
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


	public boolean collideWithWall(BrickWall w) { // 碰撞到普通墙时
		if (this.live && this.getRect().intersects(w.getRect())) {
			this.changToOldDir(); // 转换到原来的方向上去
			return true;
		}
		return false;
	}

	public boolean collideWithWall(MetalWall w) { // 撞到金属墙
		if (this.live && this.getRect().intersects(w.getRect())) {
			this.changToOldDir();
			return true;
		}
		return false;
	}

	public boolean collideRiver(River r) { // 撞到河流的时候
		if (this.live && this.getRect().intersects(r.getRect())) {
			this.changToOldDir();
			return true;
		}
		return false;
	}

	public boolean collideHome(Home h) { // 撞到家的时候
		if (this.live && this.getRect().intersects(h.getRect())) {
			this.changToOldDir();
			return true;
		}
		return false;
	}

	public boolean collideWithTanks(java.util.List<Tank> tanks) {// 撞到坦克时
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