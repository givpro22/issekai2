package mapleGame;

import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Skill 클래스는 게임 내의 플레이어가 사용하는 스킬을 표현하는 클래스입니다.
 * 
 * @author 박영서
 */
public class Skill extends JLabel {
	public int x;
	public int y;
	int width = 120;
	int height = 110;
	ImageIcon skillIcon;
	ImageIcon skillIconLeft, skillEffect;
	Player player;
	MapleApp mapleApp;
	GamePoint gamePoint;
	ArrayList<Enemy> enemy;

	boolean isSkill = true;

	int skillType;

	public Skill(Player player, ArrayList<Enemy> enemy){
		this(player,enemy, 1);
	}

	public Skill(Player player, ArrayList<Enemy> enemy, int skillType) {
		this.enemy = enemy;
		this.x = player.x;
		this.y = player.y;
		this.player = player;
		this.skillType = skillType;

		if(skillType == 1){
			skillIcon = new ImageIcon("image/스킬샷.png");
			skillIconLeft = new ImageIcon("image/스킬샷왼쪽.png");
			skillEffect = new ImageIcon("image/스킬맞출때2.gif");
			gamePoint = new GamePoint();
			setSize(400, 110);
			setLocation(x, y);
		} else if(skillType == 2) {
			skillIcon = new ImageIcon("image/스킬샷2오른쪽.png");
			skillIconLeft = new ImageIcon("image/스킬샷2왼쪽.png");
			skillEffect = new ImageIcon("image/스킬샷2맞출때.png");
			gamePoint = new GamePoint();
			setSize(300, 300);
			y = y-100;
			setLocation(x, y);
		}
		if (player.seewhere == true) {
			skill();
		} else if (player.seewhere == false) {
			skillLeft();
		}
	}
	
	// Skill 객체 생성시 자동 실행

	/**
	 * Skill 객체가 생성될 때 자동으로 실행되며, 스킬의 움직임과 공격 방향을 오른쪽으로 설정합니다.
	 * 
	 * @author 박영서
	 */
	public void skill() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (isSkill) {
					setIcon(skillIcon);
					Col(enemy);
					
					if(skillType == 1) {
						x = x+2;
					}
					else if(skillType == 2) {
						x++;
					}
					setLocation(x, y); // 내부에 repaint() 존재
					try {
						Thread.sleep(1);

					} catch (InterruptedException e) {
						e.printStackTrace();

					}
				}
			}
		}).start();

	}

	/**
	 * 스킬의 움직임과 공격 방향을 왼쪽으로 설정하는 메소드입니다.
	 * 
	 * @author 박영서
	 */
	public void skillLeft() {
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (isSkill) {
					setIcon(skillIconLeft);
					Col(enemy);
					x--;
					setLocation(x, y); // 내부에 repaint() 존재
					try {
						Thread.sleep(1);

					} catch (InterruptedException e) {
						e.printStackTrace();

					}
				}
			}
		}).start();
	}
	/**
	 * 플레이어의 공격이 몬스터에게 적중했을 때 스킬과 몬스터의 변화를 정의하는 메소드 입니다.
	 *
	 * @author 성세현
	 */
	public void Col(ArrayList<Enemy> enemy) {
		for (int i = 0; i < enemy.size(); i++) {
			if (crash(this.x, this.y, enemy.get(i).x, enemy.get(i).y, this.width, this.height, enemy.get(i).width,
					enemy.get(i).height)) {
				try {
					Thread.sleep(1);
					enemy.get(i).moveState = 0;
					setIcon(skillEffect);
					setSize(500, 300);
					Thread.sleep(1000);
				} catch (Exception e) {
				}
				System.out.println("스킬 적중!");
				MapleApp.deadEnemy[2] += 20;
				this.isSkill = false;
				setIcon(null);
				enemy.get(i).hp -= 40;
				System.out.println(enemy.get(i).name + " hp :" + enemy.get(i).hp);
				if (enemy.get(i).hp <= 0) {
					System.out.println(enemy.get(i).name + " 죽음...");
					enemy.get(i).x = 99999;

					MapleApp.score(enemy.get(i).name); // 점수 계산
					gamePoint.setText("Point : " + MapleApp.deadEnemy[2]); // 점수 표시

					if (enemy.get(i) instanceof Mushroom) {
						try {
							Thread.sleep(3000);
							enemy.get(i).x = 550;
							enemy.get(i).hp = 20;
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

					if (enemy.get(i) instanceof Stone) {
						try {
							Thread.sleep(3000);
							enemy.get(i).x = 120;
							enemy.get(i).hp = 20;
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					if (enemy.get(i) instanceof Barlog) {
						try {
							Thread.sleep(3000);
							enemy.get(i).x = 120;
							enemy.get(i).hp = 20;
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

					if (enemy.get(i) instanceof Block) {
						try {
							Thread.sleep(3000);
							enemy.get(i).x = 120;
							enemy.get(i).hp = 20;
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}

				}

			}
		}
	}
	/**
	 * 캐릭터가 적과 충돌했을 때를 판단하는 메소드 입니다.
	 *
	 * @author 박영서
	 */
	// 충돌 함수
	public boolean crash(int playerX, int playerY, int enemyX, int enemyY, int playerW, int playerH, int enemyW,
			int enemyH) {
		boolean check = false;
		if (Math.abs((playerX + (playerW / 2) + 80) - (enemyX + enemyW / 2 + 20)) < (enemyW / 2 + playerW / 2 - 60)
				&& Math.abs((playerY + playerH / 2) - (enemyY + enemyH / 2)) < (enemyH / 2 + playerH / 2)) {
			check = true;
		} else {
			check = false;
		}
		return check;
	}
	// end 충돌

}