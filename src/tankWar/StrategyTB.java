package tankWar;
/*
 * ����ģʽ ��Ҫ�ԣ�Ϊ���Ժ��/�����ܵ�ʱ�����OCP
 * Tank��Bullets֮����ײ�Ľ��
 */
public interface StrategyTB {
	public boolean collide(Tank t,Bullets b);
}
