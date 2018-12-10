package tankWar;
/*
 * 策略模式 必要性：为了以后加/换功能的时候符合OCP
 * Tank和StillObject之间的关系
 * 坦克和树河流墙之间碰撞的接口
 */
public interface StrategyTS {
	public boolean collide(Tank t,StillObject s);
}
