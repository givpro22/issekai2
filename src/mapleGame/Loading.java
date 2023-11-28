package mapleGame;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Loading 클래스는 게임 시작 시 로딩 화면을 구현한 클래스입니다. JFrame을 상속받아 로딩 화면 구성 요소를 설정하고, 로딩 시간
 * 동안 대기 후 로그인 화면으로 전환하는 기능을 가집니다.
 * 
 * @author 정아진
 */
public class Loading extends JFrame {
	ImageIcon icon;
	JLabel la;
	Loading loading = this;
	LoginScr loginScr;

	public Loading() {
		la = new JLabel();

		icon = new ImageIcon("image/gamestart.gif");
		la.setIcon(icon);

		add(la);
		setSize(icon.getIconWidth(), icon.getIconHeight());
		setLocationRelativeTo(null);

		new Thread(new Runnable() {

			@Override
			public void run() {
				int n = 0;
				while (true) {
					try {
						Thread.sleep(1000);
						n++;
						if (n == 8) {
							new LoginScr(loading);
							loading.setVisible(false);
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();

		setVisible(true);
	}

	public static void main(String[] args) {
		new Loading();
	}
}