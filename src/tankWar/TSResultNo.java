package tankWar;
/*
 * StrategyTS的第一种实现
 * 实现的策略是相撞但是没有影响的情况
 * 具体例子就是坦克和树相撞的情况
 * 必要性：为了以后加功能的时候符合OCP 1.以后可以有能过河的坦克 那么可以直接使用这个策略； 2.以后对碰到树的操作可能有其他选择 所以提前抽象出来；
 */
public class TSResultNo implements StrategyTS{

	@Override
	public boolean collide(Tank t, StillObject s) {
		return false;//没影响就什么都不需要做
	}
	
}
