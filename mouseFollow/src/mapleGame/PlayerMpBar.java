package mapleGame;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * PlayerMpBar 클래스는 플레이어의 현재 마나(마력)을 화면에 표시하는 기능을 가지고 있습니다. JLabel을 상속받아 이미지
 * 아이콘, 크기, 위치 등을 설정합니다.
 * 
 * @author 성세현
 */
public class PlayerMpBar extends JLabel {
	PlayerMpBar bar = this;
	int width, height;
	Player player;
	ImageIcon imgIcon;

	public PlayerMpBar() {
		imgIcon = new ImageIcon("image/mpp50.png");

		width = imgIcon.getIconWidth();
		height = imgIcon.getIconHeight();
		setIcon(imgIcon);
		setSize(width, height);
		setLocation(400, 630);
	}
}