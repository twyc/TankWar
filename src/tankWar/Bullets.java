package tankWar;

import java.awt.*;
import java.util.List;

/**
 * 子弹类
 */

public class Bullets {
	public static int speed = Info.getInstance().getBulletSpeed(1);

	public static final int width = Info.getInstance().getBulletWidth();
	public static final int length = Info.getInstance().getBulletLength();

	///子弹的属性
	private int x, y;///位置
	Direction diretion;///枚举类型的方向

	private boolean good;
	public boolean isGood() {
		return good;
	}

	public void setGood(boolean good) {
		this.good = good;
	}

	private boolean live = true;///是否存在

	private GameFrame tc;

	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] bulletImages = null;
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

	public Bullets(int x, int y, boolean good, Direction dir, GameFrame tc) {
		this.x = x;
		this.y = y;
		this.diretion = dir;
		this.good = good;
		this.tc = tc;
		this.live = true;
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
			x -= speed;
			y -= speed;
			break;
		case LD:
			x -= speed;
			y += speed;
			break;
		case RU:
			x += speed;
			y -= speed;
			break;
		case RD:
			x += speed;
			y += speed;
			break;
		case STOP: // 游戏暂停
			break;
		}

		if (x < 0 || y < 0 || x > GameFrame.Fram_width || y > GameFrame.Fram_width) {
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
		default:
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

	public boolean hitWall(Wall cw) { // 子弹打到CommonWall上
		if (this.live && this.getRect().intersects(cw.getRect())) {
			if(cw instanceof BrickWall) {
				tc.otherWall.remove(cw); // 子弹打到CommonWall墙上时则移除此击中墙
				tc.homeWall.remove(cw);
			}
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
