package tankWar;

/*
 * StrategyTB的实现
 * 实现的策略是有生命值的坦克碰撞的时候扣血
 */
public class TBResultLife implements StrategyTB{

	@Override
	public boolean collide(Tank t, Bullets b) {
		if(t.good==b.isGood()) {
			return false;
		}
		if (t.isLive() && b.isLive() && t.getRect().intersects(b.getRect())) {
			b.setLive(false);
			if(t instanceof HomeTank) {
				int temp =((HomeTank) t).getLife();
				if(temp>=50) {
					((HomeTank) t).setLife(temp-50);
				}
				else {
					((HomeTank) t).setLife(0);
				}
			}
			else {
				System.out.println("策略调用错误");//这个策略只适用有生命值的坦克
			}
			return true;
		}
		return false;
	}
	
}
