package mapleGame;

import javax.swing.ImageIcon;

/**
 * Stone 클래스는 Enemy 클래스를 상속받아 구현한 클래스로서, 게임 내의 특정 적 "Stone"을 표현합니다.
 * 
 * @author 박영서
 */
public class Stone extends Enemy {
	private Player player;

	/**
	 * 기본 생성자. Stone 객체를 초기화합니다.
	 */
	public Stone() {
	}

	/**
	 * Stone의 매개변수를 받는 생성자입니다.
	 * 
	 * @param string 적 이미지의 경로를 나타냅니다.
	 * @param x      적의 초기 x 좌표를 나타냅니다.
	 * @param y      적의 초기 y 좌표를 나타냅니다.
	 * @param hp     적의 초기 체력을 나타냅니다.
	 * @param name   적의 이름을 나타냅니다.
	 * @param player 플레이어 객체를 나타냅니다.
	 */
	public Stone(String string, int x, int y, int hp, String name, Player player) {
		this.player = player;
		enemyMove = new ImageIcon(string);

		this.x = x;
		this.y = y;
		this.width = enemyMove.getIconWidth();
		this.height = enemyMove.getIconHeight();
		this.hp = hp;
		this.name = name;

		setIcon(enemyMove);
		setSize(200, 150);
		setLocation(x, y);

		moveChange();
		moveDirection();
	}

	/**
	 * 적의 움직임을 변경하는 메소드입니다.
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
	 * 적의 이동 방향을 설정하는 메소드입니다.
	 */
	public void moveDirection() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					int characterX = player.x;
					int characterY = player.y;
					int distance = Math.abs(characterX - x);
					System.out.println(player.x);
					if (distance <= 800 && (characterY > 163 && characterY < 203)) {
						if (characterX > x) {
							setIcon(new ImageIcon("image/Stone.gif"));
							x++;
						} else if (characterX < x) {
							setIcon(new ImageIcon("image/StoneL.gif"));
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
							setIcon(new ImageIcon("image/Stone.gif"));
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
							setIcon(new ImageIcon("image/StoneL.gif"));
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