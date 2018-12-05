package tankWar;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.Random;

/*
 * 游戏道具类
 * 暂定要有血包 秒表 炸弹
 */
public class Prop {

	public final int width = 34;
	public final int length = 30;

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
		x = r.nextInt(GameFrame.Fram_length - width);
		y = r.nextInt(GameFrame.Fram_width - length);
	}
	public static void main(String[] args) {
		for(int i=72;i>-72;i--) {
			for(int j=72;j>-72;j--) {
				for(int k=72;k>-72;k--) {
					if(i+j+k==27&&i*j*k==72) {
						System.out.println("i="+i+"j="+j+"k="+k);
					}
				}
			}
		}
		System.out.println("end");
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