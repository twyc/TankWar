package tankWar;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.JOptionPane;


/**
 * 坦克大战的主类
 */

public class GameFrame extends Frame implements ActionListener {

	private static final long serialVersionUID = 5972735870004738773L;

	public static final int Fram_width = Info.getInstance().getFramWidth(); // 静态全局窗口大小
	public static final int Fram_length = Info.getInstance().getFramLength();
	public static boolean printable = true; // 记录暂停状态，此时线程不刷新界面
	MenuBar jmb = null;
	Menu jm1 = null, jm2 = null, jm3 = null, jm4 = null;
	MenuItem jmi1 = null, jmi2 = null, jmi3 = null, jmi4 = null, jmi5 = null, jmi6 = null, jmi7 = null, jmi8 = null,
			jmi9 = null;
	Image screenImage = null;

	HomeTank homeTank = new HomeTank(300, 560, Direction.STOP, this);// 实例化坦克
	Home home = Home.getInstance(this);// 实例化home
	TSResultNo tsResultNo = new TSResultNo();//坦克和静止物体之间撞到没反应的策略
	TSResultYes tsResultYes = new TSResultYes();//坦克和静止物体之间撞到没反应的策略
	TBResultLife tbResultLife = new TBResultLife();//有血条的坦克和子弹之间撞到的策略
	TBResultNoLife tbResultNoLife = new TBResultNoLife();//有血条的坦克和子弹之间撞到的策略
	TTResult ttResult = new TTResult();//坦克之间相互碰撞的策略
	
	List<Prop> props = new ArrayList<Prop>();
	List<River> theRiver = new ArrayList<River>();
	List<AutoTank> tanks = new ArrayList<AutoTank>();
	List<BombTank> bombTanks = new ArrayList<BombTank>();
	List<Bullets> bullets = new ArrayList<Bullets>();
	List<Tree> trees = new ArrayList<Tree>();
	List<Wall> homeWall = new ArrayList<Wall>();
	List<BrickWall> otherWall = new ArrayList<BrickWall>();
	List<MetalWall> metalWall = new ArrayList<MetalWall>();

	// 将之前写在构造函数里面的初始化部分提取出来重新写两个init方法
	// 初始化页面
	public void initPage() {

		jmb = new MenuBar();
		jm1 = new Menu("游戏");
		jm2 = new Menu("暂停/继续");
		jm3 = new Menu("帮助");
		jm4 = new Menu("游戏级别");
		jm1.setFont(new Font("TimesRoman", Font.BOLD, 15));// 设置菜单显示的字体
		jm2.setFont(new Font("TimesRoman", Font.BOLD, 15));// 设置菜单显示的字体
		jm3.setFont(new Font("TimesRoman", Font.BOLD, 15));// 设置菜单显示的字体
		jm4.setFont(new Font("TimesRoman", Font.BOLD, 15));// 设置菜单显示的字体

		jmi1 = new MenuItem("开始新游戏");
		jmi2 = new MenuItem("退出");
		jmi3 = new MenuItem("暂停");
		jmi4 = new MenuItem("继续");
		jmi5 = new MenuItem("游戏说明");
		jmi6 = new MenuItem("级别1");
		jmi7 = new MenuItem("级别2");
		jmi8 = new MenuItem("级别3");
		jmi9 = new MenuItem("级别4");
		jmi1.setFont(new Font("TimesRoman", Font.BOLD, 15));
		jmi2.setFont(new Font("TimesRoman", Font.BOLD, 15));
		jmi3.setFont(new Font("TimesRoman", Font.BOLD, 15));
		jmi4.setFont(new Font("TimesRoman", Font.BOLD, 15));
		jmi5.setFont(new Font("TimesRoman", Font.BOLD, 15));

		jm1.add(jmi1);
		jm1.add(jmi2);
		jm2.add(jmi3);
		jm2.add(jmi4);
		jm3.add(jmi5);
		jm4.add(jmi6);
		jm4.add(jmi7);
		jm4.add(jmi8);
		jm4.add(jmi9);

		jmb.add(jm1);
		jmb.add(jm2);

		jmb.add(jm4);
		jmb.add(jm3);

		jmi1.addActionListener(this);
		jmi1.setActionCommand("NewGame");
		jmi2.addActionListener(this);
		jmi2.setActionCommand("Exit");
		jmi3.addActionListener(this);
		jmi3.setActionCommand("Stop");
		jmi4.addActionListener(this);
		jmi4.setActionCommand("Continue");
		jmi5.addActionListener(this);
		jmi5.setActionCommand("help");
		jmi6.addActionListener(this);
		jmi6.setActionCommand("level1");
		jmi7.addActionListener(this);
		jmi7.setActionCommand("level2");
		jmi8.addActionListener(this);
		jmi8.setActionCommand("level3");
		jmi9.addActionListener(this);
		jmi9.setActionCommand("level4");

		this.setMenuBar(jmb);// 菜单Bar放到JFrame上
		this.setVisible(true);
		this.setSize(Fram_width, Fram_length); // 设置界面大小
		this.setLocation(280, 50); // 设置界面出现的位置
		setLocationRelativeTo(null);// 让窗体居中
		this.setTitle("坦克大战——(重新开始：R键  开火：F键 火力全开：A键)                 ");

	}

