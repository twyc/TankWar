package tankWar;

import java.awt.event.KeyEvent;
/*
 * 把监听释放和监听按下的方法提取出来 维护GameFrame里面的代码可读性
 */
public class KeyTest {
	public void keyReleased(KeyEvent e,HomeTank tank) { // 键盘释放监听
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
		tank.decideDirection(); // 释放键盘后确定移动方向
	}
	public void keyPressed(KeyEvent e,GameFrame tc,HomeTank tank) { // 接受键盘事件
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_R: // 当按下R时，重新开始游戏
			Home.setFlag(false);
			tc.dispose();
			new GameFrame(); // 重新创建面板
			break;
		case KeyEvent.VK_RIGHT: // 监听向右键
			tank.bR = true;
			break;

		case KeyEvent.VK_LEFT:// 监听向左键
			tank.bL = true;
			break;

		case KeyEvent.VK_UP: // 监听向上键
			tank.bU = true;
			break;

		case KeyEvent.VK_DOWN:// 监听向下键
			tank.bD = true;
			break;
		}
		tank.decideDirection();// 调用函数确定移动方向
	}
}
