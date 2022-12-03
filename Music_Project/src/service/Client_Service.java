package service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import controller.Controller;
import dao.Client_DAO;
import util.ScanUtil;
import util.View;

public class Client_Service {
static private Client_Service instance = null;
	String new_id, new_pwd,new_name;
	private Client_Service() {}

	Client_DAO client_dao = Client_DAO.getInstance();
	
	public static Client_Service getInstance() {
		if(instance==null) {instance=new Client_Service();}
		return instance;
	}
	
	public int login() {
		System.out.println("아이디를 입력해주세요>> ");
		String id = ScanUtil.nextLine();	
		System.out.println("비밀번호를 입력해주세요>>");
		String pwd = ScanUtil.nextLine();
		Map<String,Object> row = client_dao.login(id, pwd);
		if(row==null) {
			System.out.println("존재하지 않는 회원입니다!");
			return View.HOME; 
		}
		if(row.get("CLI_ID").equals("admin")) {
			Controller.login_num = 2;
			System.out.println("관리자 로그인 성공!");
			Controller.login_id = (String)row.get("CLI_ID");
			Controller.login_pwd = pwd;
			return View.MANAGER_MAIN;
		}else {
			Controller.login_num = 1;
			System.out.println("회원 로그인 성공!");
			Controller.login_id = (String)row.get("CLI_ID");
			Controller.login_pwd = pwd;
			
			Map<String, Object> map = client_dao.get_perfer_musics();
			Controller.genre1 = (String)map.get("선호장르1");
			Controller.genre2 = (String)map.get("선호장르2");
			
			return View.CLI_MAIN;
		}
		
		
	}
	
	
	public int showmain() {
		System.out.println("1. 마이페이지   2.노래차트   3.이용권 구매   4.고객센터  5.로그아웃   0.종료");
		System.out.println("번호를 입력하세요>> ");
		
		switch(ScanUtil.nextInt()) {
		case 1:
			return View.MY_PAGE;
		case 4:
			return View.SERVICE_CENTER;
		case 5:
			Controller.login_num = 0;
			Controller.login_id = null;
			Controller.login_pwd=null;
			System.out.println("로그아웃 되었습니다!"); 
			return View.HOME;
		case 0:
			return View.SHUTDOWN;
		default: return 1;
		}
	
	}
	
	
	public int my_page() {
		
		System.out.println("1.내 정보관리   2.이용권 관리   3.내 플레이리스트   4. 멜론라운지");
		System.out.println("번호를 입력하세요>> ");
		switch(ScanUtil.nextInt()) {
		case 1:
			return View.MY_INFO;
		default: return 1;
		}
		
	}

	public int my_info() {
		System.out.println("1. 내정보  2. 내 정보 수정");
		switch(ScanUtil.nextInt()) {
		case 1:
			return View.SHOW_MYINFO;
		case 2:
			return View.UPDATE_MYINFO;
		default: return 100000;
		}
		
	}

	public int show_myinfo() {
		String[] array1 = new String[] {"CLI_NAME","CLI_GENRE1","CLI_GENRE2","CLI_PAYCHECK","CLI_RANK"};
		String[] array2 = new String[] {"이름","선호장르1","선호장르2","결제유무","고객등급"};
		
		Map<String, String> helper = new HashMap<>();
		for(int i = 0; i<array1.length; i++) {
			helper.put(array1[i],array2[i]);
			
		}
		
		List<Map<String, Object>> data = client_dao.show_myinfo();
		
		System.out.println("==================================");
		for(Map<String, Object> dt : data) {
			for(int i = 0; i<array1.length; i++) {
				if(dt.get(array1[i])==null) {
					System.out.println(array2[i]+" : "+"없음");
					continue;
				}
				System.out.println(array2[i]+" : "+dt.get(array1[i]));
			}
			
		}
		System.out.println("==================================");
		return View.CLI_MAIN;
	}
	
