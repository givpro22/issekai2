package mapleGame;

/**
 * Initable 인터페이스는 초기화, 화면 구성, 이벤트 처리 등의 메소드를 정의한 인터페이스입니다. 이 인터페이스를 구현한 클래스는 이
 * 인터페이스의 모든 메소드를 오버라이드해야 합니다.
 * 
 * @author 정아진
 */
public interface Initable {
	void init(); // new

	void setting(); // JFrame 기본 세팅

	void batch(); // 화면 구성

	void listener(); // 리스너(이벤트)
}