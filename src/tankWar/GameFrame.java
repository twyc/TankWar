package tankWar;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.JOptionPane;

/**
 * ̹�˴�ս������
 */

public class GameFrame extends Frame implements ActionListener {

	private static final long serialVersionUID = 5972735870004738773L;

	public static final int Fram_width = 800; // ��̬ȫ�ִ��ڴ�С
	public static final int Fram_length = 600;
	public static boolean printable = true; // ��¼��ͣ״̬����ʱ�̲߳�ˢ�½���
	MenuBar jmb = null;
	Menu jm1 = null, jm2 = null, jm3 = null, jm4 = null;
	MenuItem jmi1 = null, jmi2 = null, jmi3 = null, jmi4 = null, jmi5 = null, jmi6 = null, jmi7 = null, jmi8 = null,
			jmi9 = null;
	Image screenImage = null;

	HomeTank homeTank = HomeTank.getInstance(this);// ʵ����̹��
	Blood blood = new Blood(); // ʵ����Ѫ��

	Home home = Home.getInstance(this);// ʵ����home

	List<River> theRiver = new ArrayList<River>();
	List<Tank> tanks = new ArrayList<Tank>();
	List<BombTank> bombTanks = new ArrayList<BombTank>();
	List<Bullets> bullets = new ArrayList<Bullets>();
	List<Tree> trees = new ArrayList<Tree>();
	List<BrickWall> homeWall = new ArrayList<BrickWall>();
	List<BrickWall> otherWall = new ArrayList<BrickWall>();
	List<MetalWall> metalWall = new ArrayList<MetalWall>();

	// ��֮ǰд�ڹ��캯������ĳ�ʼ��������ȡ��������д����init����
	// ��ʼ��ҳ��
	public void initPage() {

		jmb = new MenuBar();
		jm1 = new Menu("��Ϸ");
		jm2 = new Menu("��ͣ/����");
		jm3 = new Menu("����");
		jm4 = new Menu("��Ϸ����");
		jm1.setFont(new Font("TimesRoman", Font.BOLD, 15));// ���ò˵���ʾ������
		jm2.setFont(new Font("TimesRoman", Font.BOLD, 15));// ���ò˵���ʾ������
		jm3.setFont(new Font("TimesRoman", Font.BOLD, 15));// ���ò˵���ʾ������
		jm4.setFont(new Font("TimesRoman", Font.BOLD, 15));// ���ò˵���ʾ������

		jmi1 = new MenuItem("��ʼ����Ϸ");
		jmi2 = new MenuItem("�˳�");
		jmi3 = new MenuItem("��ͣ");
		jmi4 = new MenuItem("����");
		jmi5 = new MenuItem("��Ϸ˵��");
		jmi6 = new MenuItem("����1");
		jmi7 = new MenuItem("����2");
		jmi8 = new MenuItem("����3");
		jmi9 = new MenuItem("����4");
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

		this.setMenuBar(jmb);// �˵�Bar�ŵ�JFrame��
		this.setVisible(true);
		this.setSize(Fram_width, Fram_length); // ���ý����С
		// this.setLocation(280, 50); // ���ý�����ֵ�λ��
		setLocationRelativeTo(null);// �ô������
		this.setTitle("̹�˴�ս����(���¿�ʼ��R��  ����F��)                 ");

	}

	// ��ʼ����������
	public void initContainer() {
		home.setLive(true);//����ÿ�����¿�ʼ����ֱ����ʾ����ҳ��
		for (int i = 0; i < 10; i++) { // �ҵĸ��
			if (i < 4)
				homeWall.add(new BrickWall(350, 580 - 21 * i, this));
			else if (i < 7)
				homeWall.add(new BrickWall(372 + 22 * (i - 4), 517, this));
			else
				homeWall.add(new BrickWall(416, 538 + (i - 7) * 21, this));
		}

		for (int i = 0; i < 32; i++) { // שǽ
			if (i < 16) {
				otherWall.add(new BrickWall(220 + 20 * i, 300, this)); // שǽ����
				otherWall.add(new BrickWall(500 + 20 * i, 180, this));
				otherWall.add(new BrickWall(200, 400 + 20 * i, this));
				otherWall.add(new BrickWall(500, 400 + 20 * i, this));
			} else if (i < 32) {
				otherWall.add(new BrickWall(220 + 20 * (i - 16), 320, this));
				otherWall.add(new BrickWall(500 + 20 * (i - 16), 220, this));
				otherWall.add(new BrickWall(220, 400 + 20 * (i - 16), this));
				otherWall.add(new BrickWall(520, 400 + 20 * (i - 16), this));
			}
		}

		for (int i = 0; i < 20; i++) { // ����ǽ����
			if (i < 10) {
				metalWall.add(new MetalWall(140 + 30 * i, 150, this));
				metalWall.add(new MetalWall(600, 400 + 20 * (i), this));
			} else if (i < 20)
				metalWall.add(new MetalWall(140 + 30 * (i - 10), 180, this));
			else
				metalWall.add(new MetalWall(500 + 30 * (i - 10), 160, this));
		}

		for (int i = 0; i < 4; i++) { // ���Ĳ���
			if (i < 4) {
				trees.add(new Tree(0 + 30 * i, 360, this));
				trees.add(new Tree(220 + 30 * i, 360, this));
				trees.add(new Tree(440 + 30 * i, 360, this));
				trees.add(new Tree(660 + 30 * i, 360, this));
			}
		}
		theRiver.add(new River(84, 100, this));

		for (int i = 0; i < 20; i++) { // ��ʼ��20��̹��
			if (i < 9) // ����̹�˳��ֵ�λ��
				tanks.add(new Tank(150 + 70 * i, 40, false, Direction.D, this));
			else if (i < 15)
				tanks.add(new Tank(700, 140 + 50 * (i - 6), false, Direction.D, this));
			else
				tanks.add(new Tank(10, 50 * (i - 12), false, Direction.D, this));
		}
	}

