
import java.util.Random;
import java.util.Scanner;

public class Player {
	public int currentPosition; // ��ҵĵ�ǰλ��
	public String goAndStop;// �߻�ͣ��ʶ�����磺��on����off��
	
	public String ID; // �����ݱ�ʶ�����磺"���1"�������2��
	public String roleName; // ��ҵĽ�ɫ����
	public String symbol;//����ڵ�ͼ�ϵ���ʾ���ţ����硰A����B��

	Player() {
		currentPosition = 0;
		goAndStop = "on";
	}

	/**
	 * ���ö�ս��ɫ
	 * 
	 * @param role
	 *            ��ɫ����
	 */
	public void setRole(int role) {
		//IPlayerInfo playerInfo=new PlayerInfoFromJava();
		IPlayerInfo playerInfo=new PlayerInfoFromXML();
		roleName = playerInfo.getPlayerRoles()[role];
	}

	/**
	 * ������
	 * 
	 * @return step ������������Ŀ
	 */
	public int throwShifter() {
		int step = 0;
		System.out.print("\n" + roleName + ", ������������ĸ����س����������ӣ� ");
		Scanner input = new Scanner(System.in);
		String answer = input.next();
		// ����һ��1~6������,���������ӵ���
		//step = (int) (Math.random() * 10) % 6 + 1; 
		step = new Random().nextInt(6) + 1; 
		System.out.println("\n-----------------"); // ��ʾ�����Ϣ
		System.out.println("�������� " + step);
		return step;
	}


}
