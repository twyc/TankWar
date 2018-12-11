package tankWar;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;

/**
 * 大本营类
 * 把home的位置在程序里面写死 因为一定在最中间 所以大概率在以后的扩展中也不会更改
 * 在坦克大战游戏中 一定只有一个home 那么可以使用单例模式防止被多次创建
 */

public class Home implements StillObject{
	private static final int x = Info.getInstance().gethomeX();
	private static final int y = Info.getInstance().gethomeY();
	private static GameFrame tc;
	public static final int width = Info.getInstance().gethomeWidth()
			, length = Info.getInstance().gethomeLength(); // 全局静态变量长宽
	private boolean live = true;
	private static boolean flag = false;//判断游戏是否已经结束 防止胜利后再出现失败的情况
	
	private static Toolkit tk = Toolkit.getDefaultToolkit(); // 全局静态变量
	private static Image[] homeImags = null;
	static {
		homeImags = new Image[] { tk.getImage(BrickWall.class.getResource("/Images/home.jpg")), };
	}
	private final static Home instance = new Home();
	private Home(GameFrame tc) {// 私有构造函数，传递Home的参数并赋
		Home.tc = tc; // 获得控制
	}
	public static boolean isFlag() {
		return flag;
	}
	public static void setFlag(boolean flag) {
		Home.flag = flag;
	}
	private Home() {
	}
	//谁要给谁
	public static Home getInstance(GameFrame tc) {
		Home.tc = tc;
		return instance;
	}
	
	public void gameOver(Graphics g) {
		tc.tanks.clear();// 作清理页面工作
		tc.metalWall.clear();
		tc.otherWall.clear();
		tc.homeWall.clear();
		tc.bombTanks.clear();
		tc.theRiver.clear();
		tc.trees.clear();
		tc.bullets.clear();
		tc.props.clear();
		Color c = g.getColor(); // 设置参数
		g.setColor(Color.green);
		Font f = g.getFont();
		g.setFont(new Font(" ", Font.PLAIN, 40));
		g.drawString("你输了！", 220, 250);
		g.drawString("  游戏结束！ ", 220, 300);
		g.setFont(f);
		g.setColor(c);

	}

	public void draw(Graphics g) {
		if (live) { // 如果活着，则画出home
			g.drawImage(homeImags[0], x, y, null);
		} else {
			gameOver(g); // 调用游戏结束
		}
	}
	public boolean isLive() { // 判读是否还活着
		return live|flag;
	}

	public void setLive(boolean live) { // 设置生命
		this.live = live;
	}

	public Rectangle getRect() { // 返回长方形实例
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
