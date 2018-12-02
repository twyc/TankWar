package tankWar;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

/**
 * ��ը��̹���ࣨģ��̹�˱�ը���̣�
 */
public class BombTank {
	private int x, y;
	private boolean live = true; // ��ʼ״̬Ϊ����
	private GameFrame tc;
	private static Toolkit tk = Toolkit.getDefaultToolkit();

	private static Image[] imgs = { // �洢��ըͼƬ����С����ı�ըЧ��ͼ��
			tk.getImage(BombTank.class.getClassLoader().getResource("Images/1.gif")),
			tk.getImage(BombTank.class.getClassLoader().getResource("Images/2.gif")),
			tk.getImage(BombTank.class.getClassLoader().getResource("Images/3.gif")),
			tk.getImage(BombTank.class.getClassLoader().getResource("Images/4.gif")),
			tk.getImage(BombTank.class.getClassLoader().getResource("Images/5.gif")),
			tk.getImage(BombTank.class.getClassLoader().getResource("Images/6.gif")),
			tk.getImage(BombTank.class.getClassLoader().getResource("Images/7.gif")),
			tk.getImage(BombTank.class.getClassLoader().getResource("Images/8.gif")),
			tk.getImage(BombTank.class.getClassLoader().getResource("Images/9.gif")),
			tk.getImage(BombTank.class.getClassLoader().getResource("Images/10.gif")), };
	int step = 0;

	public BombTank(int x, int y, GameFrame tc) { // ���캯��
		this.x = x;
		this.y = y;
		this.tc = tc;
	}

	public void draw(Graphics g) { // ������ըͼ��

		if (!live) { // ̹����ʧ��ɾ����ըͼ
			tc.bombTanks.remove(this);
			return;
		}
		if (step == imgs.length) {
			live = false;
			step = 0;
			return;
		}

		g.drawImage(imgs[step], x, y, null);
		step++;
	}
}
