package tankWar;

/*
 * StrategyTB��ʵ��
 * ʵ�ֵĲ������е�ֱ����
 */
public class TBResult implements StrategyTB{

	@Override
	public boolean collide(Tank t, Bullets b) {
		if(t.good==b.isGood()) {
			return false;
		}
		if (t.isLive() && b.isLive() && t.getRect().intersects(b.getRect())) {
			b.setLive(false);
			t.hit();//������֮��ķ�Ӧ��tank������ȥ���� 
			//����һ���ϵ����Ҫ����ĺ�����ʵӦ�þ��Ǳ�����֮��ķ�Ӧ ���������񲻺��� ������������������߼�д�����ֻ��ҳ�һ����߻�Ҫ�������
			return true;
		}
		return false;
	}
	
}
