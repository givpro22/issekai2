package mapleGame;

import javax.swing.ImageIcon;

/**
 * Barlog 클래스는 Enemy 클래스를 상속받아 구현한 클래스로서, 게임 내의 특정 적 "Barlog"을 표현합니다.
 * 
 * @author 박영서
 */
public class Barlog extends Enemy {

	/**
	 * 기본 생성자. Barlog 객체를 초기화합니다.
	 */
	public Barlog() {
	}

	/**
	 * 주어진 파라미터들로 Barlog 객체를 생성합니다.
	 * 
	 * @param string 적의 이미지 경로를 설정합니다.
	 * @param x      적의 X좌표를 설정합니다.
	 * @param y      적의 Y좌표를 설정합니다.
	 * @param hp     적의 체력을 설정합니다.
	 * @param name   적의 이름을 설정합니다.
	 * @author 박영서
	 */
	public Barlog(String string, int x, int y, int hp, String name) {
		enemyMove = new ImageIcon(string);

		this.x = x;
		this.y = y;
		this.width = enemyMove.getIconWidth();
		this.height = enemyMove.getIconHeight();
		this.hp = hp;
		this.name = name;

		setIcon(enemyMove);
		setSize(200, 210);
		setLocation(x, y);

		moveChange();
		moveDirection();
	}

	/**
	 * Barlog의 움직임 패턴을 변경하는 메소드입니다.
	 * 
	 * @author 박영서
	 */
	public void moveChange() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					long n = System.currentTimeMillis() / 1000;
					if (n % 5 == 0) {
						speed = 200;
						moveState = random.nextInt(3);
						try {
							Thread.sleep(random.nextInt(3500) + 2000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					speed = 5;
				}
			}
		}).start();
	}

	/**
	 * Barlog의 움직임 방향을 설정하는 메소드입니다.
	 * 
	 * @author 박영서
	 */
	public void moveDirection() {

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {

					if (moveState == 1) {
						setIcon(new ImageIcon("image/발록오른쪽.gif"));
						x++;
						if (x >= 550) {
							moveState = 2;
						}
						setLocation(x, y); // 내부에 repaint() 존재
						try {
							Thread.sleep(speed);

						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else if (moveState == 2) {
						setIcon(new ImageIcon("image/발록왼쪽.gif"));
						x--;
						if (x <= 100) {
							moveState = 1;
						}
						setLocation(x, y); // 내부에 repaint() 존재
						try {
							Thread.sleep(speed);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					} else if (moveState == 0) {
						setLocation(x, y);
						try {
							Thread.sleep(speed);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}
		}).start();
	}

}