package tankWar;
import java.awt.*;
/**
 * 墙类
 * static属性的继承里面是没有多态的
 */
public class Wall {
	int isBrick;
	public void draw(Graphics g) {// 画commonWall
		
	}
	public Rectangle getRect() { // 构造指定参数的长方形实例
		return new Rectangle(0, 0, 0, 0);
	}
}
