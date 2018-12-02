public class Main {
	public static void main(String args[]) {
	Player player1 = new Player();
	Player player2 = new Player();	
	//playerInfo = new PlayerInfoFromJava();
	IPlayerInfo playerInfo=new PlayerInfoFromXML();
	player1.roleName = playerInfo.getPlayerRoles()[0];
	player2.roleName = playerInfo.getPlayerRoles()[1];
	
	System.out.println("player1.roleName:"+player1.roleName);
	System.out.println("player2.roleName:"+player2.roleName);
	System.out.println("endPoint:"+new XMLUtil().getByName("endPoint"));
	System.out.println("className:"+new XMLUtil().getByName("className"));
}
}
