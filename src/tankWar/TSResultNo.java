package tankWar;
/*
 * StrategyTS�ĵ�һ��ʵ��
 * ʵ�ֵĲ�������ײ����û��Ӱ������
 * �������Ӿ���̹�˺�����ײ�����
 * ��Ҫ�ԣ�Ϊ���Ժ�ӹ��ܵ�ʱ�����OCP 1.�Ժ�������ܹ��ӵ�̹�� ��ô����ֱ��ʹ��������ԣ� 2.�Ժ���������Ĳ�������������ѡ�� ������ǰ���������
 */
public class TSResultNo implements StrategyTS{

	@Override
	public boolean collide(Tank t, StillObject s) {
		return false;//ûӰ���ʲô������Ҫ��
	}
	
}