	public int update_myinfo() {
		
		System.out.println("비밀번호를 입력하세요>> ");
		String pass = ScanUtil.nextLine();	
		if(!(pass.equals(Controller.login_pwd))) {
			System.out.println("비밀번호가 틀렸습니다! ");
			return View.CLI_MAIN;
		}
//		if(!pass.equals(Controller.login_pwd)) {
//			System.out.println("비밀번호가 틀렸습니다!");
//			return View.HOME;
//		}
		
		
		
		
		System.out.println("수정할 정보를 선택하세요 ");
		System.out.println("  1. 비밀번호  2. 선호장르1   3. 선호장르2   ");
		switch(ScanUtil.nextInt()) {
		case 1:
			String new_pwd1;
			while(true) {
				System.out.println("수정할 비밀번호를 입력하세요>> ");
				new_pwd1 = ScanUtil.nextLine();
				if(Controller.login_pwd.equals(new_pwd1)) {
					System.out.println("기존 비밀번호와 같습니다. 다시 입력해주세요.");
					continue;
				}
				if(!(effectiveness_judge1(new_pwd1))) {
					System.out.println("비밀번호는 소문자3개로 시작하며, 소문자와 숫자조합으로 12개이하여야 합니다!");
					continue;
				}
				
				if(!(effectiveness_judge2(new_pwd1))) {
					System.out.println("비밀번호는 특수문자를 1개 이상 포함해야 합니다!");
					continue;
				}
				
				if(!(effectiveness_judge3(Controller.login_id, new_pwd1))) {
					System.out.println("비밀번호는 아이디와 중복된 문자가 3개 이상입력할 수 없습니다!");
					continue;
				}
				
				
				System.out.println("비밀번호 변경이 완료됐습니다.");
				break;
			}
			
			
			client_dao.update_myinfo("CLI_PW",new_pwd1);
			
			ScanUtil.nextLine();
			return View.HOME;
		case 2:
//			System.out.println("선호장르1을 입력하세요>> ");
//			String new_genre1 = ScanUtil.nextLine();
//			client_dao.update_myinfo("CLI_GENRE1",new_genre1);
//			show_myinfo();
//			ScanUtil.nextLine();
			String[] array1 = new String[] {"발라드","댄스","랩/힙합","R&B/Soul","인디음악",
					"록/메탈","트로트","포크/블루스"};
			System.out.println("===========================");
			for(int i = 0; i < array1.length; i++) {
				System.out.print((i+1)+". ");
				System.out.println(array1[i]);
			}
			while(true) {
				System.out.println("===========================");
				System.out.print("선호장르1을 선택해주세요");
				int num1 = ScanUtil.nextInt();
				Controller.genre1 = array1[num1-1];
				if(Controller.genre1.equals(Controller.genre2)) {
					System.out.println("이미 선택한 장르입니다. 다시 선택해주세요.");
					continue;
				}else {
					break;
				}
			}
			client_dao.update_myinfo("CLI_GENRE1", Controller.genre1);
			show_myinfo();
			ScanUtil.nextLine();
			return View.CLI_MAIN;
		case 3:
//			System.out.println("선호장르2을 입력하세요>> ");
//			String new_genre2 = ScanUtil.nextLine();
//			client_dao.update_myinfo("CLI_GENRE2", new_genre2);
//			show_myinfo();
//			ScanUtil.nextLine();
			String[] array2 = new String[] {"발라드","댄스","랩/힙합","R&B/Soul","인디음악",
					"록/메탈","트로트","포크/블루스"};
			System.out.println("===========================");
			for(int i = 0; i < array2.length; i++) {
				System.out.print((i+1)+". ");
				System.out.println(array2[i]);
			}
			while(true) {
				System.out.println("===========================");
				System.out.print("선호장르2을 선택해주세요");
				int num2 = ScanUtil.nextInt();
				Controller.genre2 = array2[num2-1];
				if(Controller.genre1.equals(Controller.genre2)) {
					System.out.println("이미 선택한 장르입니다. 다시 선택해주세요.");
					continue;
				}else {
					break;
				}
				
			}
			client_dao.update_myinfo("CLI_GENRE2", Controller.genre2);
			show_myinfo();
			ScanUtil.nextLine();
			return View.CLI_MAIN;
		default: return 111119;
		
		}
		
		
		
		
		
	}
	
	

