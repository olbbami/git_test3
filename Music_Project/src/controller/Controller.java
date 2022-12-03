package controller;

import service.Client_Service;
import service.Service_center_Service;
import util.ScanUtil;
import util.View;

public class Controller {
	// 회원가입시 아래변수들에 저장을 해놓고 나중에 중복검사할 때 써먹으려고 선언함
	static public String genre1;
	static public String genre2;
	
	static public int login_num;
	static public String login_id;
	static public String login_pwd;
	Client_Service client_service = Client_Service.getInstance();
	Service_center_Service service_center_service = Service_center_Service.getInstance();
	public static void main(String[] args) {
		new Controller().start();
		
		

	}
	
	private void start() {
		int view = View.HOME;
		controller:
		while(true) {
			switch(view) {
			// 회원으로 로그인 후 마이페이지 영역에서의 뷰
			case View.HOME: view = home(); break;
			case View.CLIENT_LOGIN: view = client_service.login(); break;
			case View.CLI_MAIN: view = client_service.showmain(); break;
			case View.MY_PAGE: view = client_service.my_page(); break;
			case View.MY_INFO: view = client_service.my_info(); break;
			case View.SHOW_MYINFO: view = client_service.show_myinfo(); break;
			case View.UPDATE_MYINFO: view = client_service.update_myinfo(); break;
			case View.CLIENT_LOGOUT: view = client_service.logout(); break;
			// 회원가입
			case View.ENROLLMENT1: view = client_service.enrollment1(); break;
			case View.ENROLLMENT2: view = client_service.enrollment2(); break;
			
			// 회원으로 로그인 후 고객센터를 들어갈 때 
			case View.SERVICE_CENTER: view = service_center_service.center_main(); break;
			
			// 관리자로 로그인 했을 떄 
			case View.MANAGER_MAIN: view = manager_home(); break;
			case View.SERVICE_CENTER_MANAGING: view = service_center_service.manager_showlist(); break;
			//case View.MANAGER_ANSWER: view = service_center_service.manager_answer(); break;
			
			
			// 프로그램 종료 
			case View.SHUTDOWN: 
				break controller;
			}
			
			
			
		}
		
		
	}
	
	private int home() {
		System.out.println("==메인 홈화면==");
		System.out.println("\t1.로그인  2.노래차트  3.이용권 구매  4.고객센터  5.회원가입   6.자유게시판  0.종료");
		System.out.println("=====================");
		System.out.println("번호입력>> ");
		switch(ScanUtil.nextInt()) {
		case 0: return View.SHUTDOWN;
		case 1: return View.CLIENT_LOGIN;
		case 4: return View.SERVICE_CENTER;
		case 5: return View.ENROLLMENT1;
		default: return View.HOME;
		}
		
	}
	
	private int manager_home() {
		System.out.println("=========관리자 메인홈=============");
		System.out.println("1. 음원관리   2. 문의게시판관리     3.당첨이벤트관리    4. 로그아웃");
		System.out.println("====================================");
		System.out.println("번호입력>>");
		switch(ScanUtil.nextInt()) {
		case 2: return View.SERVICE_CENTER_MANAGING;
		case 4: return View.CLIENT_LOGOUT;
		default: return 1; 
		}
	}
	
	
	

}