	// 初始化对象容器
	public void initContainer() {
		home.setLive(true);//否则每次重新开始都会直接显示结束页面
		props.add(new Blood(this));
		props.add(new Clock(this));
		props.add(new Landmine(this));
		props.add(new Enhance(this));
		for (int i = 0; i < 8; i++) { // 家的格局
			if (i < 3)
				homeWall.add(new BrickWall(338, 580 - 35 * i, this));
			else if (i < 5)
				homeWall.add(new BrickWall(372 + 35 * (i - 3), 510, this));
			else
				homeWall.add(new BrickWall(412, 538 + (i - 5) * 35, this));
		}

		for (int i = 0; i < 16; i++) { // 砖墙
			otherWall.add(new BrickWall(220 + 35 * i, 300, this)); // 砖墙布局
			otherWall.add(new BrickWall(500 + 35 * i, 180, this));
			otherWall.add(new BrickWall(200, 400 + 35 * i, this));
			otherWall.add(new BrickWall(500, 400 + 35 * i, this));
		}


		for (int i = 0; i < 10; i++) { // 金属墙布局
			if (i < 10) {
				metalWall.add(new MetalWall(140 + 35 * i, 150, this));
				metalWall.add(new MetalWall(600, 400 + 35 * (i), this));
			} 
			else if (i < 20)
				metalWall.add(new MetalWall(140 + 35 * (i - 10), 180, this));
			else
				metalWall.add(new MetalWall(500 + 35 * (i - 10), 160, this));
		}

		for (int i = 0; i < 4; i++) { // 树的布局
			if (i < 4) {
				trees.add(new Tree(0 + 30 * i, 360, this));
				trees.add(new Tree(220 + 30 * i, 360, this));
				trees.add(new Tree(440 + 30 * i, 360, this));
				trees.add(new Tree(660 + 30 * i, 360, this));
			}
		}
		theRiver.add(new River(84, 100, this));

		for (int i = 0; i < 20; i++) { // 初始化20辆坦克
			if (i < 9) // 设置坦克出现的位置
				tanks.add(new AutoTank(150 + 70 * i, 40, Direction.D, this));
			else if (i < 15)
				tanks.add(new AutoTank(700, 140 + 50 * (i - 6), Direction.D, this));
			else
				tanks.add(new AutoTank(10, 50 * (i - 12), Direction.D, this));
		}
	}

	// 这是一个重写的方法,将由repaint()方法自动调用
	public void update(Graphics g) {

		screenImage = this.createImage(Fram_width, Fram_length);

		Graphics gps = screenImage.getGraphics();
		Color c = gps.getColor();
		gps.setColor(Color.GRAY);
		gps.fillRect(0, 0, Fram_width, Fram_length);
		gps.setColor(c);

		framPaint(gps);

		g.drawImage(screenImage, 0, 0, null);
	}

	// 地图上静止的东西
	public void staticPaint(Graphics g) {
		home.draw(g); // 画出home
		homeTank.draw(g); // 画出自己家的坦克
		for(Prop prop : props) {//吃道具
			homeTank.eat(prop);
		}
		//调用媒体跟踪器（MediaTracker）加载图像，确保图像完全加载完毕后再进行显示
		MediaTracker tracker = new MediaTracker(this);
		for (Image i : BombTank.imgs) { // 画出爆炸效果
			tracker.addImage(i, 0);
		}
		try {
			tracker.waitForAll();
		} catch (InterruptedException ex) {
		}
		for (int i = 0; i < bombTanks.size(); i++) { // 画出爆炸效果
			BombTank bt = bombTanks.get(i);
			bt.draw(g);
		}
		for (int i = 0; i < otherWall.size(); i++) { // 画出otherWall
			BrickWall cw = otherWall.get(i);
			cw.draw(g);
		}
		for (int i = 0; i < metalWall.size(); i++) { // 画出metalWall
			MetalWall mw = metalWall.get(i);
			mw.draw(g);
		}
		for (int i = 0; i < homeWall.size(); i++) { // 画出metalWall
			Wall mw = homeWall.get(i);
			if(Enhance.time > 0) {
				mw.draw(g);
				Enhance.time--;
			}else if(Enhance.flag) {
				Enhance.refun(this);
				mw.draw(g);
			}
		}
		for(Prop prop : props) {
			prop.draw(g);
		}
	}

