package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import controller.Controller;
import dao.Service_centerDAO;
import util.ScanUtil;
import util.View;

public class Service_center_Service {
	static private Service_center_Service instance = null;
	Service_centerDAO service_center_dao = Service_centerDAO.getInstance();
	
	
	// 관리자가 답변하기로 한 게시판의 번호를 저장해놓기 위해서 선언함
	static String manager_answer;
	
	
	// 고객센터 게시글 목록에서 마지막번째 페이지의 게시글이 얼마나 남았는지 확인하기 위함.
	static public int page_remain;
	private Service_center_Service() {}

	
	public static Service_center_Service getInstance() {
		if(instance==null) {instance=new Service_center_Service();}
		return instance;
	}
	
	public int center_show1() {
		String[] array1 = new String[] {"SERVICE_NUM","SERVICE_TITLE","CLIENT_ID"};
		String[] array2 = new String[] {"게시글 번호","제목","작성자 아이디"};
		List<Map<String, Object>> rows = null;
		rows = service_center_dao.show_center_rows();
		
		if(rows==null) {
			System.out.println("올라온 게시글이 없습니다.");
			return View.SERVICE_CENTER;
		}
		
		page_remain = rows.size() % 5;
		if(rows.size()<5) {
			for(int i = 0; i<rows.size(); i++) {
				System.out.println("========================");
				for(int j = 0; j<array1.length; j++) {
					System.out.print(array2[j]+" : ");
					System.out.println(rows.get(i).get(array1[j])+"\t");
				}
				System.out.println("========================");
			}
			
			System.out.println("1.이전<<<    0.게시글선택      >>>2.다음");
			String answer = ScanUtil.nextLine();
			if(answer.equals("2")) {
				center_show1();
			}else if(answer.equals("1")) {
				center_show1();
			}else if(answer.equals("0")){
				select_content();
			}else {
				return View.SERVICE_CENTER;
			}
			
		}else {
			for(int i = 0; i<5; i++) {
				System.out.println("========================");
				for(int j = 0; j<array1.length; j++) {
					System.out.print(array2[j]+" : ");
					System.out.println(rows.get(i).get(array1[j])+"\t");
				}
				System.out.println("========================");
				
			}
			
			System.out.println("1.이전<<<    0.게시글선택      >>>2.다음");
			String answer = ScanUtil.nextLine();
			if(answer.equals("2")) {
				center_show2();
			}else if(answer.equals("1")) {
				center_show1();
			}else if(answer.equals("0")){
				select_content();
			}else {
				return View.SERVICE_CENTER;
			}
		}
		
		return View.SERVICE_CENTER;

	}


	public int center_show2() {
		String[] array1 = new String[] {"SERVICE_NUM","SERVICE_TITLE","CLIENT_ID"};
		String[] array2 = new String[] {"게시글 번호","제목","작성자 아이디"};
		List<Map<String, Object>> rows = null;
		rows = service_center_dao.show_center_rows();
		
		
		if(rows.size()<10) {
			for(int i = 5; i<5+page_remain; i++) {
				System.out.println("========================");
				for(int j = 0; j<array1.length; j++) {
					System.out.print(array2[j]+" : ");
					System.out.println(rows.get(i).get(array1[j])+"\t");
				}
				System.out.println("========================");
			}
			
			System.out.println("1.이전<<<    0.게시글선택      >>>2.다음");
			String answer = ScanUtil.nextLine();
			if(answer.equals("2")) {
				center_show2();
			}else if(answer.equals("1")) {
				center_show1();
			}else if(answer.equals("0")){
				select_content();
			}else {
				return View.SERVICE_CENTER;
			}
		}else {
			for(int i = 5; i<10; i++) {
				System.out.println("========================");
				for(int j = 0; j<array1.length; j++) {
					System.out.print(array2[j]+" : ");
					System.out.println(rows.get(i).get(array1[j])+"\t");
				}
				System.out.println("========================");
				
			}
			
			System.out.println("1.이전<<<    0.게시글선택      >>>2.다음");
			String answer = ScanUtil.nextLine();
			if(answer.equals("2")) {
				center_show3();
			}else if(answer.equals("1")) {
				center_show1();
			}else if(answer.equals("0")){
				select_content();
			}else {
				return View.SERVICE_CENTER;
			}
		}
		
		return View.SERVICE_CENTER;
	}


