
import java.util.Random;
import java.util.Scanner;

public class Player {
	public int currentPosition; // 玩家的当前位置
	public String goAndStop;// 走或停标识，例如：“on”或“off”
	
	public String ID; // 玩家身份标识，例如："玩家1"、“玩家2”
	public String roleName; // 玩家的角色名称
	public String symbol;//玩家在地图上的显示符号，例如“A”或“B”

	Player() {
		currentPosition = 0;
		goAndStop = "on";
	}

	/**
	 * 设置对战角色
	 * 
	 * @param role
	 *            角色代号
	 */
	public void setRole(int role) {
		//IPlayerInfo playerInfo=new PlayerInfoFromJava();
		IPlayerInfo playerInfo=new PlayerInfoFromXML();
		roleName = playerInfo.getPlayerRoles()[role];
	}

	/**
	 * 掷骰子
	 * 
	 * @return step 掷出的骰子数目
	 */
	public int throwShifter() {
		int step = 0;
		System.out.print("\n" + roleName + ", 请您按任意字母键后回车启动掷骰子： ");
		Scanner input = new Scanner(System.in);
		String answer = input.next();
		// 产生一个1~6的数字,即掷的骰子点数
		//step = (int) (Math.random() * 10) % 6 + 1; 
		step = new Random().nextInt(6) + 1; 
		System.out.println("\n-----------------"); // 显示结果信息
		System.out.println("骰子数： " + step);
		return step;
	}


}
