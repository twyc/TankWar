package tankWar;
import java.awt.*;
import java.util.Random;

/**
 * 血包类（医疗箱，可加血）
 */

public class Blood extends Prop{

	public Blood(GameFrame tc) {
		super(tc);
		// TODO Auto-generated constructor stub
	}

	private Image[] bloodImags = new Image[] { 
			tk.getImage(Blood.class.getResource("/Images/hp.png")), };
	
	public void draw(Graphics g) {
		if (r.nextInt(100) > 98) {
			if(r.nextInt(100)<20) {//不让血包停的太久
				return;
			}
			this.live = true;
			move();
		}
		if (!live)
			return;
		g.drawImage(bloodImags[0], x, y, null);

	}

	public void fun() {
		if (tc.homeTank.getLife() <= 100)
			tc.homeTank.setLife(tc.homeTank.getLife()+100);// 每吃一个，增加100生命点
		else
			tc.homeTank.setLife(200);
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