package tankWar;

import java.awt.event.KeyEvent;
/*
 * �Ѽ����ͷźͼ������µķ�����ȡ���� ά��GameFrame����Ĵ���ɶ���
 */
public class KeyTest {
	public void keyReleased(KeyEvent e,HomeTank tank) { // �����ͷż���
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_A:
			tank.superFire();
			break;
		case KeyEvent.VK_F:
			tank.fire();
			break;
		case KeyEvent.VK_RIGHT:
			tank.bR = false;
			break;
		case KeyEvent.VK_LEFT:
			tank.bL = false;
			break;
		case KeyEvent.VK_UP:
			tank.bU = false;
			break;
		case KeyEvent.VK_DOWN:
			tank.bD = false;
			break;
		}
		tank.decideDirection(); // �ͷż��̺�ȷ���ƶ�����
	}
	public void keyPressed(KeyEvent e,GameFrame tc,HomeTank tank) { // ���ܼ����¼�
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_R: // ������Rʱ�����¿�ʼ��Ϸ
			Home.setFlag(false);
			tc.dispose();
			new GameFrame(); // ���´������
			break;
		case KeyEvent.VK_RIGHT: // �������Ҽ�
			tank.bR = true;
			break;

		case KeyEvent.VK_LEFT:// ���������
			tank.bL = true;
			break;

		case KeyEvent.VK_UP: // �������ϼ�
			tank.bU = true;
			break;

		case KeyEvent.VK_DOWN:// �������¼�
			tank.bD = true;
			break;
		}
		tank.decideDirection();// ���ú���ȷ���ƶ�����
	}
}
