package tankWar;
import java.awt.*;
import java.util.Random;

/**
 * 秒表类（暂停道具）
 */

public class Clock extends Prop{

	public Clock(GameFrame tc) {
		super(tc);
		// TODO Auto-generated constructor stub
	}

	private Image[] clockImags = new Image[] { 
			tk.getImage(Clock.class.getResource("/Images/clock.png")), };
	public static void main(String[] args) {
        System.out.println(Clock.class.getResource(""));
        System.out.println(Clock.class.getResource("/"));
	}
	public void draw(Graphics g) {
		if (r.nextInt(100) > 98) {
			if(r.nextInt(100)<20) {//不让停的太久
				return;
			}
			this.live = true;
			move();
		}
		if (!live)
			return;
		g.drawImage(clockImags[0], x, y, null);
	}

	public void fun() {
		Tank.pause = 1000;
	}
	public Rectangle getRect() {
		return new Rectangle(x, y, width, length);
	}

	public boolean isLive() {// 判断是否还活着
		return live;
	}

	public void setLive(boolean live) { // 设置生命
		this.live = live;
	}
}