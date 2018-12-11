package tankWar;

/*
 * StrategyTB的实现
 * 实现的策略是没生命值的坦克碰撞的时候消失
 */
public class TBResultNoLife implements StrategyTB{

	@Override
	public boolean collide(Tank t, Bullets b) {
		if(t.good==b.isGood()) {
			return false;
		}
		if (t.isLive() && b.isLive() && t.getRect().intersects(b.getRect())) {
			b.setLive(false);
			t.setLive(false);
			return true;
		}
		return false;
	}
	
}
