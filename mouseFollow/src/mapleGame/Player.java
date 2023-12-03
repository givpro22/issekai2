package mapleGame;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * Player 클래스는 게임 캐릭터의 동작을 정의한 클래스입니다. JLabel을 상속받아 캐릭터의 위치, 이동, 점프, 공격 등의 동작을
 * 설정하고, 캐릭터의 이미지를 설정합니다.
 * 
 * @author 박영서
 * 
 * 
 */
public class Player extends JLabel {
	Player player = this;
	final static String TAG = "Player : ";
	Mushroom mushroom;
	ImageIcon icPlayerR, icPlayerR2, icPlayerR3, icPlayerR4, icPlayerL, icPlayerW, icPlayerJ, icPlayerWL, icPlayerJL,
			icPlayerAR1, icPlayerAR2, icPlayerAR3, icPlayerAL1, icPlayerAL2, icPlayerAL3, die, skillMotion,
			skillIconLeft;
	int x = 55;
	int y = 470;
	int speed = 0;
	int hp = 100;
	int mp = 50;
	int width;
	int height;
	public boolean isRight = false;
	public boolean isLeft = false;
	public boolean isMove = false;
	public boolean seewhere = true;
	public boolean isAttack = false;
	public boolean jumpState = false;
	public boolean isJump = false;
	public int floor = FloorHeight.floor1; // 470 / 2f = 328 / 3f = 183 / 4f = 38

	/**
	 * @author 박영서 이 메서드는 플레이어의 초기 설정을 하는 생성자 메서드입니다. 별도의 파라미터는 필요하지 않습니다.
	 * 
	 * +정아진: 스킨기능 추가로 인해 이미지 경로 체계와 조금의 로직이 추가되었습니다.
	 */
	public Player() {

		String buttonText = LoginScr.getButtonText(); // 다른 클래스에서 buttonText 값을 가져옴
		System.out.println("확인 된 버튼 텍스트: " + buttonText);
		String imagePath = "image/" + buttonText + "/";

		icPlayerR = new ImageIcon(imagePath + "캐릭오른쪽걷기1.png");
		icPlayerR2 = new ImageIcon(imagePath + "캐릭오른쪽걷기2.png");
		icPlayerR3 = new ImageIcon(imagePath + "캐릭오른쪽걷기3.png");
		icPlayerR4 = new ImageIcon(imagePath + "캐릭오른쪽걷기4.png");
		icPlayerAR1 = new ImageIcon(imagePath + "캐릭공격1.png");
		icPlayerAR2 = new ImageIcon(imagePath + "캐릭공격2.png");
		icPlayerAR3 = new ImageIcon(imagePath + "캐릭공격3.png");
		icPlayerAL1 = new ImageIcon(imagePath + "캐릭왼쪽공격1.png");
		icPlayerAL2 = new ImageIcon(imagePath + "캐릭왼쪽공격2.png");
		icPlayerAL3 = new ImageIcon(imagePath + "캐릭왼쪽공격3.png");
		icPlayerL = new ImageIcon(imagePath + "캐릭왼쪽걷기1.png");
		icPlayerW = new ImageIcon(imagePath + "대기상태.png");
		icPlayerWL = new ImageIcon(imagePath + "왼쪽대기상태.png");
		icPlayerJ = new ImageIcon(imagePath + "캐릭점프.png");
		icPlayerJL = new ImageIcon(imagePath + "캐릭왼쪽점프.png");
		die = new ImageIcon(imagePath + "die.png");
		skillMotion = new ImageIcon(imagePath + "캐릭공격3.png");
		skillIconLeft = new ImageIcon(imagePath + "스킬샷왼쪽.png");

		width = icPlayerW.getIconWidth();
		height = icPlayerW.getIconHeight();

		setIcon(icPlayerW);
		if (buttonText == "female") {
			setSize(80, 110);
		} else {
			setSize(80, 130);
		}
//		setSize(80, 110);
		setLocation(x, y);
	}

