package tankWar;

import java.awt.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 子弹类
 */

public class Bullets {
	/**
	 * 子弹的全局属性
	   将两个速度属性合并为一个
	 */
	public static int speed = 15;

	public static final int width = 10;
	public static final int length = 10;

	///子弹的属性
	private int x, y;///位置
	Direction diretion;///枚举类型的方向

	private boolean good;
	private boolean live = true;///是否存在

	private GameFrame tc;

	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] bulletImages = null;
	private static Map<String, Image> imgs = new HashMap<String, Image>(); // 定义Map键值对，是不同方向对应不同的弹头

	static {
		bulletImages = new Image[] { // 不同方向的子弹
		///取资源文件
				tk.getImage(Bullets.class.getClassLoader().getResource("Images/bulletL.gif")),
				tk.getImage(Bullets.class.getClassLoader().getResource("Images/bulletU.gif")),
				tk.getImage(Bullets.class.getClassLoader().getResource("Images/bulletR.gif")),
				tk.getImage(Bullets.class.getClassLoader().getResource("Images/bulletD.gif")),
				tk.getImage(Bullets.class.getClassLoader().getResource("Images/bulletLU.gif")),
				tk.getImage(Bullets.class.getClassLoader().getResource("Images/bulletLD.gif")),
				tk.getImage(Bullets.class.getClassLoader().getResource("Images/bulletRU.gif")),
				tk.getImage(Bullets.class.getClassLoader().getResource("Images/bulletRD.gif")),
		};
	}

	public Bullets(int x, int y, Direction dir) { // 构造函数1，传递位置和方向
		this.x = x;
		this.y = y;
		this.diretion = dir;
	}

	// 构造函数2，接受另外两个参数
	public Bullets(int x, int y, boolean good, Direction dir, GameFrame tc) {
		this(x, y, dir);
		this.good = good;
		this.tc = tc;
	}

	private void move() {

		switch (diretion) {
		case L:
			x -= speed; // 子弹不断向左进攻
			break;
		case U: // 子弹不断向上进攻
			y -= speed;
			break;
		case R:
			x += speed; // 子弹不断向右进攻
			break;
		case D: // 子弹不断向下进攻
			y += speed;
			break;
		case LU:
			x -= speed; // 子弹不断向左进攻
			y -= speed;
			break;
		case LD:
			x -= speed; // 子弹不断向左进攻
			y += speed;
			break;
		case RU:
			x += speed; // 子弹不断向左进攻
			y -= speed;
			break;
		case RD:
			x += speed; // 子弹不断向左进攻
			y += speed;
			break;
		case STOP: // 游戏暂停
			break;
		}

		if (x < 0 || y < 0 || x > GameFrame.Fram_width || y > GameFrame.Fram_length) {
			live = false;
		}
	}

	public void draw(Graphics g) {
		if (!live) {
			tc.bullets.remove(this);
			return;
		}

		switch (diretion) { // 选择不同方向的子弹
		case L:
			g.drawImage(bulletImages[0], x, y, null);
			break;
		case U:
			g.drawImage(bulletImages[1], x, y, null);
			break;
		case R:
			g.drawImage(bulletImages[2], x, y, null);
			break;
		case D:
			g.drawImage(bulletImages[3], x, y, null);
			break;
		case LU:
			g.drawImage(bulletImages[4], x, y, null);
			break;
		case LD:
			g.drawImage(bulletImages[5], x, y, null);
			break;
		case RU:
			g.drawImage(bulletImages[6], x, y, null);
			break;
		case RD:
			g.drawImage(bulletImages[7], x, y, null);
			break;
		}

		move(); // 调用子弹move()函数
	}

	public boolean isLive() { // 是否还活着
		return live;
	}
	public void setLive(boolean state) {
		live = state;
	}
	public Rectangle getRect() {
		return new Rectangle(x, y, width, length);
	}

	public boolean hitTanks(List<Tank> tanks) {// 当子弹打到坦克时
		for (int i = 0; i < tanks.size(); i++) {
			if (hitTank(tanks.get(i))) { // 对每一辆坦克，调用hitTank
				return true;
			}
		}
		return false;
	}

	public boolean hitTank(Tank t) { // 当子弹打到坦克上

		if (this.live && this.getRect().intersects(t.getRect()) && t.isLive() && this.good != false) {

			BombTank e = new BombTank(t.getX(), t.getY(), tc);
			tc.bombTanks.add(e);
			t.setLive(false);
			this.live = false;
			return true; // 射击成功，返回true
		}
		return false; // 否则返回false
	}
	
	public boolean hitTank(HomeTank t) { // 当子弹打到坦克上

		if (this.live && this.getRect().intersects(t.getRect()) && t.isLive() && this.good != true) {

			BombTank e = new BombTank(t.getX(), t.getY(), tc);
			tc.bombTanks.add(e);
				t.setLife(t.getLife() - 50); // 中一颗子弹寿命减少50，中4枪就死，总生命值200
				if (t.getLife() <= 0)
					t.setLive(false); // 当寿命为0时，设置寿命为死亡状态
			this.live = false;
			return true; // 射击成功，返回true
		}
		return false; // 否则返回false
	}

	public boolean hitWall(BrickWall w) { // 子弹打到CommonWall上
		if (this.live && this.getRect().intersects(w.getRect())) {
			this.tc.otherWall.remove(w); // 子弹打到CommonWall墙上时则移除此击中墙
			this.tc.homeWall.remove(w);
			return true;
		}
		return false;
	}

	public boolean hitWall(MetalWall w) { // 子弹打到金属墙上
		if (this.live && this.getRect().intersects(w.getRect())) {
			this.live = false;
			return true;
		}
		return false;
	}

	public boolean hitHome() { // 当子弹打到大本营时
		if (this.live && this.getRect().intersects(tc.home.getRect())) {
			this.live = false;
			this.tc.home.setLive(false|Home.isFlag()); // 当大本营接受一枪时就毁灭
			return true;
		}
		return false;
	}

}