	public int center_show3() {
		String[] array1 = new String[] {"SERVICE_NUM","SERVICE_TITLE","CLIENT_ID"};
		String[] array2 = new String[] {"게시글 번호","제목","작성자 아이디"};
		List<Map<String, Object>> rows = null;
		rows = service_center_dao.show_center_rows();
		
		
		if(rows.size()<15) {
			for(int i = 10; i<10+page_remain; i++) {
				System.out.println("========================");
				for(int j = 0; j<array1.length; j++) {
					System.out.print(array2[j]+" : ");
					System.out.println(rows.get(i).get(array1[j])+"\t");
				}
				System.out.println("========================");
				
				
			}
			
			System.out.println("1.이전<<<    0.게시글선택      >>>2.다음");
			String answer = ScanUtil.nextLine();
			if(answer.equals("2")) {
				center_show3();
			}else if(answer.equals("1")) {
				center_show2();
			}else if(answer.equals("0")){
				select_content();
			}else {
				return View.SERVICE_CENTER;
			}
			
		}else {
			for(int i = 10; i<15; i++) {
				System.out.println("========================");
				for(int j = 0; j<array1.length; j++) {
					System.out.print(array2[j]+" : ");
					System.out.println(rows.get(i).get(array1[j])+"\t");
				}
				System.out.println("========================");
				
			}
			
			System.out.println("1.이전<<<    0.게시글선택      >>>2.다음");
			String answer = ScanUtil.nextLine();
			if(answer.equals("2")) {
				center_show3();
			}else if(answer.equals("1")) {
				center_show2();
			}else if(answer.equals("0")){
				select_content();
			}else {
				return View.SERVICE_CENTER;
			}
		}
		
		return View.SERVICE_CENTER;
	}
	
	public int center_show4() {
		String[] array1 = new String[] {"SERVICE_NUM","SERVICE_TITLE","CLIENT_ID"};
		String[] array2 = new String[] {"게시글 번호","제목","작성자 아이디"};
		List<Map<String, Object>> rows = null;
		rows = service_center_dao.show_center_rows();
		
		if(rows==null) {
			System.out.println("올라온 게시글이 없습니다.");
			return View.SERVICE_CENTER;
		}
		
		page_remain = rows.size() % 5;
		if(rows.size()<5) {
			for(int i = 0; i<rows.size(); i++) {
				System.out.println("========================");
				for(int j = 0; j<array1.length; j++) {
					System.out.print(array2[j]+" : ");
					System.out.println(rows.get(i).get(array1[j])+"\t");
				}
				System.out.println("========================");
			}
			
			System.out.println("1.이전<<<    0.게시글선택      >>>2.다음");
			int answer = ScanUtil.nextInt();
			if(answer==2) {
				center_show4();
			}else if(answer==1) {
				center_show4();
			}else {
				manager_answer();
			}
			
		}else {
			for(int i = 0; i<5; i++) {
				System.out.println("========================");
				for(int j = 0; j<array1.length; j++) {
					System.out.print(array2[j]+" : ");
					System.out.println(rows.get(i).get(array1[j])+"\t");
				}
				System.out.println("========================");
				
			}
			
			System.out.println("1.이전<<<    0.게시글선택      >>>2.다음");
			int answer = ScanUtil.nextInt();
			if(answer==2) {
				center_show5();
			}else if(answer==1) {
				center_show4();
			}else {
				manager_answer();
			}
		}
		
		return View.MANAGER_MAIN;

	}


