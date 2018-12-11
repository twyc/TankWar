package tankWar;

/*
 * StrategyTB��ʵ��
 * ʵ�ֵĲ�����û����ֵ��̹����ײ��ʱ����ʧ
 */
public class TBResultNoLife implements StrategyTB{

	@Override
	public boolean collide(Tank t, Bullets b) {
		if(t.good==b.isGood()) {
			return false;
		}
		if (t.isLive() && b.isLive() && t.getRect().intersects(b.getRect())) {
			b.setLive(false);
			t.setLive(false);
			return true;
		}
		return false;
	}
	
}
