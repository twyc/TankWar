package tankWar;
import java.awt.*;

/**
 * 秒表类（暂停道具）
 */

public class Enhance extends Prop{
	public static int time = 0;
	public static boolean flag = false;

	private Image[] clockImags = new Image[] { 
			tk.getImage(Enhance.class.getResource("/Images/Enhance.jpg")), };
	public Enhance(GameFrame gameFrame) {
		super(gameFrame);
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
		tc.homeWall.clear();
		for (int i = 0; i < 8; i++) { // 家的格局
			if (i < 3)
				tc.homeWall.add(new MetalWall(338, 580 - 35 * i, tc));
			else if (i < 5)
				tc.homeWall.add(new MetalWall(372 + 35 * (i - 3), 510, tc));
			else
				tc.homeWall.add(new MetalWall(412, 538 + (i - 5) * 35, tc));
		}
		flag=true;
		time = 500;
	}
	public static void refun(GameFrame tc) {
		tc.homeWall.clear();
		for (int i = 0; i < 8; i++) { // 家的格局
			if (i < 3)
				tc.homeWall.add(new BrickWall(338, 580 - 35 * i, tc));
			else if (i < 5)
				tc.homeWall.add(new BrickWall(372 + 35 * (i - 3), 510, tc));
			else
				tc.homeWall.add(new BrickWall(412, 538 + (i - 5) * 35, tc));
		}
		flag=false;
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