package tankWar;

/*
 * StrategyTB��ʵ��
 * ʵ�ֵĲ�����������ֵ��̹����ײ��ʱ���Ѫ
 */
public class TBResultLife implements StrategyTB{

	@Override
	public boolean collide(Tank t, Bullets b) {
		if(t.good==b.isGood()) {
			return false;
		}
		if (t.isLive() && b.isLive() && t.getRect().intersects(b.getRect())) {
			b.setLive(false);
			if(t instanceof HomeTank) {
				int temp =((HomeTank) t).getLife();
				if(temp>=50) {
					((HomeTank) t).setLife(temp-50);
				}
				else {
					((HomeTank) t).setLife(0);
				}
			}
			else {
				System.out.println("���Ե��ô���");//�������ֻ����������ֵ��̹��
			}
			return true;
		}
		return false;
	}
	
}
