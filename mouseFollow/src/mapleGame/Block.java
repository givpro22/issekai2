package mapleGame;

import javax.swing.ImageIcon;

/**
 * Block 클래스는 Enemy 클래스를 상속받아 구현한 클래스로서, 게임 내의 특정 적 "Block"을 표현합니다.
 * 
 * @author 박영서
 */
public class Block extends Enemy {

	private Player player;

	/**
	 * 기본 생성자. Block 객체를 초기화합니다.
	 */
	public Block() {
	}

	/**
	 * 주어진 파라미터들로 Block 객체를 생성합니다.
	 * 
	 * @param string 적의 이미지 경로를 설정합니다.
	 * @param x      적의 X좌표를 설정합니다.
	 * @param y      적의 Y좌표를 설정합니다.
	 * @param hp     적의 체력을 설정합니다.
	 * @param name   적의 이름을 설정합니다.
	 */
	public Block(String string, int x, int y, int hp, String name, Player player) {
		this.player = player;
		enemyMove = new ImageIcon(string);

		this.x = x;
		this.y = y;
		this.width = enemyMove.getIconWidth();
		this.height = enemyMove.getIconHeight();
		this.hp = hp;
		this.name = name;

		setIcon(enemyMove);
		setSize(150, 140);
		setLocation(x, y);

		moveChange();
		moveDirection();
	}

	/**
	 * Block 객체의 움직임을 변경하는 메소드입니다. 쓰레드를 생성하여 일정 시간마다 움직임 상태와 속도를 변경합니다.
	 */
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

	/**
	 * Block 객체의 움직임 방향을 결정하는 메소드입니다. 쓰레드를 생성하여 움직임 상태에 따라 객체의 위치를 변경합니다.
	 */
	public void moveDirection() {

		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					int characterX = player.x;
					int characterY = player.y;
					int distance = Math.abs(characterX - x);
					System.out.println(distance);
					if (distance <= 500 && characterY > 100) {
						if (characterX > x) {
							setIcon(new ImageIcon("image/블록골렘오른쪽.gif"));
							x++;
						} else if (characterX < x) {
							setIcon(new ImageIcon("image/블록골렘왼쪽.gif"));
							x--;
						}
						setLocation(x, y);
						try {
							Thread.sleep(speed);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					} else {

						if (moveState == 1) {
							setIcon(new ImageIcon("image/블록골렘오른쪽.gif"));
							x++;
							if (x >= 900) {
								moveState = 2;
							}
							setLocation(x, y);
							try {
								Thread.sleep(speed);

							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						} else if (moveState == 2) {
							setIcon(new ImageIcon("image/블록골렘왼쪽.gif"));
							x--;
							if (x <= 100) {
								moveState = 1;
							}
							setLocation(x, y);
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
			}
		}).start();
	}

}