	// ����һ����д�ķ���,����repaint()�����Զ�����
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

	// ��ͼ�Ͼ�ֹ�Ķ���
	public void staticPaint(Graphics g) {
		home.draw(g); // ����home
		homeTank.draw(g); // �����Լ��ҵ�̹��
		homeTank.eat(blood);// ��Ѫ--����ֵ
		for (int i = 0; i < bombTanks.size(); i++) { // ������ըЧ��
			BombTank bt = bombTanks.get(i);
			bt.draw(g);
		}
		for (int i = 0; i < otherWall.size(); i++) { // ����otherWall
			BrickWall cw = otherWall.get(i);
			cw.draw(g);
		}
		for (int i = 0; i < metalWall.size(); i++) { // ����metalWall
			MetalWall mw = metalWall.get(i);
			mw.draw(g);
		}
		blood.draw(g);// ������Ѫ��
	}

	// ÿ���ӵ�
	public void everyBullet(Graphics g) {
		for (int i = 0; i < bullets.size(); i++) { // ��ÿһ���ӵ�
			Bullets m = bullets.get(i);
			m.hitTanks(tanks); // ÿһ���ӵ���̹����
			m.hitTank(homeTank); // ÿһ���ӵ����Լ��ҵ�̹����ʱ
			m.hitHome(); // ÿһ���ӵ��򵽼���ʱ

			for (int j = 0; j < metalWall.size(); j++) { // ÿһ���ӵ��򵽽���ǽ��
				MetalWall mw = metalWall.get(j);
				m.hitWall(mw);
			}

			for (int j = 0; j < otherWall.size(); j++) {// ÿһ���ӵ�������ǽ��
				BrickWall w = otherWall.get(j);
				m.hitWall(w);
			}

			for (int j = 0; j < homeWall.size(); j++) {// ÿһ���ӵ��򵽼ҵ�ǽ��
				BrickWall cw = homeWall.get(j);
				m.hitWall(cw);
			}
			m.draw(g); // ����Ч��ͼ
		}
	}

	// ÿ������̹��
	public void everyTank(Graphics g) {
		// ����ÿһ���з�̹��
		for (int i = 0; i < tanks.size(); i++) {
			Tank t = tanks.get(i); // ��ü�ֵ�Եļ�

			for (int j = 0; j < homeWall.size(); j++) {
				BrickWall cw = homeWall.get(j);
				t.collideWithWall(cw); // ÿһ��̹��ײ�������ǽʱ
				cw.draw(g);
			}
			for (int j = 0; j < otherWall.size(); j++) { // ÿһ��̹��ײ���������ǽ
				BrickWall cw = otherWall.get(j);
				t.collideWithWall(cw);
				cw.draw(g);
			}
			for (int j = 0; j < metalWall.size(); j++) { // ÿһ��̹��ײ������ǽ
				MetalWall mw = metalWall.get(j);
				t.collideWithWall(mw);
				mw.draw(g);
			}

			for (int j = 0; j < theRiver.size(); j++) {
				River r = theRiver.get(j); // ÿһ��̹��ײ������ʱ
				t.collideRiver(r);
				r.draw(g);
			}
			t.collideWithTanks(tanks); // ײ���Լ�����
			t.collideHome(home);
			t.draw(g);
		}
		for (int i = 0; i < trees.size(); i++) { // ����trees
			Tree tr = trees.get(i);
			tr.draw(g);
		}
	}

