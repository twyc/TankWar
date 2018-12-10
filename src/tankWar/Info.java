package tankWar;

//只从配置文件里读属性 所以不使用接口
public class Info {
	
	private Info() {
	}
	private final static Info instance = new Info();
	private XMLUtil xmlUtil = new XMLUtil();//好像还是不能避免其他地方直接调用XMLUtil
	public static Info getInstance() {
		return instance;
	}
	public int getPauseTime() {
		 String count[] = xmlUtil.getByNames("pauseTime");
		 return Integer.parseInt(count[0]);
	}
	
	public int getFramWidth() {
		 String count[] = xmlUtil.getByNames("FramWidth");
		 return Integer.parseInt(count[0]);
	}
	public int getFramLength() {
		 String count[] = xmlUtil.getByNames("FramLength");
		 return Integer.parseInt(count[0]);
	}
	
	public int getWallWidth() {
		 String count[] = xmlUtil.getByNames("wallWidth");
		 return Integer.parseInt(count[0]);
	}
	public int getWallLength() {
		 String count[] = xmlUtil.getByNames("wallLength");
		 return Integer.parseInt(count[0]);
	}
	
	public int getBulletWidth() {
		 String count[] = xmlUtil.getByNames("bulletWidth");
		 return Integer.parseInt(count[0]);
	}
	public int getBulletLength() {
		 String count[] = xmlUtil.getByNames("bulletLength");
		 return Integer.parseInt(count[0]);
	}
	
	public int getPropWidth() {
		 String count[] = xmlUtil.getByNames("propWidth");
		 return Integer.parseInt(count[0]);
	}
	public int getPropLength() {
		 String count[] = xmlUtil.getByNames("propLength");
		 return Integer.parseInt(count[0]);
	}
	
	public int getTankWidth() {
		 String count[] = xmlUtil.getByNames("tankWidth");
		 return Integer.parseInt(count[0]);
	}
	public int getTankLength() {
		 String count[] = xmlUtil.getByNames("tankLength");
		 return Integer.parseInt(count[0]);
	}
	
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
