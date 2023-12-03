package mapleGame;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Loading 클래스는 게임 시작 시 로딩 화면을 구현한 클래스입니다.
 *  JFrame을 상속받아 로딩 화면 구성 요소를 설정하고, 로딩 시간
 * 동안 대기 후 로그인 화면으로 전환하는 기능을 가집니다.
 * 
 * @author 정아진
 */
public class Loading extends JFrame {
	ImageIcon icon; // 로딩 화면에 사용될 이미지 아이콘
    JLabel la; // 이미지를 표시할 레이블
    Loading loading = this; // 현재 인스턴스의 참조
    LoginScr loginScr; // 로그인 화면 클래스의 참조

	/**
     * 로딩 화면의 생성자.
	 * @author 정아진
     */
	public Loading() {
		la = new JLabel();

		icon = new ImageIcon("image/gamestart.gif");
		la.setIcon(icon);

		add(la);
		setSize(icon.getIconWidth(), icon.getIconHeight());
		setLocationRelativeTo(null);// 화면 중앙에 위치

		// 별도의 스레드에서 로딩 시간을 처리
		new Thread(new Runnable() {

			@Override
			public void run() {
				int n = 0;
				while (true) {
					try {
						Thread.sleep(1000); // 1초 대기
            n++;
            if (n == 10) { // 10초 후 로그인 화면으로 전환
							new LoginScr(loading);
							loading.setVisible(false);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		setVisible(true);// 로딩 화면 표시
	}
		/**
     * 메인 메소드.
     * @param args 프로그램 인자.
		 * @author 정아진
     */
	public static void main(String[] args) {
		new Loading();
	}
}