	/**
	 * 오른쪽으로 움직일 때의 범위와 조건을 정의합니다.
	 *
	 * @author 박영서
	 */
	public void moveRangeR() { // 오른쪽으로 움직일 때 조건
		if (floor == FloorHeight.floor1 && x < 1208) { // 1층일 때
			x++;
		} else if (floor == FloorHeight.floor2) { // 2층일 때
			if ((x >= 108 && x <= 600) || (x >= 891 && x <= 1178)) {
				x++;
			} else if (x < 108 || (x > 600) || (x < 891 || x >= 1178)) {
				x++;
				if (!jumpState) {
					floor = FloorHeight.floor1; // 1층으로 떨어짐
					moveDown(floor);
				}
			}
		} else if (floor == FloorHeight.floor3) { // 3층일 때
			if (x >= 108 && x <= 955) {
				x++;
			} else if (x < 108 || x > 955) {
				floor = FloorHeight.floor2; // 3층에서 2층으로 떨어짐
				if (!jumpState)
					moveDown(floor);
			}
		} else if (floor == FloorHeight.floor4) {
			if (x >= 108 && x <= 600) {
				x++;
			} else if (x < 108) {
				floor = FloorHeight.floor1; // 4층에서 1층으로 떨어짐
				moveDown(floor);
			} else if (x > 600) {
				floor = FloorHeight.floor3; // 4층에서 3층으로 떨어짐
				if (!jumpState)
					moveDown(floor);
			}
		}
	}

	/**
	 * 왼쪽으로 움직일 때의 범위와 조건을 정의합니다.
	 *
	 * @author 박영서
	 */
	public void moveRangeL() { // 왼쪽으로 움직일 때 조건
		if (floor == FloorHeight.floor1) { // 1층일 때
			if (x >= 0 && x <= 1280) {
				x--;
			}
		} else if (floor == FloorHeight.floor2) { // 2층일 때
			if ((x >= 108 && x <= 600) || (x >= 891 && x <= 1178)) {
				x--;
			} else if (x < 108 || (x > 600) || (x < 891 || x >= 1178)) {
				x--;
				if (!jumpState) {
					floor = FloorHeight.floor1; // 1층으로 떨어짐
					moveDown(floor);
				}
			}
		} else if (floor == FloorHeight.floor3) { // 3층일 때
			x--;
			if (x < 108 || x > 955) {
				floor = FloorHeight.floor2; // 3층에서 2층으로 떨어짐
				if (!jumpState)
					moveDown(floor);
			}
		} else if (floor == FloorHeight.floor4) {
			x--;
			if (x < 108) {
				floor = FloorHeight.floor3; // 4층에서 1층으로 떨어짐
				moveDown(floor);
			} else if (x > 600) {
				floor = FloorHeight.floor3; // 4층에서 3층으로 떨어짐
				if (!jumpState)
					moveDown(floor);
			}
		}
	}

