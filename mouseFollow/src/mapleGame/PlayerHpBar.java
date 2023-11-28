package mapleGame;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * PlayerHpBar 클래스는 플레이어의 현재 체력을 화면에 표시하는 기능을 가지고 있습니다. JLabel을 상속받아 이미지 아이콘,
 * 크기, 위치 등을 설정합니다.
 * 
 * @author 성세현
 */
public class PlayerHpBar extends JLabel {
	PlayerHpBar bar = this;
	int width, height;
	Player player;
	ImageIcon imgIcon;

	public PlayerHpBar() {
		imgIcon = new ImageIcon("image/hpp100.png");

		width = imgIcon.getIconWidth();
		height = imgIcon.getIconHeight();
		setIcon(imgIcon);
		setSize(width, height);
		setLocation(55, 630);
	}
}