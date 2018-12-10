package tankWar;
/*
 * StrategyTS的第一种实现
 * 实现的策略是相撞然后有影响的情况
 * 具体例子就是坦克和河流、墙相撞的情况
 */
public class TSResultYes implements StrategyTS{

	@Override
	public boolean collide(Tank t, StillObject s) {
		if (t.isLive() && t.getRect().intersects(s.getRect())) {
			t.changToOldDir(); // 转换到原来的方向上去
			return true;
		}
		return false;
	}
	
}
