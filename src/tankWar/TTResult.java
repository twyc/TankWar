package tankWar;

/*
 * StrategyTB的实现
 * 实现的策略是中弹直接死
 */
public class TTResult implements StrategyTT{

	@Override
	public boolean collide(Tank a, Tank b) {
		if (a.live && b.isLive() && a.getRect().intersects(b.getRect())) {
			a.changToOldDir();
			b.changToOldDir();
			return true;
		}
		return false;
	}
}
