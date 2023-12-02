package mapleGame;

import javax.swing.ImageIcon;

/**
 * Boss 클래스는 Enemy 클래스를 상속받아 구현한 클래스로서, 게임 내의 특정 적 "Boss"을 표현합니다.
 * 
 * @author 박영서
 */
public class Boss extends Enemy {

	/**
	 * 기본 생성자. Boss 객체를 초기화합니다.
	 */
	public Boss() {
	}

	/**
	 * Boss의 매개변수를 받는 생성자입니다.
	 * 
	 * @param string 적의 이미지 경로를 설정합니다.
	 * @param x      적의 초기 x 좌표를 설정합니다.
	 * @param y      적의 초기 y 좌표를 설정합니다.
	 * @param hp     적의 초기 체력을 설정합니다.
	 * @param name   적의 이름을 설정합니다.
	 */
	public Boss(String string, int x, int y, int hp, String name) {
		enemyMove = new ImageIcon(string);

		this.x = x;
		this.y = y;
		this.width = enemyMove.getIconWidth();
		this.height = enemyMove.getIconHeight();
		this.hp = hp;
		this.name = name;

		setIcon(enemyMove);
		setSize(730, 700);
		setLocation(x, y);

	}

}
