package tankWar;
/*
 * ����ģʽ ��Ҫ�ԣ�Ϊ���Ժ��/�����ܵ�ʱ�����OCP
 * Tank��StillObject֮��Ĺ�ϵ
 * ̹�˺�������ǽ֮����ײ�Ľӿ�
 */
public interface StrategyTS {
	public boolean collide(Tank t,StillObject s);
}
