package tankWar;
/*
 * StrategyTS�ĵ�һ��ʵ��
 * ʵ�ֵĲ�������ײȻ����Ӱ������
 * �������Ӿ���̹�˺ͺ�����ǽ��ײ�����
 */
public class TSResultYes implements StrategyTS{

	@Override
	public boolean collide(Tank t, StillObject s) {
		if (t.isLive() && t.getRect().intersects(s.getRect())) {
			t.changToOldDir(); // ת����ԭ���ķ�����ȥ
			return true;
		}
		return false;
	}
	
}
