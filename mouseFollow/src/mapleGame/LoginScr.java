package mapleGame;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

/**
 * LoginScr 클래스는 로그인 화면을 구현한 클래스입니다. JFrame을 상속받아 로그인 화면 구성 요소를 설정하고, 로그인 버튼에 대한
 * 이벤트를 처리합니다.
 * 
 * @author 정아진
 */
public class LoginScr extends JFrame {

	private JPanel contentPane;
	private LoginScr loginScr = this;
	private JTextField tfUsername;
	private JButton btnLogin, button, buttonS;
	private ImageIcon icon;

	JScrollPane scrollPane;

	// 스킨지정용 변수
	private static String[] buttonTexts = { "female", "male", "nothuman" };
	private int buttonTextIndex = 0;
	private static String buttonText = buttonTexts[0]; // 스킨용 변수. 초기값 설정, buttonTexts 배열의 첫 번째 요소로 초기화

	/**
	 * 메인 메소드.
	 * 
	 * @param args 프로그램 인자.
	 * @author 정아진
	 */
	public static void main(String[] args) {

	}

	/**
	 * 로그인 화면 생성자.
	 * 
	 * @param loading 로딩 화면 객체.
	 * @author 정아진
	 */

	public LoginScr(Loading loading) {
		icon = new ImageIcon("image/loginBG.png");

// 배경 이미지 설정을 위한 JPanel 커스텀.
		JPanel background = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(icon.getImage(), 0, 0, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};

		button = new JButton("로그인");
		button.setBounds(706, 438, 85, 36);
		tfUsername = new JTextField(20);
		tfUsername.setBounds(468, 446, 226, 21);
		buttonS = new JButton(buttonTexts[0]); // buttonTexts 배열의 첫 번째 요소로 버튼 초기화
		buttonS.setBounds(530, 500, 200, 36);
		background.setLayout(null);
		JLabel la1 = new JLabel("ID : ");
		la1.setBounds(433, 438, 45, 36);
		background.add(la1);
		background.add(tfUsername);
		background.add(button);
		background.add(buttonS);

		scrollPane = new JScrollPane(background);
		setContentPane(scrollPane);

		setTitle("LOGIN");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1280, 720);

		initListener();
		setLocationRelativeTo(null);
		setVisible(true);

	}

	/**
	 * 이벤트 리스너 초기화 메소드.
	 * 
	 * @author 정아진
	 */
	private void initListener() {

		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String username = tfUsername.getText();
				if (username.equals("issekai")) {
					int result = JOptionPane.showConfirmDialog(null, "로그인 완료", "안내메세지", JOptionPane.OK_OPTION);
					if (result == JOptionPane.OK_OPTION) {
						new MapleApp(loginScr);
						loginScr.setVisible(false);
					}
				} else {
					System.out.println("로그인 실패 : 아이디를 다시 입력하세요!");
				}
			}
		});
		// buttonS 버튼 클릭 이벤트 처리
		buttonS.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// 버튼 텍스트 변경
				buttonTextIndex = (buttonTextIndex + 1) % buttonTexts.length;
				buttonS.setText(buttonTexts[buttonTextIndex]);

				// 변경된 버튼 텍스트를 변수에 저장
				buttonText = buttonS.getText();

				// 여기서 buttonText 변수에 저장된 값 활용
				System.out.println("버튼 텍스트: " + buttonText);

				// 버튼 텍스트를 활용하여 스킨 변경 작업 수행
			}
		});
	}

	/**
	 * buttonText의 값을 반환하는 게터 메서드 스킨이름을 게임코드에 가져가기 위함.
	 * 
	 * @author 정아진
	 */
	public static String getButtonText() {
		System.out.println("버튼 텍스트: " + buttonText);
		return buttonText;
	}

}