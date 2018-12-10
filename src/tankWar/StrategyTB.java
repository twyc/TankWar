package tankWar;
/*
 * 策略模式 必要性：为了以后加/换功能的时候符合OCP
 * Tank和Bullets之间碰撞的结果
 */
public interface StrategyTB {
	public boolean collide(Tank t,Bullets b);
}
