package mapleGame;

import javax.swing.ImageIcon;

/**
 * Mushroom 클래스는 Enemy 클래스를 상속받아 구현한 클래스로서, 게임 내의 특정 적 "Mushroom"을 표현합니다.
 * 
 * @author 박영서
 */
public class Mushroom extends Enemy {

	public Mushroom() {
	}

	public Mushroom(String string, int x, int y, int hp, String name) {
		enemyMove = new ImageIcon(string);

		this.x = x;
		this.y = y;
		this.width = enemyMove.getIconWidth();
		this.height = enemyMove.getIconHeight();
		this.hp = hp;
		this.name = name;

		setIcon(enemyMove);
		setSize(200, 200);
		setLocation(x, y);

		moveChange();
		moveDirection();
	}

	public void moveChange() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					long n = System.currentTimeMillis() / 1000;
					if (n % 5 == 0) {
						speed = 100;
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

	public void moveDirection() {

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {

					if (moveState == 1) {
						setIcon(new ImageIcon("image/주황버섯오른쪽.gif"));
						x++;
						if (x >= 1100) {
							moveState = 2;
						}
						setLocation(x, y); // 내부에 repaint() 존재
						try {
							Thread.sleep(speed);

						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else if (moveState == 2) {
						setIcon(new ImageIcon("image/주황버섯왼쪽.gif"));
						x--;
						if (x <= 05) {
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