	public int center_show5() {
		String[] array1 = new String[] {"SERVICE_NUM","SERVICE_TITLE","CLIENT_ID"};
		String[] array2 = new String[] {"게시글 번호","제목","작성자 아이디"};
		List<Map<String, Object>> rows = null;
		rows = service_center_dao.show_center_rows();
		
		
		if(rows.size()<10) {
			for(int i = 5; i<5+page_remain; i++) {
				System.out.println("========================");
				for(int j = 0; j<array1.length; j++) {
					System.out.print(array2[j]+" : ");
					System.out.println(rows.get(i).get(array1[j])+"\t");
				}
				System.out.println("========================");
			}
			
			System.out.println("1.이전<<<    0.게시글선택      >>>2.다음");
			int answer = ScanUtil.nextInt();
			if(answer==2) {
				center_show5();
			}else if(answer==1) {
				center_show4();
			}else {
				manager_answer();
			}
		}else {
			for(int i = 5; i<10; i++) {
				System.out.println("========================");
				for(int j = 0; j<array1.length; j++) {
					System.out.print(array2[j]+" : ");
					System.out.println(rows.get(i).get(array1[j])+"\t");
				}
				System.out.println("========================");
				
			}
			
			System.out.println("1.이전<<<    0.게시글선택      >>>2.다음");
			int answer = ScanUtil.nextInt();
			if(answer==2) {
				center_show6();
			}else if(answer==1) {
				center_show4();
			}else {
				manager_answer();
			}
		}
		
		return View.MANAGER_MAIN;
	}


	public int center_show6() {
		String[] array1 = new String[] {"SERVICE_NUM","SERVICE_TITLE","CLIENT_ID"};
		String[] array2 = new String[] {"게시글 번호","제목","작성자 아이디"};
		List<Map<String, Object>> rows = null;
		rows = service_center_dao.show_center_rows();
		
		
		if(rows.size()<15) {
			for(int i = 10; i<10+page_remain; i++) {
				System.out.println("========================");
				for(int j = 0; j<array1.length; j++) {
					System.out.print(array2[j]+" : ");
					System.out.println(rows.get(i).get(array1[j])+"\t");
				}
				System.out.println("========================");
				
				
			}
			
			System.out.println("1.이전<<<    0.게시글선택      >>>2.다음");
			int answer = ScanUtil.nextInt();
			if(answer==2) {
				center_show6();
			}else if(answer==1) {
				center_show5();
			}else {
				manager_answer();
			}
			
			
		}else {
			for(int i = 10; i<15; i++) {
				System.out.println("========================");
				for(int j = 0; j<array1.length; j++) {
					System.out.print(array2[j]+" : ");
					System.out.println(rows.get(i).get(array1[j])+"\t");
				}
				System.out.println("========================");
				
			}
			
			System.out.println("1.이전<<<    0.게시글선택      >>>2.다음");
			int answer = ScanUtil.nextInt();
			if(answer==2) {
				center_show6();
			}else if(answer==1) {
				center_show5();
			}else {
				manager_answer();
			}
		}
		
		return View.MANAGER_MAIN;
	}
	


	
	public int select_content() {
		System.out.println("게시글 번호를 선택해주세요>> ");
		int answer2 = ScanUtil.nextInt();
		Map<String, Object> row = service_center_dao.read_content(answer2);
		System.out.println("============================");
		System.out.print("게시글 번호 : ");
		System.out.println(row.get("SERVICE_NUM"));
		System.out.print("제목 : ");
		System.out.println(row.get("SERVICE_TITLE"));
		System.out.print("작성자 아이디 : ");
		System.out.println(row.get("CLIENT_ID"));
		System.out.print("내용: ");
		System.out.println(row.get("SERVICE_CONTENT"));
		System.out.print("답변: ");
		System.out.println(row.get("SERVICE_ANSWER"));
		System.out.println("============================");
		ScanUtil.nextLine();
		return View.SERVICE_CENTER;
	}
	
	
	public int center_main() {
		System.out.println("1. 문의게시판 목록  2. 게시글 작성    3.나의 문의내역조회  4. 뒤로가기");
		System.out.println("번호 입력하세요>> ");
		switch(ScanUtil.nextInt()) {
		case 1:
			center_show1();
			
			

			return View.SERVICE_CENTER;
		case 2:
			// 회원이 아닐경우 지원되지 않는 서비스이므로 이를 처리하는 코드 필요
			if(!(Controller.login_num==1)) {
				System.out.println("회원에게만 지원되는 서비스입니다!");
				return View.CLIENT_LOGIN;
			}
			
			// 게시글번호, 고객아이디는 자동으로 데이터가 입력되도록 해야함. 
			// 답변은 공백으로 일단 채워두자 
			System.out.println("제목을 입력해주세요>> ");
			String title = ScanUtil.nextLine();
			System.out.println("내용을 입력해주세요>> ");
			String content = ScanUtil.nextLine();
			service_center_dao.center_write(title,content);
			
			center_show1();
			ScanUtil.nextLine();
			return View.SERVICE_CENTER;
		case 3:
			// 회원이 아닐시 뜨는 문구
			if(!(Controller.login_num==1)) {
				System.out.println("회원에게만 지원되는 서비스입니다!");
				return View.CLIENT_LOGIN;
			}
			
			
			String[] array1 = new String[] {"SERVICE_NUM","SERVICE_TITLE","CLIENT_ID","SERVICE_CONTENT","SERVICE_ANSWER"};
			String[] array2 = new String[] {"게시글 번호","제목","작성자 아이디","내용","답변"};
			List<Map<String, Object>> row2 = service_center_dao.show_my_content(Controller.login_id);
			// 아래if문은 회원이 작성한 글이 없으면 다시 돌려보내기 위한 코드
			if(row2==null) {
				return View.SERVICE_CENTER;
			}
			
			for(int j = 0; j < row2.size(); j++) {
				System.out.println("==============================");
				for(int i =0; i<array1.length; i++) {
					System.out.print(array2[i]+ " : ");
					System.out.println(row2.get(j).get(array1[i])+"\t");
				}
				System.out.println("==============================");
			}
			
			System.out.println("게시글을 삭제하시겠습니까?(y/n)");
			if(ScanUtil.nextLine().equals("y")) {
				System.out.println("삭제할 게시글의 번호를 입력해주세요. ");
				int ans = ScanUtil.nextInt();
				service_center_dao.center_delete(ans);
				return View.SERVICE_CENTER;
				
				
			}
			
			
			return View.SERVICE_CENTER;
		case 4:
			if(Controller.login_num==1) {
				return View.CLI_MAIN;
			}else {
				return View.HOME;
			}
		}
		return 0;
	}


	
	public int manager_showlist() {
		
		center_show4();
		return View.MANAGER_MAIN;
	}


