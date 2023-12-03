package mapleGame;

import java.util.Random;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Enemy 클래스는 JLabel을 확장하여 게임의 적을 표현합니다. 적은 위치, 속도, 상태, 체력, 너비, 높이 등의 속성을 가지고
 * 있습니다.
 * 
 * @author 박영서
 */
abstract class Enemy extends JLabel {

	Enemy enemy = this;
	ImageIcon enemyMove;
	final static String TAG = "Enemy : ";
	int x;
	int y;
	int speed;
	int moveState;
	int hp;
	int width;
	int height;
	boolean isMove = true;
	String name;
	Random random = new Random();
	Timer timer = new Timer();

	/**
	 * 기본 생성자. Enemy 객체를 초기화합니다.
	 */
	public Enemy() {

	}

	/**
	 * 이름, X 좌표, Y 좌표를 받아서 Enemy 객체를 생성합니다.
	 * 
	 * @param string 적의 이름을 설정합니다.
	 * @param x      적의 X 좌표를 설정합니다.
	 * @param y      적의 Y 좌표를 설정합니다.
	 */
	public Enemy(String string, int x, int y) {

	}

	/**
	 * 적의 움직임을 변경하는 메소드입니다.
	 */
	public void moveChange() {

	}

	/**
	 * 적의 움직임 방향을 결정하는 메소드입니다.
	 */
	public void moveDirection() {
	}
}