	// 每颗子弹
	public void everyBullet(Graphics g) {
		boolean flag = false;//待会用来检测普通墙是不是被打掉了 然后来决定子弹要不要删了
		for (int i = 0; i < bullets.size(); i++) { // 对每一个子弹
			Bullets m = bullets.get(i);
			tbResultLife.collide(homeTank, m);// 每一个子弹打到自己家的坦克上时
			for (Tank tank : tanks) {
				tbResultNoLife.collide(tank, m);// 每一个子弹打到电脑坦克上时
			}
			m.hitHome(); // 每一个子弹打到家里时

			for (int j = 0; j < metalWall.size(); j++) { // 每一个子弹打到金属墙上
				MetalWall mw = metalWall.get(j);
				m.hitWall(mw);
			}

			for (int j = 0; j < otherWall.size(); j++) {// 每一个子弹打到其他墙上
				BrickWall w = otherWall.get(j);
				flag |= m.hitWall(w);
			}
			if( flag ) {
				m.setLive(false);
			}
			for (int j = 0; j < homeWall.size(); j++) {// 每一个子弹打到家的墙上
				Wall cw = homeWall.get(j);
				flag |= m.hitWall(cw);
			}
			if( flag ) {
				m.setLive(false);
			}
			m.draw(g); // 画出效果图
		}
	}

	// 每个电脑坦克
	public void everyTank(Graphics g) {
		// 画出每一辆敌方坦克
		for (int i = 0; i < tanks.size(); i++) {
			Tank t = tanks.get(i); // 获得键值对的键

			for (int j = 0; j < homeWall.size(); j++) {
				Wall cw = homeWall.get(j);
				tsResultYes.collide(t, cw); // 每一个坦克撞到家里的墙时
				cw.draw(g);
			}
			for (int j = 0; j < otherWall.size(); j++) { // 每一个坦克撞到家以外的墙
				BrickWall cw = otherWall.get(j);
				tsResultYes.collide(t, cw);
				cw.draw(g);
			}
			for (int j = 0; j < metalWall.size(); j++) { // 每一个坦克撞到金属墙
				MetalWall mw = metalWall.get(j);
				tsResultYes.collide(t, mw);
				mw.draw(g);
			}

			for (int j = 0; j < theRiver.size(); j++) {
				River r = theRiver.get(j); // 每一个坦克撞到河流时
				tsResultYes.collide(t, r);
				r.draw(g);
			}
			ttResult.collide(t, homeTank); // 撞到其他坦克
			for(Tank tank:tanks) {
				if(tank != t) {
					ttResult.collide(t, tank);
				}
			}
			tsResultYes.collide(t, home);
			t.draw(g);
		}
		for (int i = 0; i < trees.size(); i++) { // 画出trees
			Tree tr = trees.get(i);
			tr.draw(g);
		}
	}

