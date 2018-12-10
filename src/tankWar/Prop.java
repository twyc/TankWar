package tankWar;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;

/*
 * 游戏道具类
 * 暂定要有血包 秒表 炸弹
 */
public class Prop {

	public final int width = Info.getInstance().getPropWidth();
	public final int length = Info.getInstance().getPropLength();

	protected int x, y;
	GameFrame tc;
	protected static Random r = new Random();

	protected boolean live = false;

	protected static Toolkit tk = Toolkit.getDefaultToolkit();
	public Prop(GameFrame tc) {
		// TODO Auto-generated constructor stub
		this.tc = tc;
	}
	public void draw(Graphics g) {
		return;
	}
	public void fun() {
		return;
	}
	protected void move() {
		 do{
			x = r.nextInt(GameFrame.Fram_length);
			y = r.nextInt(GameFrame.Fram_width);
		}while (!isOk(x,y));
	}
	private boolean isOk(int x, int y) {//判断这个位置是不是会和其他东西碰撞
		if(x-width<0||y-length<0||x+width>tc.Fram_width||y+width>tc.Fram_length) {
			return false;
		}
		for (int j = 0; j < tc.homeWall.size(); j++) {
			Wall cw = tc.homeWall.get(j);
			if(cw.getRect().intersects(getRect())) {
				return false;
			}
		}
		for (int j = 0; j < tc.otherWall.size(); j++) { // 每一个坦克撞到家以外的墙
			BrickWall cw = tc.otherWall.get(j);
			if(cw.getRect().intersects(getRect())) {
				return false;
			}
		}
		for (int j = 0; j < tc.metalWall.size(); j++) { // 每一个坦克撞到金属墙
			MetalWall cw = tc.metalWall.get(j);
			if(cw.getRect().intersects(getRect())) {
				return false;
			}
		}
		for (int j = 0; j < tc.theRiver.size(); j++) {
			River cw = tc.theRiver.get(j); // 每一个坦克撞到河流时
			if(cw.getRect().intersects(getRect())) {
				return false;
			}
		}
		return !Home.getInstance(tc).getRect().intersects(getRect());
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