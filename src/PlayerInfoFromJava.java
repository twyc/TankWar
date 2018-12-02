public class PlayerInfoFromJava implements IPlayerInfo {

	@Override
	public String[] getPlayerRoles() {
		// TODO Auto-generated method stub
		String[] names = {"关羽","张飞","吕布","赵云"};				
		return names;
	}
	
	@Override
	public String[] getPlayerIDs() {
		// TODO Auto-generated method stub
		String[] IDs = {"玩家1","玩家2"};
		return IDs;
	}
}