	/**
	 * 캐릭터가 아래로 내려올 때의 동작을 정의합니다.
	 *
	 * @param height 캐릭터가 내려올 높이
	 * @author 박영서
	 */
	public void moveDown(int height) { // 아래로 내려오는거
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (y <= height) {
					y++;
					setLocation(x, y); // 내부에 repaint()가 존재 따로 안해도 됨
					try {
						Thread.sleep(1);
						// 4층에서 떨어질 때 3층으로 떨어지는 조건
						if (x >= 108 && x < 200 && (y > FloorHeight.floor4 && y < FloorHeight.floor3)) { // 4층에서
																											// 3층
							floor = FloorHeight.floor3;
							while (y < FloorHeight.floor3) {
								y++;
								setLocation(x, y); // 내부에 repaint()가 존재 따로 안해도 됨
								try {
									Thread.sleep(0);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							return;
						}
						// 3층에서 떨어질 때 2층으로 떨어지는 조건
						else if (x >= 108 && x < 200 && (y > FloorHeight.floor3 && y < FloorHeight.floor2)) { // 3층에서 2층
							floor = FloorHeight.floor2;
							while (y < FloorHeight.floor2) {
								y++;
								setLocation(x, y); // 내부에 repaint()가 존재 따로 안해도 됨
								try {
									Thread.sleep(0);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
							}
							return;
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	private boolean isBossStage = false;

	/**
	 * @author 박영서
	 * @param isBossStage 보스 스테이지 여부를 설정하는 불린 변수입니다. 이 메서드는 보스 스테이지 여부를 설정하는 메서드입니다.
	 */
	public void setBossStage(boolean isBossStage) {
		this.isBossStage = isBossStage;
	}

	/**
	 * 캐릭터가 점프할 때의 동작을 정의합니다.
	 *
	 * @author 박영서
	 */
	public void moveJump() {
		if (isJump == false && jumpState == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					jumpState = true;
					// 점프할 때 올라가는 부분
					if (seewhere == true) {
						for (int i = 0; i < 160; i++) {
							setIcon(icPlayerJ);
							y--;
							setLocation(x, y); // 내부에 repaint()가 존재 따로 안해도 됨
							try {
								Thread.sleep(2);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							setIcon(icPlayerW);
						}
					} else {
						for (int i = 0; i < 160; i++) {
							setIcon(icPlayerJL);
							y--;
							setLocation(x, y); // 내부에 repaint()가 존재 따로 안해도 됨
							try {
								Thread.sleep(2);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							setIcon(icPlayerWL);
						}
					}
					// 점프할 때 내려가는 부분
					for (int i = 0; i < 160; i++) {
						y++;
						setLocation(x, y); // 내부에 repaint()가 존재 따로 안해도 됨
						try {
							Thread.sleep(3);
							// 1층에서 2층 착지
							if (!isBossStage && floor == FloorHeight.floor1
									&& ((x >= 108 && x <= 600) || (x >= 891 && x <= 1178))) {
								floor = FloorHeight.floor2;
								while (y < FloorHeight.floor2) {
									y++;
									setLocation(x, y); // 내부에 repaint()가 존재 따로 안해도 됨
									try {
										Thread.sleep(3);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
								jumpState = false;
								return;
								// 2층에서 3층으로 착지
							} else if (floor == FloorHeight.floor2 && (x >= 108 && x <= 955)) {
								floor = FloorHeight.floor3;
								while (y < FloorHeight.floor3) {
									y++;
									setLocation(x, y); // 내부에 repaint()가 존재 따로 안해도 됨
									try {
										Thread.sleep(3);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
								jumpState = false;
								return;
								// 2층에서 2층으로 착지
							} else if (floor == FloorHeight.floor2 && (x >= 956 && x <= 1178)) {
								floor = FloorHeight.floor2;
								while (y < FloorHeight.floor2) {
									y++;
									setLocation(x, y); // 내부에 repaint()가 존재 따로 안해도 됨
									try {
										Thread.sleep(3);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
								jumpState = false;
								return;
								// 3층에서 4층으로 착지
							} else if (floor == FloorHeight.floor3 && (x >= 108 && x <= 600)) {
								floor = FloorHeight.floor4;
								while (y < FloorHeight.floor4) {
									y++;
									setLocation(x, y); // 내부에 repaint()가 존재 따로 안해도 됨
									try {
										Thread.sleep(3);
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
								jumpState = false;
								return;
								// 3층에서 3층으로 착지
							} else if (floor == FloorHeight.floor3 && (x > 600 && x <= 955)) {
								floor = FloorHeight.floor3;
								while (y < FloorHeight.floor3) {
									y++;
									setLocation(x, y); // 내부에 repaint()가 존재 따로 안해도 됨
									try {
										Thread.sleep(3);
										if (x >= 956 && x <= 1178) {
											while (y < FloorHeight.floor2) {
												floor = FloorHeight.floor2;
												y++;
												setLocation(x, y); // 내부에 repaint()가 존재 따로 안해도 됨
												try {
													Thread.sleep(3);
												} catch (InterruptedException e) {
													e.printStackTrace();
												}
											}
										}
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
								jumpState = false;
								return;
								// 4층에서 점프하면 다시 4층으로 떨어짐 맨 위층이니깐!
							} else if (floor == FloorHeight.floor4 && (x <= 108 && x >= 600)) {
								floor = FloorHeight.floor4;

								while (y < FloorHeight.floor4) {
									y++;
									setLocation(x, y); // 내부에 repaint()가 존재 따로 안해도 됨
									try {
										Thread.sleep(3);
										if (x > 600) {
											floor = FloorHeight.floor3;
											while (y < FloorHeight.floor3) {
												y++;
												setLocation(x, y); // 내부에 repaint()가 존재 따로 안해도 됨
												try {
													Thread.sleep(3);
												} catch (InterruptedException e) {
													e.printStackTrace();
												}
											}
											return;
										}
									} catch (InterruptedException e) {
										e.printStackTrace();
									}
								}
								jumpState = false;
								return;
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					jumpState = false;
				}
			}).start();
		}
	}

	/**
	 * 캐릭터가 오른쪽으로 움직일 때의 대기 동작을 정의합니다.
	 *
	 * @author 박영서
	 */
	public void moveWating() {
		if (isMove == false) {
			setIcon(icPlayerW);
		}
	}

	/**
	 * 캐릭터가 왼쪽으로 움직일 때의 대기 동작을 정의합니다.
	 *
	 * @author 박영서
	 */
	public void moveWatingleft() {
		if (isMove == false) {
			setIcon(icPlayerWL);
		}
	}

	/**
	 * 캐릭터가 오른쪽으로 움직일 때의 동작을 정의합니다.
	 *
	 * @author 박영서
	 */
	public void moveRight1() {
		if (isRight == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					seewhere = true;
					isRight = true;
					while (isRight && hp > 0) {
						moveRangeR();
						// 오른쪽으로 보는중
						setLocation(x, y); // 내부에 repaint() 존재
						try {
							Thread.sleep(3);
							setIcon(icPlayerW);
							Thread.sleep(3);
							setIcon(icPlayerR);

						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					setIcon(icPlayerW);

				}
			}).start();
		}
	}

	/**
	 * 캐릭터가 왼쪽으로 움직일 때의 동작을 정의합니다.
	 *
	 * @author 박영서
	 */
	public void moveLeft() {
		if (isLeft == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					setIcon(icPlayerL);
					isMove = true;

					isLeft = true;
					seewhere = false;
					while (isLeft && hp > 0) {
						moveRangeL();
						setLocation(x, y); // 내부에 repaint() 존재
						try {
							Thread.sleep(3);
							setIcon(icPlayerWL);
							Thread.sleep(3);
							setIcon(icPlayerL);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					moveWatingleft();
				}
			}).start();
		}

	}

	/**
	 * 캐릭터가 공격할 때의 동작을 정의합니다.
	 *
	 * @author 정아진
	 */
	public void attack() {
		if (isAttack == false) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					if (seewhere == true) {
						setIcon(icPlayerAR1);
						isAttack = true;
						while (isAttack) {
							player.setSize(500, 110);
							setLocation(x, y);
							try {
								Thread.sleep(120);
								setIcon(icPlayerAR1);
								Thread.sleep(120);
								setIcon(icPlayerAR2);
								Thread.sleep(120);
								setIcon(icPlayerAR3);
							} catch (Exception e) {

							}
						}
						setIcon(icPlayerW);
					} else if (seewhere == false) {
						setIcon(icPlayerAL1);
						isAttack = true;
						while (isAttack) {
							player.setSize(250, 110);
							setLocation(x, y);
							try {
								Thread.sleep(120);
								setIcon(icPlayerAL1);
								Thread.sleep(120);
								setIcon(icPlayerAL2);
								Thread.sleep(120);
								setIcon(icPlayerAL3);
							} catch (Exception e) {

							}
						}
						setIcon(icPlayerL);
					}
				}
			}).start();
		}
	}

	/**
	 * 캐릭터가 죽었을 때의 동작을 정의합니다.
	 *
	 * @author 정아진
	 */
	public void dieDown() {
		y -= 170;
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 160; i++) {
					setIcon(die);
					y++;
					setLocation(x, y);
					try {
						Thread.sleep(3);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

	/**
	 * 캐릭터가 스킬을 사용할 때의 동작을 정의합니다.
	 *
	 * @author 정아진
	 */
	public void skilshot() {

		player.setSize(150, 110);

		// 스킬 사용시 캐릭터 모션 변경 쓰레드
		new Thread(new Runnable() {
			public void run() {
				if (seewhere == true) {
					setIcon(skillMotion);
					try {
						setIcon(icPlayerAR3);
						Thread.sleep(300);
						setIcon(icPlayerW);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else if (seewhere == false) {
					try {
						setIcon(icPlayerAL3);
						Thread.sleep(300);
						setIcon(icPlayerWL);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}

		}).start();

		player.mp = player.mp - 10; // 성세현 추가 부분
	}

	/**
	 * @author 성세현 이 메서드는 주기적으로 플레이어의 체력과 마력을 회복하는 메서드입니다.
	 * 
	 */
	public void healing() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					while (true) {
						Thread.sleep(5000);
						if (player.hp < 100) {
							player.hp = player.hp + 5;
							if (player.hp >= 100) {
								player.hp = 100;
							}
						}
						if (player.mp < 50) {
							player.mp = player.mp + 50;
							if (player.mp >= 50) {
								player.mp = 50;
							}
						}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}