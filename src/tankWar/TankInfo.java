package tankWar;

//只从配置文件里读属性 所以不使用接口
public class TankInfo {
	private XMLUtil xmlUtil = new XMLUtil();
	
	public int getTankCount(int level) {
		 String count[] = xmlUtil.getByNames("tankCount");
		 level=(level-1)/2;
		 return Integer.parseInt(count[level]);
	}

	public int getTankSpeed(int level) {
		 String count[] = xmlUtil.getByNames("tankSpeed");
		 level=level-1;
		 return Integer.parseInt(count[level]);
	}

	public int getBulletSpeed(int level) {
		 String count[] = xmlUtil.getByNames("bulletSpeed");
		 level=level-1;
		 return Integer.parseInt(count[level]);
	}
}
