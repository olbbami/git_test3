package dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import controller.Controller;
import util.JDBCUtil;

public class Client_DAO {
	//static int center_num = 0;
	
	static private Client_DAO instance = null;
	JDBCUtil jdbc = JDBCUtil.getInstance();
	private Client_DAO() {}
	
	public static Client_DAO getInstance() {
		if(instance==null) {instance=new Client_DAO();}
		return instance;
	}
	
	public Map<String,Object> login(String id, String pwd) {
		String sql = "SELECT CLI_ID, CLI_PW FROM CLIENT "
				+ " WHERE CLI_ID='"+id+"' AND "
						+ " CLI_PW='"+pwd+"' ";
		Map<String, Object> map = null;
		map = jdbc.selectOne(sql);
		return map;
		
		
		
	
		
	}
	
	

	public List<Map<String, Object>> show_myinfo() {
		String my_id = Controller.login_id;
		String sql = "SELECT * FROM CLIENT\r\n" + 
				"        WHERE CLI_ID="+"'"+my_id+"'";
		List<Map<String, Object>> row = jdbc.selectList(sql);
		return row;
	}

	public void update_myinfo(String column, String content) {  // 비밀번호를 선택할 경우 비밀번호를 파라미터로 받는다?
		String my_id = Controller.login_id;
		
		
		String sql = "UPDATE CLIENT \r\n" + 
				"   SET "+column+" = ? \r\n" + 
				"   WHERE CLI_ID= ?";
		List<Object> param = new ArrayList<Object>();
		param.add(content);
		param.add(my_id);
		
		jdbc.update(sql,param);
		
		
	}

	

	

	public void enrollment(String new_id, String new_pwd, String genre1, String genre2,String new_name) {
		String sql = "INSERT INTO CLIENT VALUES(?, ?, ?, ?, 'n', '',?)";
		List<Object> param = new ArrayList<>();
		param.add(new_id);
		param.add(new_pwd);
		param.add(genre1);
		param.add(genre2);
		param.add(new_name);
		jdbc.insert_data(sql, param);
		
	}

	public boolean overlap_check(String new_id) {
		String sql = "SELECT * FROM CLIENT";
		List<Map<String, Object>> rows = jdbc.selectList(sql); 
		for(Map<String, Object> row : rows) {
			if(row.get("CLI_ID").equals(new_id)) {
				return false;
			}
		}
		
		return true;
	}
	
	
	// 노래장르(지현이) 추천 메소드 작성할 때 필요한 데이터를 얻기 위한 메소드임
	public Map<String, Object> get_perfer_musics(){
		Map<String, Object> map  = new HashMap<>();
		String sql = "SELECT * FROM CLIENT "
				+ " WHERE CLI_ID = '"+Controller.login_id+"' ";
		Map<String, Object> row = jdbc.selectOne(sql);
		map.put("선호장르1", row.get("CLI_GENRE1"));
		map.put("선호장르2", row.get("CLI_GENRE2"));
		return map;
	}
	

}