	// ������ʾ���Ƿ�������ж�
	public void framPaint(Graphics g) {
		Color c = g.getColor();
		g.setColor(Color.green); // ����������ʾ����
		Font f1 = g.getFont();
		g.setFont(new Font("TimesRoman", Font.BOLD, 20));
		g.drawString("�����ڻ��ез�̹��: ", 200, 70);
		g.setFont(new Font("TimesRoman", Font.ITALIC, 30));
		g.drawString("" + tanks.size(), 400, 70);
		g.setFont(new Font("TimesRoman", Font.BOLD, 20));
		g.drawString("ʣ������ֵ: ", 500, 70);
		g.setFont(new Font("TimesRoman", Font.ITALIC, 30));
		g.drawString("" + homeTank.getLife(), 650, 70);
		g.setFont(f1);
		// ������Ӯ�ˣ������ǵз�̹��ȫ�𡢴�Ӫ���ڡ����̹������Ѫ����
		if (tanks.size() == 0 && home.isLive() && homeTank.isLive()) {
			Font f = g.getFont();
			g.setFont(new Font("TimesRoman", Font.BOLD, 60));
			this.otherWall.clear();
			g.drawString("��Ӯ�ˣ� ", 310, 300);
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
		// ���̹��
		homeTank.collideWithTanks(tanks);
		homeTank.collideHome(home);
		for (int i = 0; i < metalWall.size(); i++) {// ײ������ǽ
			MetalWall w = metalWall.get(i);
			homeTank.collideWithWall(w);
			w.draw(g);
		}
		for (int i = 0; i < otherWall.size(); i++) {
			BrickWall cw = otherWall.get(i);
			homeTank.collideWithWall(cw);
			cw.draw(g);
		}
		for (int i = 0; i < homeWall.size(); i++) { // �����̹��ײ���Լ���
			BrickWall w = homeWall.get(i);
			homeTank.collideWithWall(w);
			w.draw(g);
		}
		for (int i = 0; i < theRiver.size(); i++) {
			River r = theRiver.get(i); // ײ������ʱ
			homeTank.collideRiver(r);
			r.draw(g);
		}
	}

	public GameFrame() {
		initContainer();
		initPage();
		this.addWindowListener(new WindowAdapter() { // ���ڹرռ���
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		this.setResizable(false);
		this.setBackground(Color.GREEN);
		this.setVisible(true);

		this.addKeyListener(new KeyMonitor());// ���̼���
		new Thread(new PaintThread()).start(); // �߳�����
	}

	public static void main(String[] args) {
		new GameFrame(); // ʵ����
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

		public void keyReleased(KeyEvent e) { // ���������ͷ�
			homeTank.keyReleased(e);
		}

		public void keyPressed(KeyEvent e) { // �������̰���
			homeTank.keyPressed(e);
		}
	}

	public void actionPerformed(ActionEvent e) {
		TankInfo tankInfo = new TankInfo();
		if (e.getActionCommand().equals("NewGame")) {
			printable = false;
			Object[] options = { "ȷ��", "ȡ��" };
			int response = JOptionPane.showOptionDialog(this, "��ȷ��Ҫ��ʼ����Ϸ��", "", JOptionPane.YES_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (response == 0) {

				printable = true;
				this.dispose();
				new GameFrame();
			} else {
				printable = true;
				new Thread(new PaintThread()).start(); // �߳�����
			}

		} else if (e.getActionCommand().endsWith("Stop")) {// ��ͣ
			printable = false;
		} else if (e.getActionCommand().equals("Continue")) {// ����
			if (!printable) {
				printable = true;
				new Thread(new PaintThread()).start(); // �߳�����
			}
		} else if (e.getActionCommand().equals("Exit")) {// �˳�
			printable = false;
			Object[] options = { "ȷ��", "ȡ��" };
			int response = JOptionPane.showOptionDialog(this, "��ȷ��Ҫ�˳���", "", JOptionPane.YES_OPTION,
					JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
			if (response == 0) {
				System.out.println("�˳�");
				System.exit(0);
			} else {
				printable = true;
				new Thread(new PaintThread()).start(); // �߳�����
			}
		} else if (e.getActionCommand().equals("help")) {
			printable = false;
			JOptionPane.showMessageDialog(null, "�á� �� �� �����Ʒ���F���̷��䣬R���¿�ʼ��", "��ʾ��", JOptionPane.INFORMATION_MESSAGE);
			this.setVisible(true);
			printable = true;
			new Thread(new PaintThread()).start(); // �߳�����
		} else if (e.getActionCommand().equals("level1")) {//�������ļ���ȡ��ͬ�ȼ��µ�����
			Tank.count = tankInfo.getTankCount(1);
			Tank.speed = tankInfo.getTankSpeed(1);
			Bullets.speed = tankInfo.getBulletSpeed(1);
			this.dispose();
			new GameFrame();
		} else if (e.getActionCommand().equals("level2")) {
			Tank.count = tankInfo.getTankCount(2);
			Tank.speed = tankInfo.getTankSpeed(2);
			Bullets.speed = tankInfo.getBulletSpeed(2);
			this.dispose();
			new GameFrame();
		} else if (e.getActionCommand().equals("level3")) {
			Tank.count = tankInfo.getTankCount(3);
			Tank.speed = tankInfo.getTankSpeed(3);
			Bullets.speed = tankInfo.getBulletSpeed(3);
			this.dispose();
			new GameFrame();
		} else if (e.getActionCommand().equals("level4")) {
			Tank.count = tankInfo.getTankCount(4);
			Tank.speed = tankInfo.getTankSpeed(4);
			Bullets.speed = tankInfo.getBulletSpeed(4);
			this.dispose();
			new GameFrame();
		}
	}
}