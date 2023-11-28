package mapleGame;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;

/**
 * GamePoint 클래스는 게임 점수를 화면에 표시하는 기능을 가지고 있습니다. JLabel을 상속받아 텍스트, 폰트, 색상, 크기, 위치
 * 등을 설정합니다.
 * 
 * @author 성세현
 */
public class GamePoint extends JLabel {
	GamePoint gamePoint = this;

	public GamePoint() {
		setText("Point : 0");
		setFont(new Font("Point : ", Font.BOLD, 30));
		setForeground(Color.WHITE);
		setSize(200, 30);
		setLocation(1000, 30);
	}
}