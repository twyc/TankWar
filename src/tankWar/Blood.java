package tankWar;
import java.awt.*;
import java.util.Random;

/**
 * Ѫ���ࣨҽ���䣬�ɼ�Ѫ��
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
			if(r.nextInt(100)<20) {//����Ѫ��ͣ��̫��
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
			tc.homeTank.setLife(tc.homeTank.getLife()+100);// ÿ��һ��������100������
		else
			tc.homeTank.setLife(200);
	}
	public Rectangle getRect() {
		return new Rectangle(x, y, width, length);
	}

	public boolean isLive() {// �ж��Ƿ񻹻���
		return live;
	}

	public void setLive(boolean live) { // ��������
		this.live = live;
	}
}