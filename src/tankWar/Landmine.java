package tankWar;
import java.awt.*;
import java.util.Random;

/**
 * Ѫ���ࣨҽ���䣬�ɼ�Ѫ��
 */

public class Landmine extends Prop{

	public Landmine(GameFrame tc) {
		super(tc);
		// TODO Auto-generated constructor stub
	}

	private Image[] bloodImags = new Image[] { 
			tk.getImage(Landmine.class.getResource("/Images/landmine.png")), };
	
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
		g.drawImage(bloodImags[0], x, y, null);
	}

	public void fun() {
		for(Tank i : tc.tanks) {
			tc.bombTanks.add(new BombTank(i.getX(),i.getY(),tc));
			i.setLive(false);
		}
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