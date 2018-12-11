package tankWar;

import java.awt.*;

/**
 * 坦克类
 * 父类
 */

public class Tank {
	public static int speed = Info.getInstance().getTankSpeed(1); // 静态全局变量速度
	public static final int width = Info.getInstance().getTankWidth(), length = Info.getInstance().getTankLength(); // 坦克的全局大小，具有不可改变性
	protected Direction direction = Direction.STOP; // 初始化状态为静止
	protected Direction Kdirection = Direction.U; // 初始化方向为向上
	GameFrame tc;

	protected int x, y;
	protected int oldX, oldY;
	protected boolean live = true; // 初始化为活着
	protected boolean good;
	protected static Toolkit tk = Toolkit.getDefaultToolkit();// 控制面板
	protected static Image[] tankImags = null; // 存储全局静态
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

	public Tank(int x, int y,Direction dir, GameFrame tc,boolean good) {// Tank的构造函数
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
		oldX = x;
		oldY = y;

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
		
	}

	protected void changToOldDir() {
		x = oldX;
		y = oldY;
	}

	public void hit() {//被击中之后的动作 主要动作都在子类里面完成 这里只写共性
		tc.bombTanks.add(new BombTank(x, y, tc));
	}

	public Bullets fire() { // 开火方法
		if (!live) {
			return null;
		}
		int x = this.x + Tank.width / 2 - Bullets.width / 2; // 开火位置
		int y = this.y + Tank.length / 2 - Bullets.length / 2;
		Bullets m = new Bullets(x, y + 2, good, Kdirection, this.tc); // 没有给定方向时，向原来的方向发火
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

//运用了策略模式 所以去掉了碰撞检测方法

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}