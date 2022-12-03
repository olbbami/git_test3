package util;

public interface View {
	// 프로그램 종료
	int SHUTDOWN = 100;
	// 메인홈화면
	int HOME = 0;
	
	// 마이페이지 - 내정보 관리
	int CLIENT_LOGIN=1;
	int CLIENT_LOGOUT = 15;
	int CLI_MAIN = 6; 
	int MY_PAGE=11;
	int MY_INFO = 111;
	int SHOW_MYINFO=1111;
	int UPDATE_MYINFO=1112;
	
	
	// 회원가입
	int ENROLLMENT1=5;
	int ENROLLMENT2 =55;
	
	// 고객센터 
	int SERVICE_CENTER=4;
	
	// 관리자의 고객센터 관리
	int MANAGER_MAIN=7;
	int SERVICE_CENTER_MANAGING = 9;
	int MANAGER_ANSWER = 92;
	
	
	// 메소드 동작체크
	int METHOD_CHECK = 11111111;
}