	public int logout() {
		System.out.println("로그아웃 되었습니다!");
		Controller.login_num = 0;
		Controller.login_id = null;
		Controller.login_pwd = null;
		Controller.genre1 = null;
		Controller.genre2 = null;
		return View.HOME; 
	}

	public int enrollment1() {
		System.out.print("이름:");
		new_name = ScanUtil.nextLine();
		
		checking:
		while(true) {
			// 아이디 체크
			System.out.print("아이디: ");
			new_id = ScanUtil.nextLine();
			if(!(effectiveness_judge1(new_id))) {
				System.out.println("아이디는 소문자3개로 시작하며, 소문자와 숫자조합으로 12개이하여야 합니다!");
				continue checking;
			}
			
			// 아이디 중복 체크
			if(!(client_dao.overlap_check(new_id))) {
				System.out.println("이미 존재하는 아이디입니다!");
				continue checking;
			}
			// 비밀번호 체크
			pwd_check:
			while(true) {
				
				System.out.print("비밀번호: ");
				new_pwd = ScanUtil.nextLine();
				if(!(effectiveness_judge1(new_pwd))) {
					System.out.println("비밀번호는 소문자3개로 시작하며, 소문자와 숫자조합으로 12개이하여야 합니다!");
					continue pwd_check;
				}
				
				if(!(effectiveness_judge2(new_pwd))) {
					System.out.println("비밀번호는 특수문자를 1개 이상 포함해야 합니다!");
					continue pwd_check;
				}
				
				if(!(effectiveness_judge3(new_id, new_pwd))) {
					System.out.println("비밀번호는 아이디와 중복된 문자가 3개 이상입력할 수 없습니다!");
					continue pwd_check;
				}
				
				break checking;
			}
			
		}
		return View.ENROLLMENT2;
		
		
		
		
	}
	
	public int enrollment2() {
		String[] array1 = new String[] {"발라드","댄스","랩/힙합","R&B/Soul","인디음악",
				"록/메탈","트로트","포크/블루스"};
		System.out.println("===========================");
		for(int i = 0; i < array1.length; i++) {
			System.out.print((i+1)+". ");
			System.out.println(array1[i]);
		}
		System.out.println("===========================");
		System.out.print("선호장르1을 선택해주세요");
		int num1 = ScanUtil.nextInt();
		Controller.genre1 = array1[num1-1];
		while(true) {
			System.out.print("선호장르2을 선택해주세요");
			int num2 = ScanUtil.nextInt();
			Controller.genre2 = array1[num2-1];
			if(Controller.genre2.equals(Controller.genre1)) {
				System.out.println("이미 선택한 장르입니다. 다시 선택해주세요.");
				continue;
			}else {
				break;
			}
		}
		client_dao.enrollment(new_id, new_pwd, Controller.genre1, Controller.genre2,new_name);
		System.out.println("회원가입이 완료되었습니다!");
		return View.HOME;
		
	}
	
	public boolean effectiveness_judge1(String new_id) {
		boolean check = Pattern.matches("^[a-z]{3}[a-z0-9!@#$]{1,9}$", new_id);
		return check;
	}
	
	public boolean effectiveness_judge2(String new_pwd) {
		String val = "[^a-zA-Z0-9ㄱ-힣]";
		Pattern p = Pattern.compile(val);
		Matcher m = p.matcher(new_pwd);
		return m.find();
	}
	
	public boolean effectiveness_judge3(String new_id ,String new_pwd) {
		int match_cnt = 0;
		for(int i = 0; i<new_pwd.length(); i++) {
			for(int j = 0; j < new_id.length(); j++) {
				if((new_pwd.charAt(i))==(new_id.charAt(j))) {
					match_cnt += 1;
					break;
				}
				
			}
		}
		
		if(match_cnt>=3) {
			return false;
		} else {
			return true;
		}
	}

	

	
	
}
