package tankWar;
import java.awt.*;

/**
 * 砖墙类（子弹可打穿）
 */
public class Wall implements StillObject{
	public static final int width = Info.getInstance().getWallWidth(); // 设置墙的固定参数
	public static final int length = Info.getInstance().getWallLength();
	protected int x, y;
	GameFrame tc;
	protected static Toolkit tk = Toolkit.getDefaultToolkit();

	public Wall(int x, int y, GameFrame tc) { // 构造函数
		this.x = x;
		this.y = y;
		this.tc = tc; // 获得界面控制
	}

	public void draw(Graphics g) {// 画commonWall
		return;
	}

	public Rectangle getRect() { // 构造指定参数的长方形实例
		return new Rectangle(x, y, width, length);
	}

	@Override
	public boolean hit(Bullets b) {
		if (b.isLive() && getRect().intersects(b.getRect())) {
			b.setLive(false);
			return true;//通过返回值保证打到了 具体的处理还是放在GameFrame里面通过对容器的操作来完成
		}
		return false;
	}


}