	// 文字显示和是否结束的判断 以及游戏进程的进行
	public void framPaint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.green); // 设置字体显示属性
		Font f1 = g.getFont();
		g.setFont(new Font("TimesRoman", Font.BOLD, 20));
		g.drawString("区域内还有敌方坦克: ", 200, 70);
		g.setFont(new Font("TimesRoman", Font.ITALIC, 30));
		g.drawString("" + tanks.size(), 400, 70);
		g.setFont(new Font("TimesRoman", Font.BOLD, 20));
		g.drawString("剩余生命值: ", 500, 70);
		g.setFont(new Font("TimesRoman", Font.ITALIC, 30));
		g.drawString("" + homeTank.getLife(), 650, 70);
		g.setFont(f1);
		// 如果玩家赢了（条件是敌方坦克全灭、大本营健在、玩家坦克仍有血量）
		if (tanks.size() == 0 && home.isLive() && homeTank.isLive()) {
			Font f = g.getFont();
			g.setFont(new Font("TimesRoman", Font.BOLD, 60));
			this.otherWall.clear();
			Home.setFlag(true);
			g.drawString("你赢了！ ", 310, 300);
			g.setFont(f);
		}

		if (homeTank.isLive() == false) {
			home.gameOver(g);
			Font f = g.getFont();
			g.setFont(new Font("TimesRoman", Font.BOLD, 40));
			tanks.clear();
			bullets.clear();
			g.setFont(f);
		}
		g.setColor(c);

		staticPaint(g);
		everyBullet(g);
		everyTank(g);
		hometank(g);
	}

	public void hometank(Graphics g) {
		tsResultYes.collide(homeTank, home);
		for (int i = 0; i < metalWall.size(); i++) {// 撞到金属墙
			MetalWall w = metalWall.get(i);
			tsResultYes.collide(homeTank, w);
			w.draw(g);
		}
		for (int i = 0; i < otherWall.size(); i++) {
			BrickWall cw = otherWall.get(i);
			tsResultYes.collide(homeTank, cw);
			cw.draw(g);
		}
		for (int i = 0; i < homeWall.size(); i++) { // 家里的坦克撞到自己家
			Wall w = homeWall.get(i);
			tsResultYes.collide(homeTank, w);
			w.draw(g);
		}
		for (int i = 0; i < theRiver.size(); i++) {
			River r = theRiver.get(i); // 撞到河流时
			tsResultYes.collide(homeTank, r);
			System.out.println("here");
			r.draw(g);
		}
	}
	public GameFrame() {
		initContainer();
		initPage();
		this.addWindowListener(new WindowAdapter() { // 窗口关闭监听
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		this.setResizable(false);
		this.setBackground(Color.GREEN);
		this.setVisible(true);

		this.addKeyListener(new KeyMonitor());// 键盘监听
		new Thread(new PaintThread()).start(); // 线程启动
	}

	public static void main(String[] args) {
		new GameFrame(); // 实例化
	}

	private class PaintThread implements Runnable {
		public void run() {
			// TODO Auto-generated method stub
			while (printable) {
				repaint();
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private class KeyMonitor extends KeyAdapter {

		public void keyReleased(KeyEvent e) { // 监听键盘释放
			homeTank.keyReleased(e);
		}

		public void keyPressed(KeyEvent e) { // 监听键盘按下
			homeTank.keyPressed(e);
		}
	}

	public void actionPerformed(ActionEvent e) {
		Info info = Info.getInstance();
		if (e.getActionCommand().equals("NewGame")) {
			printable = false;
			Object[] options = { "确定", "取消" };
			int response = JOptionPane.showOptionDialog(this, "您确认要开始新游戏！", "", JOptionPane.YES_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (response == 0) {

				printable = true;
				this.dispose();
				new GameFrame();
			} else {
				printable = true;
				new Thread(new PaintThread()).start(); // 线程启动
			}

		} else if (e.getActionCommand().endsWith("Stop")) {// 暂停
			printable = false;
		} else if (e.getActionCommand().equals("Continue")) {// 继续
			if (!printable) {
				printable = true;
				new Thread(new PaintThread()).start(); // 线程启动
			}
		} else if (e.getActionCommand().equals("Exit")) {// 退出
			printable = false;
			Object[] options = { "确定", "取消" };
			int response = JOptionPane.showOptionDialog(this, "您确认要退出吗", "", JOptionPane.YES_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (response == 0) {
				System.out.println("退出");
				System.exit(0);
			} else {
				printable = true;
				new Thread(new PaintThread()).start(); // 线程启动
			}
		} else if (e.getActionCommand().equals("help")) {
			printable = false;
			JOptionPane.showMessageDialog(null, "用→ ← ↑ ↓控制方向，F键盘发射，A键盘向所有方向发射，R重新开始！", "提示！", JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(true);
			printable = true;
			new Thread(new PaintThread()).start(); // 线程启动
		} else if (e.getActionCommand().equals("level1")) {//用配置文件读取不同等级下的属性
			AutoTank.count = info.getTankCount(1);
			Tank.speed = info.getTankSpeed(1);
			Bullets.speed = info.getBulletSpeed(1);
			this.dispose();
			new GameFrame();
		} else if (e.getActionCommand().equals("level2")) {
			AutoTank.count = info.getTankCount(2);
			Tank.speed = info.getTankSpeed(2);
			Bullets.speed = info.getBulletSpeed(2);
			this.dispose();
			new GameFrame();
		} else if (e.getActionCommand().equals("level3")) {
			AutoTank.count = info.getTankCount(3);
			Tank.speed = info.getTankSpeed(3);
			Bullets.speed = info.getBulletSpeed(3);
			this.dispose();
			new GameFrame();
		} else if (e.getActionCommand().equals("level4")) {
			AutoTank.count = info.getTankCount(4);
			Tank.speed = info.getTankSpeed(4);
			Bullets.speed = info.getBulletSpeed(4);
			this.dispose();
			new GameFrame();
		}
	}
}
