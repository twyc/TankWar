package tankWar;
import java.awt.*;

/**
 * ����ࣨ��ͣ���ߣ�
 */

public class Clock extends Prop{

	public Clock(GameFrame tc) {
		super(tc);
		// TODO Auto-generated constructor stub
	}

	private Image[] clockImags = new Image[] { 
			tk.getImage(Clock.class.getResource("/Images/clock.png")), };
	public void draw(Graphics g) {
		if (r.nextInt(100) > 98) {
			if(r.nextInt(100)<20) {//����ͣ��̫��
				return;
			}
			this.live = true;
			move();
		}
		if (!live)
			return;
		g.drawImage(clockImags[0], x, y, null);
	}

	public void fun() {
		AutoTank.pause = Info.getInstance().getPauseTime();
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