	public int manager_answer() {
		while(true) {
			String[] array1 = new String[] {"SERVICE_NUM","SERVICE_TITLE","SERVICE_CONTENT","CLIENT_ID"};
			List<Map<String, Object>> rows = service_center_dao.manager_showlist();
			List<String> current_service_num = new ArrayList<>();
			for(int i = 0; i < rows.size(); i++) {
				for(int j = 0; j < array1.length; j++) {
					current_service_num.add((String)rows.get(i).get(array1[0]));
				}
			}
			System.out.println("답변할 게시글 선택: ");
			manager_answer = ScanUtil.nextLine();
			if(current_service_num.contains(manager_answer)) {
				break;
			}else {
				System.out.println("다시 선택해주세요! ");
				continue;
			}
		}
		String[] array1 = new String[] {"SERVICE_TITLE","CLIENT_ID","SERVICE_CONTENT"};
		List<Map<String, Object>> rows = service_center_dao.manager_showlist();
		int idx = 0;
		for(int i = 0; i < rows.size(); i++) {
			if(rows.get(i).get("SERVICE_NUM").equals(manager_answer)) {
				idx = i;
				break;
			}
		}
		System.out.println("----------------------------");
		System.out.print("제목: ");
		System.out.println(rows.get(idx).get(array1[0]));
		System.out.print("작성자 아이디: ");
		System.out.println(rows.get(idx).get(array1[1]));
		System.out.println("내용: ");
		System.out.println(rows.get(idx).get(array1[2]));
		System.out.println("----------------------------");
		System.out.println("답변: ");
		String manager_answer2 = ScanUtil.nextLine();
		
		service_center_dao.manager_answer(manager_answer,manager_answer2);
		return View.MANAGER_MAIN;
	}
	
	
	
	

}
