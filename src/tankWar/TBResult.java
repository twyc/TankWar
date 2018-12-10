package tankWar;

/*
 * StrategyTB的实现
 * 实现的策略是中弹直接死
 */
public class TBResult implements StrategyTB{

	@Override
	public boolean collide(Tank t, Bullets b) {
		if(t.good==b.isGood()) {
			return false;
		}
		if (t.isLive() && b.isLive() && t.getRect().intersects(b.getRect())) {
			b.setLive(false);
			t.hit();//被击中之后的反应扔tank类里面去处理 
			//在这一组关系里面要处理的核心其实应该就是被击中之后的反应 我这样好像不合适 但是如果不这样代码逻辑写出来又会乱成一坨或者还要添加新类
			return true;
		}
		return false;
	}
	
}
