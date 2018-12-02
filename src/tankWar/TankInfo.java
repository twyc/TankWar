package tankWar;

//ֻ�������ļ�������� ���Բ�ʹ�ýӿ�
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
