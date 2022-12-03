package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import controller.Controller;
import util.JDBCUtil;

public class Service_centerDAO {
	static private Service_centerDAO instance = null;
	private Service_centerDAO() {}
	JDBCUtil jdbc = JDBCUtil.getInstance();
	
	public static Service_centerDAO getInstance() {
		if(instance==null) {instance=new Service_centerDAO();}
		return instance;
	}
	
	
	
	public List<Map<String, Object>> show_center_rows() {
		String sql = "SELECT * FROM SERVICE_CENTER "
				+ " ORDER BY 1 ";
		List<Map<String, Object>> map = jdbc.selectList(sql);
		return map;
	}
	
	public void center_write(String title, String content) {
//		int num = ++center_num;
//		String num2 = ""+num;
		List<Object> param = new ArrayList<Object>();
//		param.add(num2);
		param.add(Controller.login_id);
		param.add(title);
		param.add(content);
		String sql = "INSERT INTO SERVICE_CENTER VALUES(center_seq.NEXTVAL, ?, ?, ?, ' ')";
		jdbc.insert_data(sql, param);
	}

	public Map<String, Object> read_content(int answer) {
		String sql = "SELECT * FROM SERVICE_CENTER "
				+ " WHERE SERVICE_NUM = ? ";
		List<Object> param = new ArrayList<Object>();
		param.add(answer);
		Map<String, Object> row = jdbc.selectOne(sql, param);
		return row;
	}

	public List<Map<String, Object>> show_my_content(String my_id) {
		String sql = "SELECT * FROM SERVICE_CENTER "
				+ " WHERE CLIENT_ID = ?"
				+ " ORDER BY 1 ";
		
		// 아래는 고객이 작성한 글이 없을 시 돌려보내기 위해 작성한 코드
		String sql2 = "SELECT * FROM SERVICE_CENTER "
				+ " WHERE CLIENT_ID = '"+Controller.login_id+"'";
		
		List<Map<String, Object>> row1 = jdbc.selectList(sql2);
		if(row1==null) {
			System.out.println("작성한 게시글이 없습니다!");
			return row1;
		}
		
		// 작성한 글이 있다면 sql1문을 타고 아래의 코드가 실행될 것임  
		List<Object> param = new ArrayList<Object>();
		
		param.add(my_id);
		List<Map<String, Object>> row2 = jdbc.selectList(sql, param);
		return row2;
	}

	
	public int center_delete(int ans) {
		
		
		List<Object> param = new ArrayList<>();
		
		String sql2 = "SELECT * FROM SERVICE_CENTER "
				+ " ORDER BY 1 ";
		List<Map<String, Object>> rows1 = jdbc.selectList(sql2);
		
		
		int result = 0;
		String answer = ""+ans;
		String sql = "DELETE FROM SERVICE_CENTER "
				+ " WHERE SERVICE_NUM = ? ";
		
		param.add(answer);
		
		result = jdbc.update(sql, param);
		
		List<Map<String, Object>> rows2 = jdbc.selectList(sql2);
		
		if(rows1.size()==1&&rows2==null) {
			System.out.println("삭제가 완료되었습니다!");
			return 1;
		}
		
		
		if(rows1.size()>rows2.size()) {
			System.out.println("삭제가 완료되었습니다!");
		}else {
			System.out.println("작성하신 글이 없습니다! ");
		}
		return 1;
	}



	public List<Map<String, Object>> manager_showlist() {
		String sql = "SELECT * FROM SERVICE_CENTER "
				+ " ORDER BY 1 ";
		List<Map<String, Object>> rows = jdbc.selectList(sql);
		return rows;
	}



	public void manager_answer(String manager_answer1, String manager_answer2) {
		String sql = "UPDATE SERVICE_CENTER "
				+ " SET SERVICE_ANSWER = '"+manager_answer2+"' "
						+ " WHERE SERVICE_NUM = '"+manager_answer1+"'";
		int result = jdbc.update(sql);
		if(result==1) {
			System.out.println("답변이 완료됐습니다!");
		}else {
			System.out.println("답변실패!");
		}
		
	}

}
